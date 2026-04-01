package a.entity.gus06.swing.label.hold.link.web;

import a.framework.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class EntityImpl implements Entity, I, P, G, MouseListener {

	public String creationDate() {return "20150214";}
	
	public static final Color COLOR = Color.BLUE;
	
	private Service browseLink;
	
	private JLabel label;
	private Object link;


	public EntityImpl() throws Exception
	{
		browseLink = Outside.service(this,"gus06.awt.desktop.browse");
		
		label = new JLabel(" ");
		label.setForeground(COLOR);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(this);
	}
	
	
	public Object g() throws Exception
	{return link;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	
	public void p(Object obj) throws Exception
	{link = obj;}

	
		
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {perform();}
	
		
	private void perform()
	{
		if(link!=null) browseLink(link);
	}
	
	
	
	private void browseLink(Object link)
	{
		try{browseLink.p(link);}
		catch(Exception e)
		{Outside.err(this,"browseLink(Object)",e);}
	}
}
