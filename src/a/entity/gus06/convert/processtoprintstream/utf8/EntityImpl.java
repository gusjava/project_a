package a.entity.gus06.convert.processtoprintstream.utf8;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191108";}

	public static final String CHARSET = "UTF-8";

	
	public Object t(Object obj) throws Exception
	{
		Process p = (Process) obj;
		OutputStream os = p.getOutputStream();
		return new PrintStream(os,false,CHARSET);
	}
}
