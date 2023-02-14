package com.transaction.mgmt.controller;

import com.transaction.mgmt.helper.TransactionHelper;
import com.transaction.mgmt.model.User;
import com.transaction.mgmt.model.request.TransactionRequest;
import com.transaction.mgmt.model.response.RejectedTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping({"/transaction"})
public class TransactionController {

    private final TransactionHelper transactionHelper;

    @Autowired
    public TransactionController(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    @PostMapping("/processTransactions")
    public Mono<RejectedTransactionResponse> processTransactions(@RequestBody List<TransactionRequest> transactionList) {
        RejectedTransactionResponse rejectedTransaction = new RejectedTransactionResponse();

        Flux<User> userFlux =  Flux.fromIterable(transactionHelper.getUsers());

        return Flux.fromIterable(transactionList)
                .flatMap(transaction ->
                        userFlux
                                .filter(user -> transaction.getEmailId().equals(user.getEmailId()))
                                .next()
                                .flatMap(user -> {
                                    if (transaction.getTransactionCost() > user.getCreditLimit()) {
                                        rejectedTransaction.getRejectedTransactions().add(transactionHelper.convertToTransactionResponse(transaction));
                                        return Mono.just(rejectedTransaction);
                                    } else {
                                        user.setCreditLimit(user.getCreditLimit() - transaction.getTransactionCost());
                                        return Mono.just("Processed");
                                    }
                                })
                                .switchIfEmpty(Mono.defer(() -> {
                                    rejectedTransaction.getRejectedTransactions().add(transactionHelper.convertToTransactionResponse(transaction));
                                    return Mono.just(rejectedTransaction);
                                }))
                )
                .then(Mono.just(rejectedTransaction));

    }

}
