package a.entity.gus06.sys.mailclient1.tool.show.message.viewer.html;

import a.framework.*;
import javax.mail.Message;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240322";}


	private Service messageToHtml;
	private Service exceptionToString;
	
	private JPanel panel;
	private JTextArea area;
	
	private Message message;
	private String text;
	
	public EntityImpl() throws Exception
	{
		messageToHtml = Outside.service(this,"gus06.mail.retrieve.message.content.html");
		exceptionToString = Outside.service(this,"gus06.tostring.exception");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setMargin(new Insets(2,2,2,2));
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null){resetGui();return;}
		try
		{
			message = (Message) obj;
			text = (String) messageToHtml.t(message);
			
			area.setText(text);
			area.setCaretPosition(0);
		}
		catch(Exception e)
		{
			String s = (String) exceptionToString.t(e);
			area.setText(s);
			area.setCaretPosition(0);
		}
	}
	
	private void resetGui()
	{
		message = null;
		text = null;
		area.setText("");
	}
}