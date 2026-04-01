package a.entity.gus06.data.viewer.class1.introspector.listrenderer;

import a.framework.*;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140820";}
	
	public static final Color DEFAULT_COLOR = new Color(153,204,255);
	
	private Service repaintLabel;
	private Service findColor;
	
	private Color color;
	
	
	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.data.viewer.class1.introspector.listrenderer.repaintlabel");
		findColor = Outside.service(this,"gus06.find.color");
		color = DEFAULT_COLOR;
	}
	
	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		if(color==null) color = DEFAULT_COLOR;
		list.setCellRenderer(new ListRenderer1(color));
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color")) {color = (Color) findColor.t(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private void repaintLabel(JLabel label, Object value)
	{
		try{repaintLabel.p(new Object[]{label,value});}
		catch(Exception e)
		{Outside.err(this,"repaintLabel(JLabel,Object)",e);}
	}
	
	
	
	
	private class ListRenderer1 extends JLabel implements ListCellRenderer
	{
		private Color color;
	
		public ListRenderer1(Color color)
		{
			setOpaque(true);
			this.color = color;
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setBackground(bg(isSelected));
			repaintLabel(this,value);
			return this;
		}
		
		private Color bg(boolean isSelected)
		{return isSelected?color:Color.WHITE;}
	}
}
