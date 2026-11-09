package a.entity.gus06.file.rar.findentries;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200305";}


	private Service listEntries;

	public EntityImpl() throws Exception
	{
		listEntries = Outside.service(this,"gus06.sys.unrar1.listentries");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		return listEntries.t(file);
	}
}
