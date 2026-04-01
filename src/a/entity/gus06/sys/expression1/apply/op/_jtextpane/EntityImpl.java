package a.entity.gus06.sys.expression1.apply.op._jtextpane;

import a.framework.*;
import java.util.Map;
import javax.swing.JTextPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180402";}


	private Service custComp;
	
	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.swing.textcomp.cust3.map1");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return build((String) obj);
		if(obj instanceof Map) return build((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JTextPane build(Map map) throws Exception
	{
		JTextPane pane = new JTextPane();
		custComp.p(new Object[]{pane,map});
		return pane;
	}
	
	private JTextPane build(String text) throws Exception
	{
		JTextPane pane = new JTextPane();
		pane.setText(text);
		return pane;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
