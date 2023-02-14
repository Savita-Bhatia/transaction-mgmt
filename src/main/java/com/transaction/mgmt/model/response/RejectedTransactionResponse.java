package com.transaction.mgmt.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RejectedTransactionResponse {

    @JsonProperty("Rejected Transactions")
    private List<TransactionResponse> rejectedTransactions = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class TransactionResponse {
        @JsonProperty("First Name")
        private String firstName;

        @JsonProperty("Last Name")
        private String lastName;
        @JsonProperty("Email Id")
        private String emailId;
        @JsonProperty("Transaction Number")
        private String transactionId;



    }

}
