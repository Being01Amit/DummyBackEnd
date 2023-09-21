package com.DummyBackEnd.DummyBackEnd.StudentData;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class Students {


    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private StudentAddress studentAddress;
    private List<String> favSub;
    private BigDecimal spendOnBooks;
    private LocalDateTime createdAt;

    public Students(
            String firstName,
            String lastName,
            String email,
            Gender gender,
            StudentAddress studentAddress,
            List<String> favSub,
            BigDecimal spendOnBooks,
            LocalDateTime createdAt
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.studentAddress = studentAddress;
        this.favSub = favSub;
        this.spendOnBooks = spendOnBooks;
        this.createdAt = createdAt;
    }
}
