package a.entity.gus06.appli.laboswing.gui.jtextarea;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20160430";}


	private Service persister;
	private Service support;
	private Service custConsole;
	private Service escapClear;
	private Service eventToString;


	private JSplitPane split;
	private JTextArea area;
	private JTextArea console;
	
	private R area_r;
	

	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.swing.textcomp.persister.text");
		support = Outside.service(this,"gus06.appli.laboswing.gui.jtextarea.support");
		custConsole = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");
		escapClear = Outside.service(this,"gus.x.swing.textcomp.cust.action.escap.clear");
		eventToString = Outside.service(this,"gus06.appli.laboswing.gui.jtextarea.eventtostring");
		
		area = new JTextArea();
		persister.v(getClass().getName()+"_area",area);
		
		area_r = (R) support.t(area);
		((S) area_r).addActionListener(this);
		
		console = new JTextArea();
		console.setEditable(false);
		
		custConsole.p(console);
		escapClear.p(console);
		
		split = new JSplitPane();
		split.setDividerLocation(400);
		split.setRightComponent(new JScrollPane(console));
		split.setLeftComponent(new JScrollPane(area));
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	private void print(String m)
	{console.append(m+"\n");}


	public void actionPerformed(ActionEvent e)
	{updateConsole();}
	
	
	
	private void updateConsole()
	{
		try
		{
			Object event = area_r.r("event");
			String method = (String) area_r.r("method");
			
			if(method.startsWith("mouse")) return;
			if(method.startsWith("key")) return;
			
			String eventStr = (String) eventToString.t(event);
			print(method+"\t"+eventStr);
		}
		catch(Exception e)
		{Outside.err(this,"updateConsole()",e);}
	}

}
