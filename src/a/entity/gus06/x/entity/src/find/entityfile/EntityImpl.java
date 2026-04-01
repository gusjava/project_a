package a.entity.gus06.x.entity.src.find.entityfile;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251111";}

	public static final String FILENAME = "EntityImpl.java";

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof File) return findEntityFile((File) obj);
		if(obj instanceof Object[]) return findEntityFile((Object[]) obj);
		
		throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	private File findEntityFile(File dir)
	{
		return new File(dir, FILENAME);
	}
	
	private File findEntityFile(Object[] o) throws Exception
	{
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		File dir = (File) o[0];
		String entityName = (String) o[1];

		String path = ("gus06.entity." + entityName).replace(".", File.separator);
		return new File(new File(dir, path), FILENAME);
	}
}