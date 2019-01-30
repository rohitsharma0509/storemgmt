package com.app.myproject.validator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.myproject.constants.Constants;
import com.app.myproject.dto.EmailTemplateDto;
import com.app.myproject.util.CommonUtil;

@Component
public class EmailTemplateValidator implements Validator {
    
    @Inject
    private CommonUtil commonUtil;

    @Override
    public boolean supports(Class<?> aClass) {
        return EmailTemplateDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmailTemplateDto email = (EmailTemplateDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "to", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "NotEmpty");

        String[] to = commonUtil.convertStringToArray(email.getTo(), Constants.COMMA);
        if(null != to && to.length>0){
            for (String emailTo : to) {
                if (!emailTo.matches("[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
                    errors.rejectValue("to", "Email.Address.Not.Valid");
                }
            }
        }
    }
}
