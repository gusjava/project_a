package a.entity.gus.y.convert1.executetorunnable;

import a.framework.*;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140919";}

	
	public Object t(Object obj) throws Exception
	{return new Runnable1((E) obj);}
	
	
	private class Runnable1 implements Runnable
	{
		private E ex;
		public Runnable1(E ex) {this.ex = ex;}
		public void run() {execute(ex);}
	}
	
	private void execute(E ex)
	{try{ex.e();} catch(Exception e) {Outside.err(this,"execute(E)",e);}}
}
