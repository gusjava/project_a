package a.entity.gus06.file.read.ico.asimage;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191217";}

	private Service read;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.ico");
	}

	public Object t(Object obj) throws Exception
	{
		List images = (List) read.t(obj);
		if(images==null) return null;
		return images.get(0);
	}
}
