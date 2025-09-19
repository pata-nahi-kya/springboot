package com.paymentproject.payment.ServiceStructureImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.paymentproject.payment.RepositoryLevel.repository;
import com.paymentproject.payment.customer.customerInfo;
import com.paymentproject.payment.serviceStructure.ss;

@Service
public class ssImplementation implements ss {
    @Autowired
    repository rp;

    BCryptPasswordEncoder BCPE = new BCryptPasswordEncoder(10);
    @Override
    public customerInfo createUser(customerInfo cs) {
        cs.setPassword(BCPE.encode(cs.getPassword()));
        return rp.save(cs);
         
    }

    @Override
    @Transactional
    public customerInfo transferMoney(int amount , int ri , int si) {
        customerInfo reciever = rp.getById(ri);
        customerInfo sender = rp.getById(si);
        
        int presentMoney = reciever.getMoney() + amount;
        int deductedMoney = sender.getMoney() - amount;
        reciever.setMoney(presentMoney);
        sender.setMoney(deductedMoney);
        rp.save(sender);
        return rp.save(reciever) ;
    }

    @Override
    public customerInfo addMoney(int amount , int id) {
        customerInfo ci = rp.findById(id).get();
        
        ci.setMoney(ci.getMoney() + amount);
        rp.save(ci);
        return ci;
        
        
    }

    @Override
    public void deleteUser(int id) {
        rp.deleteById(id);
    }

    @Override
    public List<customerInfo> getAllInfo() {
        return rp.findAll();
    }

    @Override
    public customerInfo getMydetail(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        customerInfo c = rp.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));
        if(c.getCustomerName().equals(username)){
            return c;
        }
        
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "please enter valid id");


        
    }

    

    
    
        
    
    
}
