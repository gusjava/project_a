package a.entity.gus06.sys.filemanagement1.gui.ebooks.ebooklist.viewer;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JScrollPane;
import java.util.Map;
import javax.swing.JLabel;
import java.util.HashMap;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Color;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20201103";}
	
	public static final String KEY_TITLE = "ebook.title";


	private Service fieldGui;
	private Service newViewer;
	private Service filterString;

	private JPanel panel;
	private JComponent field;
	private JScrollPane scroll;
	private JPanel panelCenter;
	private JLabel labelNumber;
	private JLabel labelLoading;
	
	private Object engine;
	private List md5List;
	
	private Map propMap;
	private Map previewMap;
	private Map viewerMap;
	
	private Thread t;
	private volatile boolean interrupt = false;
	

	public EntityImpl() throws Exception
	{
		fieldGui = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		newViewer = Outside.service(this,"factory#gus.sys.filemanagement1.gui.ebooks.ebook.viewer");
		filterString = Outside.service(this,"gus06.filter.string.build.allofthem_n");
		
		propMap = new HashMap();
		previewMap = new HashMap();
		viewerMap = new HashMap();
		
		field = (JComponent) fieldGui.i();
		
		labelNumber = new JLabel(" ");
		
		labelLoading = new JLabel("");
		labelLoading.setFont(labelLoading.getFont().deriveFont(Font.ITALIC));
		labelLoading.setForeground(Color.GRAY);
		
		panelCenter = new JPanel(new GridLayout(0,1));
		
		scroll = new JScrollPane(panelCenter);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(labelLoading,BorderLayout.NORTH);
		panel1.add(scroll,BorderLayout.CENTER);
		panel1.add(labelNumber,BorderLayout.SOUTH);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
		
		fieldGui.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive())
		{
			interrupt = true;
			t.join(200);
			if(t.isAlive()) return;
		}
		interrupt = false;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		md5List = (List) o[1];
		
		field.setEnabled(false);
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			if(engine==null || md5List==null) {reset();return;}
			
			clearPanelCenter();	
			propMap.clear();
			previewMap.clear();
			viewerMap.clear();
			
			loadData();
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		field.setEnabled(true);
	}
	
	
	
	
	private void loadData() throws Exception
	{
		int nb = md5List.size();
		labelLoading.setText(" Currently loading: 0 / "+nb+" ");
		labelLoading.setForeground(Color.GRAY);
		
		for(int i=0;i<nb;i++)
		{
			String md5 = (String) md5List.get(i);
			
			Map prop = (Map) ((R)engine).r("prop1:"+md5);
			Object preview = ((R)engine).r("preview:"+md5);
			
			propMap.put(md5,prop);
			previewMap.put(md5,preview);
			viewerMap.put(md5,newViewer(prop,preview));
			
			labelLoading.setText(" Currently loading: "+(i+1)+" / "+nb+" ");
			
			if(interrupt) return;
			sleep(20);
			if(interrupt) return;
		}
	}
	
	
	
	private Object newViewer(Map prop, Object preview) throws Exception
	{
		Object viewer = newViewer.g();
		((P) viewer).p(new Object[]{engine,prop,preview});
		return viewer;
	}
	
	
	private void reset()
	{
		engine = null;
		md5List = null;
		
		propMap.clear();
		previewMap.clear();
		viewerMap.clear();
		
		refresh();
		field.setEnabled(true);
	}
	
	


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			if(md5List==null)
			{
				clearPanelCenter();
				labelLoading.setText("");
				labelNumber.setText(" ");
				return;
			}
			
			String input = (String) fieldGui.g();
			F filter = (F) filterString.t(input);
			
			List md5List1 = new ArrayList(md5List);
			Collections.sort(md5List1,titleComparator());
			
			clearPanelCenter();
			
			int count = 0;
			for(int i=0;i<md5List1.size();i++)
			{
				String md5 = (String) md5List1.get(i);
				if(keepMd5(md5,input,filter) && viewerMap.containsKey(md5))
				{
					count++;
					Object viewer = viewerMap.get(md5);
					JComponent c = (JComponent) ((I)viewer).i();
					panelCenter.add(c);
				}
			}
			
			labelNumber.setText(" Number: "+count);
			
			SwingUtilities.invokeLater(()->{
				scroll.getVerticalScrollBar().setValue(0);
			});
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private boolean keepMd5(String md5, String input, F filter) throws Exception
	{
		if(input==null || input.equals("")) return true;
		
		String[] nn = input.split("[ ,;]+");
		for(String n : nn) if(md5.equals(n)) return true;
		
		if(filter==null) return true;
		return filter.f(title(md5));
	}
	
	private String title(String md5)
	{
		if(md5==null) return "";
		if(!propMap.containsKey(md5)) return "";
		Map prop = (Map) propMap.get(md5);
		if(prop.containsKey(KEY_TITLE)) return (String) prop.get(KEY_TITLE);
		return "";
	}
	
	private Comparator titleComparator()
	{return (s1,s2)->title((String) s1).compareTo(title((String) s2));}
	
	
	
	private void sleep(long t)
	{
		try{Thread.sleep(t);}
		catch(Exception e)
		{Outside.err(this,"sleep(long)",e);}
	}
	
	
	
	private void clearPanelCenter()
	{
		panelCenter.removeAll();
		panelCenter.validate();
		panelCenter.repaint();
	}
}