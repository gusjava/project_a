package a.entity.gus06.convert.executetoaction3;

import a.framework.*;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190401";}

	
	public Object t(Object obj) throws Exception
	{return new Action1((E) obj);}
	
	
	private class Action1 extends AbstractAction implements Runnable
	{
		private E ex;
		
		public Action1(E ex) {this.ex = ex;}
		public void run() {execute(ex);}
		
		public void actionPerformed(ActionEvent e)
		{SwingUtilities.invokeLater(this);}
	}
	
	private void execute(E ex)
	{try{ex.e();} catch(Exception e) {Outside.err(this,"execute(E)",e);}}
}
