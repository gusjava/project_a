package a.entity.gus06.y.entitydev1.retrieve.javafile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251110";}

	public static final String KEY_ENTITYNAME = "entityname";
	public static final String KEY_ROOTDIR = "rootdir";

	private Service nameToPath;

	public EntityImpl() throws Exception
	{nameToPath = Outside.service(this,"entitynametopath");}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File rootDir = toFile(o[0]);
		String entityName = (String) o[1];
		
		String entityClassPath = (String) nameToPath.t(entityName);
		String relPath = entityClassPath.replace(".",File.separator)+".java";
		
		return new File(rootDir, relPath);
	}
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return new File((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}