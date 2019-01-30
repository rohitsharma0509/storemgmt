package com.app.myproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.myproject.dto.EmailTemplateDto;
import com.app.myproject.model.EmailTemplate;

public interface EmailTemplateService {

    EmailTemplateDto getEmailTemplateById(Long id);

    void addEmailTemplate(EmailTemplateDto emailTemplateDto);

    Page<EmailTemplate> getEmailTemplates(Pageable pageable);
}
