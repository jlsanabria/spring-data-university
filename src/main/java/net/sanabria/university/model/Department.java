package net.sanabria.university.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing a Department of study at the University
 */
@Entity
@Table(name = "STUDENT")
public class Department {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String name;

    @OneToOne
    private Staff staff;

    @OneToMany
    private List<Course> courses = new ArrayList<>();

    public Department() {
    }

    public Department(String name, Staff staff) {
        this.name = name;
        this.staff = staff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer departmentId) {
        this.id = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + id +
                ", name='" + name + '\'' +
                ", staff=" + staff +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(staff, that.staff) && Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, staff, courses);
    }
}
