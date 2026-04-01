package a.entity.gus06.input.text.dialog.from;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20201018";}
	
	public static final String MESSAGE = "Please, change value";
	
	
	public Object t(Object obj) throws Exception
	{
		String initValue = (String) obj;
		return JOptionPane.showInputDialog(MESSAGE,initValue);
	}
	
	public Object g() throws Exception
	{
		return JOptionPane.showInputDialog(MESSAGE,"");
	}
}
