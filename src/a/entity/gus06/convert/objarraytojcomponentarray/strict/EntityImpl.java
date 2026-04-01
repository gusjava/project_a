package a.entity.gus06.convert.objarraytojcomponentarray.strict;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160830";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		Object[] oo = (Object[]) obj;
		int length = oo.length;
		
		JComponent[] yy = new JComponent[length];
		for(int i=0;i<length;i++)
		{
			if(!(oo[i] instanceof JComponent)) return null;
			yy[i] = (JComponent) oo[i];
		}
		return yy;
	}
}
