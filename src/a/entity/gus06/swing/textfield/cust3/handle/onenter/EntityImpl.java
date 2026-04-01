package a.entity.gus06.swing.textfield.cust3.handle.onenter;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160907";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextField comp = (JTextField) o[0];
		P p = (P) o[1];
		
		new Holder(comp,p);
	}
	

	private class Holder implements ActionListener
	{
		private P p;
		private JTextField comp;
		
		public Holder(JTextField comp, P p)
		{
			this.p = p;
			this.comp = comp;
			comp.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{perform(comp,p);}
	}
	
	
	private void perform(JTextField comp, P p)
	{
		try
		{
			String text = comp.getText();
			p.p(text);
		}
		catch(Exception e)
		{Outside.err(this,"perform(JTextField,P)",e);}
	}
}
