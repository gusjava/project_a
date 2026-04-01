package a.entity.gus06.java.srccode.generate.emptyclass;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20251219";}


	private Service inferPackage;
	private Service getName0;

	public EntityImpl() throws Exception
	{
		inferPackage = Outside.service(this,"gus06.java.srccode.inferpackage");
		getName0 = Outside.service(this,"gus06.file.getname0");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		String packageName = (String) inferPackage.t(file);
		String className = (String) getName0.t(file);
		
		StringBuilder sb = new StringBuilder();
		if(packageName!=null)
		{
			sb.append("package ");
			sb.append(packageName);
			sb.append(";\n\n");
		}
		sb.append("public class ");
		sb.append(className);
		sb.append(" {\n\n\tpublic ");
		sb.append(className);
		sb.append("() {\n\t\t\n\t}\n}");
		
		return sb.toString();
	}
}
