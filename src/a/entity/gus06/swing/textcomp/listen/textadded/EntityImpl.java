package a.entity.gus06.swing.textcomp.listen.textadded;

import a.framework.*;
import javax.swing.text.JTextComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180221";}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTextComponent comp = (JTextComponent) o[0];
		P handler = (P) o[1];
		
		new Holder(comp,handler);
	}
	
	
	private class Holder implements DocumentListener
	{
		private JTextComponent comp;
		private P handler;
		
		public Holder(JTextComponent comp, P handler)
		{
			this.comp = comp;
			this.handler = handler;
			comp.getDocument().addDocumentListener(this);
		}
		
		public void changedUpdate(DocumentEvent e) {}
		public void removeUpdate(DocumentEvent e) {}
		public void insertUpdate(DocumentEvent e)
		{
			int offset = e.getOffset();
			int length = e.getLength();
			String addedText = comp.getText().substring(offset,offset+length);
			send(handler,addedText);
		}
	}
	
	
	private void send(P handler ,String addedText)
	{
		try{handler.p(addedText);}
		catch(Exception e)
		{Outside.err(this,"send(P,String)",e);}
	}
}
