package a.entity.gus06.convert.sockettoprintstream;

import a.framework.*;
import java.io.PrintStream;
import java.net.Socket;
import java.io.OutputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191108";}

	
	public Object t(Object obj) throws Exception
	{
		Socket s = (Socket) obj;
		OutputStream os = s.getOutputStream();
		return new PrintStream(os);
	}
}
