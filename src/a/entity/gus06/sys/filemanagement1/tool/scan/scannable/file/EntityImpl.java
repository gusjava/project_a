package a.entity.gus06.sys.filemanagement1.tool.scan.scannable.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201007";}
	
	public static final long LIMIT_SIZE = 100000;
	public static final String KEY_SCAN_FILE_LIMITSIZE = "scan.file.limitsize";
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		File file = (File) o[1];
		
		Long limitSize = (Long) ((R) engine).r("config:"+KEY_SCAN_FILE_LIMITSIZE);
		long limit = limitSize!=null ? limitSize : LIMIT_SIZE;
		
		return file.isFile() && file.length()>=limit;
	}
}