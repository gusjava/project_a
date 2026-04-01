package a.entity.gus06.swing.popupmenu.builder1;

import a.framework.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140821";}

	
	
	public Object t(Object obj) throws Exception
	{
		Component comp = (Component) obj;
		JPopupMenu menu = new JPopupMenu();
		comp.addMouseListener(new PopupAdapter(menu));
		return menu;
	}
	
	
	
	private class PopupAdapter extends MouseAdapter
	{
		private JPopupMenu popup;
        
		public PopupAdapter(JPopupMenu popup) 
		{this.popup = popup;}
        
		public void mousePressed(MouseEvent e) {maybeShowPopup(e);}
		public void mouseReleased(MouseEvent e) {maybeShowPopup(e);}
        
		private void maybeShowPopup(MouseEvent e)
		{
			if(e.isPopupTrigger())
			{popup.show(e.getComponent(),e.getX(),e.getY());}
		}
	}
}
