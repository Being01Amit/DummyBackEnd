package com.DummyBackEnd.DummyBackEnd.repository;

import com.DummyBackEnd.DummyBackEnd.StudentData.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentsRepo extends MongoRepository<Students, String> {


}
