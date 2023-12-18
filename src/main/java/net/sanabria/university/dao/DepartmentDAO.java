package net.sanabria.university.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import net.sanabria.university.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentDAO {
    private EntityManager entityManager;

    public DepartmentDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List findAll() {
        return entityManager.createQuery("FROM Department").getResultList();
    }

    public Optional<Department> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));
    }

    public Department save(Department department) {
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

    public void delete(Department department) {
        entityManager.remove(department);
    }

    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Department ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Optional<Department> findByName(String name) {
        TypedQuery<Department> query = entityManager
                .createQuery("SELECT d FROM Department d WHERE LOWER(d.name) LIKE LOWER(:name)", Department.class);
        return Optional.ofNullable(query.setParameter("name", "%" + name + "%").getSingleResult());
    }
}
