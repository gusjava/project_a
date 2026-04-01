package a.entity.gus06.swing.combobox.cust.clicknext;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComboBox;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160616";}
	

	public void p(Object obj) throws Exception
	{new JComboBoxHolder((JComboBox)obj);}


	
	private class JComboBoxHolder implements MouseListener
	{
		private JComboBox combo;
		
		public JComboBoxHolder(JComboBox combo)
		{
			this.combo = combo;
			combo.addMouseListener(this);
		}

		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e)
		{
			int b = e.getButton();
			if(b==MouseEvent.BUTTON1) next();
			else if(b==MouseEvent.BUTTON3) previous();
		}
		
		private void next()
		{
			int index = combo.getSelectedIndex()+1;
			if(index==combo.getItemCount()) index=0;
			combo.setSelectedIndex(index);
			combo.hidePopup();
		}
		
		private void previous()
		{
			int index = combo.getSelectedIndex()-1;
			if(index==-1) index=combo.getItemCount()-1;
			combo.setSelectedIndex(index);
			combo.hidePopup();
		}
	}
}
