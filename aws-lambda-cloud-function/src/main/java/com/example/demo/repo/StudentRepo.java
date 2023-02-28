package com.example.demo.repo;
//
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.domain.Student;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//@Repository
//public class StudentRepo {
//    public List<Student> getAllStudents(){
//        return Stream.of(
//                new Student(101, "Chris Robert ", 3.5, 17),
//                new Student(102, "Steeve Cullen", 2.99, 18),
//                new Student(278, "Raj Tiwari", 3.25, 16),
//                new Student(953, "Min Young", 3.8, 17)
//        ).collect(Collectors.toList());
//    }
//}