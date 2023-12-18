package net.sanabria.university.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entity representing a staff member of a department
 */
@Entity
@Table(name = "STAFF")
public class Staff {
    @Id
    @GeneratedValue
    private Integer id;

    @Embedded
    private Person member;

    protected Staff() {
    }

    public Staff(Person member) {
        this.member = member;
    }

    public Integer getId() {
        return id;
    }

    public Person getMember() {
        return member;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + id +
                ", member=" + member +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(id, staff.id) && Objects.equals(member, staff.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member);
    }
}
