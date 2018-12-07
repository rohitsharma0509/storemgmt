package com.app.myproject.api;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.Supplier;
import com.app.myproject.service.SupplierService;

@Controller
public class SuppliersController {

	@Inject
	private SupplierService supplierService;
	
	@RequestMapping(value = RequestUrls.ADD_SUPPLIER, method = RequestMethod.GET)
	public String saveStudent(Model model, @RequestParam(value = "id", required=false) Long id) {
		Supplier supplier;
		if(id != null){
			supplier = supplierService.getSupplierById(id);
		}else {
			supplier = new Supplier();
		}
		model.addAttribute("supplier", supplier);
		return "addSupplier";
	}
	
	@RequestMapping(value = RequestUrls.SUPPLIERS, method = RequestMethod.POST)
	public String addSupplier(Model model, @Valid Supplier supplier, BindingResult bindingResult) {
		supplierService.addSupplier(supplier);
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value = RequestUrls.SUPPLIERS, method = RequestMethod.GET)
	public String getSuppliers(Model model, @PageableDefault(page=1, size=2) Pageable pageable) {
		Page<Supplier> page = supplierService.getSuppliers(pageable);

	    int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());

	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("page", page);
		return "suppliers";
	}
	
	@RequestMapping(value = RequestUrls.SUPPLIERS_ALL, method = RequestMethod.GET)
	public String getAllSuppliers(Model model) {
		List<Supplier> suppliers = supplierService.getAllSuppliers();
		model.addAttribute("suppliers", suppliers);
		return "suppliers";
	}
	
	@RequestMapping(value = RequestUrls.DELETE_SUPPLIER, method = RequestMethod.DELETE)
	public String deleteSupplier(Model model, @PathVariable("id") Long id) {
		supplierService.deleteSupplier(id);
		return "suppliers";
	}
	
	@RequestMapping(value = RequestUrls.SUPPLIERS, method = RequestMethod.PUT)
	public String editSupplier(Model model, @ModelAttribute("supplier") Supplier supplier) {
		supplierService.editSupplier(supplier);
		return "suppliers";
	}
}