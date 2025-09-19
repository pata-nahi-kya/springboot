package com.paymentproject.payment.RepositoryLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paymentproject.payment.customer.customerInfo;

@Repository
public interface repository extends JpaRepository<customerInfo , Integer>{

    customerInfo findBycustomerName(String username);
    
}
