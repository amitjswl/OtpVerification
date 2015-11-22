package jaiswal.amit;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Path("/gen")
public class GenerateOtp{
	

	@Produces(MediaType.TEXT_HTML)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public Response generate(@FormParam("phoneNum") String phoneNum){
		
		try{ 
	System.out.println("generate otp request recieved for "+phoneNum);

	String url="http://www.exploreprogramming.com/android_sms/request_sms.php?name=xyz&email=abc@gmail.com&mobile=";
	url=url+phoneNum;
   
	//String jsonResponse=GenerateOtp.excuteGET(url);

	//System.out.println("url response is "+ jsonResponse);
	
	//JSONArray arr=new JSONArray(jsonResponse);
	String testString=new String("{\"error\":false,\"message\":\"SMS request is initiated! You will be receiving it shortly.\",\"otp\":904721}" );
	
	
	JSONParser parser = new JSONParser();
	Object obj = parser.parse(testString);

	JSONObject jsonObject = (JSONObject) obj;

	Long otp =  (Long)jsonObject.get("otp");
	System.out.println(otp);
	
	OtpStore.setOtp(phoneNum,otp);
	
	
	return(Response.ok().entity(" <!DOCTYPE html><html><head><title>example</title><script>function init()"
           +"{window.location.href=\"../vldt.html\";} </script></head>"+
			"<body onload=\"init()\"></body> </html>").build());
		}
		catch(Exception e){
			e.printStackTrace();
			return(Response.status(400).entity("some error occured").build());
		}

	}
	
	 static String excuteGET(String targetURL) {
		  HttpURLConnection connection = null;  
		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");
		    
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		    connection.getOutputStream());
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    
		    while((line = rd.readLine()) != null) {
		      response.append(line);
//		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
		}

} 
	


