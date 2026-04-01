package a.entity.gus06.convert.bytearraytoint;

import a.framework.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191007";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		ByteArrayInputStream baos = new ByteArrayInputStream((byte[]) obj);
		DataInputStream dos = new DataInputStream(baos);
		int result = dos.readInt();
		dos.close();
		
		return result;
	}
}
