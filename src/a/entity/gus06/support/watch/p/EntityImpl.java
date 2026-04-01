package a.entity.gus06.support.watch.p;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180404";}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		S s = (S) o[0];
		P p = (P) o[1];
		
		Holder holder = new Holder((G) s, p);
		s.addActionListener(holder);
	}
	
	
	private class Holder implements ActionListener
	{
		private G g;
		private P p;
		
		public Holder(G g, P p)
		{
			this.g = g;
			this.p = p;
		}
		
		public void actionPerformed(ActionEvent evt)
		{transfer(g,p);}
	}
	
	
	private void transfer(G g, P p)
	{
		try{p.p(g.g());}
		catch(Exception ex)
		{Outside.err(this,"transfer(G,P)",ex);}
	}
}
