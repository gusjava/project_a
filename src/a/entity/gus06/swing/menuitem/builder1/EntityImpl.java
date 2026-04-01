package a.entity.gus06.swing.menuitem.builder1;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140726";}


	private Service custDisplay;
	
	
	public EntityImpl() throws Exception
	{
		custDisplay = Outside.service(this,"gus06.swing.button.cust2.display");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof E) return new JMenuItem1((E) obj);
		if(obj instanceof Object[])
		{
			Object[] o = (Object[]) obj;
			if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
			E exe = (E) o[0];
			String display = (String) o[1];
			
			JMenuItem1 item = new JMenuItem1(exe);
			custDisplay.v(display,item);
			return item;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}



	private class JMenuItem1 extends JMenuItem implements ActionListener
	{
		private E e;
		public JMenuItem1(E e)
		{
			super();
			this.e = e;
			addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{execute(e);}
	}
	
	
	
	private void execute(E e)
	{
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}

}