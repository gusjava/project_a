package a.entity.gus06.map.deep.put;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150905";}
	
	public static final String MODE_REPLACE = "replace";
	public static final String MODE_REPLACE0 = "replace0";
	
	public static final String MODE_CHANGE = "change";
	public static final String MODE_CHANGE0 = "change0";
	
	public static final String MODE_APPEND = "append";
	public static final String MODE_APPEND0 = "append0";
	


	private Service replace;
	private Service replace0;
	
	private Service change;
	private Service change0;
	
	private Service append;
	private Service append0;
	
	private Service remove;
	private Service empty;


	public EntityImpl() throws Exception
	{
		replace = Outside.service(this,"gus06.map.deep.put.replace");
		replace0 = Outside.service(this,"gus06.map.deep.put.replace0");
		
		change = Outside.service(this,"gus06.map.deep.put.change");
		change0 = Outside.service(this,"gus06.map.deep.put.change0");
		
		append = Outside.service(this,"gus06.map.deep.put.append");
		append0 = Outside.service(this,"gus06.map.deep.put.append0");
		
		remove = Outside.service(this,"gus06.map.deep.put.remove");
		empty = Outside.service(this,"gus06.map.deep.put.empty");
	}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		
		if(o.length==4)	{handleMain(o[0],(String) o[1],o[2],(String) o[3]);return;}
		if(o.length==3)	{handleReplace(o[0],(String) o[1],o[2]);return;}
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private void handleMain(Object data, String key, Object value, String mode) throws Exception
	{
		if(mode.equals(MODE_REPLACE)) {handleReplace(data,key,value);return;}
		if(mode.equals(MODE_REPLACE0)) {handleReplace0(data,key,value);return;}
		
		if(mode.equals(MODE_CHANGE)) {handleChange(data,key,value);return;}
		if(mode.equals(MODE_CHANGE0)) {handleChange0(data,key,value);return;}
		
		if(mode.equals(MODE_APPEND)) {handleAppend(data,key,value);return;}
		if(mode.equals(MODE_APPEND0)) {handleAppend0(data,key,value);return;}
		
		throw new Exception("Invalid mode: "+mode);
	}
	
	
	private void handleReplace(Object data, String key, Object value) throws Exception
	{
		if(value==null) remove.p(new Object[]{data,key});
		else replace.p(new Object[]{data,key,value});
	}
	
	
	private void handleReplace0(Object data, String key, Object value) throws Exception
	{
		replace0.p(new Object[]{data,key,value});
	}
	
	
	private void handleChange(Object data, String key, Object value) throws Exception
	{
		if(value==null) empty.p(new Object[]{data,key});
		else change.p(new Object[]{data,key,value});
	}
	
	
	private void handleChange0(Object data, String key, Object value) throws Exception
	{
		change0.p(new Object[]{data,key,value});
	}
	
	
	private void handleAppend(Object data, String key, Object value) throws Exception
	{
		append.p(new Object[]{data,key,value});
	}
	
	
	private void handleAppend0(Object data, String key, Object value) throws Exception
	{
		append0.p(new Object[]{data,key,value});
	}
}
