package a.entity.gus06.java.srccode.extract.classdata;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170221";}
	
	public static final Pattern P_CLASSNAME = Pattern.compile("\\s(class|interface|enum|@interface|record)\\s+([A-Za-z][A-Za-z0-9_]*)",Pattern.DOTALL);
	public static final Pattern P_IMPORT = Pattern.compile("import +([^;]+);");
	public static final Pattern P_PACKAGE = Pattern.compile("package +([^;]+);");
	
	public static final String KEY_CLASSTYPE = "classtype";
	public static final String KEY_CLASSNAME = "classname";
	public static final String KEY_CLASSPATH = "classpath";
	public static final String KEY_PACKAGE = "package";
	public static final String KEY_IMPORTS = "imports";
	
	
	
	private Service removeComments;
	private Service toArray;
	
	public EntityImpl() throws Exception
	{
		removeComments = Outside.service(this,"gus06.java.srccode.remove.comments");
		toArray = Outside.service(this,"gus06.java.srccode.toarray");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		String src = (String) removeComments.t(obj);
		
		Matcher m = P_CLASSNAME.matcher(src);
		if(!m.find()) throw new Exception("Class name extraction failed");
		
		String classType = m.group(1);
		String className = m.group(2);
		
		String[] lines = (String[]) toArray.t(src);
		
		String package1 = findPackage(lines);
		Set imports = findImports(lines);
		
		Map map = new HashMap();
		
		map.put(KEY_CLASSTYPE,classType);
		map.put(KEY_CLASSNAME,className);
		map.put(KEY_CLASSPATH,package1+"."+className);
		map.put(KEY_PACKAGE,package1);
		map.put(KEY_IMPORTS,imports);
		
		return map;
	}
	
	
	private String findPackage(String[] lines) throws Exception
	{
		for(String line:lines) if(line.trim().startsWith("package "))
		{
			Matcher m = P_PACKAGE.matcher(line);
			if(!m.find()) throw new Exception("Package extraction failed for line: "+line);
			return m.group(1);
		}
		throw new Exception("Package declaration not found");
	}
	
	
	private Set findImports(String[] lines) throws Exception
	{
		Set imports = new HashSet();
		for(String line:lines) if(line.startsWith("import "))
		{
			Matcher m = P_IMPORT.matcher(line);
			if(!m.find()) throw new Exception("Import extraction failed for line: "+line);
			
			String value = m.group(1).trim();
			if(value.startsWith("static ")) value = value.substring(7);
			imports.add(value.trim());
		}
		return imports;
	}
}
