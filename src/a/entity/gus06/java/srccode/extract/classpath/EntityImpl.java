package a.entity.gus06.java.srccode.extract.classpath;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160410";}


	private Service findClassName;
	private Service findPackage;

	public EntityImpl() throws Exception
	{
		findClassName = Outside.service(this,"gus06.java.srccode.extract.classname");
		findPackage = Outside.service(this,"gus06.java.srccode.extract.package1");
	}

	
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String src = (String) obj;
		
		String className = (String) findClassName.t(src);
		String package1 = (String) findPackage.t(src);
		
		return package1+"."+className;
	}
}
