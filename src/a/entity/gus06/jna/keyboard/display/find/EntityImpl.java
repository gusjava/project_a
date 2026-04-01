package a.entity.gus06.jna.keyboard.display.find;

import a.framework.*;
import java.lang.reflect.Field;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160418";}

	
	
	public Object t(Object obj) throws Exception
	{
		return display((String) obj);
	}
	
	
	private String display(String code) throws Exception
	{
		if(code==null) return null;
		String d = display_(code);
		if(d==null) return null;
		return d.replace("CONTROL","CTRL");
	}
	
	private String display_(String code) throws Exception
	{
		Field[] f = KeyEvent.class.getFields();
		
		for(int i=0;i<f.length;i++)
		if(f[i].get(null).toString().equals(code))
			return f[i].getName().replace("VK_","");
			
		return null;
	}
}
