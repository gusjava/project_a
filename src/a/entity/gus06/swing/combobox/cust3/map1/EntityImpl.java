package a.entity.gus06.swing.combobox.cust3.map1;

import a.framework.*;
import javax.swing.JComboBox;
import java.util.Map;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180415";}
	
	public final static String KEY_DATA = "data";
	public final static String KEY_DISPLAY = "display";


	private Service initData;
	private Service initDisplay;
	private Service defaultCust;

	public EntityImpl() throws Exception
	{
		initData = Outside.service(this,"gus06.swing.combobox.cust3.initdata");
		initDisplay = Outside.service(this,"gus06.swing.combobox.cust3.display");
		defaultCust = Outside.service(this,"gus06.swing.comp.cust3.map1");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComboBox comp = (JComboBox) o[0];
		Map map = (Map) o[1];
		
		defaultCust.p(new Object[]{comp,map});
		
		Object data = get(map,KEY_DATA);
		T display = (T) get(map,KEY_DISPLAY);
		
		if(data!=null)
		{
			initData.p(new Object[]{comp, data});
		}
		if(display!=null)
		{
			initDisplay.p(new Object[]{comp, display});
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
