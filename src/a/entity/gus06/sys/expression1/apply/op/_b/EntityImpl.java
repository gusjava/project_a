package a.entity.gus06.sys.expression1.apply.op._b;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180505";}


	private Service nameToFile;
	
	public EntityImpl() throws Exception
	{
		nameToFile = Outside.service(this,"gus06.dir.hdd.drivername.inv");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Integer) return driver((Integer) obj);
		if(obj instanceof String) return driver((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File driver(Integer n)
	{
		File[] d = File.listRoots();
		int index = n.intValue();
		if(index<0 || index>=d.length) return null;
		return d[index];
	}
	
	private File driver(String s) throws Exception
	{
		if(s.length()==1)			return driverByLetter(s+":\\");
		if(s.length()==2 && s.endsWith(":"))	return driverByLetter(s+"\\");
		if(s.length()==3 && s.endsWith(":\\"))	return driverByLetter(s);
			
		return (File) nameToFile.t(s);
	}
	
	private File driverByLetter(String s)
	{return new File(s.toUpperCase());}
}
