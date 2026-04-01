package a.entity.gus06.sys.desktop1.action.item.remove.enabler;

import a.framework.*;
import java.util.Map;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20260114";}
	
	public static final String KEY_ITEM_MANAGER = "item_manager";

	public EntityImpl() throws Exception
	{
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Action action = (Action) o[0];
		Map main = (Map) o[1];
		
		S manager = (S) main.get(KEY_ITEM_MANAGER);
		manager.addActionListener(new Holder(action, (R) manager));
	}
	
	private class Holder implements ActionListener
	{
		private Action action;
		private R selectFinder;
		
		public Holder(Action action, R selectFinder)
		{
			this.action = action;
			this.selectFinder = selectFinder;
			refresh();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			String s = e.getActionCommand();
			if(s.equals("selectionChanged()")) refresh();
		}
		
		private void refresh()
		{
			try
			{
				boolean selected = selectFinder.r("selected")!=null;
				action.setEnabled(selected);
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"refresh()",e);}
		}
	}
}
