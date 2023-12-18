package net.sanabria.university.business;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.sanabria.university.dao.CourseDAO;
import net.sanabria.university.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicQueryService {
    private CourseDAO courseDAO;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DynamicQueryService(CourseDAO courseDAO, EntityManagerFactory entityManagerFactory) {
        this.courseDAO = courseDAO;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Course> findCoursesByCriteria(CourseFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        List<Predicate> predicates = new ArrayList<>();
        filter.getDepartment().ifPresent(d ->
                predicates.add(criteriaBuilder.equal(root.get("department"), d)));
        filter.getCredits().ifPresent(c ->
                predicates.add(criteriaBuilder.equal(root.get("credits"), c)));
        filter.getInstructor().ifPresent(i ->
                predicates.add(criteriaBuilder.equal(root.get("instructor"), i)));
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return courseDAO.findByCriteria(criteriaQuery);
    }
}
