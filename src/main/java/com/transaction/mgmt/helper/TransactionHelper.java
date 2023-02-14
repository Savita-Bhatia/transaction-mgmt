package com.transaction.mgmt.helper;

import com.transaction.mgmt.model.User;
import com.transaction.mgmt.model.request.TransactionRequest;
import com.transaction.mgmt.model.response.RejectedTransactionResponse.TransactionResponse;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class TransactionHelper {

    public List<User> getUsers() {
        return Arrays.asList(
                new User("john", "burns", "john.burns@gmail.com", 120),
                new User("savita", "bhatia", "savita.bhatia@gmail.com", 500),
                new User("alan", "johnson", "alan.johnson@gmail.com", 800)
        );
    }

    public TransactionResponse convertToTransactionResponse (TransactionRequest request) {
        return new TransactionResponse(request.getFirstName(), request.getLastName(), request.getEmailId(), request.getTransactionId());
    }

}
