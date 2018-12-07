package com.app.myproject.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.persistence.Tuple;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.SearchCriteria;
import com.app.myproject.querybuilder.QueryBuilder;

@Component
public class ChartGenerator {
	
	@Inject
	private QueryBuilder queryBuilder;
	
	@Inject
	private CommonUtil commonUtil;

	public byte[] createPieChart(Long alertProducts, Long availableProducts, Long outOfStockProduct) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			DefaultPieDataset dataset = new DefaultPieDataset();
			dataset.setValue("Non-Alert", availableProducts);
			dataset.setValue("Alert", alertProducts);
			dataset.setValue("Out Of Stock", outOfStockProduct);

			JFreeChart chart = ChartFactory.createPieChart("Stock Status",
					dataset, true, true, false);
			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderPaint(Color.WHITE);
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setSectionPaint("Non-Alert", Color.GREEN);
			plot.setSectionPaint("Alert", Color.YELLOW);
			plot.setSectionPaint("Out Of Stock", Color.RED);
			plot.setOutlinePaint(Color.WHITE);

			BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	public byte[] createLineChart() {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			List<String> dates = new LinkedList<>();
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(5L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(4L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(3L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(2L), Constants.YYYY_MM_DD));
			dates.add(commonUtil.convertZonedDateTimeToString(ZonedDateTime.now().minusDays(1L), Constants.YYYY_MM_DD));
			
			List<SearchCriteria> criterias = new ArrayList<>();
			criterias.add(new SearchCriteria("dates", dates, Constants.IN));
			
			StringBuilder query = new StringBuilder("select date(o.order_date) date, sum(od.quantity) soldQty, sum(p.per_product_price*od.quantity) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profit from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where date(o.order_date) in (:dates) group by date(o.order_date) order by o.order_date");
			List<Tuple> tuples = queryBuilder.getTupleByQuery(query.toString(), criterias);
			
			Map<String, Double> salesMap = new HashMap<>();
			for(Tuple tuple: tuples){
				salesMap.put(String.valueOf(tuple.get("date")), Double.parseDouble(String.valueOf(tuple.get("amountReceived"))));
			}
			
			DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
			for(String date: dates){
				if(salesMap.containsKey(date)){
					dataset1.addValue(salesMap.get(date), "Sales", date);
				}else {
					dataset1.addValue(0, "Sales", date);
				}
			}
			
			JFreeChart lineChart = ChartFactory.createLineChart("Last 5 Days Sales",
					"Dates", "Sales", dataset1, PlotOrientation.VERTICAL, true,
					true, false);
			Plot plot = lineChart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setOutlinePaint(Color.WHITE);
			
			
			BufferedImage bufferedImage = lineChart.createBufferedImage(500,
					500);

			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	public byte[] createBarChart() {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			List<SearchCriteria> criterias = new ArrayList<>();
			criterias.add(new SearchCriteria("year", Calendar.getInstance().get(Calendar.YEAR), Constants.EQUALS));
			Map<Integer, Double> salesMap = new HashMap<>();
			List<Tuple> tuples = queryBuilder.getTupleByQuery("select month(o.order_date) month, sum(od.quantity) soldQty, sum(p.per_product_price*od.quantity) amountReceived, sum(od.quantity*p.per_product_price-od.quantity*p.purchase_price) profit from orders o left join order_details od on o.order_id=od.order_id left join products p on od.product_id=p.product_id where year(o.order_date)= :year group by month(o.order_date), year(o.order_date)", criterias);
			for(Tuple tuple : tuples){ 
				salesMap.put(Integer.parseInt(String.valueOf(tuple.get("month"))), Double.parseDouble(String.valueOf(tuple.get("amountReceived"))));
			}
			DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
			for(Month month : Month.values()){
				if(salesMap.containsKey(month.getValue())){
					dataset2.addValue(salesMap.get(month.getValue()), "Sales", month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
				}else {
					dataset2.addValue(0, "Sales", month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
				}
			}
			JFreeChart barChart = ChartFactory.createBarChart("Monthly Sales", "Month", "Sales", dataset2, PlotOrientation.VERTICAL, true, true, false);
			Plot plot = barChart.getPlot();
			plot.setBackgroundPaint(Color.WHITE);
			plot.setOutlinePaint(Color.WHITE);
			
			BufferedImage bufferedImage = barChart.createBufferedImage(500, 500);
			ImageIO.write(bufferedImage, "png", buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toByteArray();
	}
}
