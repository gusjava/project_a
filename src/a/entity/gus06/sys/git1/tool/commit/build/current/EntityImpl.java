package a.entity.gus06.sys.git1.tool.commit.build.current;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201203";}


	private Service readText;

	public EntityImpl() throws Exception
	{
		readText = Outside.service(this,"gus06.file.read.string.autodetect");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		if(file==null) return null;
		
		Map map = new HashMap();
		map.put("name","");
		map.put("author","");
		map.put("email","");
		map.put("time",null);
		map.put("message","");
		map.put("file",file);
		map.put("src",readText.t(file));
		return map;
	}
}