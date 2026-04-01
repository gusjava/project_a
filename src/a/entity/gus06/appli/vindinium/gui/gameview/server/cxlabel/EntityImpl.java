package a.entity.gus06.appli.vindinium.gui.gameview.server.cxlabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20170923";}


	private Service fromWeb;
	private JLabel label;

	public EntityImpl() throws Exception
	{
		fromWeb = Outside.service(this,"gus06.appli.vindinium.data.retrievedata.getjson.fromweb");
		fromWeb.addActionListener(this);
		
		label = new JLabel(" ");
		label.setFont(label.getFont().deriveFont(Font.ITALIC));
	}


	public Object i() throws Exception
	{return label;}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		
		if(s.equals("start()")) start();
		else if(s.equals("end()")) end();
	}
	
	
	private void start()
	{
		label.setForeground(Color.ORANGE.darker());
		label.setText("Waiting for server's response");
	}
	
	
	private void end()
	{
		label.setText(" ");
	}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Exception exception = (Exception) obj;
		
		label.setForeground(Color.RED);
		label.setText("Game interrupted");
		label.setToolTipText(exception.toString());
	}


}
