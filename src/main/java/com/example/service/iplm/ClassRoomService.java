package com.example.service.iplm;

import com.example.model.ClassRoom;
import com.example.repository.iplm.ClassRoomRepository;
import com.example.repository.itf.IClassRoomRepository;
import com.example.service.itf.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassRoomService implements IClassRoomService {
    @Autowired
    IClassRoomRepository roomRepository;

    @Override
    public List<ClassRoom> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void save(ClassRoom classRoom) {

    }

    @Override
    public ClassRoom findById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public void remove(Long id) {

    }
}
