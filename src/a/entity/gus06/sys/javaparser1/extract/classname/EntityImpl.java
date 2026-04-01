package a.entity.gus06.sys.javaparser1.extract.classname;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231024";}


	private Service buildCU;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
	}

	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		
		List<String> list = new ArrayList<>();
		ClassNameVisitor classNameVisitor = new ClassNameVisitor();
		classNameVisitor.visit(cu,list);
		return list.get(0);
	}
	
	
	public class ClassNameVisitor extends VoidVisitorAdapter<List<String>>
	{
		public void visit(ClassOrInterfaceDeclaration n, List<String> list)
		{
			super.visit(n, list);
			if(!n.isInnerClass())
			list.add(n.getNameAsString());
		}
	}
}