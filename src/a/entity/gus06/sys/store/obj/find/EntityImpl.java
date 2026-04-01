package a.entity.gus06.sys.store.obj.find;

import a.framework.*;

public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140907";}


	private Service findProcess;


	public EntityImpl() throws Exception
	{findProcess = Outside.service(this,"gus06.sys.store.process.find");}
	
	
	
	public Object r(String key) throws Exception
	{return findObj(key);}
	
	
	public Object t(Object obj) throws Exception
	{return findObj((String) obj);}
	
	
	
	private Object findObj(String rule) throws Exception
	{
		if(rule==null) throw new Exception("Invalid null rule for findObj");
    	
		try
		{
			if(rule.equals("null")) return null;
			if(!rule.contains(":")) throw new Exception("Invalid rule: "+rule+" (: is missing)");
    		
			String[] n = rule.split(":",2);
			String seq = n[0];
			String info = n[1];
    		
			return findObj(seq,info);
		}
		catch(Exception e)
		{
			String message = "findObj failed for rule = "+rule;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	
	private Object findObj(String seq, String info) throws Exception
	{
		if(!seq.contains(";"))
			return processData(seq,info);
    	
		String[] n = seq.split(";");
		Object data = info;
    	
		for(int i=n.length-1;i>=0;i--)
			data = processData(n[i],data);
		return data;
	}
	
	
	
	
	
	
	private Object processData(String id, Object data) throws Exception
	{
		try
		{
			T process = (T) findProcess.r(id);
			return process.t(data);
		}
		catch(Exception e)
		{
			String message = "Data processing failed for id="+id+" (input="+toString(data)+")";
			throw new Exception(message,e);
		}
	}
	
	
	
	
	private String toString(Object data)
	{
		if(data==null) return null;
		if(data instanceof String) return (String) data;
		return "["+data.getClass().getName()+"]";
	}
}
