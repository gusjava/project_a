package a.entity.gus06.sys.javaparser1.extract.enumerations;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250907";}

	private Service buildCU;

	public EntityImpl() throws Exception
	{
		buildCU = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
	}
	
	public Object t(Object obj) throws Exception
	{
		CompilationUnit cu = (CompilationUnit) buildCU.t(obj);
		List<EnumDeclaration> enums = cu.findAll(EnumDeclaration.class);
		
		Map map = new HashMap();
		for (EnumDeclaration enumDecl : enums)
		{
			String enumName = enumDecl.getName().asString();
			List list = new ArrayList();
			
			for (EnumConstantDeclaration constant : enumDecl.getEntries())
				list.add(constant.getName());
		}
		return map;
	}
}