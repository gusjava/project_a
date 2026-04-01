package a.entity.gus06.swing.comp.contains.comptof;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Component;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170314";}
	
	
	public Object t(Object obj) throws Exception
	{return new F1((JComponent) obj);}
	
	
	private class F1 implements F
	{
		private JComponent comp;
		public F1(JComponent comp){this.comp = comp;}
		
		public boolean f(Object obj) throws Exception
		{return contains(comp,(Component) obj);}
	}
	
	private boolean contains(JComponent comp, Component obj)
	{
		for(int i=0;i<comp.getComponentCount();i++)
		{
			Component child = comp.getComponent(i);
			if(obj.equals(child)) return true;
		}
		return false;
	}
}
