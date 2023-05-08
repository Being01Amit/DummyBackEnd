package com.DummyBackEnd.DummyBackEnd;

import com.DummyBackEnd.DummyBackEnd.StudentData.Address;
import com.DummyBackEnd.DummyBackEnd.StudentData.Gender;
import com.DummyBackEnd.DummyBackEnd.StudentData.Student;
import com.DummyBackEnd.DummyBackEnd.StudentData.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DummyBackEndApplication {
	public static void main(String[] args) {
		SpringApplication.run(DummyBackEndApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			String email = "amit@gmail.com";
			Address address = new Address("India", "Punjab","Jalandhar","Suranussi","144027");
			Student student = new Student("Amit","Amit",email, Gender.MALE,address, List.of("Computer Science"), BigDecimal.TEN, LocalDateTime.now());
			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

			repository.findStudentByEmail(email).ifPresentOrElse(
							// This is a If Condition i Email is exists or not
							s ->{
								System.out.println(student.getFirstName()+" with "+ student.getEmail()+" is already exists");
								},
							//Lamda and Else condition
							() -> {
								System.out.println("Student added successfully ");
								repository.insert(student);
							});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

//			Here we are using the mongo Template for find and use our query
		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1){
			throw new IllegalStateException("Found many student with same email: "+ email);
		} else if (students.isEmpty()) {
			System.out.println("Student added successfully ");
			repository.insert(student);
		}else {
			System.out.println(student.getFirstName()+" with "+ student.getEmail()+" is already exists");
		}
	}
}
