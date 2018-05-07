package WebApplication;

import CasualHRSystem.Course.Course;
import CasualHRSystem.DatabaseDriver;
import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

import com.google.gson.*;
import java.net.URL;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import org.sqlite.core.DB;

public class WebApplication {

        public static ArrayList<String> datesInRange(LocalDate startDate, LocalDate endDate){
            ArrayList<LocalDate> datesShown = new ArrayList();
            LocalDate temp = startDate;
            while(!temp.isAfter(endDate)){
                datesShown.add(temp);
                temp = temp.plusDays(1);
            }
            ArrayList<String> dates = new ArrayList();
            for(LocalDate i:datesShown){
                        dates.add(i.toString());
            }
            return dates;
        }
        
        public static ArrayList<String> timesInRange(LocalTime startTime, LocalTime endTime, int minutesInterval){
            ArrayList<LocalTime> timesShown = new ArrayList();
            LocalTime temp = startTime;
            while (!temp.isAfter(endTime)){
                timesShown.add(temp);
                if(!temp.plusMinutes(minutesInterval).isBefore(temp)){
                    temp=temp.plusMinutes(minutesInterval);
                } else {
                    break;
                }
                System.out.println(temp.toString());
            }
            ArrayList<String> times = new ArrayList();
            for(LocalTime i:timesShown){
                times.add(i.toString());
            }
            return times;
        }
    
        public static HashMap<String,String> getParams(String request){
            HashMap hashMap = new HashMap<String,String>();
            StringTokenizer strtok = new StringTokenizer(request, "&");
            while(strtok.hasMoreTokens()){
                String[] parts=strtok.nextToken().split("=");
                hashMap.put(parts[0], parts[1]);
            }
            return hashMap;
        }
    
