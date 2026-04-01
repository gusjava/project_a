package a.entity.gus06.sys.expression1.apply.op._jpanel_v;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.List;
import java.awt.GridLayout;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250718";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.jcomponent");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return listToJPanel((List) obj);
		if(obj instanceof Object[]) return listToJPanel((Object[]) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private JPanel listToJPanel(List list) throws Exception
	{
		int nb = list.size();
		JPanel p = new JPanel(new GridLayout(nb,1));
		for(int i=0;i<nb;i++)
		{
			JComponent comp = (JComponent) find.t(list.get(i));
			p.add(comp);
		}
		return p;
	}
	
	
	private JPanel listToJPanel(Object[] array) throws Exception
	{
		int nb = array.length;
		JPanel p = new JPanel(new GridLayout(nb,1));
		for(int i=0;i<nb;i++)
		{
			JComponent comp = (JComponent) find.t(array[i]);
			p.add(comp);
		}
		return p;
	}
}