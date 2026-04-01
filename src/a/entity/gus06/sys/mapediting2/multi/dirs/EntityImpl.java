package a.entity.gus06.sys.mapediting2.multi.dirs;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, V, I {

	public String creationDate() {return "20220504";}


	private JPanel panel;
	
	private Map map;
	private String mapKey;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		refresh();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("mapKey")) {mapKey = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void refresh() throws Exception
	{
		if(mapKey==null) {reset();return;}
		
		String value = get(mapKey);
	}
	
	private void reset() throws Exception
	{
	}
	
	private String get(String key)
	{
		if(map==null) return null;
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}