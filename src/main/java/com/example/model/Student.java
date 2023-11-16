package com.example.model;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ColumnDefault("false")
    private boolean deleteFlag;

    private String address;
    private String image;
    @ManyToOne
    @JoinColumn(name = "classRoomId", nullable = false)
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(Long id, String name, String address, String image, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.classRoom = classRoom;
    }

    public Student(Long id, String name, boolean deleteFlag, String address, String image, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.deleteFlag = deleteFlag;
        this.address = address;
        this.image = image;
        this.classRoom = classRoom;
    }

    public Student(String name, String address, String image, ClassRoom classRoom) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.classRoom = classRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
