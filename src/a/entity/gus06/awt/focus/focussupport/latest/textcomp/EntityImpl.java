package a.entity.gus06.awt.focus.focussupport.latest.textcomp;

import java.util.List;
import a.framework.*;
import javax.swing.text.JTextComponent;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170314";}

	
	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.awt.focus.focussupport.history");
	}
	
	
	public Object g() throws Exception
	{
		List list = (List) find.g();
		
		int nb = list.size();
		for(int i=0;i<nb;i++)
		{
			Object element = list.get(nb-i-1);
			if(element instanceof JTextComponent) return element;
		}
		return null;
	}
}
