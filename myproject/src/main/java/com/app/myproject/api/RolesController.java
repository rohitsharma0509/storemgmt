package com.app.myproject.api;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.myproject.constants.FieldNames;
import com.app.myproject.constants.RequestUrls;
import com.app.myproject.model.Role;
import com.app.myproject.service.RoleService;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.validator.RoleValidator;

@Controller
public class RolesController {
	@Inject
	private RoleService roleService;
	
	@Inject
	private CommonUtil commonUtil;
	
	@Inject
	private RoleValidator roleValidator;
	
	@GetMapping(value = RequestUrls.ROLES)
    public String getRoles(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
		Page<Role> page = roleService.getRoles(pageable);
		model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.ROLES, page.getNumber()+1, page.getTotalPages(), null));
		model.addAttribute(FieldNames.PAGE, page);
        return RequestUrls.ROLES;
    }
	
	@PostMapping(value = RequestUrls.ROLES)
	public String addCategory(Model model, @Valid Role role, BindingResult bindingResult) {
		roleValidator.validate(role, bindingResult);
		if (bindingResult.hasErrors()) {
			return RequestUrls.ADD_ROLE;
		}
		
		roleService.addRole(role);
		return "redirect:"+RequestUrls.ROLES;
	}
	
	@GetMapping(value = RequestUrls.ADD_ROLE)
	public String addRole(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
		Role role;
		if(id != null){
			role = roleService.getRoleById(id);
		}else {
			role = new Role();
		}
		model.addAttribute(FieldNames.ROLE, role);
		return RequestUrls.ADD_ROLE;
	}
}
