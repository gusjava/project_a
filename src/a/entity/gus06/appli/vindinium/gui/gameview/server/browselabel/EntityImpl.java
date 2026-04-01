package a.entity.gus06.appli.vindinium.gui.gameview.server.browselabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Map;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, P, I, MouseListener {

	public String creationDate() {return "20170923";}


	public static final Color COLOR = Color.BLUE;


	private JLabel label;
	private URL viewUrl;
	

	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		label.setForeground(COLOR);
		label.addMouseListener(this);
		label.setFocusable(true);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}


	public Object i() throws Exception
	{return label;}
	


	public void p(Object obj) throws Exception
	{
		Map data = (Map) obj;
		if(data==null) return;
		
		String s = (String) data.get(DATA.K_VIEWURL);
		if(s==null || s.equals(""))
		{
			viewUrl = null;
			label.setText(" ");
		}
		else
		{
			viewUrl = new URL(s);
			label.setText("Watch online");
		}
	}


	
	public void mouseClicked(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {activate();}
	public void mouseExited(MouseEvent e) {disactivate();}

	
	public void mousePressed(MouseEvent e)
	{
		disactivate();
		browseViewUrl();
	}

	
	
	private void activate()
	{
		label.setFont(label.getFont().deriveFont(Font.BOLD));
	}
	
	private void disactivate()
	{
		label.setFont(label.getFont().deriveFont(Font.PLAIN));
	}
	
	
	
	

	private void browseViewUrl()
	{
		try
		{
			if(viewUrl!=null)
			Desktop.getDesktop().browse(viewUrl.toURI());
		}
		catch(Exception e){Outside.err(this,"browseViewUrl()",e);}
	}
}
