package a.entity.gus06.swing.comp.setmargin;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import java.awt.Insets;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231130";}


	private Service findInsets;

	public EntityImpl() throws Exception
	{
		findInsets = Outside.service(this,"gus06.find.insets");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object comp = o[0];
		Insets margin = (Insets) findInsets.t(o[1]);
		
		if(comp instanceof JTextComponent)	{((JTextComponent) comp).setMargin(margin);return;}
			
		throw new Exception("Invalid data type: "+comp.getClass().getName());
	}
}
