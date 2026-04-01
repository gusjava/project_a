package a.entity.gus06.swing.comp.settext;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170806";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		String text = (String) o[1];
		
		if(comp instanceof JLabel)		{((JLabel) comp).setText(text);return;}
		if(comp instanceof JTextComponent)	{((JTextComponent) comp).setText(text);return;}
		if(comp instanceof AbstractButton)	{((AbstractButton) comp).setText(text);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}
