package a.entity.gus06.data.perform.show;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Window;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.swing.frame.show");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof I)
		{perform.p(obj);return;}
		
		if(obj instanceof JComponent)
		{perform.p(obj);return;}
		
		if(obj instanceof JFrame)
		{perform.p(obj);return;}
		
		if(obj instanceof Window)
		{showWindow((Window) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void showWindow(Window window)
	{
		window.setVisible(true);
	}
}
