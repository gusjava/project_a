package a.entity.gus06.find.action;

import a.framework.*;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150622";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof Action) return obj;
		if(obj instanceof E) return new Action0(obj);
		if(obj instanceof Runnable) return new Action0(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private class Action0 extends AbstractAction
	{
		private Object element;
		public Action0(Object element)
		{this.element = element;}
		
		public void actionPerformed(ActionEvent e)
		{
			if(element instanceof Runnable)		perform1((Runnable)element);
			else if(element instanceof E)		perform2((E)element);
		}
	}
	
	
	
	
	
	private void perform1(Runnable r)
	{
		new Thread(r,"THREAD_"+getClass().getName()).start();
	}
	
	
	private void perform2(E exe)
	{
		try{exe.e();}
		catch(Exception e)
		{Outside.err(this,"handle(E)",e);}
	}
}
