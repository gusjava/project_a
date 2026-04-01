package a.entity.gus06.sys.webserver1.gui.test1;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20140928";}

	public static final String DELIM = "_________________________";
	public static final String KEY_DATA = "data";

	private Service receiver;
	
	
	private JPanel panel;
	private JTextArea area;


	public EntityImpl() throws Exception
	{
		receiver = Outside.service(this,"gus06.sys.webserver1.engine.receiver");
		
		area = new JTextArea();
		area.setEditable(false);
		area.setLineWrap(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		
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
			byte[] data = (byte[]) map.get(KEY_DATA);
			
			ByteArrayInputStream is = new ByteArrayInputStream(data);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			while((line = br.readLine()) != null)
			{println(line);}
			println(DELIM);
		}
		catch(Exception e)
		{Outside.err(this,"receive()",e);}
	}
	
	
	
	private void println(String line)
	{area.append(line+"\n");}

}
