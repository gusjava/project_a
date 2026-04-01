package a.entity.gus06.input.text.dialog;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20140801";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String)
			return JOptionPane.showInputDialog((String) obj);
		if(obj instanceof String[])
		{
			String[] o = (String[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
			
			String message = o[0];
			String initValue = o[1];
			return JOptionPane.showInputDialog(message,initValue);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	public Object g() throws Exception
	{
		return JOptionPane.showInputDialog("");
	}
}
