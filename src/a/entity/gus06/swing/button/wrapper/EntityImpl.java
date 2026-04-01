package a.entity.gus06.swing.button.wrapper;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((AbstractButton) obj);}
	
	
	private class Holder extends S1 implements ActionListener, P, G, I
	{
		private AbstractButton comp;
		
		public Holder(AbstractButton comp)
		{
			this.comp = comp;
			comp.addActionListener(this);
		}
		
		public Object i() throws Exception
		{return comp;}
		
		public Object g() throws Exception
		{return ""+comp.isSelected();}

		public void p(Object obj) throws Exception
		{
			boolean value = obj.equals("true");
			if(comp.isSelected()==value) return;
			
			setActivated(false);
			comp.setSelected(value);
			setActivated(true);
			dataModified();
		}

		public void actionPerformed(ActionEvent e)
		{
			dataEdited();
			dataModified();
		}
		
		private void dataModified()
		{send(this,"dataModified()");}
		
		private void dataEdited()
		{send(this,"dataEdited()");}
	}
}