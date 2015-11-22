package jaiswal.amit;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/validate")
public class ValidateOtp {
	
	@Produces(MediaType.TEXT_HTML)
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	public String validate(@FormParam("phoneNum") String phoneNum,@FormParam("otp") String otp)
	{
		System.out.println("phone nember "+ phoneNum);
		String aotp = OtpStore.getOtp(phoneNum);
		System.out.print("got otp from db as "+ aotp);
		System.out.print("got otp from form as "+ otp);

		
		//Long itop=Long.parseLong(phoneNum);
		if(otp.equals(aotp)){
			return("OTP VERIFIED SUCCESSFULLLY \n");
		}
		else{
			return("otp Verification FAILED \n");

		}
			
	}

}
