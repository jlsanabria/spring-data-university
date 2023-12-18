package net.sanabria.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import net.sanabria.university.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StaffDAO {
    private EntityManager entityManager;

    public StaffDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List findAll() {
        return entityManager.createQuery("FROM Staff").getResultList();
    }

    public Optional<Staff> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Staff.class, id));
    }

    public Staff save(Staff staff) {
        entityManager.getTransaction().begin();
        entityManager.persist(staff);
        entityManager.getTransaction().commit();
        return staff;
    }

    public void delete(Staff staff) {
        entityManager.getTransaction().begin();
        entityManager.remove(staff);
        entityManager.getTransaction().commit();
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Staff").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<Staff> findByLastName(String lastName) {
        TypedQuery<Staff> query = entityManager
                .createQuery("SELECT s FROM Staff s WHERE lower(s.member.lastName) LIKE lower(:lastName)", Staff.class);
        return query.setParameter("lastName", "%" + lastName + "%").getResultList();
    }

    public List<Staff> find(int pageNumber, int pageSize) {
        TypedQuery<Staff> query = entityManager
                .createQuery("", Staff.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
