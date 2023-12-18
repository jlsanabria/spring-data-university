package net.sanabria.university.business;

import net.sanabria.university.dao.CourseDAO;
import net.sanabria.university.dao.DepartmentDAO;
import net.sanabria.university.dao.StaffDAO;
import net.sanabria.university.dao.StudentDAO;
import net.sanabria.university.model.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UniversityService {
    private DepartmentDAO departmentDAO;
    private StaffDAO staffDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    public UniversityService(DepartmentDAO departmentDAO, StaffDAO staffDAO, StudentDAO studentDAO, CourseDAO courseDAO) {
        this.departmentDAO = departmentDAO;
        this.staffDAO = staffDAO;
        this.studentDAO = studentDAO;
        this.courseDAO = courseDAO;
    }

    public Student createStudent(String firstName, String lastName, boolean fullTime, Short age) {
        return studentDAO.save(new Student(new Person(firstName, lastName), fullTime, age));
    }

    public Staff createFaculty(String firstName, String lastName) {
        return staffDAO.save(new Staff(new Person(firstName, lastName)));
    }

    public Department createDepartment(String departmentName, Staff departmentChair) {
        return departmentDAO.save(new Department(departmentName, departmentChair));
    }

    public Course createCourse(String name, int credits, Staff professor, Department department) {
        return courseDAO.save(new Course(name, credits, professor, department));
    }

    public Course createCourse(String name, int credits, Staff professor, Department department, Course...prerequisites) {
        Course c = new Course(name, credits, professor, department);
        Arrays.asList(prerequisites).forEach(c::addPrerequisite);
        return courseDAO.save(c);
    }

    public List findAllCourses() {
        return courseDAO.findAll();
    }

    public List findAllStaff() {
        return staffDAO.findAll();
    }

    public List findAllDepartments() {
        return departmentDAO.findAll();
    }

    public List findAllStudents() {
        return studentDAO.findAll();
    }

    public void deleteAll() {
        try {
            studentDAO.deleteAll();
            courseDAO.deleteAll();
            departmentDAO.deleteAll();
            staffDAO.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
