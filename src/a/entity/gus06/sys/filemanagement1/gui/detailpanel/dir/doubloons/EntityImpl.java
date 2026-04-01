package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250609";}


	private Service resultsGui;
	private Service compute;
	private Service clean;

	
	private Object engine;
	private Map selected;
	private Map md5Map;
	
	private JPanel panel;
	private JButton buttonAnalyze;
	private JButton buttonClean;
	private Thread t;
	
	
	public EntityImpl() throws Exception
	{
		resultsGui = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.results");
		compute = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.compute");
		clean = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.dir.doubloons.clean");
		
		buttonAnalyze = new JButton("Analyze");
		buttonAnalyze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{analyze();}
		});
		
		buttonClean = new JButton("Clean");
		buttonClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{clean();}
		});
		
		JPanel panelButtons = new JPanel(new GridLayout(1,2));
		panelButtons.add(buttonAnalyze);
		panelButtons.add(buttonClean);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) resultsGui.i(), BorderLayout.CENTER);
		panel.add(panelButtons, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		buttonAnalyze.setText("Analyze");
		buttonAnalyze.setEnabled(true);
		buttonClean.setEnabled(false);
		resultsGui.p(null);
		md5Map = null;
	}
	
	
	private void reset() throws Exception
	{
		engine = null;
		selected = null;
		
		buttonAnalyze.setEnabled(false);
		buttonClean.setEnabled(false);
		resultsGui.p(null);
		md5Map = null;
	}
	
	
	
	
	private void startAnalyze()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this::analyze, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void analyze()
	{
		try
		{
			if(selected==null) return;
			
			buttonClean.setEnabled(false);
			buttonAnalyze.setEnabled(false);
			
			buttonAnalyze.setText("Analyze (building results)");
			md5Map = (Map) compute.t(selected);
			buttonAnalyze.setText("Analyze (displaying results: "+md5Map.size()+")");
			resultsGui.p(new Object[]{engine, selected, md5Map});
			buttonAnalyze.setText("Analyze (displayed)");

			buttonClean.setEnabled(true);
		}
		catch(Exception e)
		{Outside.err(this,"analyze()",e);}
	}
	
	
	
	private void startClean()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this::clean, "THREAD_"+getClass().getName());
		t.start();
	}
	
	private void clean()
	{
		try
		{
			buttonClean.setEnabled(false);
			buttonAnalyze.setEnabled(false);
			
			clean.p(new Object[]{engine, selected, md5Map});
		}
		catch(Exception e)
		{Outside.err(this,"clean()",e);}
	}
}