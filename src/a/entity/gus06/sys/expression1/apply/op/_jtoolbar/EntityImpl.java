package a.entity.gus06.sys.expression1.apply.op._jtoolbar;

import a.framework.*;
import javax.swing.Action;
import java.util.List;
import javax.swing.JToolBar;
import java.awt.Component;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200327";}

	
	private Service build;
	
	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.swing.toolbar.toolbar1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Action) return build((Action) obj);
		if(obj instanceof List) return build((List) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private JToolBar build(Action action) throws Exception
	{
		JToolBar toolbar = (JToolBar) build.i();
		toolbar.add(action);
		return toolbar;
	}
	
	
	private JToolBar build(List list) throws Exception
	{
		JToolBar toolbar = (JToolBar) build.i();
		for(int i=0;i<list.size();i++)
		{
			Object element = list.get(i);
			if(element instanceof Action)
				toolbar.add((Action) element);
			if(element instanceof Component)
				toolbar.add((Component) element);
			else if(element.equals("|"))
				toolbar.addSeparator();
		}
		return toolbar;
	}
}
