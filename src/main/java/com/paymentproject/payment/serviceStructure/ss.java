package com.paymentproject.payment.serviceStructure;

import java.util.List;

import com.paymentproject.payment.customer.customerInfo;


public interface ss {
    customerInfo createUser(customerInfo cs);
    customerInfo transferMoney(int amount , int ri , int si);
    customerInfo addMoney(int amount , int id );
    void deleteUser(int id);
    List<customerInfo> getAllInfo();
    customerInfo getMydetail(int id) ;
    

}
