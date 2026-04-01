package a.entity.gus06.appli.gusclient1.menu.gus.cust;

import a.framework.*;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class EntityImpl implements Entity, P, ActionListener {

	public String creationDate() {return "20140811";}

	private Service findPseudo;
	private Service isGusPseudo;
	private JMenu menu;
	
	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		isGusPseudo = Outside.service(this,"gus06.entitydev.pseudo.check.gus");
		
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
			menu.setVisible(isGusPseudo());
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	private boolean isGusPseudo() throws Exception
	{return isGusPseudo.f(null);}
}
