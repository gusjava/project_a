package a.entity.gus06.swing.comp.settext1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180413";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		String text = (String) o[1];
		
		if(comp instanceof JLabel)		{handle((JLabel) comp,text);return;}
		if(comp instanceof JTextComponent)	{handle((JTextComponent) comp,text);return;}
		if(comp instanceof AbstractButton)	{handle((AbstractButton) comp,text);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
	
	
	
	private void handle(JLabel comp, String text)
	{
		String text1 = comp.getText();
		if(!text1.equals(text)) comp.setText(text);
	}
	
	private void handle(JTextComponent comp, String text)
	{
		String text1 = comp.getText();
		if(!text1.equals(text)) comp.setText(text);
	}
	
	private void handle(AbstractButton comp, String text)
	{
		String text1 = comp.getText();
		if(!text1.equals(text)) comp.setText(text);
	}
}
