package a.entity.gus06.find.jlabel;

import a.framework.*;
import java.util.Map;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Icon;
import java.awt.Color;
import java.net.URL;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191125";}

	
	private Service repaint;
	private Service custLabel;
	private Service gToLabel;
	private Service urlToLabel;
	private Service fileToLabel;
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
		custLabel = Outside.service(this,"gus06.swing.label.cust3.map1");
		gToLabel = Outside.service(this,"gus06.swing.label.build.watcher.g");
		urlToLabel = Outside.service(this,"gus06.swing.label.build.urllabel");
		fileToLabel = Outside.service(this,"gus06.swing.label.build.filelabel.a");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Boolean) return build(""+obj);
		if(obj instanceof Number) return build(""+obj);
		if(obj instanceof String) return build((String) obj);
		if(obj instanceof Font) return build((Font) obj);
		if(obj instanceof Icon) return build((Icon) obj);
		if(obj instanceof Color) return build((Color) obj);
		if(obj instanceof Map) return build((Map) obj);
		
		if(obj instanceof G) return gToLabel.t(obj);
		if(obj instanceof URL) return urlToLabel.t(obj);
		if(obj instanceof File) return fileToLabel.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private JLabel build(String display) throws Exception
	{
		JLabel label = new JLabel();
		repaint.v(display,label);
		return label;
	}
	
	private JLabel build(Icon icon) throws Exception
	{
		JLabel label = new JLabel();
		label.setIcon(icon);
		return label;
	}
	
	private JLabel build(Color color) throws Exception
	{
		JLabel label = new JLabel();
		label.setBackground(color);
		label.setOpaque(true);
		return label;
	}
	
	private JLabel build(Font font) throws Exception
	{
		JLabel label = new JLabel();
		label.setFont(font);
		return label;
	}
	
	private JLabel build(Map map) throws Exception
	{
		JLabel label = new JLabel();
		custLabel.p(new Object[]{label,map});
		return label;
	}
}