package a.entity.gus06.sys.javaparser1.extract.field.data;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220928";}


	private Service buildCU;
	private Service buildInfo;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildInfo = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.field");
	}

	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		List list = new ArrayList();
		Visitor1 v = new Visitor1();
		v.visit(cu, list);
		return list;
	}
	
	
	private class Visitor1 extends VoidVisitorAdapter<List>
	{
		public void visit(FieldDeclaration fd, List list)
		{
			super.visit(fd, list);
			list.add(buildInfo(fd));
		}
	}
	
	private Map buildInfo(FieldDeclaration fd)
	{
		try{return (Map) buildInfo.t(fd);}
		catch(Exception e){Outside.err(this,"buildInfo(FieldDeclaration)",e);}
		return new HashMap();
	}
}