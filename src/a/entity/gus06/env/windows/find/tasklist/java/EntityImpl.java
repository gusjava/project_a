package a.entity.gus06.env.windows.find.tasklist.java;

import a.framework.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170410";}
	
	public static final String CMD = "tasklist";
		

	private Service execToString;
	private Service handleRow;

	public EntityImpl() throws Exception
	{
		execToString = Outside.service(this,"gus06.data.perform.exec.tostring");
		handleRow = Outside.service(this,"gus06.env.windows.find.tasklist.handlerow");
	}
	
	
	public Object g() throws Exception
	{
		String res = (String) execToString.t(CMD);
		
		String[] lines = res.trim().split("\n");
		int nb = lines.length;
		
		List list = new ArrayList();
		if(nb<2) return list;
		
		for(int i=2;i<nb;i++) if(isJavaLine(lines[i]))
		{
			Map map = (Map) handleRow.t(new String[]{lines[1],lines[i]});
			list.add(map);
		}
		return list;
	}
	
	private boolean isJavaLine(String line)
	{
		String l = line.toLowerCase().trim();
		return l.startsWith("java.exe") || l.startsWith("javaw.exe");
	}
}
