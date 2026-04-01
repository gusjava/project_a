package a.entity.gus06.sys.git1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20201126";}


	private Service findDir;
	private Service gitBuilder;
	private Service gitCreate;
	private Service guiHolder;
	private Service renderLabel;


	private JPanel panel;
	private JButton button;
	private JLabel label;
	
	private File initDir;
	private File gitDir;
	private Thread t;
	private Object git;
	

	public EntityImpl() throws Exception
	{
		findDir = Outside.service(this,"gus06.sys.git1.find.gitfolder");
		gitBuilder = Outside.service(this,"gus06.sys.git1.builder");
		gitCreate = Outside.service(this,"gus06.sys.git1.create");
		guiHolder = Outside.service(this,"*gus06.sys.git1.gui.tab");
		renderLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		button = new JButton(" ");
		button.addActionListener(this);
		button.setEnabled(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) guiHolder.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		initDir = (File) obj;
		if(initDir==null) {reset();return;}
		
		reload();
		button.setEnabled(true);
		button.setText(hasGitDir() ? "Connect" : "Create");
	}
	
	
	
	private void reset() throws Exception
	{
		gitDir = null;
		button.setEnabled(false);
		button.setText(" ");
		renderLabel.v(" ",label);
		guiHolder.p(null);
	}
	
	
	private void reload() throws Exception
	{
		gitDir = (File) findDir.t(initDir);
		renderLabel.v(buildLabelDisplay(),label);
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
		if(!hasGitDir())
		{
			button.setForeground(Color.ORANGE);
			button.setText("Creating...");
			
			if(!create())
			{
				button.setForeground(Color.RED);
				button.setText("Creation failed");
				return;
			}
		}
		
		button.setForeground(Color.ORANGE);
		button.setText("Connecting...");
		
		if(!connect())
		{
			button.setForeground(Color.RED);
			button.setText("Connection failed");
			return;
		}
		if(git==null)
		{
			button.setForeground(Color.BLACK);
			button.setText("Connect");
			return;
		}
		
		button.setForeground(Color.BLUE);
		button.setText("Connected");
	}
	
	
	
	
	private boolean create()
	{
		try
		{
			gitCreate.p(initDir);
			reload();
			
			if(!hasGitDir()) throw new Exception("Git creation done but gitDir not found");
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"create()",e);
			return false;
		}
	}
	
	
	
	private boolean connect()
	{
		try
		{
			guiHolder.p(null);
			git = gitBuilder.t(gitDir);
			guiHolder.p(git);
			return true;
		}
		catch(Exception e)
		{
			Outside.err(this,"connect()",e);
			return false;
		}
	}
	
	
	
	private String buildLabelDisplay()
	{
		if(hasGitDir()) return "dir_git#"+gitDir.getAbsolutePath();
		if(initDir.isDirectory()) return "dir#"+initDir.getAbsolutePath();
		return initDir.getAbsolutePath();
	}
	
	private boolean hasGitDir()
	{return gitDir!=null && gitDir.isDirectory();}
}