package com.DummyBackEnd.DummyBackEnd.StudentData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String State;
    private String city;
    private String area;
    private String postalCode;
}
