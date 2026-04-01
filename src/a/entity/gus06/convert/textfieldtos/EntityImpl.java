package a.entity.gus06.convert.textfieldtos;

import a.framework.*;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250706";}

	
	
	public Object t(Object obj) throws Exception
	{
		JTextField field = (JTextField) obj;
		return new Holder(field);
	}
	
	
	private class Holder implements S, G, P
	{
		private JTextField field;
		public Holder(JTextField field)
		{this.field = field;}
		
		public void addActionListener(ActionListener listener)
		{field.addActionListener(listener);}
		
		public void removeActionListener(ActionListener listener)
		{field.removeActionListener(listener);}
		
		public List listeners()
		{
			ActionListener[] arr = field.getActionListeners();
			return new ArrayList(Arrays.asList(arr));
		}
		
		public Object g() throws Exception
		{return field.getText();}
		
		public void p(Object obj) throws Exception
		{field.setText((String) obj);}
	}
}