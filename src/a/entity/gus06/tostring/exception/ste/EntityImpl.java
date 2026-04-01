package a.entity.gus06.tostring.exception.ste;

import a.framework.*;
import java.io.StringWriter;
import java.io.PrintWriter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221117";}
	
	public Object t(Object obj) throws Exception
	{
		Exception e = (Exception) obj;
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String s = sw.toString();
		pw.close();
		
		return s;
	}
}
