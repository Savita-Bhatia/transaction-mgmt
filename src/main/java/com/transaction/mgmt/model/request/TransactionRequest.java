package com.transaction.mgmt.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TransactionRequest {
    @Id
    private String transactionId;

    private Integer transactionCost;

    private String firstName;

    private String lastName;

    private String emailId;

}
