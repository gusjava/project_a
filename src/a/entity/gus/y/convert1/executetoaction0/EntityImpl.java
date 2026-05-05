package a.entity.gus.y.convert1.executetoaction0;

import a.framework.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20140731";}

	public Object t(Object obj) throws Exception
	{return new Action1((E) obj);}
	
	private class Action1 extends AbstractAction
	{
		private E ex;
		public Action1(E ex) {this.ex = ex;}
		public void actionPerformed(ActionEvent e)
		{execute(ex);}
	}
	
	private void execute(E ex)
	{try{ex.e();} catch(Exception e) {Outside.err(this,"execute(E)",e);}}
}
