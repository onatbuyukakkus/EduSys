package main.java.service;

import java.util.ArrayList;
import java.util.List;

import main.java.models.*;

import main.utility.HibernateUtility;


public class ServiceImpl implements Service{
	
	HibernateUtility hibernateUtility= new HibernateUtility();
	private static Service instance = null;
	
	
	public static Service getInstance() {	
		if (instance == null) {
			return new ServiceImpl();
		} else {
			return instance;
		}
	}

	public void addPerson(EduUser person){  //This function is explained in Service.java
		hibernateUtility.save(person);      //This calls the hibernateUtility function that saves the person to the database.
	}

	public EduUser getPerson(String id){	//The function is explained in the Service.java
        List columnNameList=new ArrayList<String>(); 
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
        valueList.add(id);					//This is the array that will be used in hibernateUtility to get value of the ids.
		return (EduUser) hibernateUtility.get(EduUser.class, valueList,columnNameList).get(0); //This returns the id of the person.
	}


	public List<EduUser> getAllPeople(){	
		return hibernateUtility.get(EduUser.class);  //This calles the get function in hibernateUtility end return the values.
	}
	public void deletePerson(String id){	//This function is the same as the getPerson.
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
		hibernateUtility.delete(EduUser.class, valueList,columnNameList);
	}
	
	public void updatePerson(EduUser person){ //This is explained in the Service.java.
		hibernateUtility.update(person);	  //This calls the function from hibernate updates the database.
	}
	
	
	/* New class addition */

	//Course Functions

	public void addCourse(Course course){  //This function is explained in Service.java
		hibernateUtility.save(course);      //This calls the hibernateUtility function that saves the course to the database.
	}

	public Course getCourse(String id){	//The function is explained in the Service.java
        List columnNameList=new ArrayList<String>(); 
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
        valueList.add(id);					//This is the array that will be used in hibernateUtility to get value of the ids.
		return (Course) hibernateUtility.get(Course.class, valueList,columnNameList).get(0); //This returns the id of the course.
	}


	public List<Course> getAllCourses(){	
		return hibernateUtility.get(Course.class);  //This calles the get function in hibernateUtility end return the values.
	}

	public void deleteCourse(String id){	//This function is the same as the getCourse.
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
		hibernateUtility.delete(Course.class, valueList,columnNameList);
	}
	
	public void updateCourse(Course course){ //This is explained in the Service.java.
		hibernateUtility.update(course);	  //This calls the function from hibernate updates a course in the database.
	}
	

	//Attendance Functions
	
	public void addAttendance(Attendance attendance){  //This function is explained in Service.java
		hibernateUtility.save(attendance);      //This calls the hibernateUtility function that saves the attendance to the database.
	}

	public void getAttendance(String id){	//The function is explained in the Service.java
        List columnNameList=new ArrayList<String>(); 
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
        valueList.add(id);					//This is the array that will be used in hibernateUtility to get value of the ids.
		return (Attendance) hibernateUtility.get(Attendace.class, valueList,columnNameList).get(0); //This returns the id of the attendance.
	}


	public List<Attendacne> getAllAttendance(){	
		return hibernateUtility.get(Attendance.class);  //This calles the get function in hibernateUtility end return the values.
	}

	public void deleteAttendances(String id){	//This function is the same as the getAttendance.
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("id");
        valueList.add(id);
		hibernateUtility.delete(Attendance.class, valueList,columnNameList);
	}
	
	public void updateAttendance(Attendance attendance){ //This is explained in the Service.java.
		hibernateUtility.update(attendance);	  //This calls the function from hibernate updates an attendace in the database.
	}
	



	//StudentGrades Functions
	
	public void addStudentGrade(StudentGrade studentGrade){  //This function is explained in Service.java
		hibernateUtility.save(studentGrade);      //This calls the hibernateUtility function that saves the student grade to the database.
	}

	public void getStudentGrade(String userId, String examId){	//The function is explained in the Service.java
        List columnNameList=new ArrayList<String>(); 
		List valueList=new ArrayList<String>();
		columnNameList.add("userId");			//This is the array that will be used in hibernateUtility to get the id column.
		columnNameList.add("examId");
        valueList.add(userId);					//This is the array that will be used in hibernateUtility to get value of the ids.
        valueList.add(examId)
		return (StudentGrade) hibernateUtility.get(StudentGrade.class, valueList,columnNameList).get(0); //This returns the id of the student grade.
	}


	public List<StudentGrade> getAllStudentGrades(){	
		return hibernateUtility.get(StudentGrade.class);  //This calles the get function in hibernateUtility end return the values.
	}

	public void deleteStudentGrade(String userId, String examId){	//This function is the same as the getStudentGrade.
        List columnNameList=new ArrayList<String>();
        List valueList=new ArrayList<String>();
        columnNameList.add("userId");
        columnNameList.add("examId");
        valueList.add(userId);
        valueList.add(examId);
		hibernateUtility.delete(StudentGrade.class, valueList,columnNameList);
	}
	
