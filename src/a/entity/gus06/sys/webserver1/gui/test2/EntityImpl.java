package a.entity.gus06.sys.webserver1.gui.test2;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.net.InetAddress;


public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20140928";}

	

	private Service receiver;
	private Service formatInput;
	private Service mapViewer;
	
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		receiver = Outside.service(this,"gus06.sys.webserver1.engine.receiver");
		formatInput = Outside.service(this,"gus06.sys.webserver1.format.input");
		mapViewer = Outside.service(this,"*gus06.data.viewer.map");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) mapViewer.i(),BorderLayout.CENTER);
		
		receiver.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{receive();}
	
	
	
	private void receive()
	{
		try
		{
			Map map = (Map) receiver.g();
			mapViewer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"receive()",e);}
	}
}
