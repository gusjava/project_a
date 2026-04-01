package a.entity.gus06.env.windows.find.tasklist.bypid;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190522";}
	

	private Service execToString;
	private Service handleRow;

	public EntityImpl() throws Exception
	{
		execToString = Outside.service(this,"gus06.data.perform.exec.tostring");
		handleRow = Outside.service(this,"gus06.env.windows.find.tasklist.handlerow");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		int pid = toInt(obj);
		if(pid<=0) throw new Exception("Invalid pid value: "+pid);
		
		String cmd = "tasklist /fi \"pid eq "+pid+"\"";
		String res = (String) execToString.t(cmd);
		
		String[] lines = res.trim().split("\n");
		int nb = lines.length;
		if(nb!=3) throw new Exception("Invalid response: "+res);
		
		return handleRow.t(new String[]{lines[1],lines[2]});
	}
	
	
	private int toInt(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null data");
		if(obj instanceof Integer)	return ((Integer) obj).intValue();
		if(obj instanceof Long)		return ((Long) obj).intValue();
		if(obj instanceof String)	return Integer.parseInt((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
