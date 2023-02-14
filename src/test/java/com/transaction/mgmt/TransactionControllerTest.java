package com.transaction.mgmt;

import com.transaction.mgmt.controller.TransactionController;
import com.transaction.mgmt.helper.TransactionHelper;
import com.transaction.mgmt.model.User;
import com.transaction.mgmt.model.request.TransactionRequest;
import com.transaction.mgmt.model.response.RejectedTransactionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {
    @Mock
    private TransactionHelper transactionHelper;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void testProcessTransactionsWhenTransactionCostExceedCreditLimit() {
        User user1 = new User("john", "burns", "j.b@gmail.com", 100);
        User user2 = new User("savita", "bhatia", "savita.bhatia@gmail.com", 200);
        when(transactionHelper.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<TransactionRequest> transactions = Arrays.asList(
                new TransactionRequest("TR0001",50, "john","burns","j.b@gmail.com"),
                new TransactionRequest("TR0002",150, "john","burns","j.b@gmail.com"),
                new TransactionRequest("TR0003",190, "savita","bhatia", "savita.bhatia@gmail.com")
        );

        RejectedTransactionResponse expected = new RejectedTransactionResponse();
        expected.getRejectedTransactions().add(transactionHelper.convertToTransactionResponse(transactions.get(1)));
        Mono<RejectedTransactionResponse> result = transactionController.processTransactions(transactions);
        assertEquals(expected, result.block());

    }

    @Test
    public void testProcessTransactionsWhenTransactionCostIsLessThanCreditLimit() {
        User user1 = new User("john", "burns", "j.b@gmail.com", 100);
        User user2 = new User("savita", "bhatia", "savita.bhatia@gmail.com", 200);
        when(transactionHelper.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<TransactionRequest> transactions = Arrays.asList(
                new TransactionRequest("TR0001",50, "john","burns","j.b@gmail.com"),
                new TransactionRequest("TR0002",180, "savita","bhatia","savita.bhatia@gmail.com"),
                new TransactionRequest("TR0003",45, "john","burns","j.b@gmail.com")
        );
        Mono<RejectedTransactionResponse> result = transactionController.processTransactions(transactions);

        assertTrue(result.block().getRejectedTransactions().isEmpty());
    }


    @Test
    public void testProcessTransactionsWithInvalidEmailId() {
        User user = new User("john", "doe", "john.doe@example.com", 150);
        when(transactionHelper.getUsers()).thenReturn(Collections.singletonList(user));
        List<TransactionRequest> transactions = Collections.singletonList(new TransactionRequest("TR0001", 100, "john", "doe", "jane.doe@example.com"));
        RejectedTransactionResponse expected = new RejectedTransactionResponse();
        expected.getRejectedTransactions().add(transactionHelper.convertToTransactionResponse(transactions.get(0)));
        Mono<RejectedTransactionResponse> result = transactionController.processTransactions(transactions);
        assertEquals(expected, result.block());
    }
}


