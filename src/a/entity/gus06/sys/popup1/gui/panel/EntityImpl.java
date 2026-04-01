package a.entity.gus06.sys.popup1.gui.panel;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.util.List;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20161005";}

	public static final int GAP = 10;
	public static final Color BACKGROUND = Color.WHITE;


	private Service buildNotif;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		buildNotif = Outside.service(this,"gus06.sys.popup1.gui.buildnotif");
		
		panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		panel.setBackground(BACKGROUND);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		List notifs = (List) obj;
		panel.removeAll();
		
		JPanel current = panel;
		int nb = notifs.size();
		for(int i=0;i<nb;i++)
		{
			Map notif = (Map) notifs.get(i);
			JComponent comp = (JComponent) buildNotif.t(notif);
			
			if(i<nb-1)
			{
				JPanel next = new JPanel(new BorderLayout());
				next.setOpaque(false);
				current.add(comp, BorderLayout.NORTH);
				current.add(next, BorderLayout.CENTER);
				current = next;
			}
			else
			{
				current.add(comp, BorderLayout.CENTER);
			}
		}
	}
}