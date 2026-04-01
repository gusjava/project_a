package a.entity.gus06.sys.javaparser1.extract.method.data;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180224";}

	private Service buildCU;
	private Service buildInfo;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildInfo = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.method");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof CompilationUnit) return cuToList((CompilationUnit) obj);
		if(obj instanceof File) return cuToList(buildCU(obj));
		if(obj instanceof InputStream) return cuToList(buildCU(obj));
		if(obj instanceof Reader) return cuToList(buildCU(obj));
		if(obj instanceof String) return handleString((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private CompilationUnit buildCU(Object obj) throws Exception
	{return (CompilationUnit) buildCU.t(obj);}
	
	private List cuToList(CompilationUnit cu) throws Exception
	{
		List list = new ArrayList();
		Visitor1 v = new Visitor1();
		v.visit(cu, list);
		return list;
	}
	
	private List handleString(String src) throws Exception
	{
		src = src.trim();
		
		if(src.startsWith("```java")) src = src.substring(7);
		if(src.startsWith("```")) src = src.substring(3);
		if(src.endsWith("```")) src = src.substring(0, src.length()-3);
		
		try {return cuToList(buildCU(src));}
		catch(Exception e){}
		
		try{return cuToList(buildCU("package tempwrapper; class Temp {"+src+"}"));}
		catch(Exception e){}
		
		throw new Exception("Failed to parse java src: ["+src+"]");
	}
	
	private class Visitor1 extends VoidVisitorAdapter<List>
	{
		public void visit(MethodDeclaration md, List list)
		{
			super.visit(md, list);
			list.add(buildInfo(md));
		}
	}
	
	private Map buildInfo(MethodDeclaration md)
	{
		try{return (Map) buildInfo.t(md);}
		catch(Exception e){Outside.err(this,"buildInfo(MethodDeclaration)",e);}
		return new HashMap();
	}
}
