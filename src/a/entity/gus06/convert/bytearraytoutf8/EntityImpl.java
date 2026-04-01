package a.entity.gus06.convert.bytearraytoutf8;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191007";}

	public final static Charset CHARSET = Charset.forName("UTF-8");
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return new String((byte[]) obj,CHARSET);
	}
}
