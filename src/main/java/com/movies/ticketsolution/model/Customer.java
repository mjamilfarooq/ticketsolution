package com.movies.ticketsolution.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class Customer {

    private String name;
    private Integer age;

    @JsonIgnore
    private Double cost = 0.0;

    @JsonIgnore
    private TicketType type;

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
