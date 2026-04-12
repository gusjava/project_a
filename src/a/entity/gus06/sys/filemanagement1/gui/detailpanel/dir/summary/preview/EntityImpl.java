package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary.preview;

import a.framework.*;
import java.util.Map;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.Border;
import java.util.HashMap;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20200108";}
	
	public static final String KEY_CHILDREN = "children";
	public static final String KEY_SELECTED_BY = "selectedBy";
	public static final String KEY_MD5 = "md5";
	public static final String KEY_NAME = "name";
	public static final String KEY_PARENT = "parent";
	
	public static final String FAILED_MD5 = "###";
	
	public static final Border BORDER_HOVER = BorderFactory.createRaisedBevelBorder();
	public static final Border BORDER_EMPTY = BorderFactory.createEmptyBorder(0,0,0,0);
	
	public static final Dimension DIM = new Dimension(0,150);
	public static final int LOAD_LIMIT = 1000;
	public static final int ROW_NB = 8;
	
	


	private Service screenBuilder;
	private Service keepFiles;
	private Service findPreview1;
	private Service onClicked2;
	
	private JComponent comp;
	
	private Object engine;
	private Map selected;
	
	private List screens;
	private Map comp_handler;
	
	private JPanel panelGrid;
	private JPanel panel;
	private JLabel label;
	
	private Thread t;
	private volatile boolean interrupt = false;
	
	
	public EntityImpl() throws Exception
	{
		screenBuilder = Outside.service(this,"factory#gus06.swing.panel.screen.image");
		keepFiles = Outside.service(this,"gus06.sys.filemanagement1.tool.treemap.children.filetype");
		findPreview1 = Outside.service(this,"gus06.sys.filemanagement1.tool.preview1.find.image");
		onClicked2 = Outside.service(this,"gus06.sys.filemanagement1.gui.detailpanel.dir.summary.preview.onclicked2");
		
		screens = new ArrayList();
		comp_handler = new HashMap();
		
		panelGrid = new JPanel(new GridLayout(0,ROW_NB,5,5));
		panelGrid.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JScrollPane scroll = new JScrollPane(panelGrid);
		scroll.getVerticalScrollBar().setUnitIncrement(16);
		
		label = new JLabel("");
		label.setFont(label.getFont().deriveFont(Font.ITALIC));
		label.setForeground(Color.GRAY);
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
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
		
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	private void reset()
	{
		try
		{
			panelGrid.removeAll();
			
			engine = null;
			selected = null;
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	
	public void run()
	{
		try
		{
			panelGrid.removeAll();
			
			for(int i=0;i<screens.size();i++)
			((P) screens.get(i)).p(null);
			
			List children = (List) selected.get(KEY_CHILDREN);
			children = (List) keepFiles.t(children);
			
			int nb = children.size();
			if(nb>LOAD_LIMIT) nb = LOAD_LIMIT;
			
			label.setText(" Currently loading: 0 / "+nb+" ");
			
			for(int i=0;i<nb;i++)
			{
				Map child = (Map) children.get(i);
				String md5 = (String) child.get(KEY_MD5);
				String name = (String) child.get(KEY_NAME);
				
				P screen = findScreen(i);
				if(interrupt) return;
				
				JComponent comp = toComp((I) screen);
				panelGrid.add(comp);
				
				comp.setMaximumSize(DIM);
				comp.setMinimumSize(DIM);
				comp.setPreferredSize(DIM);
				comp.setBorder(BORDER_EMPTY);
				comp.setToolTipText(name);
				
				handleComp(comp,selected,child);
				
				if(md5.equals(FAILED_MD5))
				{
					comp.setBackground(Color.LIGHT_GRAY);
				}
				else
				{
					Object preview = findPreview1.t(new Object[]{engine,md5});
					screen.p(preview);
				}
				
				label.setText(" Currently loading: "+(i+1)+" / "+nb+" ");
				
				if(interrupt) return;
				sleep(20);
				if(interrupt) return;
			}
			
			label.setText("");
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	private P findScreen(int index) throws Exception
	{
		while(screens.size()<=index) screens.add(screenBuilder.g());
		return (P) screens.get(index);
	}
	
	
	private void sleep(long t)
	{
		try{Thread.sleep(t);}
		catch(Exception e)
		{Outside.err(this,"sleep(long)",e);}
	}
	
	
	private JComponent toComp(I screen) throws Exception
	{return (JComponent) screen.i();}
	
	
	
	private void handleComp(JComponent comp, Map selected, Map child)
	{
		if(!comp_handler.containsKey(comp))
			comp_handler.put(comp,new CompHandler(comp,selected,child));
		else ((CompHandler) comp_handler.get(comp)).init(selected,child);
	}
	
	
	
	
	private class CompHandler implements MouseListener
	{
		private JComponent comp;
		private Map selected;
		private Map child;
		
		public CompHandler(JComponent comp, Map selected, Map child)
		{
			this.comp = comp;
			this.selected = selected;
			this.child = child;
			
			comp.addMouseListener(this);
		}
		
		public void init(Map selected, Map child)
		{
			this.selected = selected;
			this.child = child;
		}
		
		public void mouseClicked(MouseEvent e)
		{
			if(e.getClickCount()==2) onClicked2(selected,child);
		}
		public void mouseEntered(MouseEvent e)
		{
			comp.setBorder(BORDER_HOVER);
		}
		public void mouseExited(MouseEvent e)
		{
			comp.setBorder(BORDER_EMPTY);
		}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	
		
		
		private Object get(Map map, String key)
		{return map.containsKey(key) ? map.get(key) : null;}
	}
	
	
	private void onClicked2(Map selected, Map child)
	{
		try{onClicked2.p(new Object[]{engine,selected,child});}
		catch(Exception e)
		{Outside.err(this,"onClicked2(Map,Map)",e);}
	}
}