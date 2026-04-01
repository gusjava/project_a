package a.entity.gus06.swing.textfield.cust3.execute.onenter;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210104";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextField comp = (JTextField) o[0];
		E exe = (E) o[1];
		
		new Holder(comp,exe);
	}
	

	private class Holder implements ActionListener
	{
		private E exe;
		private JTextField comp;
		
		public Holder(JTextField comp, E exe)
		{
			this.exe = exe;
			this.comp = comp;
			comp.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{execute(exe);}
	}
	
	
	private void execute(E exe)
	{
		try{exe.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}
}