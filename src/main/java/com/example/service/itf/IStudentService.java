package com.example.service.itf;

import com.example.model.Student;

import java.util.List;

public interface IStudentService extends IGenerateService<Student>{
    List<Student> findByName(String name);
}
