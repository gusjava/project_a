package a.entity.gus06.ling.localize.perform;

import a.framework.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, V {

	public String creationDate() {return "20140808";}

	private Service lingString;

	public EntityImpl() throws Exception
	{
		lingString = Outside.service(this,"gus06.ling.find.lingstring");
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String value = (String) lingString.r(key);
	
		if(obj instanceof JMenu) ((JMenu) obj).setText(value);
		else if(obj instanceof JMenuItem) ((JMenuItem) obj).setText(value);
		else if(obj instanceof JLabel) ((JLabel) obj).setText(value);
		else if(obj instanceof AbstractButton) ((AbstractButton) obj).setText(value);
		
		
		else if(obj instanceof Action)
		{
			Action action = (Action) obj;
			action.putValue(Action.NAME,value);
		}
		else if(obj instanceof JTextComponent)
		{
			JTextComponent comp = (JTextComponent) obj;
			comp.setText(value);
			comp.setCaretPosition(0);
		}
		
		else throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
}
