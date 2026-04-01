package a.entity.gus06.sys.dirdoubloon1.gui.gui2;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.util.Map;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221219";}

	private Service newViewer;
	private Service actionBuilder;
	private Service toolbar;

	private JScrollPane scroll;
	private JPanel panelCenter;
	private JLabel labelNumber;
	private JPanel panel;
	
	private Map map;
	private List keys;
	

	public EntityImpl() throws Exception
	{
		newViewer = Outside.service(this,"factory#gus.sys.dirdoubloon1.gui.md5viewer1");
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		
		panelCenter = new JPanel(new GridLayout(0,1));
		
		scroll = new JScrollPane(panelCenter);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(labelNumber, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		if(map==null) {resetGui();return;}
		updateGui();
	}
	
	
	
	private void resetGui()
	{
		try
		{
			labelNumber.setText(" ");
			
			panelCenter.removeAll();
			panelCenter.validate();
			panelCenter.repaint();
		}
		catch(Exception e)
		{Outside.err(this,"resetGui()",e);}
	}
	
	
	private void updateGui()
	{
		try
		{
			keys = new ArrayList(map.keySet());
			labelNumber.setText(" "+keys.size());
			Collections.sort(keys, new Comparator1(map));
			
			for(int i=0;i<keys.size();i++)
			{
				String md5 = (String) keys.get(i);
				Map m = (Map) map.get(md5);
				
				Object viewer = newViewer.g();
				((P)viewer).p(m);
				
				JComponent c = (JComponent) ((I)viewer).i();
				panelCenter.add(c);
			}
			panelCenter.validate();
			panelCenter.repaint();
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	


	private class Comparator1 implements Comparator
	{
		private Map map;
		public Comparator1(Map map)
		{this.map = map;}
		
		public int compare(Object o1, Object o2)
		{
			Map m1 = (Map) map.get(o1);
			Map m2 = (Map) map.get(o2);
			
			Long lost1 = (Long) m1.get("lost");
			Long lost2 = (Long) m2.get("lost");
			
			return lost2.compareTo(lost1);
		}
	}
}