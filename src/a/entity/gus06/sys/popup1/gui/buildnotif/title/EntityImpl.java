package a.entity.gus06.sys.popup1.gui.buildnotif.title;

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

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250330";}
	
	public static final String KEY_TITLE = "title";
	public static final String KEY_SIZE = "size";
	public static final String KEY_TITLE_SIZE = "title_size";
	public static final String KEY_FOREGROUND = "foreground";
	public static final String KEY_TITLE_FOREGROUND = "title_foreground";
	public static final String KEY_EXECUTE = "execute";
	
	public static final Font FONT = new Font("Calibri", Font.BOLD, 16);
	

	private Service labelRender;
	private Service executeOnClick;
	private Service findColor;

	public EntityImpl() throws Exception
	{
		labelRender = Outside.service(this,"gus06.swing.label.cust2.display");
		executeOnClick = Outside.service(this,"gus06.swing.label.cust3.onclick.execute");
		findColor = Outside.service(this,"gus06.find.color");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map notif = (Map) obj;
		
		String title = (String) get(notif,KEY_TITLE);
		Integer size = (Integer) get(notif,KEY_TITLE_SIZE);
		Color foreground = (Color) findColor.t(get(notif,KEY_TITLE_FOREGROUND));
		E execute = (E) get(notif,KEY_EXECUTE);
		
		JLabel label = new JLabel("");
		label.setFont(buildFont(FONT,size));
		label.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		if(foreground!=null) label.setForeground(foreground);
		if(title!=null) labelRender.v(title,label);
		if(execute!=null) executeOnClick.p(new Object[]{label,execute});
		
		return label;
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get(Map map, String key, Object defaultValue)
	{
		if(!map.containsKey(key)) return defaultValue;
		return map.get(key);
	}
	
	private Font buildFont(Font defaultFont, Integer size)
	{
		if(size==null) return defaultFont;
		return defaultFont.deriveFont((float) size);
	}
}