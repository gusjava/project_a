package a.entity.gus06.sys.script1.tool.execute.op.output;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150906";}
	
	
	public static final String ENABLED = "enabled";
	public static final String MODE = "mode";
	public static final String CLEAR = "clear";
	public static final String ADD = "add";
	public static final String REMOVE = "remove";
	public static final String REDIRECT = "redirect";

	public static final String ENABLE = "enable";
	public static final String DISABLE = "disable";
	
	public static final String MODE_ONLYPRINT = "onlyprint";
	public static final String MODE_ONLYTEXT = "onlytext";
	public static final String MODE_ALL = "all";
	


	private Service getOutput;
	private Service evalAsObject;
	private Service evalAsBoolean;

	public EntityImpl() throws Exception
	{
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
		evalAsObject = Outside.service(this,"gus06.sys.script1.context.evaluate");
		evalAsBoolean = Outside.service(this,"gus06.sys.script1.context.evaluate.boolean1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		String line = (String) o[1];
		
		V output = (V) getOutput.t(context);
		if(output==null) return;
		
		
		if(line.equals(DISABLE)) 	{output.v(ENABLED,"false");return;}
		if(line.equals(ENABLE)) 	{output.v(ENABLED,"true");return;}
		if(line.equals(CLEAR)) 		{output.v(CLEAR,null);return;}
		if(line.equals(MODE_ONLYPRINT)) {output.v(MODE,MODE_ONLYPRINT);return;}
		if(line.equals(MODE_ONLYTEXT))	{output.v(MODE,MODE_ONLYTEXT);return;}
		if(line.equals(MODE_ALL)) 	{output.v(MODE,MODE_ALL);return;}
		
		
		String[] n = line.split(" ",2);
		String action = n[0];
		String info = n.length==2?n[1]:"";
		
		if(action.equals(ENABLED))
		{
			Boolean r = evalAsBoolean(context,info);
			output.v(ENABLED,""+r);
			return;
		}
		
		if(action.equals(ADD))
		{
			T filter = (T) evalAsObject(context,info);
			output.v(ADD,filter);
			return;
		}
		
		if(action.equals(REMOVE))
		{
			T filter = (T) evalAsObject(context,info);
			output.v(REMOVE,filter);
			return;
		}
		
		if(action.equals(REDIRECT))
		{
			Object redirect = evalAsObject(context,info);
			output.v(REDIRECT,redirect);
			return;
		}
		
		throw new Exception("Invalid line: "+line);
	}
	
	
	private Object evalAsObject(Map context, String id) throws Exception
	{return evalAsObject.t(new Object[]{context,id});}
	
	private Boolean evalAsBoolean(Map context, String id) throws Exception
	{return (Boolean) evalAsBoolean.t(new Object[]{context,id});}
}
