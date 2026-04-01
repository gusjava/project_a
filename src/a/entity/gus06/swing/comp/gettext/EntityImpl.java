package a.entity.gus06.swing.comp.gettext;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;
import javax.swing.JLabel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161127";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof JLabel)
			return ((JLabel) obj).getText();
		if(obj instanceof JTextComponent)
			return ((JTextComponent) obj).getText();
		if(obj instanceof AbstractButton)
			return ((AbstractButton) obj).getText();
			
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
