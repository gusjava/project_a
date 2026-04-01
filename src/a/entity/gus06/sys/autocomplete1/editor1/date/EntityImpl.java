package a.entity.gus06.sys.autocomplete1.editor1.date;

import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160622";}
	

	
	
	public EntityImpl() throws Exception
	{
	}

	
	public void p(Object obj) throws Exception
	{
		JTextComponent comp = (JTextComponent) obj;
		String text = comp.getSelectedText();
		
		//A FAIRE ...
		// d�tecter des formats de date FR / EN
		
	}
}
