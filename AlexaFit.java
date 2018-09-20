package wiseguy;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class AlexaFit {
	//AlexaDao dao = new AlexaDao();
    public AlexaFit() {
        super();
    }
    private static String apiUrl =
        "https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate";
    private static String calendarEventUpdateUrl="https://www.googleapis.com/calendar/v3/calendars/calendarId/events";
    private static final ArrayList<String> ACTIVITY_LIST = new ArrayList<String>();
    
    static{
    	ACTIVITY_LIST.add("5K Run");
    	ACTIVITY_LIST.add("Swimming");
    	ACTIVITY_LIST.add("Cycling");
    	ACTIVITY_LIST.add("30 minutes Walking");
    	ACTIVITY_LIST.add("Jogging");
    	ACTIVITY_LIST.add("Trekking");
    	ACTIVITY_LIST.add("Badminton or any sport");
    }
    
    public boolean updateCalendar(String email, String token){
    	try{
    		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	       
    	Calendar calendar = Calendar.getInstance();
    	int weekday = calendar.get(Calendar.DAY_OF_WEEK);
    	int days = Calendar.SUNDAY - weekday;
    	if (days <= 0)
    	{
    	    
    	    days += 7;
    	}
    	
    	calendar.add(Calendar.DAY_OF_YEAR, days);
         
    	 String startDateString = formatter.format(calendar.getTime());
    	 calendarPostEvent(calendar.getTime(), calendar.getTime(), token);
    	 return true;
    	 /*
    	if(!dao.getEventDate(email,startDateString)){ 
         calendarPostEvent(calendar.getTime(), calendar.getTime(), token);
         dao.insertEvent(email, startDateString);
         return true;
    	}else{
    		return false;
    	}
    	*/
    	}catch(Exception e){
    		throw new RuntimeException(e.getMessage());
    	}

    }
    
  

    public  String getWeeklyData(String token) {
        Date endDate = new Date();
        Long endL = endDate.getTime();
        Long startL = endL - 604800000;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 604800000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String weekData = sendPostRequest(apiUrl, payload, token);
       return parseJsonTogetStepCount(weekData);

    }

    
   
    public  String getDayData(String token) {
        Date endDate = new Date();
        Long endL = endDate.getTime();
        Long startL = endL - 86400000;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 86400000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String dayData = sendPostRequest(apiUrl, payload, token);
        return parseJsonTogetStepCount(dayData);
    }
    
    public String getCurrentDayData(String token) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        long startL = c.getTimeInMillis(); 
        Calendar end = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
        long endL = end.getTimeInMillis();
        long bucketTime=endL-startL;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": "+bucketTime+" },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String dayData = sendPostRequest(apiUrl, payload, token);
       return  parseJsonTogetStepCount(dayData);
    }
    
    /*
    public String getCurrentDayData(String token) {
        Date today = new Date(); 
        long endL = today.getTime(); 
        Calendar cal = Calendar.getInstance();
        //Get milliseconds since start of today 
        long milisSinceMidnight = (cal.get(Calendar.HOUR_OF_DAY)*60*60*1000) + (cal.get(Calendar.MINUTE)*60*1000) + (cal.get(Calendar.SECOND)*1000) + (cal.get(Calendar.MILLISECOND));  
         
        //Calculate the difference 
        long startL = (endL - milisSinceMidnight); 
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 86400000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String dayData = sendPostRequest(apiUrl, payload, token);
        return parseJsonTogetStepCount(dayData);
    } 
    */
    public String getLastDayData(String token) {
        Date today = new Date(); 
        long endL = today.getTime(); 
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
            
        //Get milliseconds since start of today 
        long milisSinceMidnight = (cal.get(Calendar.HOUR_OF_DAY)*60*60*1000) + (cal.get(Calendar.MINUTE)*60*1000) + (cal.get(Calendar.SECOND)*1000) + (cal.get(Calendar.MILLISECOND));  
         
         endL = (endL - milisSinceMidnight); 
        
       long startL=endL-86400000;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 86400000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String dayData = sendPostRequest(apiUrl, payload, token);
        return parseJsonTogetStepCount(dayData);
    } 
    
    public String getWeeklyCaloryBurntData(String token) {
        Date endDate = new Date();
        Long endL = endDate.getTime();
        Long startL = endL - 604800000;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.calories.expended:com.google.android.gms:platform_calories_expended\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 604800000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String weekData = sendPostRequest(apiUrl, payload, token);
       return parseJsonTogetCaloryCount(weekData);
    }

    public String getDailyCaloryBurntData(String token) {
        Date endDate = new Date();
        Long endL = endDate.getTime();
        Long startL = endL - 86400000;
        String payload = "{\"aggregateBy\": [{\n" +
            "    \"dataTypeName\": \"com.google.step_count.delta\",\n" +
            "    \"dataSourceId\": \"derived:com.google.calories.expended:com.google.android.gms:platform_calories_expended\"\n" +
            "  }],\n" +
            "  \"bucketByTime\": { \"durationMillis\": 86400000 },\n" +
            "  \"startTimeMillis\": " + startL + ",\n" +
            "  \"endTimeMillis\": " + endL + "}";
        String dayData = sendPostRequest(apiUrl, payload, token);
        return parseJsonTogetCaloryCount(dayData);
    }

    public static String sendPostRequest(String requestUrl, String payload, String gtoken) {
        StringBuffer jsonString;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection =
                (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type",
                                          "application/json; charset=UTF-8");
            String token =
                "Bearer "+gtoken;
            connection.setRequestProperty("Authorization", token);
            OutputStreamWriter writer =
                new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write(payload);
            writer.close();
            BufferedReader br =
                new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonString = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
            System.out.println(jsonString);

        } catch (Exception e) {
            return "Data not available. Please connect or download google fit app";
        }
        return jsonString.toString();
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public static String parseJsonTogetStepCount(String jsonString) {
    	try{
        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jsonObjects = jelement.getAsJsonObject();
        JsonArray bucketArray = jsonObjects.getAsJsonArray("bucket");
        jsonObjects = bucketArray.get(0).getAsJsonObject();
        JsonArray datasetArray = jsonObjects.getAsJsonArray("dataset");
        JsonArray pointArray =
            datasetArray.get(0).getAsJsonObject().getAsJsonArray("point");
        JsonArray valueArray =
            pointArray.get(0).getAsJsonObject().getAsJsonArray("value");
        jsonObjects = valueArray.get(0).getAsJsonObject();
        String stepCount = jsonObjects.get("intVal").getAsString();
        return stepCount;
    	}catch(Exception e){
    		return "Data not available. Please connect or download google fit app";
    	}
    }
    
    public static String parseJsonTogetCaloryCount(String jsonString) {
    	try{
        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jsonObjects = jelement.getAsJsonObject();
        JsonArray bucketArray = jsonObjects.getAsJsonArray("bucket");
        jsonObjects = bucketArray.get(0).getAsJsonObject();
        JsonArray datasetArray = jsonObjects.getAsJsonArray("dataset");
        JsonArray pointArray =
            datasetArray.get(0).getAsJsonObject().getAsJsonArray("point");
        JsonArray valueArray =
            pointArray.get(0).getAsJsonObject().getAsJsonArray("value");
        jsonObjects = valueArray.get(0).getAsJsonObject();
        String stepCount = jsonObjects.get("fpVal").getAsString();
        return stepCount;
    	}catch(Exception e){
    		return "Data not available. Please connect or download google fit app";
    	}
    }
    
    public static String parseJsonTogetEvent(String jsonString){
    	JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jsonObjects = jelement.getAsJsonObject();
        String value = jsonObjects.get("id").getAsString();
        return value;
    }
    
    public static void calendarPostEvent(Date startDate,Date endDate, String token){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String startDateString = formatter.format(startDate);
        String endDateString=formatter.format(endDate);
     
        int activeID = (int) Math.floor(Math.random() * ACTIVITY_LIST.size());
        
        calendarEventUpdateUrl=calendarEventUpdateUrl+"?calendarId"+"=primary"+"&sendNotifications"+"=true"+"&supportsAttachments"+"=false";
        String requestBody="{\n" + 
        "          \"end\": {\n" + 
        "            \"date\": \""+endDateString+"\",\n" + 
        "            \"timeZone\": \"America/Los_Angeles\"\n" + 
        "          },\n" + 
        "          \"start\": {\n" + 
        "            \"date\": \""+startDateString+"\",\n" + 
        "            \"timeZone\": \"America/Los_Angeles\"\n" + 
        "          },\n" + 
        "          \"description\": \"To reach your target weight, Alexa fit has updated your calendar\",\n" + 
        "          \"summary\": \""+ACTIVITY_LIST.get(activeID)+" this Sunday\"\n" + 
        "        }";
        sendPostRequest(calendarEventUpdateUrl, requestBody, token);
    }

    public static void setCalendarEventUpdateUrl(String calendarEventUpdateUrl) {
        AlexaFit.calendarEventUpdateUrl = calendarEventUpdateUrl;
    }

    public static String getCalendarEventUpdateUrl() {
        return calendarEventUpdateUrl;
    }
}
