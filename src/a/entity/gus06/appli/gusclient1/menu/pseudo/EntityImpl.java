package a.entity.gus06.appli.gusclient1.menu.pseudo;

import java.util.List;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140904";}

	public static final String LINGKEY = "gusclient1_menu_pseudo";


	
	private Service localize;
	
	private Service resetPseudo;
	private Service registerPseudo;
	
	private JMenu menu;
	

	public EntityImpl() throws Exception
	{
		localize = Outside.service(this,"gus06.ling.localize.manager");
		
		resetPseudo = Outside.service(this,"gus06.appli.gusclient1.action.pseudo.reset");
		registerPseudo = Outside.service(this,"gus06.appli.gusclient1.action.pseudo.register");
		
		menu = new JMenu("Pseudo");
		localize.v(LINGKEY,menu);
		
		addAction(resetPseudo);
		addAction(registerPseudo);
	}
	
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	
	public void addAction(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}
