package a.entity.gus06.sys.filemanagement1.tool.scan.file.find;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String rootName = (String) o[1];
		String timeStamp = (String) o[2];
		
		File dir0 = (File) ((R) engine).r("dirScans");
		
		File dir1 = new File(dir0,rootName);
		if(!dir1.isDirectory()) throw new Exception("Scan dir not found for root: name="+rootName);
		
		File file = new File(dir1,timeStamp+".txt");
		if(!file.isFile()) throw new Exception("Scan file not found for root: name="+rootName+" & timeStamp="+timeStamp);
		
		return file;
	}
}
