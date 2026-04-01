package a.entity.gus06.sys.filesdico1.buildline;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150825";}
	

	private Service buildMd5;
	private Service buildDate;
	
	public EntityImpl() throws Exception
	{
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");
		buildDate = Outside.service(this,"gus06.file.lastmodified.timestamp.s");
	}

	
	public Object t(Object obj) throws Exception
	{
		File f = (File) obj;
		if(!f.exists()) return null;
		
		String md5 = (String) buildMd5.t(f);
		String date = (String) buildDate.t(f);
		String size = ""+f.length();
		String location = f.getParentFile().getAbsolutePath();
		String name = f.getName();
		
		return md5+"\t"+date+"\t"+size+"\t"+location+"\t"+name;
	}
}