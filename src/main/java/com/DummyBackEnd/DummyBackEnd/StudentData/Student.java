package com.DummyBackEnd.DummyBackEnd.StudentData;

import com.DummyBackEnd.DummyBackEnd.StudentData.Address;
import com.DummyBackEnd.DummyBackEnd.StudentData.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Student {

    @Id
    private String id;

    private String firstName;
    private String lastName;

//    Here we are defining the email should be unique for when ever we create a JSON Object
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<String> favSub;
    private BigDecimal totalSpendingInBooks;
    private LocalDateTime createdAt;

    public Student(
            String firstName,
            String lastName,
            String email,
            Gender gender,
            Address address,
            List<String> favSub,
            BigDecimal totalSpendingInBooks,
            LocalDateTime createdAt
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.favSub = favSub;
        this.totalSpendingInBooks = totalSpendingInBooks;
        this.createdAt = createdAt;
    }
}
