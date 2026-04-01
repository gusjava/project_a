package a.entity.gus06.sys.javaprojectviewer1.data.check.importtype;

import a.framework.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}
	
	public static final String TYPE_PROJECT = "$";
	public static final String TYPE_JRE = "*";
	public static final String TYPE_UNKNOWN = "?";
	public static final String TYPE_ERROR = "#";


	private Service isRtClass;
	private Service findPackage;
	private Service findClasspath1;

	public EntityImpl() throws Exception
	{
		isRtClass = Outside.service(this,"gus06.java.isclass.rt");
		findPackage = Outside.service(this,"gus06.java.classpath.topackage");
		findClasspath1 = Outside.service(this,"gus06.java.classpath.toclasspath1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String import1 = (String) o[0];
		Set packages = (Set) o[1];
		Set classpaths = (Set) o[2];
		
		if(import1.endsWith(".*"))
		{
			String package1 = import1.substring(0,import1.length()-2);
			if(packages.contains(package1)) return TYPE_PROJECT;
		}
		
		String classpath1 = (String) findClasspath1.t(import1);
		
		if(classpaths.contains(classpath1))
		{
			return TYPE_PROJECT;
		}
		
		if(isRtClass.f(classpath1))
		{
			return TYPE_JRE;
		}
		
		String package1 = (String) findPackage.t(classpath1);
		if(packages.contains(package1))
		{
			return TYPE_ERROR;
		}
		
		return TYPE_UNKNOWN;
	}
}
