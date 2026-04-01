package a.entity.gus06.runtime.exec.resolveexe;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180220";}

	private Service cmdMap;
	
	public EntityImpl() throws Exception
	{
		cmdMap = Outside.service(this,"gus06.system.env.path.map.cache");
	}

	public Object t(Object obj) throws Exception
	{
		return prepareCmd((String) obj);
	}
	
	private String prepareCmd(String cmd) throws Exception
	{
		String[] nn = cmd.split(" ",2);
		if(nn.length==1) return nn[0];
		return firstPart(nn[0])+" "+nn[1];
	}
	
	private String firstPart(String c) throws Exception
	{
		Map map = (Map) cmdMap.g();
		if(!map.containsKey(c)) return c;
		
		File f = (File) map.get(c);
		return "\""+f.getAbsolutePath()+"\"";
	}
}
