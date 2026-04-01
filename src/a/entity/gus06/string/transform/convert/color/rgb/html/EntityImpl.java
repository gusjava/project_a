package a.entity.gus06.string.transform.convert.color.rgb.html;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210401";}


	private Service stringToColor;
	private Service colorToString;


	public EntityImpl() throws Exception
	{
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor.rgb");
		colorToString = Outside.service(this,"gus06.convert.colortostring.html");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return colorToString.t(stringToColor.t(s));
	}
}