package a.entity.gus06.convert.maptoborder;

import a.framework.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160908";}

		
	private Service bevel;
	private Service empty;
	private Service etched;
	private Service line;
	private Service lowered;
	private Service raised;
	private Service matte;
	private Service titled;
	private Service oval;


	public EntityImpl() throws Exception
	{
		bevel = Outside.service(this,"gus06.convert.stringtoborder.bevel");
		empty = Outside.service(this,"gus06.convert.stringtoborder.empty");
		etched = Outside.service(this,"gus06.convert.stringtoborder.etched");
		line = Outside.service(this,"gus06.convert.stringtoborder.line");
		lowered = Outside.service(this,"gus06.convert.stringtoborder.lowered");
		raised = Outside.service(this,"gus06.convert.stringtoborder.raised");
		matte = Outside.service(this,"gus06.convert.stringtoborder.matte");
		titled = Outside.service(this,"gus06.convert.stringtoborder.titled");
		oval = Outside.service(this,"gus06.convert.stringtoborder.oval");
	}


	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String type = (String) get(map,"type");
		String info = (String) get(map,"info");

		return (Border) find(type).t(info);
	}
	
	
	private Service find(String type) throws Exception
	{
		if(type.equals("bevelborder")) return bevel;
		if(type.equals("emptyborder")) return empty;
		if(type.equals("etchedborder")) return etched;
		if(type.equals("lineborder")) return line;
		if(type.equals("loweredborder")) return lowered;
		if(type.equals("raisedborder")) return raised;
		if(type.equals("matteborder")) return matte;
		if(type.equals("titledborder")) return titled;
		if(type.equals("ovalborder")) return oval;
		
		throw new Exception("Unknown border type: "+type);
	}
	
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
