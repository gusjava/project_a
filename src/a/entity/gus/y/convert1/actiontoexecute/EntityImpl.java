package a.entity.gus.y.convert1.actiontoexecute;

import a.framework.*;
import javax.swing.Action;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20180222";}
	
	public Object t(Object obj) throws Exception
	{return new E1((Action) obj);}
	
	private class E1 implements E
	{
		private Action action;
		public E1(Action action)
		{this.action = action;}

		public void e() throws Exception
		{action.actionPerformed(null);}
	}
}
