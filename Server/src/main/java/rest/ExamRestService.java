package main.java.rest;

import main.java.models.Exam;
import main.java.service.Service;
import main.java.service.ServiceImpl;
import main.java.utility.PropertiesUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;
/**
 * Created by Enver.
 */
@Path("/exam")
public class ExamRestService {

    Service service = new ServiceImpl().getInstance();
    PropertiesUtility propertiesUtility=new PropertiesUtility();

    @GET
    @Path("/test")
    public String test(){
        return "test...";
    }

    @RolesAllowed({"ADMIN","LECTURER","STUDENT"})
    @GET
    @Path("/get/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    //The parameters of the getExam are;
    //id: id of the exam which is going to be got.
    public Response getExam(@PathParam("ID") String id){
        try {


            JSONObject jo = new JSONObject();
            Exam exam = service.getExam(id);
            if(!(exam==null)){
                jo.accumulate("id", exam.getExam_id());
                jo.accumulate("course_id", exam.getCourseID());
                jo.accumulate("section_no", exam.getSectionNo());
                jo.accumulate("type", exam.getType());
                jo.accumulate("average", exam.getAverage());
                jo.accumulate("percentage", exam.getExamPercentage());
            }

            return Response.ok(jo.toString()).header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (JSONException ex) {

        }
        return Response.serverError().build();
    }

    @GET
    @Path("/getExcel")
    @Produces("application/vnd.ms-excel")
    public Response getFile() {
        String homePath= (propertiesUtility.getProperty("project.basedir")).substring(0,propertiesUtility.getProperty("project.basedir").lastIndexOf('/'));
        String seperator= propertiesUtility.getProperty("project.fileSeperator");

        String FILE_PATH = homePath+seperator+"Server"+seperator+".temp"+seperator+"excelTemplate.xlsx";
        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=new-excel-file.xlsx");
        return response.build();

    }


    @RolesAllowed({"ADMIN","LECTURER","STUDENT"})
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    //This function gets the list of exam from the database.
    public Response listExam(){
        try {
            JSONArray main = new JSONArray();
            List <Exam> exams = service.getAllExams();
            for(Exam exam : exams){

                JSONObject jo = new JSONObject();
                jo.accumulate("id", exam.getExam_id());
                jo.accumulate("course_id", exam.getCourseID());
                jo.accumulate("section_no", exam.getSectionNo());
                jo.accumulate("type", exam.getType());
                jo.accumulate("average", exam.getAverage());
                jo.accumulate("percentage", exam.getExamPercentage());

                main.put(jo);
            }
            return Response.ok(main.toString()).header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (JSONException ex) {

        }
        return Response.serverError().build();
    }

    @RolesAllowed("LECTURER")
    @POST
    @Path("/add")
    //The parameters of the addExam are;
    //id: id of the exam which is going to be added.
    //id: course id of the exam which is going to be added.
    //id: section number of the exam which is going to be added.
    //id: type of the exam which is going to be added.
    @Produces(MediaType.TEXT_PLAIN)
    public Response addExam(@QueryParam("course")String course_id, @QueryParam("section") int section_no,@QueryParam("type") String type, @QueryParam("examPercentage") int examPercentage) {
        String id = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        try{

            Exam exam=new Exam(id, course_id, section_no, type, 0.0, examPercentage);
            service.addExam(exam);

            return Response.status(200).entity("success").build();
        }
        catch(Exception ex){
            return Response.serverError().build();
        }

    }

    @RolesAllowed("LECTURER")
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    //The parameters of the deleteExam are;
    //id: id of the exam which is going to be deleted.
    public Response deleteExam(@PathParam("id") String id) {
        try{
            service.deleteExam(id);
            return Response.status(200).entity("success").build();
        }
        catch(Exception ex){
            return Response.serverError().build();
        }
    }

    @RolesAllowed("LECTURER")
    @DELETE
    @Path("/deleteGrade/{userid}/{examid}")
    @Produces(MediaType.TEXT_PLAIN)
    //The parameters of the deleteExam are;
    //id: id of the exam which is going to be deleted.
    public Response deleteExamGrade(@PathParam("userid") String uid, @PathParam("examid") String eid ) {
        try{
            service.deleteExamGrade(uid,eid);
            return Response.status(200).entity("success").build();
        }
        catch(Exception ex){
            return Response.serverError().build();
        }
    }

    @RolesAllowed("LECTURER")
    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_PLAIN)
    //The parameters of the updateExam are;
    //id: id of the exam which is going to be updated.
    //id: course id of the exam which is going to be updated.
    //id: section number of the exam which is going to be updated.
    //id: type of the exam which is going to be updated.
    public Response updateExam(@QueryParam("ID") String id,@QueryParam("course")String course_id, @QueryParam("section") int section_no,@QueryParam("type") String type,@QueryParam("average") double average, @QueryParam("percentage") int percentage) {

        try{
            Exam exam = service.getExam(id);
            Exam ex=new Exam(exam.getExam_id(), course_id, section_no, type, average, percentage);
            service.updateExam(ex);

            return Response.status(200).entity("success").build();
        }
        catch(Exception ex){
            return Response.serverError().build();
        }

    }

    @RolesAllowed({"ADMIN","LECTURER","STUDENT"})

    @GET
    @Path("/getAllGrades/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    //The parameters of the getAllGrades are;
    //id: id of the exam which is going to be got.
    public Response getAllGradesOfAnExam(@PathParam("ID") String examID){
        try {
            JSONArray main = new JSONArray();       //A new JSON array object is created.
            List <Object[]> grades = service.getAllGradesOfACourse(examID); //Getting all exam grades
            System.out.println();
            for(Object[] grade : grades){

                JSONObject jo = new JSONObject();   //A new JSON object for each course is create
                jo.accumulate("id", grade[0]);
                jo.accumulate("name", grade[1]);
                jo.accumulate("surname", grade[2]);
                jo.accumulate("grade", grade[3]);

                main.put(jo);   //Put each JSON object to the JSON array object.
            }
            return Response.ok(main.toString()).header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return Response.serverError().build();
    }

    @RolesAllowed({"ADMIN","LECTURER"})

    @GET
    @Path("/getAllExamAveragesForLecturer/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    //The parameters of the getAllGrades are;
    //id: id of the exam which is going to be got.
    public Response getAllExamAveragesForLecturer(@PathParam("ID") String userID){
        try {
            JSONArray main = new JSONArray();       //A new JSON array object is created.
            List <Object[]> grades = service.getAllExamAveragesForLecturer(userID); //Getting all exam grades
            for(Object[] grade : grades){

                JSONObject jo = new JSONObject();   //A new JSON object for each course is create
                jo.accumulate("courseid", grade[0]);
                jo.accumulate("sectionid", grade[1]);
                jo.accumulate("col", grade[2]);


                main.put(jo);   //Put each JSON object to the JSON array object.
            }
            return Response.ok(main.toString()).header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return Response.serverError().build();
    }

    @RolesAllowed({"ADMIN","LECTURER"})
    @GET
    @Path("/getExamReport/{courseID}/{sectionID}")		/*This is the url of getting an exam grade and type of a student for a specific course.
									This url is called like http://localhost:8080/rest/user/getExamGrade/{ID}/{CourseID}, the JSON object will be formed
									for the courses of the student with given id. Then the object is returned.*/
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamReport( @PathParam("courseID") String courseID, @PathParam("sectionID") String sectionID){
        try {
            List <Object[]> exams = service.getExamReport(courseID,sectionID); //Getting an exam grade and type of student with given id.

            JSONArray main = new JSONArray();
            for(Object[] exam : exams){

                JSONObject jo = new JSONObject();   //A new JSON object for each course is create
                jo.accumulate("type", exam[0]);
                jo.accumulate("percentage", exam[1]);
                jo.accumulate("average", exam[2]);

                main.put(jo);
            }
            return Response.ok(main.toString()).header("Access-Control-Allow-Origin", "*")
                    .build();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return Response.serverError().build();
    }
}