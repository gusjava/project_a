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
		JavaParser jp = new JavaParser();
		if(obj instanceof File) return jp.parse((File) obj).getResult().get();
		if(obj instanceof String) return jp.parse((String) obj).getResult().get();
		if(obj instanceof InputStream) return jp.parse((InputStream) obj).getResult().get();
		if(obj instanceof Reader) return jp.parse((Reader) obj).getResult().get();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
