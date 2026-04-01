package a.entity.gus06.swing.combobox.cust3.display;

import a.framework.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JList;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180415";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JComboBox combo = (JComboBox) o[0];
		T display = (T) o[1];
	
		combo.setRenderer(new Renderer(display));
		combo.setFont(combo.getFont().deriveFont(Font.PLAIN));
		combo.setBackground(Color.WHITE);
	}


	private class Renderer extends JLabel implements ListCellRenderer
	{
		private T display;
		
		public Renderer(T display)
		{
			setOpaque(true);
			setFont(getFont().deriveFont(Font.PLAIN));
			this.display = display;
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			setText(buildDisplay(display,value));
			setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			return this;
		}
	}
	
	
	
		
	private String buildDisplay(T display, Object value)
	{
		try
		{
			if(value==null) return " ";
			return (String) display.t(value);
		}
		catch(Exception e)
		{Outside.err(this,"buildDisplay(T,Object)",e);}
		return "###";
	}
}
