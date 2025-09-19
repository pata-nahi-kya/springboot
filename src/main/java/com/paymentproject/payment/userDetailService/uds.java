package com.paymentproject.payment.userDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.paymentproject.payment.RepositoryLevel.repository;
import com.paymentproject.payment.customer.customerInfo;

@Configuration
public class uds implements UserDetailsService {

    @Autowired
    repository rp ;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        customerInfo ci = rp.findBycustomerName(username);
        return new userCurrent(ci);
    }
        

    }
