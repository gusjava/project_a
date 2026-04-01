package a.entity.gus06.file.read.string.set;

import a.framework.*;
import java.util.List;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160804";}


	private Service read;


	public EntityImpl() throws Exception
	{read = Outside.service(this,"gus06.file.read.string.list");}
	
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) read.t(obj);
		return new HashSet(list);
	}
}