	public static void main(String[] args) {
            DatabaseDriver.initDB("chrsDB.db");
            Spark.staticFiles.location("/static");
            Logger logger = LoggerFactory.getLogger(WebApplication.class);
           
            get("/login", (request, response) -> {




			Map<String, Object> attributes = new HashMap<>();
			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/login.html");
		}, new PebbleTemplateEngine());
                
                post("/login", (request, response) -> {
                    String email=request.queryParams("userid");
                    String password=request.queryParams("password");
                    CasualHRSystem.User.User user=CasualHRSystem.DatabaseDriver.login(email,password);

                    if(user!=null){

                        request.session(true);
                        request.session().attribute("user",user.getEmail());
                        response.redirect("/dashboard");
                        Spark.halt();
                    }

                    System.out.println("User not found.");
                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("userMessage","Username or password invalid. Please try again.");
                    return new ModelAndView(attributes, "src/login.html");
                    
		}, new PebbleTemplateEngine());
                
                get("/dashboard", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");   
                        Spark.halt();
                    }
                    Map<String, Object> attributes = new HashMap<>();
                    attributes.put("User", request.session().attribute("user"));
                    // The hello.pebble file is located in directory:
                    // src/test/resources/spark/template/pebble
                    return new ModelAndView(attributes, "src/dashboard.html");
                }, new PebbleTemplateEngine());
                
                
                // Course Views
                get("/add_course", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    Map<String, Object> attributes = new HashMap<>();
                    
                        
			return new ModelAndView(attributes, "src/add_course.html");
                }, new PebbleTemplateEngine());
                
                post("/add_course", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        String name=request.queryParams("name");
                        String courseCode=request.queryParams("course-code");
                        String courseCoordinator=request.queryParams("course-coordinator");
                        String startDate = request.queryParams("start-date");
                        String endDate = request.queryParams("end-date");
                        response.redirect("courses");
                        CasualHRSystem.Course.Course addedCourse = new CasualHRSystem.Course.Course(name, courseCode, courseCoordinator, startDate, endDate);
                        addedCourse.addToDB();
			return new ModelAndView(attributes, "src/add_course.html");
		}, new PebbleTemplateEngine());
                
                get("/edit_course/:courseID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Course course = DatabaseDriver.getCourse(Integer.valueOf(request.params(":courseID")));
                        attributes.put("courseName",course.getCourseName());
                        attributes.put("courseCode",course.getCourseCode());
                        attributes.put("courseCoordinator",course.getCourseCoordinator());
                        attributes.put("courseStartDate",course.getCourseStartDate());
                        attributes.put("courseEndDate",course.getCourseEndDate());
			return new ModelAndView(attributes, "src/edit_course.html");
		}, new PebbleTemplateEngine());
                
                
                post("/edit_course/:courseID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Course course = DatabaseDriver.getCourse(Integer.valueOf(request.params(":courseID")));
                        String name=request.queryParams("name");
                        String courseCode=request.queryParams("course-code");
                        String courseCoordinator=request.queryParams("course-coordinator");
                        String startDate = request.queryParams("start-date");
                        String endDate = request.queryParams("end-date");
                        course.setCourseName(name);
                        course.setCourseCode(courseCode);
                        course.setCourseCoordinator(courseCoordinator);
                        course.setCourseStartDate(startDate);
                        course.setCourseEndDate(endDate);
                        course.updateInDB();
                        response.redirect("/courses");
                        Spark.halt();
			return new ModelAndView(attributes, "src/edit_course.html");
                }, new PebbleTemplateEngine());
                
                get("/courses", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        
                        List<Course> courses;
                        courses= DatabaseDriver.getCourses();
                        attributes.put("courses", courses);
                        
			return new ModelAndView(attributes, "src/courseTable.html");
                }, new PebbleTemplateEngine());
                
                get("course/:courseID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                        LocalTime EARLIEST_BLOCK=LocalTime.of(0, 0);
                        LocalTime LATEST_BLOCK=LocalTime.of(23, 30);
                        System.out.println(EARLIEST_BLOCK.toString());
                        System.out.println(LATEST_BLOCK.toString());
                        
                        Map<String, Object> attributes = new HashMap<>();
                        //String courseID = request.params("course");
                        //CasualHRSystem.Course.Course course = CasualHRSystem.DatabaseDriver.getCourse(Integer.valueOf(courseID));

                        LocalDate startOfWeek = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.MONDAY ) );
                        LocalDate endOfWeek = LocalDate.now().with( TemporalAdjusters.next(DayOfWeek.SUNDAY ) );
                        
                        ArrayList<String> datesShown = datesInRange(startOfWeek, endOfWeek);
                        ArrayList<String> timesShown = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                        
                        attributes.put("times", timesShown);
                        attributes.put("dates", datesShown);

			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/timetable.html");
		}, new PebbleTemplateEngine());
                
                // Activity Views
                get("/add_activity", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        
			return new ModelAndView(attributes, "src/add_activity.html");
                }, new PebbleTemplateEngine());
                
                post("/add_activity", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        String name=request.queryParams("name");
                        String courseCode=request.queryParams("course-code");
                        String courseCoordinator=request.queryParams("course-coordinator");
                        String startDate = request.queryParams("start-date");
                        String endDate = request.queryParams("end-date");
                        response.redirect("courses");
                        CasualHRSystem.Course.Course addedCourse = new CasualHRSystem.Course.Course(name, courseCode, courseCoordinator, startDate, endDate);
                        addedCourse.addToDB();
			return new ModelAndView(attributes, "src/add_course.html");
		}, new PebbleTemplateEngine());
                
                get("/edit_activity/:activityID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Course course = DatabaseDriver.getCourse(Integer.valueOf(request.params(":courseID")));
                        attributes.put("courseName",course.getCourseName());
                        attributes.put("courseCode",course.getCourseCode());
                        attributes.put("courseCoordinator",course.getCourseCoordinator());
                        attributes.put("courseStartDate",course.getCourseStartDate());
                        attributes.put("courseEndDate",course.getCourseEndDate());
			return new ModelAndView(attributes, "src/edit_course.html");
		}, new PebbleTemplateEngine());
                
                
                post("/edit_activity/:activityID", (request, response) -> {
                                        if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Course course = DatabaseDriver.getCourse(Integer.valueOf(request.params(":courseID")));
                        String name=request.queryParams("name");
                        String courseCode=request.queryParams("course-code");
                        String courseCoordinator=request.queryParams("course-coordinator");
                        String startDate = request.queryParams("start-date");
                        String endDate = request.queryParams("end-date");
                        course.setCourseName(name);
                        course.setCourseCode(courseCode);
                        course.setCourseCoordinator(courseCoordinator);
                        course.setCourseStartDate(startDate);
                        course.setCourseEndDate(endDate);
                        course.updateInDB();
                        

                        
                        response.redirect("/courses");
                        Spark.halt();
			return new ModelAndView(attributes, "src/edit_course.html");
                }, new PebbleTemplateEngine());
                
                get("/activities", (request, response) -> {
                                        if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        
                        List<Course> courses;
                        courses= DatabaseDriver.getCourses();
                        attributes.put("courses", courses);
                        
			return new ModelAndView(attributes, "src/courseTable.html");
                }, new PebbleTemplateEngine());
                
                get("activity/:activityID", (request, response) -> {
                                        if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                        LocalTime EARLIEST_BLOCK=LocalTime.of(0, 0);
                        LocalTime LATEST_BLOCK=LocalTime.of(23, 30);
                        System.out.println(EARLIEST_BLOCK.toString());
                        System.out.println(LATEST_BLOCK.toString());
                        
                        Map<String, Object> attributes = new HashMap<>();
                        //String courseID = request.params("course");
                        //CasualHRSystem.Course.Course course = CasualHRSystem.DatabaseDriver.getCourse(Integer.valueOf(courseID));

                        LocalDate startOfWeek = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.MONDAY ) );
                        LocalDate endOfWeek = LocalDate.now().with( TemporalAdjusters.next(DayOfWeek.SUNDAY ) );
                        
                        ArrayList<String> datesShown = datesInRange(startOfWeek, endOfWeek);
                        ArrayList<String> timesShown = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                        
                        attributes.put("times", timesShown);
                        attributes.put("dates", datesShown);

			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/timetable.html");
		}, new PebbleTemplateEngine());
                
                get("logout", (request, response) -> {
                    request.session(true).removeAttribute("user");
                    response.redirect("/login");
                    Spark.halt();
                    return null;
		}, new PebbleTemplateEngine());
                
	}
}