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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.Supplier;
import com.app.myproject.service.SupplierService;

@Controller
public class SuppliersController {

	@Inject
	private SupplierService supplierService;
	
	@GetMapping(value = RequestUrls.ADD_SUPPLIER)
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
	
	@PostMapping(value = RequestUrls.SUPPLIERS)
	public String addSupplier(Model model, @Valid Supplier supplier, BindingResult bindingResult) {
		supplierService.addSupplier(supplier);
		return "redirect:/suppliers";
	}
	
	@GetMapping(value = RequestUrls.SUPPLIERS)
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
	
	@GetMapping(value = RequestUrls.SUPPLIERS_ALL)
	public String getAllSuppliers(Model model) {
		List<Supplier> suppliers = supplierService.getAllSuppliers();
		model.addAttribute("suppliers", suppliers);
		return "suppliers";
	}
	
	@DeleteMapping(value = RequestUrls.DELETE_SUPPLIER)
	public String deleteSupplier(Model model, @PathVariable("id") Long id) {
		supplierService.deleteSupplier(id);
		return "suppliers";
	}
	
	@PutMapping(value = RequestUrls.SUPPLIERS)
	public String editSupplier(Model model, @ModelAttribute("supplier") Supplier supplier) {
		supplierService.editSupplier(supplier);
		return "suppliers";
	}
}