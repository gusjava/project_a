package a.entity.gus06.sys.filemanagement1.gui.allocine.movielist.viewer;

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
import java.util.Set;

public class EntityImpl implements Entity, ActionListener, I, P, Runnable {

	public String creationDate() {return "20201101";}


	private Service fieldGui;
	private Service findProp;
	private Service findPoster;
	private Service findMd5Set;
	private Service newViewer;
	private Service filterString;

	private JPanel panel;
	private JComponent field;
	private JScrollPane scroll;
	private JPanel panelCenter;
	private JLabel labelNumber;
	private JLabel labelLoading;
	
	private Object engine;
	private List codeList;
	
	private Map propMap;
	private Map viewerMap;
	
	private Thread t;
	private volatile boolean interrupt = false;
	

	public EntityImpl() throws Exception
	{
		fieldGui = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		findProp = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.prop.find.map");
		findPoster = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.find.image.lazy");
		findMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.gen.code_md5.find.data");
		newViewer = Outside.service(this,"factory#gus06.sys.filemanagement1.gui.allocine.movie.viewer");
		filterString = Outside.service(this,"gus06.filter.string.build.allofthem_n");
		
		propMap = new HashMap();
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
			field.setEnabled(true);
			if(t.isAlive()) return;
		}
		interrupt = false;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		codeList = (List) o[1];
		
		field.setEnabled(false);
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			if(engine==null || codeList==null) {reset();return;}
			
			clearPanelCenter();	
			propMap.clear();
			viewerMap.clear();
			
			loadData();
			if(interrupt) return;
			refresh();
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
		field.setEnabled(true);
	}
	
	
	
	
	private void loadData() throws Exception
	{
		int nb = codeList.size();
		labelLoading.setText(" Currently loading: 0 / "+nb+" ");
		labelLoading.setForeground(Color.GRAY);
		
		for(int i=0;i<nb;i++)
		{
			String code = (String) codeList.get(i);
			
			Map prop = (Map) findProp.t(new Object[]{engine,code});
			Object poster = findPoster.t(new Object[]{engine,code});
			Set md5Set = (Set) findMd5Set.t(new Object[]{engine,code});
			
			propMap.put(code,prop);
			viewerMap.put(code,newViewer(prop,poster,md5Set));
			
			labelLoading.setText(" Currently loading: "+(i+1)+" / "+nb+" ");
			if(interrupt) return;
		}
	}
	
	
	
	private Object newViewer(Map prop, Object poster, Set md5Set) throws Exception
	{
		Object viewer = newViewer.g();
		((P) viewer).p(new Object[]{engine,prop,poster,md5Set});
		return viewer;
	}
	
	
	private void reset()
	{
		engine = null;
		codeList = null;
		
		propMap.clear();
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
			if(codeList==null)
			{
				clearPanelCenter();
				labelLoading.setText("");
				labelNumber.setText(" ");
				return;
			}
			
			String input = (String) fieldGui.g();
			F filter = (F) filterString.t(input);
			
			List codeList1 = new ArrayList(codeList);
			Collections.sort(codeList1,titleComparator());
			
			clearPanelCenter();
			
			int count = 0;
			for(int i=0;i<codeList1.size();i++)
			{
				String code = (String) codeList1.get(i);
				if(keepCode(code,input,filter) && viewerMap.containsKey(code))
				{
					count++;
					Object viewer = viewerMap.get(code);
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
	
	
	
	private boolean keepCode(String code, String input, F filter) throws Exception
	{
		if(input==null || input.equals("")) return true;
		
		String[] nn = input.split("[ ,;]+");
		for(String n : nn) if(code.equals(n)) return true;
		
		if(filter==null) return true;
		return filter.f(title(code));
	}
	
	private String title(String code)
	{
		if(code==null) return "";
		if(!propMap.containsKey(code)) return "";
		Map prop = (Map) propMap.get(code);
		if(prop.containsKey("title")) return (String) prop.get("title");
		if(prop.containsKey("originaltitle")) return (String) prop.get("originaltitle");
		return "";
	}
	
	private Comparator titleComparator()
	{return (code1,code2)->title((String) code1).compareTo(title((String) code2));}
	
	
	
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
