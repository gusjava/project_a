package a.entity.gus06.swing.button.build.execute;

import a.framework.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191109";}


	private Service findExecute;
	
	public EntityImpl() throws Exception
	{
		findExecute = Outside.service(this,"gus06.find.execute");
	}

	
	public Object t(Object obj) throws Exception
	{
		E exe = (E) findExecute.t(obj);
		return new JButton1(exe);
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
