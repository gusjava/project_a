package a.entity.gus06.swing.label.cust.link.mail;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140918";}

	public static final Color COLOR = Color.BLUE;
	
	
	
	private Service mailTo;

	public EntityImpl() throws Exception
	{mailTo = Outside.service(this,"gus06.awt.desktop.mail");}
	
	
	public void p(Object obj) throws Exception
	{new Holder((JLabel) obj);}
	
	
	
	
	private class Holder implements MouseListener
	{
		private JLabel label;
		
		public Holder(JLabel label)
		{
			this.label = label;
			label.addMouseListener(this);
			label.setForeground(COLOR);
			label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {activate();}
		public void mouseExited(MouseEvent e) {disactivate();}
		public void mousePressed(MouseEvent e) {perform();}
		
		
		private void activate()
		{label.setFont(label.getFont().deriveFont(Font.BOLD));}
	
		private void disactivate()
		{label.setFont(label.getFont().deriveFont(Font.PLAIN));}
		
		private void perform()
		{
			disactivate();
			mailTo(label.getText());
		}
	}
	
	
	
	private void mailTo(String link)
	{
		try{mailTo.p(link);}
		catch(Exception e)
		{Outside.err(this,"mailTo(String)",e);}
	}

}
