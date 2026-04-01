package a.entity.gus06.sys.popup1.gui.buildnotif.message;

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
	
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_SIZE = "size";
	public static final String KEY_MESSAGE_SIZE = "message_size";
	public static final String KEY_FOREGROUND = "foreground";
	public static final String KEY_MESSAGE_FOREGROUND = "message_foreground";
	public static final String KEY_EXECUTE = "execute";
	
	public static final Font FONT = new Font("Calibri", Font.PLAIN, 16);
	

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
		
		Object message = get(notif,KEY_MESSAGE);
		Integer size = (Integer) get(notif,KEY_MESSAGE_SIZE);
		Color foreground = (Color) findColor.t(get(notif,KEY_MESSAGE_FOREGROUND));
		
		return buildCompMessage(message, size, foreground);
	}
	
	
	private JComponent buildCompMessage(Object data, Integer size, Color foreground) throws Exception
	{
		if(data==null) return new JLabel(" ");
		if(data instanceof JComponent) return (JComponent) data;
		if(data instanceof String) return buildCompMessageFromString((String) data, size, foreground);
		if(data instanceof List) return buildCompMessageFromList((List) data, size, foreground);
		if(data instanceof Map) return buildCompMessageFromMap((Map) data, size, foreground);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
		
	}
	
	private JComponent buildCompMessageFromString(String message, Integer size, Color foreground) throws Exception
	{
		JLabel label = new JLabel("");
		label.setFont(buildFont(FONT,size));
		label.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		if(foreground!=null) label.setForeground(foreground);
		if(message!=null) labelRender.v(message,label);
		return label;
	}
	
	private JComponent buildCompMessageFromList(List list, Integer size, Color foreground) throws Exception
	{
		if(list.isEmpty()) return new JLabel(" ");
		if(list.size()==1) return buildCompMessage(list.get(0), size, foreground);
		
		int nb = list.size();
		JPanel panel = new JPanel(new GridLayout(nb,0));
		panel.setOpaque(false);
		for(int i=0;i<nb;i++) panel.add(buildCompMessage(list.get(i), size, foreground));
		return panel;
	}
	
	private JComponent buildCompMessageFromMap(Map map, Integer size, Color foreground) throws Exception
	{
		String message = (String) get(map,KEY_MESSAGE);
		E execute = (E) get(map,KEY_EXECUTE);
		size = (Integer) get(map,KEY_SIZE,size);
		foreground = (Color) findColor.t(get(map,KEY_FOREGROUND,foreground));
		
		JLabel label = new JLabel("");
		label.setFont(buildFont(FONT,size));
		label.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		if(foreground!=null) label.setForeground(foreground);
		if(message!=null) labelRender.v(message,label);
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