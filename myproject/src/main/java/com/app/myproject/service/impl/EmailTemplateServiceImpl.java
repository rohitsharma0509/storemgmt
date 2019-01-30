package com.app.myproject.service.impl;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.myproject.dto.EmailTemplateDto;
import com.app.myproject.mapper.EmailTemplateMapper;
import com.app.myproject.model.EmailTemplate;
import com.app.myproject.repository.EmailTemplateRepository;
import com.app.myproject.service.EmailTemplateService;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

    @Inject
    private EmailTemplateRepository emailTemplateRepository;

    @Inject
    private EmailTemplateMapper emailTemplateMapper;

    @Override
    public EmailTemplateDto getEmailTemplateById(Long id) {
        Optional<EmailTemplate> optional = emailTemplateRepository.findById(id);
        if (optional.isPresent()) {
            return emailTemplateMapper.emailTemplateToEmailTemplateDto(optional.get());
        } else {
            return null;
        }
    }

    @Override
    public void addEmailTemplate(EmailTemplateDto emailTemplateDto) {
        emailTemplateRepository.save(emailTemplateMapper.emailTemplateDtoToEmailTemplate(emailTemplateDto));
    }

    @Override
    public Page<EmailTemplate> getEmailTemplates(Pageable pageable) {
        PageRequest request = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
        return emailTemplateRepository.findAll(request);
    }
}
