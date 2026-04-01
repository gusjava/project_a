package a.entity.gus06.sys.javaparser1.extract.method.signs;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231023";}


	private Service buildCU;
	private Service buildSign;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildSign = Outside.service(this,"gus06.sys.javaparser1.tool.buildsign.method");
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
		public void visit(MethodDeclaration md, List list)
		{
			super.visit(md, list);
			list.add(buildSign(md));
		}
	}
	
	private String buildSign(MethodDeclaration md)
	{
		try{return (String) buildSign.t(md);}
		catch(Exception e){Outside.err(this,"buildSign(MethodDeclaration)",e);}
		return "###";
	}
}