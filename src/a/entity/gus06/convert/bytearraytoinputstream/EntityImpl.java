package a.entity.gus06.convert.bytearraytoinputstream;

import a.framework.*;
import java.io.ByteArrayInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220616";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return new ByteArrayInputStream((byte[]) obj);
	}
}
