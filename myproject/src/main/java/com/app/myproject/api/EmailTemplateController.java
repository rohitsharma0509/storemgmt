package com.app.myproject.api;

import java.util.ArrayList;
import java.util.List;

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
import com.app.myproject.dto.EmailTemplateDto;
import com.app.myproject.dto.TemplateType;
import com.app.myproject.model.EmailTemplate;
import com.app.myproject.service.EmailTemplateService;
import com.app.myproject.util.CommonUtil;
import com.app.myproject.validator.EmailTemplateValidator;

@Controller
public class EmailTemplateController {
    
    @Inject
    private CommonUtil commonUtil;
    
    @Inject
    private EmailTemplateService emailTemplateService;
    
    @Inject
    private EmailTemplateValidator emailTemplateValidator;
    
    @GetMapping(value = RequestUrls.ADD_EMAIL_TEMPLATES)
    public String redirectToAddEmailTemplate(Model model, @RequestParam(value = FieldNames.ID, required=false) Long id) {
        EmailTemplateDto emailTemplateDto;
        if(id != null){
            emailTemplateDto = emailTemplateService.getEmailTemplateById(id);
        }else {
            emailTemplateDto = new EmailTemplateDto();
        }
        model.addAttribute("emailTemplateDto", emailTemplateDto);
        List<TemplateType> templateTypes = new ArrayList<>();
        TemplateType templateType = new TemplateType();
        templateType.setId(1L);
        templateType.setType("User registration mail");
        templateTypes.add(templateType);
        model.addAttribute("types", templateTypes);
        return RequestUrls.ADD_EMAIL_TEMPLATES;
    }
    
    @PostMapping(value = RequestUrls.EMAIL_TEMPLATES)
    public String addEmailTemplate(Model model, @Valid EmailTemplateDto emailTemplateDto, BindingResult bindingResult) {
        emailTemplateValidator.validate(emailTemplateDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return RequestUrls.ADD_EMAIL_TEMPLATES;
        }
        
        emailTemplateService.addEmailTemplate(emailTemplateDto);
        return "redirect:"+RequestUrls.EMAIL_TEMPLATES;
    }
    
    @GetMapping(value = RequestUrls.EMAIL_TEMPLATES)
    public String getCategories(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
        Page<EmailTemplate> page = emailTemplateService.getEmailTemplates(pageable);
        model.addAttribute(FieldNames.PAGGING, commonUtil.getPagging(RequestUrls.EMAIL_TEMPLATES, page.getNumber()+1, page.getTotalPages(), null));
        model.addAttribute(FieldNames.PAGE, page);
        return RequestUrls.EMAIL_TEMPLATES;
    }
}