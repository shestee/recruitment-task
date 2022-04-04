package com.shestee.recruitmenttaskus;

import com.shestee.recruitmenttaskus.model.Student;
import com.shestee.recruitmenttaskus.model.Teacher;
import com.shestee.recruitmenttaskus.service.StudentService;
import com.shestee.recruitmenttaskus.service.TeacherService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RecruitmentTaskUsApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(RecruitmentTaskUsApplication.class, args);

		/*StudentService studentService = appContext.getBean(StudentService.class);
		TeacherService teacherService = appContext.getBean(TeacherService.class);

		Teacher teacher1 = new Teacher("Micheal", "Smith", 40, "m.smith@company.com", "Mathematics");
		Teacher teacher2 = new Teacher("Ann", "King", 50, "ann.king@gmail.com", "Economics");

		Student student1 = new Student("Nicholas", "Snowie", 19, "nicholas.snowie@school.com", "IT");
		Student student2 = new Student("Jack", "Smith", 22, "jack.smith@school.com", "IT");
		Student student3 = new Student("Maria", "Kowalska", 21, "maria.kowalska@school.com", "IT");
		student1.addTeacher(teacher1);
		student2.addTeacher(teacher1);
		student3.addTeacher(teacher2);

		studentService.saveStudent(student1);
		studentService.saveStudent(student2);
		studentService.saveStudent(student3);

		Teacher teacher3 = new Teacher("Teacher", "Three", 40, "teacher.three@gmail.com", "Math");
		teacherService.saveTeacher(teacher3);
		studentService.addTeacherById(3, 3);*/
	}

}
