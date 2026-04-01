package a.entity.gus06.swing.textcomp.cust.action.escap.clear2;

import a.framework.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220602";}

	public static final KeyStroke ESCAPE = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		new Holder((JTextComponent) o[0], (E) o[1]);
	}


	private class Holder extends AbstractAction
	{
		private JTextComponent comp;
		private E defaultE;
		
		public Holder(JTextComponent comp, E defaultE)
		{
			this.comp = comp;
			this.defaultE = defaultE;
			comp.getInputMap().put(ESCAPE,this);
		}

		public void actionPerformed(ActionEvent e)
		{clear();}

		private void clear()
		{
			if(!comp.getText().equals(""))
				comp.setText("");
			else execute(defaultE);
		}
	}
	
	
	
	private void execute(E exe)
	{
		try{exe.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}

}