package a.entity.gus06.convert.stringtoobject.convertor1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140729";}

	private Service stringToBorder;
	private Service stringToColor;
	private Service stringToFont;
	private Service stringToIcon;
	private Service stringToKeystroke;
	private Service stringToString;
	private Service stringToFile;
	private Service stringToUrl;
	private Service stringToHexa;
	private Service stringToBase64;
	private Service stringToDate;
	private Service stringToEntity;

	public EntityImpl() throws Exception
	{
		stringToBorder = Outside.service(this,"gus06.convert.stringtoborder");
		stringToColor = Outside.service(this,"gus06.convert.stringtocolor");
		stringToFont = Outside.service(this,"gus06.convert.stringtofont");
		stringToIcon = Outside.service(this,"gus06.convert.stringtoicon");
		stringToKeystroke = Outside.service(this,"gus06.convert.stringtokeystroke");
		stringToString = Outside.service(this,"gus06.convert.stringtostring");
		stringToFile = Outside.service(this,"gus06.convert.stringtofile");
		stringToUrl = Outside.service(this,"gus.y.convert1.stringtourl");
		stringToHexa = Outside.service(this,"gus06.convert.stringtobytearray.hexa");
		stringToBase64 = Outside.service(this,"gus06.convert.stringtobytearray.base64");
		stringToDate = Outside.service(this,"gus06.convert.stringtodate");
		stringToEntity = Outside.service(this,"gus06.convert.stringtoentity");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String)obj;
		String[] n = s.split("#",2);
		return find(n[0]).t(n[1]);
	}
	
	private T find(String s) throws Exception
	{
		if(s.equals("border")) return stringToBorder;
		if(s.equals("color")) return stringToColor;
		if(s.equals("font")) return stringToFont;
		if(s.equals("icon")) return stringToIcon;
		if(s.equals("keystroke")) return stringToKeystroke;
		if(s.equals("string")) return stringToString;
		if(s.equals("file")) return stringToFile;
		if(s.equals("url")) return stringToUrl;
		if(s.equals("hexa")) return stringToHexa;
		if(s.equals("base64")) return stringToBase64;
		if(s.equals("date")) return stringToDate;
		if(s.equals("entity")) return stringToEntity;
		
		throw new Exception("Unknown convertor type: "+s);
	}
}
