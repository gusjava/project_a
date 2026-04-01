package a.entity.gus06.swing.comp.setborder;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Font;
import javax.swing.border.Border;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250720";}


	private Service findBorder;

	public EntityImpl() throws Exception
	{
		findBorder = Outside.service(this,"gus06.find.border");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Border border = (Border) findBorder.t(o[1]);
		
		if(comp instanceof JComponent)		{((JComponent) comp).setBorder(border);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}
