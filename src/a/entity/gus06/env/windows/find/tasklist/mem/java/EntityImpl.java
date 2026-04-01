package a.entity.gus06.env.windows.find.tasklist.mem.java;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20190607";}
	
	public static final String CMD = "tasklist";
		

	private Service execToString;
	private Service handleRow;

	public EntityImpl() throws Exception
	{
		execToString = Outside.service(this,"gus06.data.perform.exec.tostring");
		handleRow = Outside.service(this,"gus06.env.windows.find.tasklist.mem.handlerow");
	}
	
	
	public Object g() throws Exception
	{
		String res = (String) execToString.t(CMD);
		
		String[] lines = res.trim().split("\n");
		int nb = lines.length;
		
		long sum = 0;
		if(nb<2) return sum;
		
		for(int i=2;i<nb;i++) if(isJavaLine(lines[i]))
		{
			Long mem = (Long) handleRow.t(new String[]{lines[1],lines[i]});
			sum+=mem;
		}
		return sum;
	}
	
	private boolean isJavaLine(String line)
	{
		String l = line.toLowerCase().trim();
		return l.startsWith("java.exe") || l.startsWith("javaw.exe");
	}
}
