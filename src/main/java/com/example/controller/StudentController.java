package com.example.controller;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.model.StudentForm;
import com.example.service.iplm.ClassRoomService;
import com.example.service.iplm.StudentService;
import com.example.service.itf.IClassRoomService;
import com.example.service.itf.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Value("/Users/apple/Desktop/Module4/webApp/exam_16_11/src/main/webapp/WEB-INF/static/image/")
    String file_upload;
  @Autowired
  private IStudentService studentService;
  @Autowired
  private IClassRoomService classRoomService;

    @GetMapping("")
    public ModelAndView showListStudent() {
        List<Student> list = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        Student student = studentService.findById(Long.parseLong(id));
        student.setDeleteFlag(true);
        studentService.save(student);
        return "redirect:/students";
    }
    @GetMapping("create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student",new StudentForm());
        return modelAndView;

    }
    @PostMapping("/create")
    public String addStudent(StudentForm form, RedirectAttributes redirectAttributes){
        ClassRoom classRoom = classRoomService.findById(form.getClassRoom().getId());
        MultipartFile multipartFile = form.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(file_upload + nameFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Student student = new Student(form.getName(),form.getAddress(),nameFile,classRoom);
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message","Add Student Success");
        return "redirect:/students";

    }

    @GetMapping("/{id}/update")
    public ModelAndView showInformation(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("update");
        modelAndView.addObject("student",studentService.findById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/update")
    private String updateStudent(StudentForm studentForm, RedirectAttributes redirectAttributes, @PathVariable Long id){
        ClassRoom c = classRoomService.findById(studentForm.getClassRoom().getId());
        MultipartFile multipartFile = studentForm.getImage();
        String nameFile = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(file_upload+ nameFile));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
       Student student = new Student(id,studentForm.getName(),studentForm.getAddress(),nameFile,c);
        studentService.save(student);
        redirectAttributes.addFlashAttribute("message","Update Student Success");
        return "redirect:/students";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String q){
        List<Student> students = studentService.findByName(q);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list",students);
        return modelAndView;
    }

    @GetMapping("/{id}/information")
    public ModelAndView showInfo(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("information");
        modelAndView.addObject("student",studentService.findById(id));
        return modelAndView;
    }

}
