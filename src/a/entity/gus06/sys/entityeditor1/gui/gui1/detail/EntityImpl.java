package a.entity.gus06.sys.entityeditor1.gui.gui1.detail;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20250925";}

	private Service editor;
	private Object engine;

	public EntityImpl() throws Exception
	{editor = Outside.service(this, "*gus.y.entityeditor1.maingui");}
	
	public Object i() throws Exception
	{return editor.i();}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine")) {engine = obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	public void p(Object obj) throws Exception
	{
		if(engine==null) throw new Exception("Engine not initialized yet");
		String name = toName(obj);
		editor.p(new Object[] {engine, name});
	}
	
	private String toName(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof String) return (String) obj;
		if(obj instanceof List) return listToName((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String listToName(List list) throws Exception
	{
		if(list.isEmpty()) return null;
		return (String) list.get(0);
	}
}
