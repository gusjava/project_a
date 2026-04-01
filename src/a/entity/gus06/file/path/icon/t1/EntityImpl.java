package a.entity.gus06.file.path.icon.t1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250612";}


	private Service getExtension;
	private Service extToIcon;
	
	public EntityImpl() throws Exception
	{
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
		extToIcon = Outside.service(this,"gus06.file.ext.icon.t1");
	}

	public Object t(Object obj) throws Exception
	{
		String ext = (String) getExtension.t(obj);
		return extToIcon.t(ext);
	}
}