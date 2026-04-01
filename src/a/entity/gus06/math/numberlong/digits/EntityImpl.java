package a.entity.gus06.math.numberlong.digits;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231119";}

	
	public Object t(Object obj) throws Exception
	{
		Long l = Long.parseLong(""+obj);
		String s = ""+l;
		int len = s.length();
		
		int[] digits = new int[len];
		for(int i=0;i<len;i++) digits[i] = (int) s.charAt(i) - 48;
		return digits;
	}
}