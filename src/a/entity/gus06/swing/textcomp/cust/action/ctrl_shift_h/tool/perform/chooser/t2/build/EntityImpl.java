package a.entity.gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.t2.build;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170225";}
	
	
	
	private Service manager;
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"*gus06.swing.textcomp.cust.action.ctrl_shift_h.tool.perform.chooser.manager");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		String key = (String) o[1];
		
		Map data0 = findData(comp);
		
		Map data = new HashMap();
		if(data0!=null) data.putAll(data0);
		data.put("comp",comp);
		
		manager.v("data",data);
		return key!=null ? manager.r(key) : null;
	}
	
	
	private Map findData(Object obj) throws Exception
	{
		if(!(obj instanceof R)) return null;
		try{return (Map) ((R) obj).r("data");}
		catch(Exception e){}
		return null;
	}
}