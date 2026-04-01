package a.entity.gus06.swing.action.cust.enable;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140731";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception();
		
		Action a = (Action) o[0];
		G g = (G) o[1];
		new Holder(a,g);
	}
	
	
	private class Holder implements ActionListener
	{
		private Action a;
		private G g;
	
		public Holder(Action a, G g) throws Exception
		{
			this.a = a;
			this.g = g;
			checkEnabled(a,g);
			((S)g).addActionListener(this);
		}
		public void actionPerformed(ActionEvent e)
		{checkEnabled(a,g);}
	}
	
	
	
	private void checkEnabled(Action a, G g)
	{
		try{a.setEnabled(g.g()!=null);}
		catch(Exception e){Outside.err(this,"checkEnabled(Action,G)",e);}
	}
}
