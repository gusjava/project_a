package a.entity.gus06.swing.comp.setforeground;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Color;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20221003";}


	private Service findColor;

	public EntityImpl() throws Exception
	{
		findColor = Outside.service(this,"gus06.find.color");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Color color = (Color) findColor.t(o[1]);
		
		if(comp instanceof JComponent)		{((JComponent) comp).setForeground(color);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}