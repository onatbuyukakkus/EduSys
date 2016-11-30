package main.java.service;



import java.util.List;

import main.java.models.*;


public interface Service {
	
	
		
	//User Functions
	
	public void addPerson(EduUser person); //The function that adds the person to the database. Parameter of it a EduUser object which has the all information such as
										   //id, name, surname, email, password, profile picture link, link of the trained phorograph and role.	
	public void deletePerson(String id);   //The function that deletes the person from database. Parameter is the id of the person.
	
	public EduUser getPerson(String id);   //The function that gets the information of the person. Parameter is the id of the person.
	
	public List<EduUser> getAllPeople();   //The function that gets the information of all people.
	
	public void updatePerson(EduUser person);  //The function updates information of a person. Parameter is just like the addPerson.

	
	//Course Functions

	public void addCourse(Course course);  //The function that adds the course to the database. Parameter of it a Course object which has the all information such as id and name.
										  
	public void deleteCourse(String id);   //The function that deletes the course from database. Parameter is the id of the course.

	public void getCourse(String id);	   //The function that gets the information of the course. Parameter is the id of the course.

	public List<Course> getAllCourses();   //The function that gets the information of all courses.

	public void updateCourse(Course course);  //The function updates information of a course. Parameter is just like the getCourse.


	//Attendance Functions

	public void addAttendance(Attendance attendance); //The function that adds the course to the database. Parameter of it a Attendance object which has the all information such as 
													  //id, sectionNo, courseId and date.
	public void deleteAttendance(String id);   //The function that deletes the attendance from database. Parameter is the id of the attendance.

	public void getAttendance(String id);	   //The function that gets the information of the attendance. Parameter is the id of the attendance.

	public List<Course> getAllAttendances();   //The function that gets the information of all attendances.

	public void updateAttendance(Attendance attendance);  //The function updates information of a attendance. Parameter is just like the getAttendance.

	
	//StudentGrades Functions

	public void addStudentGrade(StudentGrade studentGrade); //The function that adds the grade to the database. Parameter of it a StudentGrade object which has the all information such as 
															//userId, examId and grade
	public void deleteStudentGrade(String userId, String examId);  //The function that deletes the grade from database. Parameters are the id of the student and id of the exam in order to express specific grade.

	public void getStudentGrade(String userId, String examId);  //The function that gets the grade. Parameters are the same as the deleteStudnetGrade function parameters.

	public List<StudentGrade> getAllStudentGrades();  //The function that gets the all grades.

	public void updateStudentGrade(StudentGrade studentGrade);  //The function updates information of a student grade. Parameter is just like the getStudentGrade.


	//Exam Functions

	public Exam getExam(String id);	//The function that gets the information of the exam. Parameter is the id of the exam.

	List<Exam> getAllExams();	//The function that gets the information of all people.

	void addExam(Exam exam);	//The function that adds the exam to the database.

	void deleteExam(String id);	//The function that deletes the exam from database. Parameter is the id of the exam.

	void updateExam(Exam exam);	//The function that updates information of a exam. Parameter is just like the addExam.




	//Section Functions

	List<Section> getAllSections();	//The function that gets the information of all sections.

	void addSection(Section section); //The function that adds the section to the database.

	void deleteSection(String course_id, int section_id); //The function that deletes the section from database. Parameters are the courseid and sectionid of the section.

	Section getSection(String course_id, int section_no); //The function that gets the information of the section. Parameters are the courseid and sectionid of the section.

	void updateSection(Section section); //The function that updates information of a section. Parameter is just like the addSection.

	//AttendanceList Functions

	AttendanceList getAttendanceList(String id, String userID); //The function that gets the information of all attendance lists.

	List<AttendanceList> getAllAttendanceLists(); //The function that gets the information of all attendance lists.

	void addAttendanceList(AttendanceList alist); //The function that adds the attendance list to the database.

	void deleteAttendanceList(String id, String userID); //The function that deletes the attendance list from database. Parameters are the id and userid of the attendance list.

	void updateAttendanceList(AttendanceList alist); //The function that updates information of a attendance list. Parameter is just like the addAttendanceList.

	//SectionStudentList Functions

	SectionStudentList getSectionStudentList(String courseID, int sectionNo, String userID); //The function that gets the information of all section student lists.

	List<SectionStudentList> getAllSectionStudentLists(); //The function that gets the information of all section student lists.

	void addSectionStudentList(SectionStudentList sectionStudentList); //The function that adds the section student list to the database.

	void deleteSectionStudentList(String courseID, int sectionNo, String userID); //The function that deletes the section student list from database. Parameters are the courseid ,sectonno and userid of the section student list.

	void updatesectionStudentList(SectionStudentList sectionStudentList); //The function that updates information of a section student list. Parameter is just like the addSectionStudentList.
	
}