package a.entity.gus.z.server1.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260409";}
	
	public static final String CMD = "cmd";
	public static final String ARGS = "args";

	public EntityImpl() throws Exception {
		
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] n = s.split(" +",2);
		String cmd = n[0];
		String args = n.length>1 ? n[1] : null;
		List argList = parseArgs(args);
		
		Map map = new HashMap();
		map.put(CMD, cmd);
		map.put(ARGS, argList);
		return map;
	}
	
	private List parseArgs(String args)
	{
		List list = new ArrayList();
		if(args==null) return list;
		
		//TODO parser "..." "..." ou blabla blabla
		return list;
	}
}
