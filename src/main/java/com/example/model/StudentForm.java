package com.example.model;

import org.springframework.web.multipart.MultipartFile;

public class StudentForm {
    private Long id;
    private String name;
    private String address;
    private ClassRoom classRoom;
    private MultipartFile image;

    public StudentForm() {
    }

    public StudentForm(Long id, String name, String address, MultipartFile image,ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.classRoom = classRoom;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
