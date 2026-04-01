package a.entity.gus06.dir.access.getfile.eml.randomid;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160608";}
	
	public static final String EXTENSION = "eml";


	private Service randomId;

	public EntityImpl() throws Exception
	{
		randomId = Outside.service(this,"gus06.data.generate.string.random.number14");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(!dir.exists()) dir.mkdirs();
		String name = (String) randomId.g();
		return new File(dir,name+"."+EXTENSION);
	}
}
