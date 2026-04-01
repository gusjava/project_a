package a.entity.gus06.sys.javaparser1.extract.method.data.forsign;

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
	private Service buildInfo;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildInfo = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.method.forsign");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		CompilationUnit cu = (CompilationUnit) buildCU.t(o[0]);
		String targetSign = (String) o[1];
		
		List list = new ArrayList();
		Visitor1 v = new Visitor1(targetSign);
		v.visit(cu, list);
		return list.size()==1 ? list.get(0) : null;
	}
	
	
	private class Visitor1 extends VoidVisitorAdapter<List>
	{
		private String targetSign;
		private boolean found = false;
		
		public Visitor1(String targetSign)
		{this.targetSign = targetSign;}
		
		public void visit(MethodDeclaration md, List list)
		{
			if(found) return;
			super.visit(md, list);
			Map info = buildInfo(md, targetSign);
			if(info!=null) 
			{
				list.add(info);
				found = true;
			}
		}
	}
	
	private Map buildInfo(MethodDeclaration md, String targetSign)
	{
		try{return (Map) buildInfo.t(new Object[]{md, targetSign});}
		catch(Exception e){Outside.err(this,"buildInfo(MethodDeclaration,String)",e);}
		return null;
	}
}