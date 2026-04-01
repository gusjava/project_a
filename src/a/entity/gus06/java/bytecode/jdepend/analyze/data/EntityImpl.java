package a.entity.gus06.java.bytecode.jdepend.analyze.data;

import a.framework.*;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;

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
		InputStream is = (InputStream) findInputStream.t(obj);
		JavaClass jClass = parser.parse(is);
		
		Map data = new HashMap();
		
		data.put("imports",jClass.getImportedPackageNames());
		data.put("majorversion", Integer.valueOf(jClass.majorVersion));
		data.put("minorversion", Integer.valueOf(jClass.minorVersion));
		
		return data;
	}
}