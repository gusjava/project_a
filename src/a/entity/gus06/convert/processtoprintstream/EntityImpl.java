package a.entity.gus06.convert.processtoprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191108";}

	
	public Object t(Object obj) throws Exception
	{
		Process p = (Process) obj;
		OutputStream os = p.getOutputStream();
		return new PrintStream(os);
	}
}
