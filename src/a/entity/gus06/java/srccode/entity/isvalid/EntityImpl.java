package a.entity.gus06.java.srccode.entity.isvalid;

import a.framework.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, F, T {
	
	public String creationDate() {return "20251204";}
	
	public static final String IMPORT_FRAMEWORK_GUS06 = "gus06.framework.*";
	public static final String INTERFACE_ENTITY = "Entity";

	private Service perform;
	private Service checkCreationDate;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.javaparser1.extract.extractor1");
		checkCreationDate = Outside.service(this,"gus06.java.srccode.entity.isvalid.creationdate");
	}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	public Object t(Object obj) throws Exception
	{
		try
		{
			Map map = (Map) perform.t(obj);
			
			List imports = (List) get(map, "imports");
			List staticImports = (List) get(map, "static_imports");
			List interfaces = (List) get(map, "implements");
			List extends1 = (List) get(map, "extends");
			List methods = (List) get(map, "methods");
			String package1 = (String) get(map, "package");
			
			if(package1==null) return null;
			if(!package1.startsWith("gus06.entity.")) return null;
			
			if(imports==null) return null;
			if(!imports.contains(IMPORT_FRAMEWORK_GUS06)) return null;
			
			if(interfaces==null) return null;
			if(!interfaces.contains(INTERFACE_ENTITY)) return null;
			
			for(int i=0;i<imports.size();i++)
			{
				String im = (String) imports.get(i);
				if(!im.equals(IMPORT_FRAMEWORK_GUS06) && im.startsWith("gus06.")) return null;
			}
			
			if(staticImports!=null)
			for(int i=0;i<staticImports.size();i++)
			{
				String im = (String) staticImports.get(i);
				if(im.startsWith("gus06.")) return null;
			}
			
			Map creationDateMethod = findMethodCreationDate(methods);
			String creationDateMethodBody = (String) creationDateMethod.get("body");
			if(creationDateMethodBody==null) return null;
			if(!checkCreationDate.f(creationDateMethodBody)) return null;
			
			return package1.substring(13);
		}
		catch(Exception e)
		{
			Outside.err(this,"EXCEPTION",e);
			return null;
		}
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
	
	
	private Map findMethodCreationDate(List methods) throws Exception
	{
		if(methods==null) return null;
		for(int i=0;i<methods.size();i++)
		{
			Map methodData = (Map) methods.get(i);
			String name = (String) methodData.get("name");
			if(name.equals("creationDate")) return methodData;
		}
		return null;
	}
}
