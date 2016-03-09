package utilities;

import java.sql.Date;

public class DateHelper {

	public DateHelper(){
		
	}
	
	public static int diffInDays(Date d1, Date d2){
		return (int)((d2.getTime() - d1.getTime()) / (1000*60*60*24));
	}
	
}
