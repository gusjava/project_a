package a.entity.gus06.swing.list.cust3.selectionhandler;

import a.framework.*;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140908";}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JList list = (JList) o[0];
		P handler = (P) o[1];
		
		new Holder(list,handler);
	}
	
	
	private class Holder implements ListSelectionListener
	{
		private JList list;
		private P handler;
		
		public Holder(JList list, P handler)
		{
			this.list = list;
			this.handler = handler;
			list.addListSelectionListener(this);
		}
		public void valueChanged(ListSelectionEvent e)
		{handleSelection(list,handler);}
	}
	
	
	
	private void handleSelection(JList list, P handler)
	{
		try
		{
			Object value = list.getSelectedValue();
			handler.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"handleSelection(JList,P)",e);}
	}	
}
