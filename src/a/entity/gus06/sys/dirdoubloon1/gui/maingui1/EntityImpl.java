package a.entity.gus06.sys.dirdoubloon1.gui.maingui1;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.util.Map;
import java.awt.GridLayout;

public class EntityImpl implements Entity, ActionListener, I, V, E {

	public String creationDate() {return "20221217";}


	private Service engine;
	private Service tab;
	private Service gui1;
	private Service gui2;
	
	private JButton buttonAnalyze;
	private JButton buttonClean;
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this,"*gus06.sys.dirdoubloon1.engine");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		gui1 = Outside.service(this,"*gus06.sys.dirdoubloon1.gui.gui1");
		gui2 = Outside.service(this,"*gus06.sys.dirdoubloon1.gui.gui2");
		
		tab.v("Summary",gui1.i());
		tab.v("All",gui2.i());
		
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
		panel.add((JComponent) tab.i(), BorderLayout.CENTER);
		panel.add(panelButtons, BorderLayout.SOUTH);
		
		buttonClean.setEnabled(false);
		engine.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("roots"))
		{engine.v("roots",obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}



	public void e() throws Exception
	{analyze();}
	
	
	private void analyze()
	{
		try
		{
			buttonClean.setEnabled(false);
			buttonAnalyze.setEnabled(false);
			buttonAnalyze.setText("Grouping by size ...");
			
			handleData(null);
			engine.p("analyze");
		}
		catch(Exception e)
		{Outside.err(this,"analyze()",e);}
	}
	
	
	private void clean()
	{
		try
		{
			buttonClean.setEnabled(false);
			buttonAnalyze.setEnabled(false);
			buttonClean.setText("Cleaning ...");
			
			engine.p("clean");
		}
		catch(Exception e)
		{Outside.err(this,"clean()",e);}
	}

	


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("groupedBySize()")) groupedBySize();
		if(s.equals("groupedByMd5()")) groupedByMd5();
		if(s.equals("cleaned()")) cleaned();
		else if(s.equals("failed()")) failed();
	}
	
	private void groupedBySize()
	{
		buttonClean.setEnabled(false);
		buttonAnalyze.setEnabled(false);
		buttonAnalyze.setText("Grouping by MD5 ...");
	}
	
	
	private void groupedByMd5()
	{
		try
		{
			buttonClean.setEnabled(true);
			buttonAnalyze.setEnabled(true);
			buttonAnalyze.setText("Analyze");
			
			Map map = (Map) engine.r("md5Map");
			handleData(map);
		}
		catch(Exception e)
		{Outside.err(this,"groupedByMd5()",e);}
	}
	
	
	private void cleaned()
	{
		try
		{
			buttonClean.setEnabled(true);
			buttonAnalyze.setEnabled(true);
			buttonClean.setText("Clean");
			
			Map map = (Map) engine.r("md5Map");
			handleData(map);
		}
		catch(Exception e)
		{Outside.err(this,"cleaned()",e);}
	}
	
	
	
	private void failed()
	{
		try
		{
			buttonAnalyze.setEnabled(true);
			buttonAnalyze.setText("Analyze");
			
			gui1.p(null);
			Exception exception = (Exception) engine.r("exception");
			
			//display exception
		}
		catch(Exception e)
		{Outside.err(this,"failed()",e);}
	}
	
	
	private void handleData(Map map) throws Exception
	{
		gui1.p(map);
		gui2.p(map);
	}
}