package a.entity.gus06.sys.filemanagement1.scan.build.row;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191128";}
	
	public static final String FAILED_MD5 = "###";
	

	private Service getMd5;
	private Service getMimeType;
	private Service getModified;
	
	public EntityImpl() throws Exception
	{
		getMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa.s");
		getMimeType = Outside.service(this,"gus06.file.mime.tika.detect.asstring.s");
		getModified = Outside.service(this,"gus06.file.lastmodifiedtime.timestamp.s");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) o[0];
		Integer offset = (Integer) o[1];
		Map previousMap = (Map) o[2];
		Integer index = (Integer) o[3];
		
		if(!f.isFile()) throw new Exception("File not found: "+f);
		
		
		String location = f.getParentFile().getAbsolutePath().substring(offset);
		String name = f.getName();
		String size = ""+f.length();
		String modified = (String) getModified.t(f);
		
		String previousKey = location+"\t"+name;
		if(previousMap!=null && previousMap.containsKey(previousKey))
		{
			String[] row0 = (String[]) previousMap.get(previousKey);
			if(matches(row0,location,name,size,modified) && !row0[4].equals(FAILED_MD5)) return row0;
		}
		
		String md5 = (String) getMd5.t(f);
		String mimeType = (String) getMimeType.t(f);
		
		return new String[]{location,name,size,modified,md5,mimeType};
	}
	
	
	private boolean matches(String[] row0, String location, String name, String size, String modified)
	{return row0[0].equals(location) && row0[1].equals(name) && row0[2].equals(size) && row0[3].equals(modified);}
}
