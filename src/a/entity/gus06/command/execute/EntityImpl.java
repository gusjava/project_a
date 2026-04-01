package a.entity.gus06.command.execute;

import java.io.PrintStream;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, P, F {

	public String creationDate() {return "20140701";}

	
	private PrintStream out;
	
	private Map prop;
	private String k;
	
	
	
	public EntityImpl() throws Exception
	{
		out = (PrintStream) Outside.resource(this,"sysout");
		prop = (Map) Outside.resource(this,"prop");
	}


	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{
		String cmd = (String) obj;
		
		if(cmd.equals("k?"))
		{out.println("k:"+k);return true;}
		
		if(cmd.equals("k-"))
		{k = null;return true;}
		
		if(cmd.startsWith("k "))
		{k = cmd.substring(2);return true;}
		
		return executeCmd(cmd);
	}
	
	
	
	
	
	
	
	private boolean executeCmd(String cmd)
	{
		try
		{
			if(k!=null) cmd = k+" "+cmd;
			
			cmd = alias(cmd);

			String[] n = cmd.split(" ",2);

			String action = n[0];
			String info = n.length>1?n[1]:null;

			if(action.equals("e"))
			{perform(info,null);return true;}
			
			action = alias(action);

			if(!prop.containsKey("cmd."+action))
				throw new Exception("Unknown command action: "+action);
			String rule = (String) prop.get("cmd."+action);
			
			perform(rule,info);
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"executeCmd(String)",e);
			out.println("Error:"+e.getMessage());
			return false;
		}
	}
	
	
	
	private void perform(String rule, String info) throws Exception
	{
		Service s = Outside.service(this,rule);
		if(info==null) s.e();
		else s.p(info);
	}
	
	
	
	private String alias(String s)
	{
		while(prop.containsKey("cmdalias."+s))
			s = (String) prop.get("cmdalias."+s);
		return s;
	}
}