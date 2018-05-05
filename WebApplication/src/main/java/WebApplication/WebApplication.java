package WebApplication;

import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.*;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.temporal.*;



/**
 * @author Nikki
 */
public class WebApplication {

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
                    	Map<String, Object> attributes = new HashMap<>();
                        attributes.put("User", request.session().attribute("user"));
			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/dashboard.html");
                }, new PebbleTemplateEngine());
                
                
                
                get("/timetable/:course", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
                        
                        String courseID = request.params("course");
                        

                        LocalDate startOfWeek = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.MONDAY ) );
                        LocalDate endOfWeek = LocalDate.now().with( TemporalAdjusters.previous( DayOfWeek.SUNDAY ) );
                        LocalDate[7] weekDays = new ArrayList();

                        CasualHRSystem.Course.Course course = CasualHRSystem.DatabaseDriver.getCourse(Integer.valueOf(courseID));
                        
                        System.out.println(course);

			// The hello.pebble file is located in directory:
			// src/test/resources/spark/template/pebble
			return new ModelAndView(attributes, "src/timetable.html");
		}, new PebbleTemplateEngine());
                
	}
}