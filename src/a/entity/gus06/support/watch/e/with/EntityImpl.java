package a.entity.gus06.support.watch.e.with;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161216";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		S s = (S) o[0];
		String id = (String) o[1];
		E e = (E) o[2];
		
		Holder holder = new Holder(id,e);
		s.addActionListener(holder);
	}
	
	
	private class Holder implements ActionListener
	{
		private String id;
		private E e;
		
		public Holder(String id, E e)
		{
			this.id = id;
			this.e = e;
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String s = evt.getActionCommand();
			if(s.equals(id)) execute(e);
		}
	}
	
	
	private void execute(E e)
	{
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}
}
