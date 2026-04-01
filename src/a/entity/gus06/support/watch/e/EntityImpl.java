package a.entity.gus06.support.watch.e;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161216";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		S s = (S) o[0];
		E e = (E) o[1];
		
		Holder holder = new Holder(e);
		s.addActionListener(holder);
	}
	
	
	private class Holder implements ActionListener
	{
		private E e;
		
		public Holder(E e)
		{this.e = e;}
		
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
