package a.entity.gus06.swing.panel.build.frommap;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180401";}

	
	public static final String KEY_NORTH = "north";
	public static final String KEY_SOUTH = "south";
	public static final String KEY_EAST = "east";
	public static final String KEY_WEST = "west";
	public static final String KEY_CENTER = "center";
	
	public static final String KEY_TITLE = "title";
	

	private Service buildTitled;
	
	public EntityImpl() throws Exception
	{
		buildTitled = Outside.service(this,"gus06.swing.comp.build.titledpanel");
	}



	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		
		JComponent north = toComp(get(map,KEY_NORTH));
		JComponent south = toComp(get(map,KEY_SOUTH));
		JComponent east = toComp(get(map,KEY_EAST));
		JComponent west = toComp(get(map,KEY_WEST));
		JComponent center = toComp(get(map,KEY_CENTER));
		
		JPanel panel = new JPanel(new BorderLayout());
		if(north!=null)		panel.add(north,BorderLayout.NORTH);
		if(south!=null)		panel.add(south,BorderLayout.SOUTH);
		if(east!=null)		panel.add(east,BorderLayout.EAST);
		if(west!=null)		panel.add(west,BorderLayout.WEST);
		if(center!=null)	panel.add(center,BorderLayout.CENTER);
		
		String title = (String) get(map,KEY_TITLE);
		if(title!=null) return buildTitled.t(new Object[]{panel,title});
		
		return panel;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof String) return new JLabel((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
