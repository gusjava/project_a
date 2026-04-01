package a.entity.gus06.file.filename.icon.t1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}


	private Service iconProvider;
	private Service getExtension;
	
	public EntityImpl() throws Exception
	{
		iconProvider = Outside.service(this,"gus06.file.ext.icon.t1");
		getExtension = Outside.service(this,"gus06.file.getextension.lowercase");
	}

	public Object t(Object obj) throws Exception
	{
		String fileName = (String) obj;
		String ext = (String) getExtension.t(fileName);
		return iconProvider.t(ext);
	}
}
