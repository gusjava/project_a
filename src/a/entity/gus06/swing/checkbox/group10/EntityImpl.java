package a.entity.gus06.swing.checkbox.group10;

import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180409";}


	public void p(Object obj) throws Exception
	{new Holder((JCheckBox[]) obj);}


	
	private class Holder implements ItemListener
	{
		private JCheckBox[] box;
		
		public Holder(JCheckBox[] box)
		{
			this.box = box;
			for(int i=0;i<box.length;i++)
			box[i].addItemListener(this);
		}
		
		public void itemStateChanged(ItemEvent e)
		{
			JCheckBox b = (JCheckBox) e.getSource();
			if(b.isSelected()) selected(b);
		}

		private void selected(JCheckBox b)
		{
			for(int i=0;i<box.length;i++)
			if(box[i]!=b && box[i].isSelected()) change(box[i],false);
		}
		
		private void change(JCheckBox b, boolean value)
		{
			b.removeItemListener(this);
			b.setSelected(value);
			b.addItemListener(this);
		}
	}
}