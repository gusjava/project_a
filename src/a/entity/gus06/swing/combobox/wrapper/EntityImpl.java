package a.entity.gus06.swing.combobox.wrapper;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141123";}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JComboBox) obj);}
	
	
	private class Holder extends S1 implements ItemListener, P, G, I, R
	{
		private JComboBox comp;
		private String lastValue;
		
		public Holder(JComboBox comp)
		{
			this.comp = comp;
			comp.addItemListener(this);
		}
		
		public Object i() throws Exception
		{return comp;}
		
		public Object g() throws Exception
		{return (String) comp.getSelectedItem();}


		public void p(Object obj) throws Exception
		{
			String value = (String) obj;
			
			setActivated(false);
			comp.setSelectedItem(value);
			setActivated(true);
			dataModified();
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("lastValue")) return lastValue;
			throw new Exception("Unknown key: "+key);
		}
		
		
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getStateChange()==ItemEvent.DESELECTED)
			{
				lastValue = (String) e.getItem();
			}
			if(e.getStateChange()==ItemEvent.SELECTED)
			{
				dataEdited();
				dataModified();
			}
		}
		
		private void dataModified()
		{send(this,"dataModified()");}
		
		private void dataEdited()
		{send(this,"dataEdited()");}
	}
}