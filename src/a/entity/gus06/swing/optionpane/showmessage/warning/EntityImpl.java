package a.entity.gus06.swing.optionpane.showmessage.warning;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150312";}
	
	public static final int TYPE = JOptionPane.WARNING_MESSAGE;
	
	
	public void p(Object obj) throws Exception
	{
		String[] n = toStringArray(obj);
		
		String message = n[0];
		String title = n[1];
		JOptionPane.showMessageDialog(null,message,title,TYPE);
	}
	
	private String[] toStringArray(Object obj) throws Exception
	{
		if(obj instanceof String[]) return (String[]) obj;
		if(obj instanceof String) return new String[]{(String) obj,"Warning"};
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
