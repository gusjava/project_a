package a.entity.gus06.file.info.string.charset.asstring;

import a.framework.*;
import java.nio.charset.Charset;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151006";}
	
	private Service check;
	private Service info;
	
	
	public EntityImpl() throws Exception
	{
		check = Outside.service(this,"gus06.file.string.check");
		info = Outside.service(this,"gus06.file.string.info.charset");
	}


	public Object t(Object obj) throws Exception
	{
		if(!check.f(obj)) return "";
		Charset charset = (Charset) info.t(obj);
		return charset.name();
	}
}
