package a.entity.gus06.data.viewer.s;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140731";}

	private Service listViewer;
	private Service watcherGui;

	private S data;

	private JTabbedPane tabbedPane;
	

	public EntityImpl() throws Exception
	{
		listViewer = Outside.service(this,"*gus06.data.viewer.list");
		watcherGui = Outside.service(this,"*gus06.data.viewer.s.watchergui");
	
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("watcher",(JComponent) watcherGui.i());
		tabbedPane.addTab("listeners",(JComponent) listViewer.i());
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return tabbedPane;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(data!=null) data.removeActionListener(this);
		data = (S) obj;
		if(data!=null) data.addActionListener(this);

		listViewer.p(listeners());
	}
	
	private List listeners() throws Exception
	{return data!=null?data.listeners():null;}
	
	private String listenersNb() throws Exception
	{return data!=null?""+data.listeners().size():"null";}
	
	
	
	public void actionPerformed(ActionEvent e)
	{watchEvent(e.getActionCommand());}
	
	
	
	private void watchEvent(String s)
	{
		try{watcherGui.p(s+" [nb listeners:"+listenersNb()+"]");}
		catch(Exception ex)
		{Outside.err(this,"watchEvent(String)",ex);}
	}
}
