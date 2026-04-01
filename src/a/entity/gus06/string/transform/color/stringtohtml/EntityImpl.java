package a.entity.gus06.string.transform.color.stringtohtml;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191231";}


	private Service stringToColor;
	private Service colorToStringHtml;
	
	public EntityImpl() throws Exception
	{
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor");
		colorToStringHtml = Outside.service(this,"gus06.convert.stringtocolor.html");
	}

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return colorToStringHtml.t(stringToColor.t(s));
	}
}
