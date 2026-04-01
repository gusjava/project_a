package a.entity.gus06.file.ext.isof;

import a.framework.*;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20190604";}


	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String[] extArray = findExt(o[1]);
		
		String name = file.getName().toLowerCase();
		for(String ext:extArray) if(name.endsWith("."+ext)) return true;
		return false;
	}
	
	
	private String[] findExt(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return ((String) obj).split("\\|");
		if(obj instanceof List)
		{
			List list = (List) obj;
			String[] array = new String[list.size()];
			list.toArray(array);
			return array;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
