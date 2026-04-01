package a.entity.gus06.sys.filemanagement1.tool.scan.file.latest;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191213";}


	private Service findPrevious;

	public EntityImpl() throws Exception
	{
		findPrevious = Outside.service(this,"gus06.sys.filemanagement1.scan.previous.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String rootName = (String) o[1];
		
		File dir0 = (File) ((R) engine).r("dirScans");
		File dir1 = new File(dir0,rootName);
		
		if(!dir1.isDirectory()) throw new Exception("Scan dir not found for root: name="+rootName);
		return findPrevious.t(dir1);
	}
}
