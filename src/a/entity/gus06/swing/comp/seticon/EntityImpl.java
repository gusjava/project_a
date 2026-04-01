package a.entity.gus06.swing.comp.seticon;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.Icon;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221003";}

	private Service findIcon;

	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.find.icon");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Icon icon = (Icon) findIcon.t(o[1]);
		
		if(comp instanceof JLabel)		{((JLabel) comp).setIcon(icon);return;}
		if(comp instanceof AbstractButton)	{((AbstractButton) comp).setIcon(icon);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}