package university;

import net.sanabria.PersistenceJPAConfig;
import net.sanabria.university.business.CourseFilter;
import net.sanabria.university.business.DynamicQueryService;
import net.sanabria.university.business.UniversityService;
import net.sanabria.university.dao.DepartmentDAO;
import net.sanabria.university.dao.StaffDAO;
import net.sanabria.university.model.Department;
import net.sanabria.university.model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static net.sanabria.university.business.CourseFilter.filterBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
public class CriteriaQueryTest {

    @Autowired
    private DynamicQueryService dynamicQueryService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private StaffDAO staffDAO;

    @Test
    public void findByCriteria() {
        BuildUniversity.fillUniversity(universityService);

        Department pureSciences = departmentDAO.findByName("Computer Sciences").get();
        System.out.println("Exists Pure sciences department? --> " + pureSciences);

        Staff professorSanabria = staffDAO.findByLastName("Sanabria").stream().findAny().get();
        System.out.println("Professor Sanabria? --> " + professorSanabria);

        System.out.println("\n=== All Computer Sciences courses ...");
        queryAndVerify(filterBy().department(pureSciences));

        System.out.println("\n=== Courses with 4 credits ...");
        queryAndVerify(filterBy().credits(4));

        System.out.println("\n=== Courses taught by professor Sanabria ...");
        queryAndVerify(filterBy().instructor(professorSanabria));

        System.out.println("\n=== Courses in Computer Sciences, taught by professor Sanabria, with 9 credits");
        queryAndVerify(filterBy()
                .department(pureSciences)
                .instructor(professorSanabria)
                .credits(9));
    }

    private void queryAndVerify(CourseFilter filter) {
        dynamicQueryService.findCoursesByCriteria(filter)
                .forEach(course -> {
                    filter.getInstructor().ifPresent(i -> assertEquals(i, course.getInstructor()));
                    filter.getCredits().ifPresent(c -> assertEquals(c, course.getCredits()));
                    filter.getDepartment().ifPresent(d -> assertEquals(d, course.getDepartment()));
                    System.out.println(">> Course --> " + course);
                });
    }
}
