package com.DummyBackEnd.DummyBackEnd.StudentData;

import lombok.Data;

@Data
public class StudentAddress {
    public StudentAddress(String country, String state, String city, String pincode) {
        this.country = country;
        State = state;
        this.city = city;
        this.pincode = pincode;
    }

    String country;
    String State;

    String city;

    String pincode;

}
