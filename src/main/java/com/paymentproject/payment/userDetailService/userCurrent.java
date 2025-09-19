package com.paymentproject.payment.userDetailService;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.paymentproject.payment.customer.customerInfo;

public class userCurrent implements UserDetails {
    customerInfo ci;

    public userCurrent(customerInfo ci) {
        this.ci = ci;
    }
    @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return ci.getRole().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList(); 
}


    @Override
    public String getPassword() {
        return ci.getPassword();
    }

    @Override
    public String getUsername() {
        return ci.getCustomerName();
        
    }

    
     
    
}
