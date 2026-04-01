package a.entity.gus06.time.calendar.buildmonthdata;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160616";}


	private Service isValid;
	private Service dayOfWeek;


	public EntityImpl() throws Exception
	{
		isValid = Outside.service(this,"gus06.time.date.int3.is.valid");
		dayOfWeek = Outside.service(this,"gus06.time.date.int3.dayofweek");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length<2) throw new Exception("Invalid data length: "+n.length);
		
		int year = n[0];
		int month = n[1];
		
		int[][] data = new int[6][7];
		for(int i=0;i<6;i++) for(int j=0;j<7;j++)
		data[i][j] = -1;
		
		int day = 1;
		int row = 0;
		
		while(isValid(year,month,day))
		{
			int dayOfWeek = dayOfWeek(year,month,day).intValue();
			data[row][dayOfWeek] = day;
			
			if(dayOfWeek==6) row++;
			day++;
		}
		return data;
	}
	
	
	
	private Integer dayOfWeek(int year, int month, int day) throws Exception
	{return (Integer) dayOfWeek.t(new int[]{year,month,day});}
	
	private boolean isValid(int year, int month, int day) throws Exception
	{return isValid.f(new int[]{year,month,day});}
}
