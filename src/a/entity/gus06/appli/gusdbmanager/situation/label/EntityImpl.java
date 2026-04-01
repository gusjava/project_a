package a.entity.gus06.appli.gusdbmanager.situation.label;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20150613";}

	
	public static final String ICONID_PC = "computer";
	public static final String ICONID_LAN = "lan";

	private Service situation;
	private Service repaintLabel;

	private JLabel label;
	
	
	public EntityImpl() throws Exception
	{
		situation = Outside.service(this,"gus06.appli.gusdbmanager.situation.builder");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		label = new JLabel(" ");
		situation.addActionListener(this);
		updateGui();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}



	public Object i() throws Exception
	{return label;}
	
	
	
	private void updateGui()
	{
		try
		{
			String s = (String) situation.g();
			String[] n = s.split("\\|");
			if(n.length!=3) throw new Exception("Wrong data number: "+n.length);
			
			String home = n[0];
			String location = n[1];
			String wan = n[2];
			
			String display = findDisplay(location,wan);
			repaintLabel.v(display,label);
			
			label.setToolTipText(s);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}



	
	
	private String findDisplay(String location, String wan)
	{
		if(location.equals("false"))
		{
			String wanPart = wan.equals("wan")?"_online":"";
			return ICONID_PC+wanPart+"#";
		}
		if(location.equals("?"))
		{
			String wanPart = wan.equals("wan")?"_online":"_offline";
			return ICONID_LAN+wanPart+"#";
		}
		String wanPart = wan.equals("wan")?"_online":"_offline";
		return ICONID_LAN+wanPart+"#"+location;
	}
}
