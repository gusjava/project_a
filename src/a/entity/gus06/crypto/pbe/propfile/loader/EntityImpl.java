package a.entity.gus06.crypto.pbe.propfile.loader;

import java.io.File;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150613";}


	private Service askPassword;
	private Service readPropPbe;
	
	public EntityImpl() throws Exception
	{
		askPassword = Outside.service(this,"gus06.security.askinfo.password1");
		readPropPbe = Outside.service(this,"gus06.file.read.properties.pbe");
	}


	public boolean f(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length==2) return load((File) t[0],(Map) t[1]);
		if(t.length==3) return load((File) t[0],(Map) t[1],(String) t[2]);
		
		throw new Exception("Wrong data number: "+t.length);
	}

	
	private boolean load(File file, Map map) throws Exception
	{
		String pwd = (String) askPassword.g();
		return load(file,map,pwd);
	}

	
	private boolean load(File file, Map map, String pwd) throws Exception
	{
		if(pwd==null || pwd.equals("")) return false;
		
		try
		{
			readPropPbe.v("password",pwd);
			Map m = (Map) readPropPbe.t(file);
			
			map.clear();
			map.putAll(m);
			return true;
		}
		catch(Exception e)
		{
			if(e.toString().startsWith("javax.crypto.")) return false;
			throw e;
		}
	}
}
