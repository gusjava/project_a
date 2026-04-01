package a.entity.gus06.sys.script1.tool.execute.op.set.analyze;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151111";}
	
	public static final String MODE_REPLACE = "replace";
	public static final String MODE_REPLACE0 = "replace0";
	
	public static final String MODE_CHANGE = "change";
	public static final String MODE_CHANGE0 = "change0";
	
	public static final String MODE_APPEND = "append";
	public static final String MODE_APPEND0 = "append0";


	private Service evalAsObject;
	private Service keyCheck;

	public EntityImpl() throws Exception
	{
		evalAsObject = Outside.service(this,"gus06.sys.script1.context.evaluate");
		keyCheck = Outside.service(this,"gus06.sys.script1.tool.execute.op.set.keycheck");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String line = (String) o[1];
		
		line = line.trim();
		
		if(!line.contains("="))
		{
			if(line.endsWith("++"))
			{
				line = line.substring(0,line.length()-2);
				line = line+"=("+line+")+1";
			}
			else if(line.endsWith("--"))
			{
				line = line.substring(0,line.length()-2);
				line = line+"=("+line+")-1";
			}
			else if(line.endsWith("**"))
			{
				line = line.substring(0,line.length()-2);
				line = line+"=("+line+")*2";
			}
			else if(line.endsWith("//"))
			{
				line = line.substring(0,line.length()-2);
				line = line+"=("+line+")/2";
			}
			
			else if(line.endsWith("!"))
			{
				line = line.substring(0,line.length()-1);
				line = line+"=!("+line+")";
			}
			else if(line.endsWith("+"))
			{
				line = line.substring(0,line.length()-1);
				line = line+"=("+line+")>=0?("+line+"):-("+line+")";
			}
			else if(line.endsWith("-"))
			{
				line = line.substring(0,line.length()-1);
				line = line+"=-("+line+")";
			}
			else if(line.endsWith("*"))
			{
				line = line.substring(0,line.length()-1);
				line = line+"=("+line+")^2";
			}
			else if(line.endsWith("/"))
			{
				line = line.substring(0,line.length()-1);
				line = line+"=1/("+line+")";
			}
			
			else throw new Exception("Invalid set operation: "+line);
		}
		
		String[] n = line.split("=",2);
		
		String key = n[0].trim();
		String exp = n[1].trim();
		String mode = MODE_REPLACE;
		
		if(key.endsWith("["))
		{
			key = key.substring(0,key.length()-1).trim()+".after";
		}
		else if(key.endsWith("]"))
		{
			key = key.substring(0,key.length()-1).trim()+".before";
		}
		else if(key.endsWith("+"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = key+"+("+exp+")";
		}
		else if(key.endsWith("-"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = "("+key+")-("+exp+")";
		}
		else if(key.endsWith("*"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = "("+key+")*("+exp+")";
		}
		else if(key.endsWith("^"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = "("+key+")^("+exp+")";
		}
		else if(key.endsWith("/"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = "("+key+")/("+exp+")";
		}
		else if(key.endsWith("||"))
		{
			key = key.substring(0,key.length()-2).trim();
			exp = "("+key+")||("+exp+")";
		}
		else if(key.endsWith("|"))
		{
			key = key.substring(0,key.length()-1).trim();
			exp = "("+key+")|("+exp+")";
		}
		else if(key.endsWith("&&"))
		{
			key = key.substring(0,key.length()-2).trim();
			exp = "("+key+")&&("+exp+")";
		}
		else if(key.endsWith("?"))
		{
			key = key.substring(0,key.length()-1).trim();
			mode = MODE_REPLACE0;
		}
		else if(key.endsWith("?>"))
		{
			key = key.substring(0,key.length()-2).trim();
			mode = MODE_CHANGE0;
		}
		else if(key.endsWith(">"))
		{
			key = key.substring(0,key.length()-1).trim();
			mode = MODE_CHANGE;
		}
		else if(key.endsWith("?<"))
		{
			key = key.substring(0,key.length()-2).trim();
			mode = MODE_APPEND0;
		}
		else if(key.endsWith("<"))
		{
			key = key.substring(0,key.length()-1).trim();
			mode = MODE_APPEND;
		}
		
		keyCheck.p(key);
		
		Object value = evalAsObject.t(new Object[]{context,exp});
		return new Object[]{key,value,mode};
	}
}
