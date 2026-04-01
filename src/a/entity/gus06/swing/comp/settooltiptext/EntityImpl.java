package a.entity.gus06.swing.comp.settooltiptext;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220610";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		String text = (String) o[1];
		
		if(comp instanceof JComponent)		{((JComponent) comp).setToolTipText(text);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}