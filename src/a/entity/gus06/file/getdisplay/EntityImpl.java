package a.entity.gus06.file.getdisplay;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200312";}


	private Service getExt;

	public EntityImpl() throws Exception
	{
		getExt = Outside.service(this,"gus06.file.getextension.lowercase");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null) return "";
		if(!file.exists()) return file.getName();
		if(file.isDirectory()) return "DIR#"+file.getName();
		
		String ext = (String) getExt.t(file);
		if(ext==null || ext.equals("")) return "FILE#"+file.getName();
		return "FILE_"+ext+"#"+file.getName();
	}
}