package a.entity.gus06.support.watch.p.with;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180405";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		S s = (S) o[0];
		String id = (String) o[1];
		P p = (P) o[2];
		
		Holder holder = new Holder(id,(G) s, p);
		s.addActionListener(holder);
	}
	
	
	private class Holder implements ActionListener
	{
		private String id;
		private G g;
		private P p;
		
		public Holder(String id, G g, P p)
		{
			this.id = id;
			this.g = g;
			this.p = p;
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			String s = evt.getActionCommand();
			if(s.equals(id)) transfer(g,p);
		}
	}
	
	
	private void transfer(G g, P p)
	{
		try{p.p(g.g());}
		catch(Exception ex)
		{Outside.err(this,"transfer(G,P)",ex);}
	}
}
