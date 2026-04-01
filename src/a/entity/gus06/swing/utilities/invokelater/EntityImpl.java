package a.entity.gus06.swing.utilities.invokelater;

import a.framework.*;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170316";}

	
	
	public Object t(Object obj) throws Exception
	{return new Holder((E) obj);}
	
	
	
	private class Holder implements Runnable, E
	{
		private E e;
		public Holder(E e) {this.e = e;}
		
		public void e() throws Exception
		{SwingUtilities.invokeLater(this);}
		
		public void run()
		{execute(e);}
	}
	
	
	private void execute(E e)
	{
		try{e.e();}
		catch(Exception ex)
		{Outside.err(this,"execute(E)",ex);}
	}
}
