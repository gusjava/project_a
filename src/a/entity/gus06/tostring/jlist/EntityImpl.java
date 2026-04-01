package a.entity.gus06.tostring.jlist;

import a.framework.*;
import javax.swing.JList;
import javax.swing.ListModel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160603";}

	
	
	public Object t(Object obj) throws Exception
	{
		JList list = (JList) obj;
		ListModel model = list.getModel();
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<model.getSize();i++)
		b.append(model.getElementAt(i)+"\n");
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
