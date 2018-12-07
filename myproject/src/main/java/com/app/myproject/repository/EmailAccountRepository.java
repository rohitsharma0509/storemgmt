package com.app.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.myproject.model.EmailAccount;

@Repository
public interface EmailAccountRepository extends JpaRepository<EmailAccount, Long> {

}
