package a.entity.gus06.app.persister1.data.color;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, V, R {

	public String creationDate() {return "20141123";}


	private Service persister;


	public EntityImpl() throws Exception
	{persister = Outside.service(this,"gus06.app.persister1");}
	
	
	
	public Object r(String key) throws Exception
	{return stringToColor((String) persister.r(key));}
	
	
	public void v(String key, Object obj) throws Exception
	{persister.v(key,colorToString((Color) obj));}
	
	
	
	private String colorToString(Color c)
	{
		if(c==null) return null;
		return ""+c.getRGB();
	}
	
	
	private Color stringToColor(String s)
	{
		try
		{
			if(s==null) return null;
			int rgb = Integer.parseInt(s);
			return new Color(rgb);
		}
		catch(Exception e) {return null;}
	}
		
}
