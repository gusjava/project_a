package a.entity.gus06.sys.expression1.apply.op._jsplitpane;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180402";}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[]) return arrayToJSplitPane((Object[]) obj);
		if(obj instanceof List) return listToJSplitPane((List) obj);
		if(obj instanceof Map) return mapToJSplitPane((Map) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JSplitPane arrayToJSplitPane(Object[] array) throws Exception
	{
		if(array.length!=2) throw new Exception("Invalid array length: "+array.length);
		JSplitPane p = new JSplitPane();
		p.setLeftComponent((JComponent) array[0]);
		p.setRightComponent((JComponent) array[1]);
		return p;
	}
	
	
	private JSplitPane listToJSplitPane(List list) throws Exception
	{
		if(list.size()!=2) throw new Exception("Invalid array length: "+list.size());
		JSplitPane p = new JSplitPane();
		p.setLeftComponent((JComponent) list.get(0));
		p.setRightComponent((JComponent) list.get(1));
		return p;
	}
	
	
	private JSplitPane mapToJSplitPane(Map map) throws Exception
	{
		JSplitPane p = new JSplitPane();
		p.setLeftComponent((JComponent) get(map,"left"));
		p.setRightComponent((JComponent) get(map,"right"));
		
		String orientation = (String) get0(map,"orientation");
		if(orientation!=null)
		{
			if(orientation.equals("h"))
				p.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			else if(orientation.equals("v"))
				p.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		
		Object dividerLoc = get0(map,"dividerloc");
		if(dividerLoc!=null)
		{
			if(dividerLoc instanceof Integer)
				p.setDividerLocation(((Integer) dividerLoc).intValue());
			else if(dividerLoc instanceof Double)
				p.setDividerLocation(((Double) dividerLoc).doubleValue());
		}
		
		Integer dividerSize = (Integer) get0(map,"dividersize");
		if(dividerSize!=null)
		{
			p.setDividerSize(dividerSize.intValue());
		}
		
		Boolean oneTouchExpandable = (Boolean) get0(map,"onetouchexpandable");
		if(oneTouchExpandable!=null)
		{
			p.setOneTouchExpandable(oneTouchExpandable.booleanValue());
		}
		
		return p;
	}
	
	
	private Object get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found inside map: "+key);
		return map.get(key);
	}
	
	
	private Object get0(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
