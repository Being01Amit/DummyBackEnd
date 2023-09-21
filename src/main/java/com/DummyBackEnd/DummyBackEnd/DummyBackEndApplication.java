package com.DummyBackEnd.DummyBackEnd;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.DummyBackEnd.DummyBackEnd.StudentData.Gender;
import com.DummyBackEnd.DummyBackEnd.StudentData.StudentAddress;
import com.DummyBackEnd.DummyBackEnd.StudentData.Students;
import com.DummyBackEnd.DummyBackEnd.repository.StudentsRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

@SpringBootApplication
public class DummyBackEndApplication {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(DummyBackEndApplication.class, args);
    }

    String firstname = sc.nextLine();
    String lastname = sc.nextLine();
    String email = sc.nextLine();

    List<String> fav = new ArrayList();

    public List<String> getFav() {
        fav.add("Java");
        fav.add("SpringBoot");
        fav.add("Android");
        fav.add("JavaScript");
        fav.add("Python");
        return fav;
    }

    @Bean
    CommandLineRunner runner(StudentsRepo studentsRepo, MongoTemplate mongoTemplate) {
        return args -> {

            System.out.println("Enter your Data for Database : ");

            StudentAddress address = new StudentAddress(
                    "India",
                    "Punjab",
                    "Mohali",
                    "160070"

            );

            Students student = new Students(
                    firstname,
                    lastname,
                    email,
                    Gender.MALE,
                    address,
                    getFav(),
                    BigDecimal.TEN,
                    LocalDateTime.now()

            );
//Command to start mongo DB in Fedora docker start my-mongodb
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));

            List<Students> students = mongoTemplate.find(query, Students.class);

            if (students.size() > 1) {
                throw new IllegalStateException("user already exists with this Email : " + email);
            }

            if (students.isEmpty()) {
                studentsRepo.insert(student);
            }else {
                System.out.println("user already exists with this Email : " + email);
            }
        };
    }
}