	public void updateStudentGrade(StudentGrade studentGrade){ //This is explained in the Service.java.
		hibernateUtility.update(studentGrade);	  //This calls the function from hibernate updates a student grade the database.
	}



	public Exam getExam(String id) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
		valueList.add(id);				//This is the array that will be used in hibernateUtility to get value of the ids.
		return (Exam) hibernateUtility.get(Exam.class, valueList,columnNameList).get(0); //This returns the id of the exam.
	}

	public List<Exam> getAllExams() {
		return hibernateUtility.get(Exam.class);
	}

	public void addExam(Exam exam) {
		hibernateUtility.save(exam);
	}

	public void deleteExam(String id) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
		valueList.add(id);				//This is the array that will be used in hibernateUtility to get value of the ids.
		hibernateUtility.delete(Exam.class, valueList,columnNameList);
	}

	public void updateExam(Exam exam) {
		hibernateUtility.update(exam);
	}





	public List<Section> getAllSections() {
		return hibernateUtility.get(Section.class);
	}

	public void addSection(Section section) {
		hibernateUtility.save(section);
	}



	public void deleteSection(String course_id, int section_id)  {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("courseid");			//This is the array that will be used in hibernateUtility to get the courseid column.
		columnNameList.add("sectionno");		//This is the array that will be used in hibernateUtility to get the sectionno column.
		valueList.add(course_id);
		valueList.add(section_id);
		hibernateUtility.delete(Section.class, valueList,columnNameList);
	}

	public Section getSection(String course_id, int section_no) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("courseid");			//This is the array that will be used in hibernateUtility to get the courseid column.
		columnNameList.add("sectionno");		//This is the array that will be used in hibernateUtility to get the sectionno column.
		valueList.add(course_id);			//This is the array that will be used in hibernateUtility to get value of the courseids.
		valueList.add(section_no);			//This is the array that will be used in hibernateUtility to get value of the sectionnos.
		return (Section) hibernateUtility.get(Section.class, valueList,columnNameList).get(0); //This returns the id of the section.
	}

	public void updateSection(Section section) {
		hibernateUtility.update(section);
	}






	public AttendanceList getAttendanceList(String id, String userID) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("id");			//This is the array that will be used in hibernateUtility to get the id column.
		columnNameList.add("userid");			//This is the array that will be used in hibernateUtility to get the userid column.
		valueList.add(id);
		valueList.add(userID);
		return (AttendanceList) hibernateUtility.get(AttendanceList.class, valueList,columnNameList).get(0); //This returns the id of the attendance.
	}

	public List<AttendanceList> getAllAttendanceLists() {
		return hibernateUtility.get(AttendanceList.class);
	}

	public void addAttendanceList(AttendanceList alist) {
		hibernateUtility.save(alist);
	}

	public void deleteAttendanceList(String id, String userID) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("id");
		columnNameList.add("userid");
		valueList.add(id);
		valueList.add(userID);
		hibernateUtility.delete(AttendanceList.class, valueList,columnNameList);
	}

	public void updateAttendanceList(AttendanceList alist) {
		hibernateUtility.update(alist);
	}

	@Override
	public SectionStudentList getSectionStudentList(String courseID, int sectionNo, String userID) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("courseid");			//This is the array that will be used in hibernateUtility to get the courseid column.
		columnNameList.add("sectionno");		//This is the array that will be used in hibernateUtility to get the sectionno column.
		columnNameList.add("userid");			//This is the array that will be used in hibernateUtility to get the userid column.
		valueList.add(courseID);			//This is the array that will be used in hibernateUtility to get value of the courseids.
		valueList.add(sectionNo);			//This is the array that will be used in hibernateUtility to get value of the sectionnos.
		valueList.add(userID);				//This is the array that will be used in hibernateUtility to get value of the userids.
		return (SectionStudentList) hibernateUtility.get(SectionStudentList.class, valueList,columnNameList).get(0);
	}

	@Override
	public List<SectionStudentList> getAllSectionStudentLists() {
		return hibernateUtility.get(SectionStudentList.class);
	}

	@Override
	public void addSectionStudentList(SectionStudentList sectionStudentList) {
		hibernateUtility.save(sectionStudentList);
	}

	@Override
	public void deleteSectionStudentList(String courseID, int sectionNo, String userID) {
		List columnNameList=new ArrayList<String>();
		List valueList=new ArrayList<String>();
		columnNameList.add("courseid");
		columnNameList.add("sectionno");
		columnNameList.add("userid");
		valueList.add(courseID);
		valueList.add(sectionNo);
		valueList.add(userID);
		hibernateUtility.delete(SectionStudentList.class, valueList,columnNameList);
	}

	@Override
	public void updatesectionStudentList(SectionStudentList sectionStudentList) {
		hibernateUtility.update(sectionStudentList);
	}




}