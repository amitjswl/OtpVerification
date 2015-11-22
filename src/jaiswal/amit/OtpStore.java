package jaiswal.amit;

import java.util.HashMap;

public class OtpStore {
	
	static HashMap otpMap=new HashMap();
	
	
	public static String getOtp(String PhoneNumber)
	{
		System.out.println("got otp from db as "+otpMap.get(PhoneNumber));
		return ( otpMap.get(PhoneNumber).toString());
		
	}
	
	public static void setOtp(String phoneNumber,Long otp){
		
		System.out.println("taking number as "+ phoneNumber+" otp as "+otp);
		otpMap.put(phoneNumber,otp);
	
	}
}
