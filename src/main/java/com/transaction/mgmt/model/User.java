package com.transaction.mgmt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private String firstName;
    private String lastName;
    private String emailId;
    @Builder.Default
    private Integer creditLimit = 0;

}
