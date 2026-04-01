package a.entity.gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.test;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20221122";}


	private Service dataViewer;
	private Service builder;
	
	private JPanel panel;
	private JButton buttonG;

	private File scriptFile;
	private Thread t;
	

	public EntityImpl() throws Exception
	{
		dataViewer = Outside.service(this,"*gus06.data.viewer.object");
		builder = Outside.service(this,"gus06.sys.script1.build1.g");
		
		buttonG = new JButton("Test as G");
		buttonG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{performTestG();}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(buttonG, BorderLayout.NORTH);
		panel.add((JComponent) dataViewer.i(), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		scriptFile = (File) obj;
		dataViewer.p(null);
	}
	
	
	private void performTestG()
	{
		if(t!=null && t.isAlive()) return;
		
		t = new Thread(this, "THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		buttonG.setEnabled(false);
		try
		{
			if(scriptFile==null) throw new Exception("Script file is null");
			if(!scriptFile.isFile()) throw new Exception("Script file not found: "+scriptFile);
			
			G g = (G) builder.t(scriptFile);
			Object data = g.g();
			dataViewer.p(data);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		buttonG.setEnabled(true);
	}
}