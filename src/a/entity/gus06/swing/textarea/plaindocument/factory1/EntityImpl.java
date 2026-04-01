package a.entity.gus06.swing.textarea.plaindocument.factory1;

import a.framework.*;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import javax.swing.event.UndoableEditEvent;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20151105";}
	
	
	public Object g() throws Exception
	{return new PlainDocument1();}
	
	
	
	public class PlainDocument1 extends PlainDocument implements V
	{
		private Object undoableHandler;
		private ActionListener listener;
	
		public PlainDocument1()
		{
			super();
			listener = new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{handleUndoableEditEvent();}
			};
		}
	
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("undoableHandler"))
			{initUndoableHandler(obj);return;}
			
			throw new Exception("Unknown key: "+key);
		}
		
		private void initUndoableHandler(Object undoableHandler_) throws Exception
		{
			resetUndoableHandler();
			if(undoableHandler_==null) return;
			
			undoableHandler = undoableHandler_;
			((S) undoableHandler).addActionListener(listener);
		}
		
		private void resetUndoableHandler() throws Exception
		{
			if(undoableHandler==null) return;
			((S) undoableHandler).removeActionListener(listener);
			undoableHandler = null;
		}
		
		private void handleUndoableEditEvent()
		{
			UndoableEditEvent evt = (UndoableEditEvent) g((G)undoableHandler);
			if(evt!=null) super.fireUndoableEditUpdate(evt);
		}
		
		protected void fireUndoableEditUpdate(UndoableEditEvent evt)
		{
			if(!f((F)undoableHandler,evt))
			super.fireUndoableEditUpdate(evt);
		}
	}
	
	
	
	
	private Object g(G g)
	{
		if(g==null) return null;
		try{return g.g();}
		catch(Exception e) {Outside.err(this,"g(G)",e);}
		return null;
	}
	
	private boolean f(F f, Object obj)
	{
		if(f==null) return false;
		try{return f.f(obj);}
		catch(Exception e) {Outside.err(this,"f(F,Object)",e);}
		return false;
	}
}
