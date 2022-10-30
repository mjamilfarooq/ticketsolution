package com.movies.ticketsolution.dto;

import com.movies.ticketsolution.model.Customer;
import lombok.Data;

import java.util.List;

/**
 * TransactionRequest contains transactionId and customer information for the tickets
 */
@Data
public class TransactionRequest {
    private Integer transactionId;
    private List<Customer> customers;
}
