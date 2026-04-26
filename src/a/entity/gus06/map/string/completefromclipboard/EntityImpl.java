package a.entity.gus06.map.string.completefromclipboard;

import a.framework.*;
import java.util.*;
import javax.swing.JOptionPane;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140911";}

	public static final String MESSAGE = "Attempt to import already existing keys with different values:";
	public static final String TITLE = "Key confusion detected";
	public static final String[] OPTIONS = new String[]{"Cancel","Keep new values","Keep old values"};


	private Service clipboard;
	private Service stringToMap;


	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		stringToMap = Outside.service(this,"gus06.convert.stringtomap.tab");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String s = (String) clipboard.g();
		if(s==null || s.equals("")) return;
		
		Map m = (Map) stringToMap.t(s);
		if(!hasConflict(map,m))
		{map.putAll(m);return;}
    		
    		int r = JOptionPane.showOptionDialog(null,MESSAGE,TITLE,
    				JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,OPTIONS,OPTIONS[1]);
    		switch(r) {
			case 0:break;
			case 1:map.putAll(m);break;
			case 2:complete(map,m);break;
		}
	}
	
	
	
	private void complete(Map map, Map m)
	{
		Iterator it = m.keySet().iterator();
    		while(it.hasNext())
    		{
    			String key = (String) it.next();
    			if(!map.containsKey(key)) map.put(key,m.get(key));
    		}
	}
	
	
	
	private boolean hasConflict(Map map1, Map map2)
	{
		Iterator it = map1.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = (String) map1.get(key);
			if(map2.containsKey(key) && !map2.get(key).equals(value))
				return true;
		}
		return false;
	}
}