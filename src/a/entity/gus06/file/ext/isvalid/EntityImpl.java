package a.entity.gus06.file.ext.isvalid;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20180410";}


	private Service getExt;
	private Service inferExt;


	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
		inferExt = Outside.service(this,"gus06.file.ext.infer");
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		String ext1 = (String) getExt.t(file);
		String ext2 = (String) inferExt.t(file);
		
		return ext1.equals(ext2);
	}
}
