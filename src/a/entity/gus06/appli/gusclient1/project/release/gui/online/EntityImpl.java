package a.entity.gus06.appli.gusclient1.project.release.gui.online;

import a.framework.*;
import javax.swing.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20141015";}


	private Service manager;
	private Service putFile;
	private Service listing;
	private Service progress;


	private JPanel panel;
	private JButton button;
	private JTextArea area;
	
	private File dir;
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		putFile = Outside.service(this,"gus06.appli.gusclient1.sender.file.put");
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		progress = Outside.service(this,"gus06.swing.progressbar.progress1a");
		
		button = new JButton("Put project online");
		button.addActionListener(this);
		
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add((JComponent) progress.i(),BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		area.setText("");
	}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			String id = (String) manager.g();
			if(id==null) return;
			if(dir==null) return;
		
			String version = dir.getName();
			String oldPath = dir.getAbsolutePath();
			String newPath = "project/"+id+"/release/"+version;
			
			List l = (List) listing.t(dir);
			if(l.isEmpty()) return;
			
			println("PROJECT RELEASE STARTED ("+l.size()+" files)");
			for(int i=0;i<l.size();i++)
			{
				File file = (File) l.get(i);
				String path = file.getAbsolutePath().replace(oldPath,newPath).replace(File.separator,"/");
				
				println("uploading: "+path);
				putFile.v("progress",progress);
				putFile.v(path,file);
			}
			println("PROJECT RELEASE COMPLETE");
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}



	private void println(String m)
	{area.append(m+"\n");}
}
