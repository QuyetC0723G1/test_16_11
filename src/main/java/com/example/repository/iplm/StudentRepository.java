package com.example.repository.iplm;

import com.example.model.Student;
import com.example.repository.itf.IStudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class StudentRepository implements IStudentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.deleteFlag =false", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.id=:id",Student.class);
        query.setParameter("id",id);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public void save(Student student) {
        if (student.getId() != null){
            entityManager.merge(student);
        }else {
            entityManager.persist(student);
        }
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<Student> findByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s where s.deleteFlag = false and s.name like :name",Student.class);
        query.setParameter("name",'%'+name+'%');
        return query.getResultList();
    }
}
