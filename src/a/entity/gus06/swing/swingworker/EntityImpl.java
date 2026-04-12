package a.entity.gus06.swing.swingworker;

import a.framework.*;

import javax.swing.SwingWorker;

public class EntityImpl implements Entity, P {

    public String creationDate() {return "20251228";}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		P p = (P) o[0];
		G g = (G) o[1];
		
		new SwingWorker<Object, Void>()
		{
			protected Object doInBackground() throws Exception
			{return g.g();}
		
			protected void done()
			{
				try{p.p(get()); }
				catch(Exception e)
				{Outside.err(EntityImpl.this, "done()", e);}
			}
		}.execute();
	}
}