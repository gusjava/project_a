package a.entity.gus06.sys.javaparser1.extract.prop.data;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.CompilationUnit;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220930";}
	
	private Service buildCU;
	private Service buildMethod;
	private Service generateProps;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		buildMethod = Outside.service(this,"gus06.sys.javaparser1.tool.buildinfo.method");
		generateProps = Outside.service(this,"gus06.sys.javaparser1.tool.generate.props");
	}
	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		ClassOrInterfaceDeclaration cid = cu.findAll(ClassOrInterfaceDeclaration.class).iterator().next();
		
		List methods = new ArrayList();
		List<MethodDeclaration> list = cid.findAll(MethodDeclaration.class);
		for(MethodDeclaration md : list) methods.add(buildMethod.t(md));
		
		return generateProps.t(methods);
	}
}