package a.entity.gus06.sys.thread1.showmonitor.gui.stacktrace;

import a.framework.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Insets;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20180124";}


	private Service toString;

	private JTextArea area;
	private JPanel panel;


	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.stacktrace");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Thread t = (Thread) obj;
		
		if(t==null)
		{
			area.setText("");
			return;
		}
		printInfos(t);
	}
	
	private void printInfos(Thread t) throws Exception
	{
		String s = (String) toString.t(t);
		area.setText(s);
	}
}
