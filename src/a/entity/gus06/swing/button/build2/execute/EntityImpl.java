package a.entity.gus06.swing.button.build2.execute;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191110";}


	private Service findExecute;
	private Service cust;
	
	public EntityImpl() throws Exception
	{
		findExecute = Outside.service(this,"gus06.find.execute");
		cust = Outside.service(this,"gus06.swing.button.cust2.display");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		E exe = (E) findExecute.t(o[0]);
		String display = (String) o[1];
		
		JButton b = new JButton1(exe);
		cust.v(display,b);
		return b;
	}
	
	private class JButton1 extends JButton implements ActionListener
	{
		private E exe;
		
		public JButton1(E exe)
		{
			super();
			this.exe = exe;
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{execute(exe);}
	}
	
	private void execute(E exe)
	{
		try{exe.e();}
		catch(Exception e)
		{Outside.err(this,"execute(E)",e);}
	}

}
