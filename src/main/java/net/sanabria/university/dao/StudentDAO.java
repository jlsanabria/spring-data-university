package net.sanabria.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import net.sanabria.university.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO {
    private EntityManager entityManager;

    public StudentDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List findAll() {
        return entityManager.createQuery("FROM Student").getResultList();
    }

    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
    }

    public Student save(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

    public void delete(Student student) {
        entityManager.remove(student);
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Student").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<Student> findStudentByFullTime(boolean fullTime) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.fullTime = :fullTime", Student.class);
        return query.setParameter("fullTime", fullTime).getResultList();
    }

    public List<Student> findStudentByAge(Short age) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student  s WHERE s.age = :age", Student.class);
        return query.setParameter("age", age).getResultList();
    }

    public List<Student> findStudentByLastName(String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s WHERE lower(s.person.lastName) LIKE lower(:lastName) ", Student.class);
        return query.setParameter("lastName", "%" + lastName + "%").getResultList();
    }

    public Optional<Student> findOldest() {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s ORDER BY s.age DESC", Student.class);
        return Optional.ofNullable(query.setMaxResults(1).getSingleResult());
    }

    public List<Student> findStudentByFirstNameAndLastName(String firstName, String lastName) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s " +
                        "WHERE lower(s.person.firstName) LIKE lower(:firstName) " +
                        "AND lower(s.person.lastName) LIKE lower(:lastName) ", Student.class);
        return query.setParameter("firstName", "%" + firstName + "%")
                .setParameter("lastName", "%" + lastName + "%").getResultList();
    }

    public List<Student> findStudentAgeLessThan(Short age) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s WHERE s.age < :age", Student.class);
        return query.setParameter("age", age).getResultList();
    }

    public Optional<Student> findFirstStudentInAlphabet() {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s ORDER BY s.person.lastName ASC", Student.class);
        return Optional.ofNullable(query.setMaxResults(1).getSingleResult());
    }

    public List<Student> findNOldest(Integer number) {
        TypedQuery<Student> query = entityManager
                .createQuery("SELECT s FROM Student s ORDER BY s.age DESC", Student.class);
        return query.setMaxResults(number).getResultList();
    }
}
