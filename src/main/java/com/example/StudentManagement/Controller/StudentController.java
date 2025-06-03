package com.example.StudentManagement.Controller;

import com.example.StudentManagement.Model.Student;
import com.example.StudentManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/addStudent")
    public String showForm(Model model){
        model.addAttribute("student",new Student());
        return "StudentForm";
    }

    @PostMapping("/save")
    public String saveStudents(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Long id){
        studentService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/editById/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model){
        Student student=studentService.getStudentById(id);
        System.out.println("Editing student id: "+student.getStudentId());
        model.addAttribute("student",student);
        return "UpdateForm";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") Student student){
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("query") String query, Model model) {
        List<Student> students = studentService.searchByName(query);
        model.addAttribute("students", students);
        return "StudentList"; // same view as list page
    }
}
