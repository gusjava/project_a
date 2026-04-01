package a.entity.gus06.appli.laboscript.gui.consolegui.format;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160624";}


	private Service format;


	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.string.transform.format.brackets.curly");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.startsWith("{")) return s;
		return "{"+format(s)+"}";
	}
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
