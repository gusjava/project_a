package a.entity.gus06.sys.expression1.apply.op._jtextarea;

import a.framework.*;
import java.util.Map;
import javax.swing.JTextArea;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180211";}


	private Service custComp;
	private Service inputStreamToArea;
	
	public EntityImpl() throws Exception
	{
		custComp = Outside.service(this,"gus06.swing.textcomp.cust3.map1");
		inputStreamToArea = Outside.service(this,"gus06.io.transfer.th.totextarea.utf8");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof String) return build((String) obj);
		if(obj instanceof Map) return build((Map) obj);
		if(obj instanceof InputStream) return inputStreamToArea.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JTextArea build(Map map) throws Exception
	{
		JTextArea area = new JTextArea();
		custComp.p(new Object[]{area,map});
		return area;
	}
	
	private JTextArea build(String text) throws Exception
	{
		JTextArea area = new JTextArea();
		area.setText(text);
		return area;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
