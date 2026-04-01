package a.entity.gus06.swing.comp.seteditable;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231125";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Boolean editable = (Boolean) o[1];
		
		if(comp instanceof JTextComponent)	{((JTextComponent) comp).setEditable(editable);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}