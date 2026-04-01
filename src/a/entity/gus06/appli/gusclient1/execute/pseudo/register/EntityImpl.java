package a.entity.gus06.appli.gusclient1.execute.pseudo.register;

import a.framework.*;
import java.util.Map;
import javax.swing.JOptionPane;
import java.net.URL;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20140904";}


	private Service step1;
	private Service step2;


	public EntityImpl() throws Exception
	{
		step1 = Outside.service(this,"gus06.appli.gusclient1.execute.pseudo.register.step1");
		step2 = Outside.service(this,"gus06.appli.gusclient1.execute.pseudo.register.step2");
	}
	
	
	public void e() throws Exception
	{
		String[] info = (String[]) step1.g();
		if(info==null) return;
		
		String url = info[0];
		String content = info[1];
		
		if(content.equals("used"))
		{
			JOptionPane.showMessageDialog(null,"Pseudo already used.\nRegistration cancelled.");
			return;
		}
		if(content.equals("blocked"))
		{
			JOptionPane.showMessageDialog(null,"Registration blocked.");
			return;
		}
		if(content.equals("available"))
		{
			//JOptionPane.showMessageDialog(null,"Pseudo available.");
			step2.p(url);
			return;
		}
	}
}
