package a.entity.gus06.appli.gusdbmanager.gui.gui1.list.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import a.framework.*;

public class EntityImpl implements Entity, P, ActionListener {

	public String creationDate() {return "20150613";}

	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	

	private Service repaintLabel;
	private Service findDisplay;
	private Service manager;
	private Service statusColor;
	
	private JList list;
	
	

	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		findDisplay = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors.builddisplay");
		manager = Outside.service(this,"gus06.appli.gusdbmanager.connection.manager");
		statusColor = Outside.service(this,"gus06.appli.gusdbmanager.connection.statuscolor");
		
		manager.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{repaintList();}

	
	
	private void repaintList()
	{if(list!=null) list.repaint();}
	
	
	
	
	
	private Color foreground(String id)
	{
		try
		{
			String status = (String) manager.r("status_"+id);
			return (Color) statusColor.t(status);
		}
		catch(Exception e)
		{Outside.err(this,"foreground(String)",e);}
		return Color.GRAY;
	}
	
	

	

	public void p(Object obj) throws Exception
	{
		list = (JList) obj;
		list.setCellRenderer(new ListRenderer0());
	}

	
	
	
	private class ListRenderer0 extends JLabel implements ListCellRenderer
	{
		public ListRenderer0()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			String id = (String) value;
			updateLabel(this,id);
			setBackground(isSelected?SELECTION_COLOR:Color.WHITE);
			setForeground(foreground(id));
			return this;
		}
	}

	
	
	
	private void updateLabel(JLabel label, String id)
	{
		try
		{
			String display = (String) findDisplay.t(id);
			repaintLabel.v(display,label);
		}
		catch(Exception e)
		{Outside.err(this,"updateLabel(JLabel,String)",e);}
	}
	
}
