package com.excelr.firstapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excelr.firstapp.model.Student;
import com.excelr.firstapp.service.StudentService;

@Controller
public class FrontEndController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("/home")
	public String home(Model model)
	{
		List<Student> allStudents=studentService.getAllStudents();
		model.addAttribute("students",allStudents);
		return "viewAllStudents";
	}
	
	@RequestMapping("/addStudentForm")
	public String addStudentForm(Model model)
	{
		Student s1=new Student();
		model.addAttribute("student",s1);
		return "addStudentForm";
	}
	
	@PostMapping("/addStudent")
	public String addStudent(@ModelAttribute("student") Student stud)
	{
		studentService.addStudent(stud);
		return "redirect:/home";
	}
	
	@RequestMapping("/deletestudent/{rno}")
	public String deleteStudent(@PathVariable("rno") int rno)
	{
		studentService.deleteStudent(rno);
		return "redirect:/home";
	}
	
	@RequestMapping("/updatestudentform/{rno}")
	public String updatestudentform(@PathVariable("rno") int rno, Model model)
	{
		Student student=studentService.getStudent(rno);
		model.addAttribute("student",student);
		return "updateStudentForm";
	}
	
	@RequestMapping("/updatestudent/{rno}")
	public String updatestudent(@ModelAttribute("student") Student newStudentValues, @PathVariable("rno") int rno)
	{
		studentService.updateStudent(rno,newStudentValues);
		return "redirect:/home";
	}
}
