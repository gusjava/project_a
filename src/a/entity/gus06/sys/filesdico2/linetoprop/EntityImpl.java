package a.entity.gus06.sys.filesdico2.linetoprop;

import a.framework.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150919";}
	
	public static final String KEY_MD5 = "md5";
	public static final String KEY_MODIFIED = "modified";
	public static final String KEY_CREATED = "created";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MIMETYPE = "mimetype";
	public static final String KEY_CHARSET = "charset";
	public static final String KEY_PAGENUMBER = "pagenumber";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_NAME = "name";
	
	public static final int NUMBER = 9;
	
	
	
	public Object t(Object obj) throws Exception
	{
		String line = (String) obj;
		String[] n = line.split("\t");
		if(n.length!=NUMBER) throw new Exception("Invalid element number: "+n.length);
		
		Properties p =  new Properties();
		p.put(KEY_MD5,n[0]);
		p.put(KEY_MODIFIED,n[1]);
		p.put(KEY_CREATED,n[2]);
		p.put(KEY_SIZE,n[3]);
		p.put(KEY_MIMETYPE,n[4]);
		p.put(KEY_CHARSET,n[5]);
		p.put(KEY_PAGENUMBER,n[6]);
		p.put(KEY_LOCATION,n[7]);
		p.put(KEY_NAME,n[8]);
		
		return p;
	}
}
