package a.entity.gus06.sys.filetool.ext.repartition1.gui.loader;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalLabelUI;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, Runnable {

	public String creationDate() {return "20151021";}


	private Service readFile;
	private Service progress;

	private JPanel panel;
	private JButton button;
	private Map map;
	private File[] files;
	
	private Thread t;
	


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.sys.filetool.ext.repartition1.readfile");
		progress = Outside.service(this,"*gus06.swing.progressbar.progress1");
		
		button = new JButton("Load");
		button.addActionListener(this);
		button.setEnabled(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) progress.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.WEST);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		files = (File[]) obj;
		button.setEnabled(files!=null);
	}
	
	
	public Object g() throws Exception
	{return map;}
	

	public void actionPerformed(ActionEvent e)
	{startLoad();}
	
	
	
	private void startLoad()
	{
		if(files==null) return;
		if(t!=null && t.isAlive()) return;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			map = new HashMap();
			progress.v("size",""+files.length);
			for(File f:files)
			{
				map.put(f,readFile.t(f));
				progress.e();
			}
			complete();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}


	private void complete()
	{send(this,"complete()");}
}
