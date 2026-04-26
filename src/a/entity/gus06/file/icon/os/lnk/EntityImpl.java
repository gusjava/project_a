package a.entity.gus06.file.icon.os.lnk;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201205";}


	private Service extractPath;
	private Service iconOs;

	public EntityImpl() throws Exception
	{
		extractPath = Outside.service(this,"gus06.file.lnk.extract.path");
		iconOs = Outside.service(this,"gus.x.file.icon.os");
	}

	public Object t(Object obj) throws Exception
	{
		File f = (File) extractPath.t(obj);
		return iconOs.t(f);
	}
}