package a.entity.gus06.sys.javaparser1.extract.classfullname;

import a.framework.*;
import com.github.javaparser.ast.CompilationUnit;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231024";}


	private Service buildCU;
	private Service extractClassName;
	private Service extractPackage;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
		extractClassName = Outside.service(this,"gus06.sys.javaparser1.extract.classname");
		extractPackage = Outside.service(this,"gus06.sys.javaparser1.extract.package1");
	}
	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		String className = (String) extractClassName.t(cu);
		String package1 = (String) extractPackage.t(cu);
		return package1+"."+className;
	}
	
	
}