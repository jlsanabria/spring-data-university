package net.sanabria.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import net.sanabria.university.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAO {
    private EntityManager entityManager;

    public CourseDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List findAll() {
        return entityManager.createQuery("FROM Course").getResultList();
    }

    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Course.class, id));
    }

    public Course save(Course course) {
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        return course;
    }

    public void delete(Course course) {
        entityManager.getTransaction().begin();
        entityManager.remove(course);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Course").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Optional<Course> findByName(String name) {
        TypedQuery<Course> query = entityManager
                .createQuery("SELECT c FROM Course c WHERE lower(c.name) LIKE lower(:name)", Course.class);
        return Optional.ofNullable(query.setParameter("name", "%" + name + "%").getSingleResult());
    }

    public List<Course> findCourseByChairLastName(String chairLastName) {
        TypedQuery<Course> query = entityManager
                .createQuery("SELECT c FROM Course c WHERE lower(c.department.staff.member.lastName) LIKE lower(:chairLastName)", Course.class);
        return query.setParameter("chairLastName", "%" + chairLastName + "%").getResultList();
    }

    public List<Course> findCourseByPrerequisite(Integer idPrerequisite) {
        TypedQuery<Course> query = entityManager
                .createQuery("SELECT c FROM Course c JOIN c.prerequisites p WHERE p.id = ?1", Course.class);
        return query.setParameter(1, idPrerequisite).getResultList();
    }

    public List<Course> findCourseByCredits(Integer credits) {
        TypedQuery<Course> query = entityManager
                .createQuery("SELECT c FROM Course c WHERE c.credits = :credits", Course.class);
        return query.setParameter("credits", credits).getResultList();
    }

    public List<Course> findByCriteria(CriteriaQuery<Course> criteria) {
        return entityManager.createQuery(criteria).getResultList();
    }
}
