package a.entity.gus06.sys.javaparser1.extract.imports;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180224";}


	private Service buildCU;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
	}

	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		
		List list = new ArrayList();
		NodeList<ImportDeclaration> nodes = cu.getImports();
		for(ImportDeclaration node : nodes) list.add(node.getName().asString());
		return list;
	}
}
