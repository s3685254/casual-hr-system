package WebApplication;

import CasualHRSystem.Course.Activity;
import CasualHRSystem.Course.Course;
import CasualHRSystem.Course.Task;
import CasualHRSystem.DatabaseDriver;
import CasualHRSystem.Request.*;
import CasualHRSystem.User.Admin;
import CasualHRSystem.User.Approvals;
import CasualHRSystem.User.CasualStaffMember;
import CasualHRSystem.User.CourseCoordinator;
import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.*;
import java.net.URL;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.sqlite.core.DB;




public class WebApplication {

                        static final LocalTime EARLIEST_BLOCK=LocalTime.of(8, 30);
                        static final LocalTime LATEST_BLOCK=LocalTime.of(20, 30);
                        static final int BLOCK_MINUTES = 30;
    
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
            timesShown.add(temp);
            while (temp.isBefore(endTime)){
                    timesShown.add(temp);
                    temp = temp.plusMinutes(minutesInterval);
                    if(temp.getHour()==0&&temp.getMinute()==0){
                        break;
                    }
            }
            ArrayList<String> times = new ArrayList();
            for(LocalTime i:timesShown){
                times.add(i.toString());
            }
            return times;
        }
    
        public static ArrayList<String> durationsInRange(int maxMinutes, int blockMinutes){
            ArrayList<Duration> durationsShown = new ArrayList();
            Duration duration = Duration.ofMinutes(BLOCK_MINUTES);
            Duration maxDuration = Duration.ofMinutes(maxMinutes);
            
            while (duration.compareTo(maxDuration)<=0){
                System.out.println(duration);
                durationsShown.add(duration);
                duration = duration.plusMinutes(BLOCK_MINUTES);
            }
            ArrayList<String> durations = new ArrayList();
            for(Duration i:durationsShown){
                String minsString = "";
                if(i.toMinutes()%60==0){
                    minsString = "00";
                } else {
                    minsString = String.valueOf(i.toMinutes()%60);
                }
                durations.add(String.valueOf(i.toMinutes()/60)+":"+minsString);
            }
            return durations;
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
            DatabaseDriver.initDB(DatabaseDriver.dbLoc);
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
                        request.session().attribute("userType",user.getUserType());
                        request.session().attribute("userID",user.getUserID());
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
                        CasualHRSystem.Course.Course addedCourse = new CasualHRSystem.Course.Course(name, courseCode, request.session(true).attribute("userID"), startDate, endDate);
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
                        course.setCourseCoordinator(request.session(true).attribute("userID"));
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
                
                get("/courses/:courseID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                        int courseID = Integer.parseInt(request.params(":courseID"));
                        
                        Map<String, Object> attributes = new HashMap<>();
                        //String courseID = request.params("course");
                        //CasualHRSystem.Course.Course course = CasualHRSystem.DatabaseDriver.getCourse(Integer.valueOf(courseID));

                        LocalDate startOfWeek = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.MONDAY ) );
                        LocalDate endOfWeek = LocalDate.now().with( TemporalAdjusters.next(DayOfWeek.SUNDAY ) );
                        
                        ArrayList<String> datesShown = datesInRange(startOfWeek, endOfWeek);
                        ArrayList<String> timesShown = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                        
                        attributes.put("times", timesShown);
                        attributes.put("dates", datesShown);
                        attributes.put("courseID", courseID);
                        attributes.put("activities", DatabaseDriver.getActivities(courseID));
                        attributes.put("tasks", DatabaseDriver.getTasks(courseID));

                        System.out.println(DatabaseDriver.getTasks(courseID));
                        
			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/timetable.html");
		}, new PebbleTemplateEngine());
                
                //Task Views
                get("/:courseID/add_task", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        attributes.put("timesAvailable", timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30));
                        attributes.put("durationsAvailable", durationsInRange(24*60, 30));
			return new ModelAndView(attributes, "src/add_task.html");
                }, new PebbleTemplateEngine());
                
                post("/:courseID/add_task", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    int courseID = Integer.valueOf(request.params(":courseID"));
                    ArrayList<String> timesAvailable = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                    ArrayList<String> durationsAvailable = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        String name=request.queryParams("task-name");
                        int staff=Integer.parseInt(request.queryParams("staff"));
                        int students=Integer.parseInt(request.queryParams("students"));
                        String location=request.queryParams("location");
                        String day=request.queryParams("day");
                        String time=request.queryParams("time");
                        String duration=request.queryParams("duration");
                        String frequency=request.queryParams("frequency");
                        Task addedTask = new Task(name, location, students, staff, day, frequency, duration, time);
                        addedTask.setCourseID(courseID);
                        addedTask.addToDB();
                        
                        Course course = DatabaseDriver.getCourse(courseID);
                        LocalDate startDate = LocalDate.parse(course.getCourseStartDate());
                        LocalDate endDate = LocalDate.parse(course.getCourseEndDate());
                        LocalDate tempDate = startDate;
                        while(tempDate.isBefore(endDate)){
                                                    if(frequency.equals("Weekly")){
                            tempDate=tempDate.plusWeeks(1);
                            
                        } else if(frequency.equals("Fortnightly")){
                            tempDate=tempDate.plusWeeks(2);
                            
                        } else if(frequency.equals("Monthly")){
                            tempDate=tempDate.plusMonths(1);
                        }
                            LocalDate toAdd = tempDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(day.toUpperCase()) ));
                            if(toAdd.isBefore(endDate)){
                                Activity activity = new Activity(courseID, name, students, staff, location, toAdd.toString(), time, duration);
                                activity.addToDB();
                            }
                        }
                        
                        response.redirect("/courses/"+String.valueOf(courseID));
                        Spark.halt();
			return new ModelAndView(attributes, "src/add_task.html");
		}, new PebbleTemplateEngine());
                
                
                // Activity Views
                get("/:courseID/add_activity", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        attributes.put("timesAvailable", timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30));
                        attributes.put("durationsAvailable", durationsInRange(24*60, 30));
			return new ModelAndView(attributes, "src/add_activity.html");
                }, new PebbleTemplateEngine());
                
                post("/:courseID/add_activity", (request, response) -> {
                    int courseID = Integer.valueOf(request.params(":courseID"));
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    ArrayList<String> timesAvailable = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                    ArrayList<String> durationsAvailable = timesInRange(EARLIEST_BLOCK, LATEST_BLOCK, 30);
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        String name=request.queryParams("activity-name");
                        int staff=Integer.parseInt(request.queryParams("staff"));
                        int students=Integer.parseInt(request.queryParams("students"));
                        String location=request.queryParams("location");
                        String date=request.queryParams("date");
                        String time=request.queryParams("time");
                        String duration=request.queryParams("duration");
                        Activity addedActivity = new Activity(courseID, name, students, staff, location, date, time, duration);
                        addedActivity.addToDB();
                        response.redirect("/courses/"+String.valueOf(courseID));
                        Spark.halt();
			return new ModelAndView(attributes, "src/add_activity.html");
		}, new PebbleTemplateEngine());
                
                get("/edit_activity/:activityID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Activity activity = DatabaseDriver.getActivity(Integer.valueOf(request.params(":activityID")));
                        String name=request.queryParams("activity-name");
                        int staff=Integer.valueOf(request.queryParams("staff"));
                        int students=Integer.valueOf(request.queryParams("students"));
                        String location=request.queryParams("location");
                        String date=request.queryParams("date");
                        String time=request.queryParams("time");
                        activity.setName(name);
                        activity.setNumStaff(staff);
                        activity.setStudents(students);
                        activity.setLocation(location);
                        activity.setDate(date);
                        activity.setTime(time);
                        activity.updateInDB();
                        return new ModelAndView(attributes, "src/edit_activity.html");
		}, new PebbleTemplateEngine());
                
                post("/edit_activity/:activityID", (request, response) -> {
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        Activity activity = DatabaseDriver.getActivity(Integer.valueOf(request.params(":activityID")));
                        String name=request.queryParams("activity-name");
                        int staff=Integer.valueOf(request.queryParams("staff"));
                        int students=Integer.valueOf(request.queryParams("students"));
                        String location=request.queryParams("location");
                        String date=request.queryParams("date");
                        String time=request.queryParams("time");
                        activity.setName(name);
                        activity.setNumStaff(staff);
                        activity.setStudents(students);
                        activity.setLocation(location);
                        activity.setDate(date);
                        activity.setTime(time);
                        activity.updateInDB();
                        response.redirect("/"+activity.getCourseID()+"/activities");
                        Spark.halt();
                        return new ModelAndView(attributes, "src/edit_activity.html");
		}, new PebbleTemplateEngine());
                
                get("/:courseID/activities", (request, response) -> {
                    int courseID = Integer.valueOf(request.params(":courseID"));
                    if (request.session(true).attribute("user") == null){
                        response.redirect("/login");
                        Spark.halt();
                    }
                    	Map<String, Object> attributes = new HashMap<>();
                        
                        List<Activity> activities;
                        activities= DatabaseDriver.getActivities(courseID);
                        attributes.put("activities", activities);
                        
			return new ModelAndView(attributes, "src/activityTable.html");
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
                
                get("/courses/:courseID/getActivitiesData", (request, response) -> {
                    int courseID = Integer.parseInt(request.params(":courseID"));
                    JSONArray activitiesList = new JSONArray();
                    
                    for(Activity i:DatabaseDriver.getActivities(courseID)){
                        JSONObject activity = new JSONObject();
                        activity.put("name",i.getName());
                        activity.put("date",i.getDate());
                        activity.put("time",i.getTime());
                        Course course = DatabaseDriver.getCourse(courseID);
                        activity.put("colour",course.getHexColour());
                        String[] durationParts = i.getDuration().split(":");
                        int hours = 0;
                        int minutes = 0;
                        for(String j:durationParts){
                            hours = Integer.parseInt(durationParts[0]);
                            minutes = Integer.parseInt(durationParts[1]);
                        }
                        int totalMinutes = hours*60+minutes;
                        activity.put("duration",totalMinutes/BLOCK_MINUTES);
                        activitiesList.add(activity);
                    }
                    
                    response.type("application/json");
                    return activitiesList.toJSONString();
                });
                
                get("/add_user", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        if(!request.session(true).attribute("userType").equals("admin")){
                            response.redirect("/notPermitted");
                            Spark.halt();
                        }
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
			return new ModelAndView(attributes, "src/add_user.html");
		}, new PebbleTemplateEngine());

                post("/add_user", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        if(!request.session(true).attribute("userType").equals("admin")){
                            response.redirect("/notPermitted");
                            Spark.halt();
                        }
                        
                        String firstName=request.queryParams("first-name");
                        String lastName=request.queryParams("last-name");
                        String email=request.queryParams("email");
                        String password=request.queryParams("password");
                        String userType=request.queryParams("user-type");
                        String nowDate = LocalDate.now().toString();
                        
                        if(userType.equals("admin")){
                            Admin user = new Admin(firstName, lastName, email, userType, password);
                            user.addToDB();
                        } else if(userType.equals("approvals")){
                            Approvals user = new Approvals(firstName, lastName, email, userType, password);
                            user.addToDB();
                        } else if(userType.equals("coordinator")){
                                    CourseCoordinator user = new CourseCoordinator(firstName, lastName, email, userType, password);
                                    user.addToDB();
                        } else if(userType.equals("casual")){
                            CasualStaffMember user = new CasualStaffMember(firstName, lastName, email, userType, password);
                            user.addToDB();
                        }
                        
                        
                        Map<String, Object> attributes = new HashMap<>();
                        response.redirect("/dashboard");
                        Spark.halt();
			return new ModelAndView(attributes, "src/add_user.html");
		}, new PebbleTemplateEngine());
                
                get("/requests", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                        
                        int userID = request.session(true).attribute("userID");
                        
                        ArrayList<StaffPlacement> sps = new ArrayList();
                        
                                for(StaffPlacement i:DatabaseDriver.getStaffPlacementsByUser(userID)){
                                        sps.add(i);
                                }
                        
                        ArrayList<Course> courses = new ArrayList();
                        
                        for(Course i:DatabaseDriver.getCourses()){
                            if(i.getCourseCoordinator()==userID){
                                courses.add(i); 
                            }
                        }
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                        attributes.put("courses", courses);
                        attributes.put("staffProposals", DatabaseDriver.getStaffProposals());
                        attributes.put("sps", sps);
                        
			return new ModelAndView(attributes, "src/requests.html");
		}, new PebbleTemplateEngine());
                
                get("/requests/proposals/:staffProposalID", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                        
                        
			return new ModelAndView(attributes, "src/request.html");
		}, new PebbleTemplateEngine());
                
                get("/requests/apply", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                        List<Course> courses;
                        courses= DatabaseDriver.getCourses();
                        attributes.put("courses", courses);     
                        return new ModelAndView(attributes, "src/apply.html");
		}, new PebbleTemplateEngine());
                
                post("/requests/apply", (request, response) -> {
                        String courseName = request.queryParams("course-name");
                        Course course = DatabaseDriver.getCourse(courseName);
                        String resume = null;
                        CourseApplication application = new CourseApplication(course.getCourseID(),request.session(true).attribute("userID"));
                        application.addToDB();
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                                              
                        response.redirect("/requests");
                        Spark.halt();
                        
			return new ModelAndView(attributes, "src/apply.html");
		}, new PebbleTemplateEngine());
                
                get("/requests/submitStaffProposal", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                        
                        
			return new ModelAndView(attributes, "src/request.html");
		}, new PebbleTemplateEngine());
                
                get("/requests/course/:courseID", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                    int courseID = Integer.parseInt(request.params(":courseID"));
                    
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("tasks",DatabaseDriver.getTasks(courseID));
                        attributes.put("activities",DatabaseDriver.getActivities(courseID));
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                        
                    attributes.put("applications", DatabaseDriver.getApplications(courseID));
                    
			return new ModelAndView(attributes, "src/viewApplications.html");
		}, new PebbleTemplateEngine());

                post("/requests/course/:courseID", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
			int courseID = Integer.parseInt(request.params(":courseID"));
                        Course course = DatabaseDriver.getCourse(courseID);
                                
                        Map<String, Object> attributes = new HashMap<>();
                        
                        StaffProposal staffProposal = new StaffProposal(courseID, course.getCourseName());
                        staffProposal.addToDB();
                        int proposalID = staffProposal.getProposalID();
                        
                        for(String i:request.queryParams()){
                            CourseApplication application = DatabaseDriver.getApplication(Integer.valueOf(i));
                            String key = i;
                            String val = request.queryParams(i);

                            if(val.equals("Reject")){
                                application.setOutcome(false);
                                application.updateInDB();
                            } else if(val.split("-")[0].equals("a")){
                                int activityID = DatabaseDriver.getActivity(Integer.valueOf(val.split("-")[1])).getActivityID();
                                application.setPending(false);
                                application.updateInDB();
                                StaffPlacement sp = new StaffPlacement(courseID, course.getCourseName(), proposalID, application.getApplicationID(), application.getUserID(), activityID, "a");
                                sp.setProposalID(proposalID);
                                sp.addToDB();
                            } else if(val.split("-")[0].equals("t")){
                                int taskID = DatabaseDriver.getTask(Integer.valueOf(val.split("-")[1])).getTaskID();
                                application.setPending(false);
                                application.updateInDB();
                                StaffPlacement sp = new StaffPlacement(courseID, course.getCourseName(), proposalID, application.getApplicationID(), application.getUserID(), taskID, "t");
                                sp.setProposalID(proposalID);
                                sp.addToDB();
                            }
                        }
                        
                        response.redirect("/requests");
                        Spark.halt();
                        
                        return new ModelAndView(attributes, "src/viewApplications.html");
		}, new PebbleTemplateEngine());
                
                get("/requests/:proposalID/viewStaffProposals", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        
                        
                    int proposalID = Integer.parseInt(request.params(":proposalID"));
                        
                        List<StaffPlacement> staffPlacements = DatabaseDriver.getStaffPlacements(proposalID);
                    
                        System.out.println(staffPlacements);
                        Map<String, Object> attributes = new HashMap<>();
                        
                        attributes.put("placements",staffPlacements);
                        
                        attributes.put("user", request.session(true).attribute("user"));
                        attributes.put("userType", request.session(true).attribute("userType"));
                    
                        
			return new ModelAndView(attributes, "src/viewPlacements.html");
		}, new PebbleTemplateEngine());

                post("/requests/:proposalID/viewStaffProposals", (request, response) -> {
                        if (request.session(true).attribute("user") == null){
                            response.redirect("/login");
                            Spark.halt();
                        }
                        int proposalID = Integer.parseInt(request.params(":proposalID"));
                    
                        StaffProposal proposal = DatabaseDriver.getStaffProposal(proposalID);
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
                        for(String i:request.queryParams()){
                            String key = i;
                            String val = request.queryParams(i);
                            System.out.println(i + "," + request.queryParams(i));
                            
                            StaffPlacement placement = DatabaseDriver.getStaffPlacement(Integer.parseInt(key));
                            CourseApplication application = DatabaseDriver.getApplication(placement.getApplicationID());
                            
                            if(val.equals("Reject")){
                                application.setOutcome(false);
                                application.setPending(true);
                                application.updateInDB();
                                placement.setPending(false);
                                placement.setApproved(false);
                                placement.updateInDB();
                            } else if(val.equals("Approve")){
                                application.setOutcome(true);
                                application.setPending(false);
                                application.updateInDB();
                                placement.setPending(false);
                                placement.setApproved(true);
                                placement.updateInDB();
                            } 
                        }
                        
                        proposal.setPending(false);
                        proposal.updateInDB();
                        
                        return new ModelAndView(attributes, "src/viewPlacements.html");
		}, new PebbleTemplateEngine());
                
                get("/notPermitted", (request, response) -> {
                        
                        Map<String, Object> attributes = new HashMap<>();
                        
			return new ModelAndView(attributes, "src/notPermitted.html");
		}, new PebbleTemplateEngine());
                
                get("/logout", (request, response) -> {
                    request.session(true).removeAttribute("user");
                    response.redirect("/login");
                    Spark.halt();
                    return null;
		}, new PebbleTemplateEngine());
                
	}
}