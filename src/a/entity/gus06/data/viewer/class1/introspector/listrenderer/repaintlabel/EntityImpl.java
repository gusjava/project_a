package a.entity.gus06.data.viewer.class1.introspector.listrenderer.repaintlabel;

import a.framework.*;

import java.awt.Font;
import java.awt.Component;
import javax.swing.JLabel;
import java.lang.reflect.*;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140821";}
	
	
	private Service repaintLabel;
	private Service findDisplay;
	
	
	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		findDisplay = Outside.service(this,"gus06.data.viewer.class1.introspector.listrenderer.finddisplay");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		repaintLabel((JLabel) o[0], o[1]);
	}
	
	
	
	
	private void repaintLabel(JLabel label, Object value)
	{
		try
		{
			String display = (String) findDisplay.t(value);
			repaintLabel.v(display,label);
			
			if(isStatic(value))
				label.setFont(label.getFont().deriveFont(Font.ITALIC));
			else label.setFont(label.getFont().deriveFont(Font.PLAIN));
		}
		catch(Exception e)
		{Outside.err(this,"repaintLabel(JLabel,Object)",e);}
	}
	
	
	
	private boolean isStatic(Object value)
	{
		if(value==null) return false;
		if(value instanceof Field)
			return Modifier.isStatic(((Field) value).getModifiers());
		if(value instanceof Method)
			return Modifier.isStatic(((Method) value).getModifiers());
		return false;
	}
}
