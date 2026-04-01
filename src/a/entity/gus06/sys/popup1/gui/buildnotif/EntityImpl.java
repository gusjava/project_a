package a.entity.gus06.sys.popup1.gui.buildnotif;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161005";}
	
	private Service buildTitle;
	private Service buildMessage;
	private Service buildBar;

	public EntityImpl() throws Exception
	{
		buildTitle = Outside.service(this,"gus06.sys.popup1.gui.buildnotif.title");
		buildMessage = Outside.service(this,"gus06.sys.popup1.gui.buildnotif.message");
		buildBar = Outside.service(this,"gus06.sys.popup1.gui.buildnotif.bar");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map notif = (Map) obj;
		
		JComponent compTitle = (JComponent) buildTitle.t(notif);
		JComponent compMessage = (JComponent) buildMessage.t(notif);
		JComponent compBar = (JComponent) buildBar.t(notif);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		
		if(compTitle!=null) panel.add(compTitle,BorderLayout.NORTH);
		if(compMessage!=null) panel.add(compMessage,BorderLayout.CENTER);
		if(compBar!=null) panel.add(compBar,BorderLayout.SOUTH);
		
		return panel;
	}
}