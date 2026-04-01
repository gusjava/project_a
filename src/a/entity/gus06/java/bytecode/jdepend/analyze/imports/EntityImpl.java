package a.entity.gus06.java.bytecode.jdepend.analyze.imports;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}

	private Service findInputStream;
	private ClassFileParser parser;

	public EntityImpl() throws Exception
	{
		findInputStream = Outside.service(this,"gus06.find.inputstream");
		parser = new ClassFileParser();
	}

	public Object t(Object obj) throws Exception
	{
		try
		{
			InputStream is = (InputStream) findInputStream.t(obj);
			JavaClass jClass = parser.parse(is);
			return jClass.getImportedPackageNames();
		}
		catch(Exception e)
		{
			throw new Exception("Failed to parse class from src: "+obj,e);
		}
	}
}
