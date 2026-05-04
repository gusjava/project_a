package a.entity.gus.x.swing.comp.cust3.on.mouseentered.execute;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import a.framework.*;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20170820";}

	public void p(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wong data number: "+t.length);
		
		JComponent comp = (JComponent) t[0];
		E exec = (E) t[1];
		
		new Holder(comp,exec);
	}
	
	private class Holder extends MouseAdapter
	{
		private JComponent comp;
		private E exec;
		
		public Holder(JComponent comp, E exec)
		{
			this.comp = comp;
			this.exec = exec;
			
			comp.setFocusable(true);
			comp.addMouseListener(this);
		}
		public void mouseEntered(MouseEvent e)
		{
			exec(exec);
		}
	}
	
	private void exec(E exec)
	{
		try{exec.e();}
		catch(Exception e)
		{Outside.err(this,"exec(E)",e);}
	}
}
