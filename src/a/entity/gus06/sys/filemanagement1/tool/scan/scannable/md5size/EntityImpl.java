package a.entity.gus06.sys.filemanagement1.tool.scan.scannable.md5size;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20201125";}
	
	public static final long LIMIT_SIZE = 100000;
	public static final String FAILED_MD5 = "###";
	public static final String KEY_SCAN_FILE_LIMITSIZE = "scan.file.limitsize";
	
	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String md5 = (String) o[1];
		String size = (String) o[2];
			
		if(md5.equals(FAILED_MD5)) return false;
		if(size.equals("")) return false;
		
		Long limitSize = (Long) ((R) engine).r("config:"+KEY_SCAN_FILE_LIMITSIZE);
		long limit = limitSize!=null ? limitSize : LIMIT_SIZE;
		
		return Long.valueOf(size)>=limit;
	}
}