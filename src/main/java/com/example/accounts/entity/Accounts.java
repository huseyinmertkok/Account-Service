package com.example.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    @Id
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;
    @Column(name = "COSTUMER_ID")
    private Long costumerId;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "BRANCH_ADDRESS")
    private String branchAddress;
}
