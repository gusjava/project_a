package a.entity.gus06.swing.button.cust.boldselected;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.AbstractButton;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180409";}



	public void p(Object obj) throws Exception
	{new Holder((AbstractButton) obj);}


	
	private class Holder implements ItemListener
	{
		private AbstractButton button;
		
		public Holder(AbstractButton button)
		{
			this.button = button;
			button.addItemListener(this);
			updateBold();
		}
		
		public void itemStateChanged(ItemEvent e)
		{updateBold();}
		
		private void updateBold()
		{
			if(button.isSelected())
				button.setFont(button.getFont().deriveFont(Font.BOLD));
			else button.setFont(button.getFont().deriveFont(Font.PLAIN));
		}
	}
}
