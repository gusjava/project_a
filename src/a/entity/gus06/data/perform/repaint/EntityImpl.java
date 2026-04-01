package a.entity.gus06.data.perform.repaint;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170426";}

	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof JComponent)	{repaint((JComponent) obj);return;}
		if(obj instanceof I)		{repaint((I)obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private void repaint(JComponent comp)
	{
		comp.repaint();
	}
	
	private void repaint(I i) throws Exception
	{
		repaint((JComponent) i.i());
	}
}
