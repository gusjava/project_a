package a.entity.gus06.file.editor.ext.jar.viewer.maven;

import a.framework.*;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20251228";}

	private Service searchOnline;
	private Service searchInside;
	private Service swingWorker;
	private Service mapViewer;

	private JButton button1;
	private JButton button2;
	private JPanel panel;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		searchOnline = Outside.service(this,"gus06.y.maven1.webapi.solrsearch.by.sha1");
		searchInside = Outside.service(this,"gus06.y.maven1.webapi.solrsearch.by.sha1");
		swingWorker = Outside.service(this,"gus06.swing.swingworker");
		mapViewer = Outside.service(this,"*gus06.data.viewer.object");
		
		
		button1 = new JButton("Search Maven Central");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{searchOnline();}
		});
		button2 = new JButton("Search inside JAR");
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{searchInside();}
		});
		
		JPanel panelButtons = new JPanel(new GridLayout());
		panelButtons.add(button1);
		panelButtons.add(button2);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) mapViewer.i(),BorderLayout.CENTER);
		panel.add(panelButtons,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		button1.setEnabled(file!=null);
		button2.setEnabled(file!=null);
		mapViewer.p(null);
	}
	
	private void searchOnline()
	{
		try
		{
			if(file==null) return;
			
			G g = (G) ()->searchOnline.t(file);
			swingWorker.p(new Object[]{mapViewer, g});
		}
		catch(Exception e)
		{Outside.err(this,"searchOnline()",e);}
	}
	
	private void searchInside()
	{
		try
		{
			if(file==null) return;
			
			G g = (G) ()->searchOnline.t(file);
			swingWorker.p(new Object[]{mapViewer, g});
		}
		catch(Exception e)
		{Outside.err(this,"searchInside()",e);}
	}
}
