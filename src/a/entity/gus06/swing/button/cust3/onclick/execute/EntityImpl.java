package a.entity.gus06.swing.button.cust3.onclick.execute;

import a.framework.*;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160830";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		AbstractButton comp = (AbstractButton) o[0];
		E ex = (E) o[1];
		
		new Holder(comp,ex);
	}
	

	private class Holder implements ActionListener
	{
		private E ex;
		private AbstractButton comp;
		
		public Holder(AbstractButton comp, E ex)
		{
			this.ex = ex;
			this.comp = comp;
			comp.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{execute(ex);}
	}
	
	
	private void execute(E ex)
	{
		try{ex.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}
