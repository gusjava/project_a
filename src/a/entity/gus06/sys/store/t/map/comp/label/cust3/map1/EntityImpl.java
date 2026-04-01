package a.entity.gus06.sys.store.t.map.comp.label.cust3.map1;

import a.framework.*;
import javax.swing.JLabel;
import java.util.Map;
import java.util.Iterator;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170806";}


	private Service repaint;

	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JLabel label = (JLabel) o[0];
		Map map = (Map) o[1];
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map.get(key);
			cust(label,key,value);
		}
	}
	
	
	
	private void cust(JLabel label, String key, String value) throws Exception
	{
		if(matches(key,"display"))			custDisplay(label,value);
		else if(matches(key,"horizontal-alignment"))	custAlignH(label,value);
		else if(matches(key,"vertical-alignment"))	custAlignV(label,value);
	}
	
	private boolean matches(String key, String type)
	{return key.equals(type) || key.endsWith("."+type);}
	
	
	
	private void custDisplay(JLabel label, String value) throws Exception
	{repaint.v(value,label);}
	
	private void custAlignH(JLabel label, String value) throws Exception
	{label.setHorizontalAlignment(int_(value));}
	
	private void custAlignV(JLabel label, String value) throws Exception
	{label.setVerticalAlignment(int_(value));}
	
	private int int_(String value)
	{return Integer.parseInt(value);}
}
