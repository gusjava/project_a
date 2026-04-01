package a.entity.gus06.sys.javaparser1.extract.all;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180224";}


	private Service buildCU;
	private Service handleNode;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		handleNode = Outside.service(this,"gus06.sys.javaparser1.tool.handle.node");
	}

	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		Node root = cu.findRootNode();
		
		Map map = new HashMap();
		handleNode.p(new Object[]{root,map});
		return map;
	}
}
