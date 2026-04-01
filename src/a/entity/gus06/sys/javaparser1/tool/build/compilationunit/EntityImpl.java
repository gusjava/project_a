package a.entity.gus06.sys.javaparser1.tool.build.compilationunit;

import a.framework.*;
import java.io.File;
import java.io.Reader;
import java.io.InputStream;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180224";}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof CompilationUnit) return obj;
		if(obj instanceof File) return JavaParser.parse((File) obj);
		if(obj instanceof String) return JavaParser.parse((String) obj);
		if(obj instanceof InputStream) return JavaParser.parse((InputStream) obj);
		if(obj instanceof Reader) return JavaParser.parse((Reader) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}