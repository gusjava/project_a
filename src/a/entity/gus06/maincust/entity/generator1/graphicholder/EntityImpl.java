package a.entity.gus06.maincust.entity.generator1.graphicholder;

import a.framework.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140811";}


	private Service isInside;

	public EntityImpl() throws Exception
	{
		isInside = Outside.service(this,"gus06.reflection.field.isfield");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String name = (String) o[0];
		G prov = (G) o[1];
		return new Holder(name,prov);
	}
	
	
	
	
	
	private class Holder implements I, ActionListener
	{
		private JPanel1 panel;
		private String name;
		private G prov;
	
		public Holder(String name, G prov) throws Exception
		{
			this.name = name;
			this.prov = prov;
			
			((S)prov).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{changed();}
		
		
		
		public Object i() throws Exception
		{
			Object o = prov.g();
			if(!(o instanceof I)) throw new Exception("Feature I not available for entity: "+name);
		
			I i = (I) o;
			JComponent c = (JComponent) i.i();
		
			if(!isFieldPanel(i,c))
			{panel = null;return c;}
			
			if(panel==null) panel = new JPanel1();
			panel.setPanel(c);
			return panel;
		}
		

		
		private void changed()
		{
			try
			{
				if(panel==null) return;
				
				Object o = prov.g();
				
				if(!(o instanceof I))
				{panel = null;return;}
		
				I i = (I) o;
				JComponent c = (JComponent) i.i();
				
				if(!isFieldPanel(i,c))
				{panel = null;return;}
			
				panel.setPanel(c);
			}
			catch(Exception e) {Outside.err(EntityImpl.this,"changed()",e);}
		}
			
		
			
		private boolean isFieldPanel(I i, JComponent c) throws Exception
		{
			if(!(c instanceof JPanel) && !(c instanceof JTabbedPane)) return false;
			return isInside.f(new Object[]{i,c});
		}
	}
			
			
			
			
	private class JPanel1 extends JPanel
	{
		public JPanel1() {super(new BorderLayout());}
		
		public void setPanel(JComponent p)
		{
			removeAll();
			if(p!=null)
			add(p,BorderLayout.CENTER);
			
			synchronized(getTreeLock())
			{validateTree();}

			if(isDisplayable())
			{validate();repaint();}
		}
	}
}