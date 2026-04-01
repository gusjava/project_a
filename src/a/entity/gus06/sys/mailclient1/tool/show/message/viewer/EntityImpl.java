package a.entity.gus06.sys.mailclient1.tool.show.message.viewer;

import a.framework.*;
import javax.mail.Message;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240314";}


	private Service gui1;
	private Service gui2;
	private Service gui3;
	private Service gui4;
	private Service gui5;
	
	private JTabbedPane tab;
	private Message message;
	
	public EntityImpl() throws Exception
	{
		gui1 = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer.text");
		gui2 = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer.html");
		gui3 = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer.attachments");
		gui4 = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer.prop");
		gui5 = Outside.service(this,"*gus06.sys.mailclient1.tool.show.message.viewer.analyze");
		
		tab = new JTabbedPane();
		tab.addTab("Text",(JComponent) gui1.i());
		tab.addTab("HTML",(JComponent) gui2.i());
		tab.addTab("Attachments",(JComponent) gui3.i());
		tab.addTab("Prop",(JComponent) gui4.i());
		tab.addTab("Analyze",(JComponent) gui5.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	public void p(Object obj) throws Exception
	{
		message = (Message) obj;
		gui1.p(message);
		gui2.p(message);
		gui3.p(message);
		gui4.p(message);
		gui5.p(message);
	}
}