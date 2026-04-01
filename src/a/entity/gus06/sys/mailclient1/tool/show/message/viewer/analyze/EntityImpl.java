package a.entity.gus06.sys.mailclient1.tool.show.message.viewer.analyze;

import a.framework.*;
import javax.mail.Message;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240321";}


	private Service viewBuilder;
	private Service shiftPanel;
	
	private JPanel panel;
	private JLabel label;
	
	private Message message;
	
	public EntityImpl() throws Exception
	{
		viewBuilder = Outside.service(this,"gus06.sys.mailclient1.tool.mimecontent.viewbuilder");
		shiftPanel = Outside.service(this,"gus06.swing.panel.shiftpanel");
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null){resetGui();return;}
		message = (Message) obj;
		
		Object content = message.getContent();
		String type = message.getContentType();
		
		String name = content.getClass().getName();
		label.setText(name);
		
		JComponent viewComp = (JComponent) viewBuilder.t(new Object[]{content,type});
		shiftPanel.p(viewComp);
	}
	
	private void resetGui() throws Exception
	{
		message = null;
		shiftPanel.p(null);
		label.setText(" ");
	}
}