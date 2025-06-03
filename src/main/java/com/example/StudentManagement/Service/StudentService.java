package com.example.StudentManagement.Service;

import com.example.StudentManagement.Model.Student;
import com.example.StudentManagement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public void deleteById(Long id){
        studentRepository.deleteById(id);
        //return "Student ID:" + id + " Deleted";
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student Not Found.!"));
    }

    public void updateStudent(Student student) {
        Student existing = studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student Not Found with ID: " + student.getStudentId()));

        existing.setName(student.getName());
        existing.setAge(student.getAge());
        existing.setStudentClass(student.getStudentClass());
        existing.setEmail(student.getEmail());
        existing.setAddress(student.getAddress());

        studentRepository.save(existing);
        //return "Updated Successfully.!";
    }

    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }
}
