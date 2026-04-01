package a.entity.gus06.sys.expression1.apply.op._jtextfield;

import a.framework.*;
import java.util.Map;
import javax.swing.JTextField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160907";}

	public final static String KEY_HANDLE = "handle";
	
	
	private Service linkToP;
	private Service custComp;
	
	public EntityImpl() throws Exception
	{
		linkToP = Outside.service(this,"gus06.swing.textfield.cust3.handle2.onenter");
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
		if(obj instanceof P) return build((P) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private JTextField build(Map map) throws Exception
	{
		JTextField field = new JTextField();
		custComp.p(new Object[]{field,map});
		
		P p = (P) get(map,KEY_HANDLE);
		if(p!=null) linkToP.p(new Object[]{field,p});
		
		return field;
	}
	
	private JTextField build(P p) throws Exception
	{
		JTextField field = new JTextField();
		linkToP.p(new Object[]{field,p});
		return field;
	}
	
	private JTextField build(String text) throws Exception
	{
		JTextField field = new JTextField();
		field.setText(text);
		return field;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
