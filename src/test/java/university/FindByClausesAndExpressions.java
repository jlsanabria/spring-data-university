package university;


import net.sanabria.PersistenceJPAConfig;
import net.sanabria.university.business.UniversityService;
import net.sanabria.university.dao.StudentDAO;
import net.sanabria.university.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class})
public class FindByClausesAndExpressions {

    @Autowired
    private UniversityService universityService;
    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void findByClausesAndExpressions() {
        BuildUniversity.fillUniversity(universityService);

        List<Student> students = universityService.findAllStudents();
        students.forEach(System.out::println);

        System.out.println("==> Student with 21 years old");
        assertTrue(studentDAO.findOldest().get().getAge().equals((short) 21));

        System.out.println("==> Students age less than 20");
        studentDAO.findStudentAgeLessThan((short)20).stream().forEach(s -> assertTrue(s.getAge() < 20));

    }
}
