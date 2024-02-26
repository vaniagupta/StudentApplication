package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    private Map<Integer, Student> studentMap = new HashMap<>();
    private int idCounter = 1;

    @PostMapping("/add")
    public int addStudent(@RequestBody Student student) {
        student.setId(idCounter);
        studentMap.put(idCounter, student);
        idCounter++;
        return student.getId();
    }

    @GetMapping("/university/{university}")
    public Map<Integer, Student> getAllStudentsByUniversity(@PathVariable String university) {
        Map<Integer, Student> studentsByUniversity = new HashMap<>();
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            if (entry.getValue().getUniversity().equals(university)) {
                studentsByUniversity.put(entry.getKey(), entry.getValue());
            }
        }
        return studentsByUniversity;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentMap.get(id);
    }
}
