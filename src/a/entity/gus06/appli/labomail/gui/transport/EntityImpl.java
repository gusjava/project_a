package a.entity.gus06.appli.labomail.gui.transport;

import a.framework.*;
import javax.mail.Transport;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160607";}
	
	private Transport transport;
	
	private JTextArea area;
	private JScrollPane scroll;


	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setEditable(false);
		
		scroll = new JScrollPane(area);
	}
	
	
	public Object i() throws Exception
	{return scroll;}
	
	
	
	public void p(Object obj) throws Exception
	{
		transport = (Transport) obj;
		if(transport==null) return;
	}
}
