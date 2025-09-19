package com.paymentproject.payment.controllerLayer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentproject.payment.ServiceStructureImplementation.ssImplementation;
import com.paymentproject.payment.customer.customerInfo;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/bank/user")
public class UserController {

    @Autowired
    ssImplementation ss;
    
    
     
    
    @PutMapping("/addMoney/{id}/{amount}")
    public customerInfo addMoney(@PathVariable int id, @PathVariable int amount ) {
        
        return ss.addMoney(amount, id);
        
    }
   
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        ss.deleteUser(id);
    }
    @PutMapping("/moneyTransfer/{idr}/{ids}/{amount}")
    public customerInfo transferMoney(@PathVariable int idr , @PathVariable int ids ,@PathVariable int amount ) {
        return ss.transferMoney(amount, idr, ids);
    }
    @GetMapping("/getMyDetail/{id}")
    public customerInfo getMethodName(@PathVariable int id) {
        
        return ss.getMydetail(id);
    }
    
    @GetMapping("/get_csrf")
    public CsrfToken cf (HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
    
    

    
}
