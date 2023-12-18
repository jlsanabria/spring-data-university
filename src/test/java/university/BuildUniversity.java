package university;

import net.sanabria.university.business.UniversityService;
import net.sanabria.university.model.Course;
import net.sanabria.university.model.Department;
import net.sanabria.university.model.Staff;
import net.sanabria.university.model.Student;

/**
 * Testing Helper class that initializes the database with a seeded
 * set of students, staff, courses and deparments
 */
public class BuildUniversity {

    public static void fillUniversity(UniversityService universityService) {
        universityService.deleteAll();
        boolean fullTime = true;

        // Student
        Student ricardo = universityService.createStudent("Ricardo", "Sanabria", fullTime, (short) 21);
        Student bruno = universityService.createStudent("Bruno", "Sanabria", fullTime, (short) 19);
        Student mike = universityService.createStudent("Mike", "Smith", fullTime, (short) 20);
        Student ally = universityService.createStudent("Ally", "Kim", !fullTime, (short) 18);

        // Staff
        Staff jose = universityService.createFaculty("Jose", "Castillo");
        Staff carmen = universityService.createFaculty("Carmen", "Castillo");
        Staff gerardo = universityService.createFaculty("Gerardo", "Zanabria");
        Staff simon = universityService.createFaculty("Simon", "Bolivar");
        Staff antonio = universityService.createFaculty("Antonio", "Sucre");
        Staff svetlana = universityService.createFaculty("Svetlana", "Alexiévich");
        Staff scarlet = universityService.createFaculty("Scarlet", "Johanson");
        Staff monica = universityService.createFaculty("Monica", "Patzi");
        Staff profJoseLuis = universityService.createFaculty("Jose Luis", "Sanabria");
        Staff profLuis = universityService.createFaculty("Luis", "Sanabria");
        Staff profSantiago = universityService.createFaculty("Santiago", "Sanabria");
        Staff profLourdes = universityService.createFaculty("Lourdes", "Mamani");
        Staff profWhite = universityService.createFaculty("Allison", "White");
        Staff profBlack = universityService.createFaculty("Hillary", "Black");
        Staff profDavis = universityService.createFaculty("James", "Davis");
        Staff profMoore = universityService.createFaculty("Allison", "Moore");
        Staff profMiller = universityService.createFaculty("Judy", "Miller");
        Staff profKing = universityService.createFaculty("Queen", "King");
        Staff profThomas = universityService.createFaculty("Tom", "Thomas");
        Staff profGreen = universityService.createFaculty("Graham", "Green");
        Staff profJones = universityService.createFaculty("John", "Jones");
        Staff profMartin = universityService.createFaculty("Matthew", "Martin");

        // Departments
        Department pureSciences = universityService.createDepartment("Computer Sciences", jose);
        Department naturalSciences = universityService.createDepartment("Natural Sciences", carmen);
        Department socialSciences = universityService.createDepartment("Social Sciences", gerardo);

        // Courses
        Course programming111 = universityService.createCourse("Programming 111", 3, profLuis, pureSciences);
        Course programming121 = universityService.createCourse("Programming 121", 4, profSantiago, pureSciences, programming111);
        Course programming131 = universityService.createCourse("Programming 131", 5, profLourdes, pureSciences, programming121);
        Course database161 = universityService.createCourse("Database 161", 4, profWhite, pureSciences);
        Course database272 = universityService.createCourse("Database 272", 6, profBlack, pureSciences, database161);
        Course systemsWorkshop281 = universityService.createCourse("Systems Workshop 281", 9, profJoseLuis, pureSciences, programming131, database272);

        //Natural Science Courses
        Course chemistry = universityService.createCourse("Chemistry", 3, profDavis, naturalSciences);
        Course physics = universityService.createCourse("Physics", 3, profMoore, naturalSciences, chemistry);
        Course cProgramming = universityService.createCourse("C Programming", 4, profMiller, naturalSciences);
        Course jProgramming = universityService.createCourse("Java Programming", 4, profKing, naturalSciences);

        //Social Science Courses
        Course history101 = universityService.createCourse("History 101", 3, profThomas, socialSciences);
        Course anthro = universityService.createCourse("Anthropology ", 3, profGreen, socialSciences, history101);
        Course sociology = universityService.createCourse("Sociology", 3, profJones, socialSciences, history101);
        Course psych = universityService.createCourse("Psychology", 4, profMartin, socialSciences, programming111);
    }
}
