package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="customer")
public class CustomerEntity {
    @Id
    @Column(name = "id_customer", nullable = false, length = 15)
    private Integer idCustomer;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 50)
    private  String email;

    @Column(name = "phone_number", nullable = false,  length = 20)
    private String phoneNumber;
}
