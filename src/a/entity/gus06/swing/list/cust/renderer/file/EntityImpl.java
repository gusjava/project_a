package a.entity.gus06.swing.list.cust.renderer.file;

import a.framework.*;

import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Icon;


public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140830";}
	
	public static final Color DEFAULT_COLOR = new Color(153,204,255);
	
	private Service labelDisplay;
	private Service findColor;
	private Service findIcon;
	
	private Color color;
	private Icon icon;
	
	
	public EntityImpl() throws Exception
	{
		labelDisplay = Outside.service(this,"gus06.swing.label.cust3.filedisplay");
		findColor = Outside.service(this,"gus06.find.color");
		findIcon = Outside.service(this,"gus06.find.icon");
		color = DEFAULT_COLOR;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		if(color==null) color = DEFAULT_COLOR;
		list.setCellRenderer(new ListRenderer1(color,icon));
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color")) {color = (Color) findColor.t(obj);return;}
		if(key.equals("icon")) {icon = (Icon) findIcon.t(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void paintLabel(JLabel label, File file)
	{
		try{labelDisplay.p(new Object[]{label,file});}
		catch(Exception e){Outside.err(this,"paintLabel(JLabel,File)",e);}
	}
	
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		private Color color;
		private Icon icon;
	
		public ListRenderer1(Color color, Icon icon)
		{
			setOpaque(true);
			this.color = color;
			this.icon = icon;
			
			setIcon(icon);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setBackground(bg(isSelected));
			File f = (File) value;
			
			if(icon==null) paintLabel(this,f);
			else setText(f.getName());
			
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?color:Color.WHITE;}
	}
}
