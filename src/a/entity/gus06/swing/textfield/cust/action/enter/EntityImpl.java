package a.entity.gus06.swing.textfield.cust.action.enter;

import a.framework.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250518";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		new Holder((JTextField) o[0], (E) o[1]);
	}


	private class Holder extends AbstractAction
	{
		private JTextField comp;
		private E defaultE;
		
		public Holder(JTextField comp, E defaultE)
		{
			this.comp = comp;
			this.defaultE = defaultE;
			comp.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e)
		{enter();}

		private void enter()
		{execute(defaultE);}
	}
	
	
	
	private void execute(E exe)
	{
		try{exe.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}

}