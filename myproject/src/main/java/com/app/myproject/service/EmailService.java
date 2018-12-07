package com.app.myproject.service;

import com.app.myproject.dto.Email;
import com.app.myproject.model.EmailAccount;

public interface EmailService {
	EmailAccount getEmailAccount();

	EmailAccount getEmailAccountById(Long id);

	EmailAccount addEmailAccount(EmailAccount emailAccount);
	
	void sendEmail(Email email);
}
