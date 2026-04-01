package a.entity.gus06.io.generate.pipedinputoutput;

import a.framework.*;
import java.io.PipedOutputStream;
import java.io.PipedInputStream;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180403";}
	
	
	public Object g() throws Exception
	{
		PipedOutputStream pos = new PipedOutputStream();
		PipedInputStream pis = new PipedInputStream(pos);
		
		return new Object[]{pos,pis};
	}
}
