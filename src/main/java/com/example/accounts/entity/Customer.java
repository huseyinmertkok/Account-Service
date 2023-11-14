package com.example.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "COSTUMER_ID")
    private Long costumerId;
    @Column(name = "COSTUMER_NAME")
    private String name;
    @Column(name = "COSTUMER_EMAIL")
    private String email;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
}
