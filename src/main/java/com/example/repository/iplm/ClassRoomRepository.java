package com.example.repository.iplm;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.repository.itf.IClassRoomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class ClassRoomRepository implements IClassRoomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ClassRoom> findAll() {
        TypedQuery<ClassRoom> typedQuery = entityManager.createQuery("select c from ClassRoom c",ClassRoom.class);
        return typedQuery.getResultList();
    }

    @Override
    public ClassRoom findById(Long id) {
        TypedQuery<ClassRoom> typedQuery = entityManager.createQuery("select c from  ClassRoom c where c.id =:id",ClassRoom.class);
        typedQuery.setParameter("id",id);
        try{
            return typedQuery.getSingleResult();
        }
        catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(ClassRoom classRoom) {

    }

    @Override
    public void remove(Long id) {

    }

}
