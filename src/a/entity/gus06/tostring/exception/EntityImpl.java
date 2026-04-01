package a.entity.gus06.tostring.exception;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20240322";}


	private Service find;
	private Service steToString;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.stacktrace");
		steToString = Outside.service(this,"gus06.tostring.stacktraceelement");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		StackTraceElement[] ste_ = (StackTraceElement[]) find.t(obj);
		
		StringBuilder sb = new StringBuilder();
		for(StackTraceElement ste : ste_)
		{
			String line = (String) steToString.t(ste);
			sb.append(line+"\n");
		}
		if(sb.length()>0) sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
