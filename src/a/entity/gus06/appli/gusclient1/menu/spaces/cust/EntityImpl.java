package a.entity.gus06.appli.gusclient1.menu.spaces.cust;

import a.framework.*;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class EntityImpl implements Entity, P, ActionListener {

	public String creationDate() {return "20140826";}

	private Service findPseudo;
	private JMenu menu;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		findPseudo.addActionListener(this);
	}
		
	
	public void actionPerformed(ActionEvent e)
	{updateGui();}
		
	
	public void p(Object obj) throws Exception
	{
		menu = (JMenu) obj;
		updateGui();
	}
	
	
	private void updateGui()
	{
		try
		{
			if(menu==null) return;
			menu.setVisible(findPseudo.g()!=null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}
