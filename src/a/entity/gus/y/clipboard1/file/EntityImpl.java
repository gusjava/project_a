package a.entity.gus.y.clipboard1.file;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240121";}

	private Service clipboard;
	
	public EntityImpl() throws Exception
	{clipboard = Outside.service(this,"gus.x.clipboard.files");}

	public Object g() throws Exception
	{
		List list = (List) clipboard.g();
		if(list==null || list.isEmpty()) return null;
		return list.get(0);
	}
}
