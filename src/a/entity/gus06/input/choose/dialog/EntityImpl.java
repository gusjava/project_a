package a.entity.gus06.input.choose.dialog;

import a.framework.*;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140830";}


	private Service findStringArray;
	
	public EntityImpl() throws Exception
	{
		findStringArray = Outside.service(this,"gus06.find.stringarray");
	}

	
	public Object t(Object obj) throws Exception
	{
		if(!(obj instanceof Object[]))
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		
		Object[] o = (Object[]) obj;
		if(o.length==2) 
		{
			String message = (String) o[0];
			String title = "CHOOSER";
			String[] values = (String[]) findStringArray.t(o[1]);
		
			return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,values,values[0]);
		}
		if(o.length==3) 
		{
			String message = (String) o[0];
			String title = (String) o[1];
			String[] values = (String[]) findStringArray.t(o[2]);
		
			return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,values,values[0]);
		}
		if(o.length==4) 
		{
			String message = (String) o[0];
			String title = (String) o[1];
			String[] values = (String[]) findStringArray.t(o[2]);
			String selected = (String) o[3];
		
			return JOptionPane.showInputDialog(null,message,title,JOptionPane.PLAIN_MESSAGE,null,values,selected);
		}
		throw new Exception("Wrong data number: "+o.length);
	}
}