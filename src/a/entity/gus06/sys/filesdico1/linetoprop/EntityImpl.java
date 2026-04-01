package a.entity.gus06.sys.filesdico1.linetoprop;

import a.framework.*;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150919";}
	
	public static final String KEY_MD5 = "md5";
	public static final String KEY_DATE = "date";
	public static final String KEY_SIZE = "size";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_NAME = "name";
	
	public static final int NUMBER = 5;
	
	
	public Object t(Object obj) throws Exception
	{
		String line = (String) obj;
		String[] n = line.split("\t");
		if(n.length!=NUMBER) throw new Exception("Invalid element number: "+n.length);
		
		Properties p =  new Properties();
		p.put(KEY_MD5,n[0]);
		p.put(KEY_DATE,n[1]);
		p.put(KEY_SIZE,n[2]);
		p.put(KEY_LOCATION,n[3]);
		p.put(KEY_NAME,n[4]);
		
		return p;
	}
}