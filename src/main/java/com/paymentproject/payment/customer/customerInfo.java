package com.paymentproject.payment.customer;

import java.util.Set;



import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rakshit" ,schema = "paymentschema")
@Data
public class customerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    String customerName;
    @Column
    int money;
    @Column
    String password;
    
    @ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "customer_id"))
@Column(name = "role")
@Enumerated(EnumType.STRING)
Set<Role> role;


    void increaseMoney(int amount){
        money = money + amount;
    }

}
