package a.entity.gus06.sys.expression1.apply.op._return_key_pressed;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161127";}
	
	public static final String T = "constant";


	private Service buffer;
	
	public EntityImpl() throws Exception
	{
		buffer = Outside.service(this,"gus06.jna.keyboard.buffer");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return returnKey();
		if(obj instanceof String) return returnKey((String) obj);
		return returnKey();
	}
	
	
	
	private String returnKey() throws Exception
	{
		String key = getLastKeyPressed();
		String key0 = key;
		
		while(equals(key,key0))
		{
			sleep_5();
			key0 = getLastKeyPressed();
		}
		return key0;
	}
	
	
	private String returnKey(String in) throws Exception
	{
		String[] nn = in.split(";");
		
		String key = getLastKeyPressed();
		String key0 = key;
		
		while(equals(key,key0) || !contains(nn,key0))
		{
			sleep_5();
			key0 = getLastKeyPressed();
		}
		return key0;
	}
	
	
	private String getLastKeyPressed() throws Exception
	{
		String key = (String) buffer.r("lastKey");
		if(key==null) return null;
		if(!key.startsWith("+")) return null;
		return key.substring(1);
	}
	
	
	
	private void sleep_5()
	{
		try{Thread.sleep(5);}
		catch(Exception e)
		{Outside.err(this,"sleep_5()",e);}
	}
	
	private boolean equals(String s1, String s2)
	{
		if(s1==null && s2==null) return true;
		if(s1==null || s2==null) return false;
		return s1.equals(s2);
	}
	
	private boolean contains(String[] nn, String key)
	{
		for(String n:nn) if(n.equals(key)) return true;
		return false;
	}
}
