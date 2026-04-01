package a.entity.gus06.time.date.int3.is.today;

import a.framework.*;
import java.util.Date;
import java.util.Calendar;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160616";}
	

	
	public boolean f(Object obj) throws Exception
	{
		int[] n = (int[]) obj;
		if(n.length!=3) throw new Exception("Invalid int array length: "+n.length);
		
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return n[0]==year && n[1]==month && n[2]==day;
	}
}
