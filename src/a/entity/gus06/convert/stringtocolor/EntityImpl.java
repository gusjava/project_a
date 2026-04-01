package a.entity.gus06.convert.stringtocolor;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140729";}


	private Service htmlToColor;
	private Service nameToColor;
	private Service rgbToColor;
	private Service random;

	public EntityImpl() throws Exception
	{
		htmlToColor = Outside.service(this,"gus06.convert.stringtocolor.html");
		nameToColor = Outside.service(this,"gus06.convert.stringtocolor.name");
		rgbToColor = Outside.service(this,"gus06.convert.stringtocolor.rgb");
		random = Outside.service(this,"gus06.awt.color.random");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		if(s.equals("")) return null;
		if(s.equals("random")) return random.g();
		
		Color c1 = (Color) htmlToColor.t(s);
		if(c1!=null) return c1;
        
		Color c2 = (Color) nameToColor.t(s);
		if(c2!=null) return c2;
        
		Color c3 = (Color) rgbToColor.t(s);
		if(c3!=null) return c3;
        
		return null;
	}
}
