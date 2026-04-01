package a.entity.gus06.file.rar.findentries;

import a.framework.*;
import java.util.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200305";}


	private Service buildHolder;
	
	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus06.zzz.file.rar.innosystec.holder");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		G holder = (G) buildHolder.t(file);
		Object list = holder.g();
		((E)holder).e();
		return list;
	}
}
