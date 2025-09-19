package com.paymentproject.payment.controllerLayer;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paymentproject.payment.customer.customerInfo;
import com.paymentproject.payment.ServiceStructureImplementation.*;
import com.paymentproject.payment.ServiceStructureImplementation.ssImplementation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/bank/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    ssImplementation ss;
    @Autowired
    JWTService sj ;


    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Admin Panel";
    }

    @PostMapping("/createUser")
    public customerInfo createUser(@RequestBody customerInfo cs) {
        return ss.createUser(cs);
    }

    @GetMapping("/getAllUser")
    public List<customerInfo> getAllInfo() {
        return ss.getAllInfo();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        ss.deleteUser(id);
    }
    @PutMapping("/authenticate/{username}/{password}")
    public String getTokenOfJWT(@PathVariable String username , @PathVariable String password) {
        return sj.generateToken(username);
    }


}

