package a.entity.gus06.data.perform.splitreg1;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190519";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String regex = (String) o[1];
		
		if(input instanceof String)
		return rebuild(split((String) input,regex));
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String[] split(String s, String regex)
	{
		return s.split(regex,-1);
	}
	
	
	
	private String[] rebuild(String[] nn)
	{
		int k = 0;
		for(String n:nn) if(empty(n)) k++;
		int len = nn.length-k;
		
		String[] aa = new String[len];
		
		k = 0;
		for(int i=0;i<len;i++)
		{
			while(empty(nn[i+k])) k++;
			aa[i] = nn[i+k];
		}
		return aa;
	}
	
	private boolean empty(Object s)
	{return s==null || s.equals("");}
}
