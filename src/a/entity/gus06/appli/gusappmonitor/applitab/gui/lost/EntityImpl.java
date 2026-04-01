package a.entity.gus06.appli.gusappmonitor.applitab.gui.lost;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20191205";}

	public static final String STATE_LOST = "lost";


	private Service tab;
	private Service readFile;
	private Service chartDays;
	private Service chartMonths;
	private Service chartYears;

	private JTable tableDays;
	private JTable tableMonths;
	private JTable tableYears;
	
	private JPanel panel;
	private JButton button;
	
	
	
	private Object config;
	

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		readFile = Outside.service(this,"gus06.file.read.string.array.cs.utf8");
		chartDays = Outside.service(this,"*gus06.swing.table.holder.freqmap.bydate");
		chartMonths = Outside.service(this,"*gus06.swing.table.holder.freqmap.bymonth");
		chartYears = Outside.service(this,"*gus06.swing.table.holder.freqmap.byyear");
		
		chartDays.v("col2","Lost count");
		chartMonths.v("col2","Lost count");
		chartYears.v("col2","Lost count");
		
		tableDays = (JTable) chartDays.i();
		tableMonths = (JTable) chartMonths.i();
		tableYears = (JTable) chartYears.i();
		
		tab.v("GUI_chart#Days",new JScrollPane(tableDays));
		tab.v("GUI_chart#Months",new JScrollPane(tableMonths));
		tab.v("GUI_chart#Years",new JScrollPane(tableYears));
		
		button = new JButton("Refresh");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) tab.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{config = obj;}


	public void actionPerformed(ActionEvent e)
	{new Thread(this,"THREAD_"+getClass().getName()).start();}
	
	
	
	public void run()
	{
		try
		{
			File logDir = (File) ((R) config).r("logDir");
			if(logDir==null) return;
			
			File[] ff = logDir.listFiles();
			if(ff==null) return;
			
			Map mapDays = new HashMap();
			Map mapMonths = new HashMap();
			Map mapYears = new HashMap();
			
			for(File f:ff)
			{
				String[] lines = (String[]) readFile.t(f);
				for(String line:lines)
				{
					String[] nn = line.split("[\t:]");
					if(nn.length!=3) throw new Exception("Invalid row: "+line);
					
					String time = nn[0];
					String state = nn[2];
					
					if(state.equals(STATE_LOST))
					{
						String day = time.substring(0,8);
						String month = time.substring(0,6);
						String year = time.substring(0,4);
						
						append(mapDays,day);
						append(mapMonths,month);
						append(mapYears,year);
					}
				}
			}
			
			chartDays.p(mapDays);
			chartMonths.p(mapMonths);
			chartYears.p(mapYears);
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private void append(Map m, String value)
	{
		if(!m.containsKey(value))
		m.put(value,Integer.valueOf(1));
		else
		{
			Integer n = (Integer) m.get(value);
			m.put(value,Integer.valueOf(n.intValue()+1));
		}
	}
}
