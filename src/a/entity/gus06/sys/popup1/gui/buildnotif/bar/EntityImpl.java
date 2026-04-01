package a.entity.gus06.sys.popup1.gui.buildnotif.bar;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComponent;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250330";}
	
	public static final String KEY_PROGRESS = "progress";
	public static final String KEY_PROGRESS_HEIGHT = "progress_height";
	public static final String KEY_PROGRESS_FOREGROUND = "progress_foreground";
	public static final String KEY_PROGRESS_BACKGROUND = "progress_background";
	
	
	private Service findColor;

	public EntityImpl() throws Exception
	{
		findColor = Outside.service(this,"gus06.find.color");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map notif = (Map) obj;
		
		Object progress = get(notif,KEY_PROGRESS);
		if(progress==null) return null;
		
		JComponent comp = (JComponent) ((I)progress).i();
		comp.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		comp.setOpaque(true);
		
		Integer progress_height = (Integer) get(notif,KEY_PROGRESS_HEIGHT);
		Color foreground = (Color) findColor.t(get(notif,KEY_PROGRESS_FOREGROUND));
		Color background = (Color) findColor.t(get(notif,KEY_PROGRESS_BACKGROUND));
		
		if(progress_height!=null)
		{
			int h = progress_height.intValue();
			int w = (int) comp.getPreferredSize().getWidth();
			Dimension d = new Dimension(w,h);
			
			comp.setPreferredSize(d);
			comp.setMinimumSize(d);
			comp.setMaximumSize(d);
		}
		if(foreground!=null)
		{
			comp.setForeground(foreground);
		}
		if(background!=null)
		{
			comp.setBackground(background);
		}
		
		return comp;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}