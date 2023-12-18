package net.sanabria.university.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing a student at the University
 */
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    @Embedded
    private Person person;
    @Column
    private Boolean fullTime;
    @Column
    private Short age;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    protected Student() {
    }

    public Student(Person person, boolean fullTime, Short age) {
        this.person = person;
        this.fullTime = fullTime;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer studentId) {
        this.id = studentId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", person=" + person +
                ", fullTime=" + fullTime +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return fullTime == student.fullTime && Objects.equals(id, student.id) && Objects.equals(person, student.person) && Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, fullTime, age);
    }
}
