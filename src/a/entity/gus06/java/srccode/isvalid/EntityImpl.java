package a.entity.gus06.java.srccode.isvalid;

import a.framework.*;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.Problem;
import com.github.javaparser.ast.CompilationUnit;
import java.util.List;

public class EntityImpl implements Entity, F {
	
	public String creationDate() {return "20251204";}

	private Service buildCu;
	
	public EntityImpl() throws Exception
	{
		buildCu = Outside.service(this,"gus06.sys.javaparser1.tool.build.compilationunit");
	}

	public boolean f(Object obj) throws Exception
	{
		try
		{
			CompilationUnit cu = (CompilationUnit) buildCu.t(obj);
			return cu!=null;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
