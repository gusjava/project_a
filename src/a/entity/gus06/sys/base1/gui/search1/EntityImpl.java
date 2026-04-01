package a.entity.gus06.sys.base1.gui.search1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20150529";}


	private Service progress;

	private Object base;
	private Object search;
	private String rule;
	private Thread t;
	
	private JPanel panel;
	private JTextField field;
	private JTextArea area;
	


	public EntityImpl() throws Exception
	{
		progress = Outside.service(this,"*gus06.swing.progressbar.progress1a");
		
		field = new JTextField();
		field.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{startSearch();}
		});
		
		area = new JTextArea();
		area.setEditable(false);
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add((JComponent) progress.i(),BorderLayout.SOUTH);
	}
	
	

	public void actionPerformed(ActionEvent e)
	{showResults();}
	
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		initSearch();
		area.setText("");
		progress.v("reset",null);
	}
	
	
	
	
	private void initSearch() throws Exception
	{
		if(search!=null)
		{
			((E)search).e();
			((S)search).removeActionListener(this);
		}
		
		search = ((R) base).r("search");
		((S)search).addActionListener(this);
		((V)search).v("progress",progress);
	}
	
	
	
	
	
	private void startSearch()
	{
		try
		{
			area.setText("");
			
			if(base==null) return;
			if(t!=null && t.isAlive()) return;
			
			rule = field.getText();
			((V) search).v("rule",rule);
			field.setEnabled(false);
			
			t = new Thread((Runnable) search,"THREAD_"+getClass().getName()+"_search");
			t.start();
		}
		catch(Exception e)
		{Outside.err(this,"startSearch()",e);}
	}
	
	
	
	
	
	private void showResults()
	{
		try
		{
			field.setEnabled(true);
			
			if(search==null) return;
			Map result = (Map) ((R) search).r("result");
			String header = getDisplay(result);
			
			println(header);
			println("");
			
			ArrayList keys = new ArrayList(result.keySet());
			Collections.sort(keys);
			
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				Map m = (Map) result.get(key);
				
				println(key);
			}
		}
		catch(Exception e)
		{Outside.err(this,"showResults()",e);}
	}



	private String getDisplay(Map result)
	{
		if(result==null) return "null";
		return "found:"+result.size();
	}
	
	
	private void println(String m)
	{area.append(m+"\n");}
}
