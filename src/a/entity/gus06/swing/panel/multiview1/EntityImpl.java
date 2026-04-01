package a.entity.gus06.swing.panel.multiview1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.util.Vector;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20190805";}
	
	public final static int TAB1_VIEW = 0;
	public final static int TAB2_VIEW = 1;
	public final static int GRID_VIEW = 2;
	public final static int COLUMN_VIEW = 3;
	public final static int ROW_VIEW = 4;
	
	public final static int NUMBER_OF_MODES = 5;
	public final static int LIMIT = 9;

	
	
	private JPanel1 panel;

	public EntityImpl()
	{panel = new JPanel1();}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof String)
		{execute((String) obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("add")) {panel.addComponent((JComponent) obj);return;}
		if(key.equals("remove")) {panel.removeComponent((JComponent) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	private void execute(String cmd) throws Exception
	{
		if(cmd.equals("row"))
		{
			panel.changeToRowMode();
			return;
		}
		if(cmd.equals("column"))
		{
			panel.changeToColumnMode();
			return;
		}
		if(cmd.equals("grid"))
		{
			panel.changeToGridMode();
			return;
		}
		if(cmd.equals("tab1"))
		{
			panel.changeToTab1Mode();
			return;
		}
		if(cmd.equals("tab2"))
		{
			panel.changeToTab2Mode();
			return;
		}
		if(cmd.equals("next"))
		{
			panel.changeToNextMode();
			return;
		}
		throw new Exception("Invalid command: "+cmd);
	}
	
	
	

	private class JPanel1 extends JPanel
	{
		private Vector list = new Vector();
		private JTabbedPane tabbedPane = new JTabbedPane();
		private int mode = TAB1_VIEW;
		
		
		public JPanel1()
		{
			super(new GridLayout(1,1));
			addComponentListener(new ComponentAdapter(){
				public void componentResized(ComponentEvent e)
				{revalidate();}
			});
		}
		
		private boolean isTab1(){return mode==TAB1_VIEW;}
		private boolean isTab2(){return mode==TAB2_VIEW;}
		private boolean isGrid(){return mode==GRID_VIEW;}
		private boolean isColumn(){return mode==COLUMN_VIEW;}
		private boolean isRow(){return mode==ROW_VIEW;}
		
		private int count() {return list.size();}
		private JComponent get(int i){return (JComponent) list.get(i);}
		private boolean has(JComponent comp) {return list.contains(comp);}
	
	
		private int tabPosition = 0;
		
		private void memorizeTabPosition()
		{
			if(isTab1() || isTab2())
			tabPosition = tabbedPane.getSelectedIndex();
		}
		
		
		public void addComponent(JComponent comp)
		{
			if(count()==LIMIT)return;
			if(has(comp))return;
			list.add(comp);
			rebuildView();
		}
		
		public void removeComponent(JComponent comp)
		{
			list.remove(comp);
			rebuildView();
		}
		
		public void changeView(int newMode)
		{
			if(mode==newMode) return;
			memorizeTabPosition();
			mode = newMode;
			rebuildView();
		}
		
		public void changeToColumnMode()
		{changeView(COLUMN_VIEW);}
		
		public void changeToRowMode()
		{changeView(ROW_VIEW);}
		
		public void changeToGridMode()
		{changeView(GRID_VIEW);}
		
		public void changeToTab1Mode()
		{changeView(TAB1_VIEW);}
		
		public void changeToTab2Mode()
		{changeView(TAB2_VIEW);}
		
		public void changeToNextMode()
		{
			int newMode = mode+1;
			if(newMode==NUMBER_OF_MODES) newMode=0; 
			changeView(newMode);
		}
		
		
		public void rebuildView()
		{
			switch(mode)
			{
				case COLUMN_VIEW:
				rebuildColumnView();
				break;
				
				case ROW_VIEW:
				rebuildRowView();
				break;
				
				case GRID_VIEW:
				rebuildGridView();
				break;
				
				case TAB1_VIEW:
				rebuildTab1View();
				break;
				
				case TAB2_VIEW:
				rebuildTab2View();
				break;
			}
		}
		
		
		public void displayFull(int index)
		{
			if(index<0 || index>=count())return;
			removeAll();
			add(get(index));
			revalidate();
		}
		
		
		
		public void displayFull(JComponent comp)
		{
			if(!has(comp)) return;
			removeAll();
			add(comp);
			revalidate();
		}
		
		
		private void rebuildColumnView()
		{
			removeAll();
			repaint();
			int nb = Math.min(LIMIT,count());
			if(nb==2)
			{
				JSplitPane split = new JSplitPane();
				split.setDividerSize(3);
				split.setResizeWeight(0.5);
				split.setLeftComponent(get(0));
				split.setRightComponent(get(1));
				if(getWidth()>0)split.setDividerLocation((int)getWidth()/2); 
				setLayout(new BorderLayout());
				add(split);
			}
			else
			{
				setLayout(new GridLayout(1,nb));
				for(int i=0;i<nb;i++)
				add(get(i));
			}
			revalidate();
		}
		
		
		private void rebuildRowView()
		{
			int nb = Math.min(LIMIT,count());
			
			removeAll();
			repaint();
			if(nb==2)
			{
				JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
				split.setDividerSize(3);
				split.setResizeWeight(0.5);
				split.setLeftComponent(get(0));
				split.setRightComponent(get(1));
				if(getHeight()>0)split.setDividerLocation((int)getHeight()/2); 
				setLayout(new BorderLayout());
				add(split);
			}
			else
			{
				setLayout(new GridLayout(nb,1));
				for(int i=0;i<nb;i++)
				add(get(i));
			}
			revalidate();
		}
		
		
		private void rebuildGridView()
		{
			int nb = Math.min(LIMIT,count());
			
			if(nb<3){rebuildRowView();return;}
			if(nb==3){rebuildGridView(2,2);return;}
			if(nb==4){rebuildGridView(2,2);return;}
			if(nb==5){rebuildGridView(3,2);return;}
			if(nb==6){rebuildGridView(3,2);return;}
			if(nb==7){rebuildGridView(3,3);return;}
			if(nb==8){rebuildGridView(3,3);return;}
			if(nb==9){rebuildGridView(3,3);return;}
		}
		
		
		private void rebuildTab1View()
		{
			int nb = Math.min(LIMIT,count());
			
			removeAll();
			repaint();
			setLayout(new BorderLayout());
			
			tabbedPane.removeAll();
			tabbedPane.setTabPlacement(JTabbedPane.TOP);
			for(int i=0;i<nb;i++)
			tabbedPane.addTab(""+(i+1),get(i));
			
			if(tabPosition>=nb)tabPosition = nb-1;
			tabbedPane.setSelectedIndex(tabPosition);
			
			add(tabbedPane,BorderLayout.CENTER);
			revalidate();
		}
		
		
		private void rebuildTab2View()
		{
			removeAll();
			repaint();
			int nb = Math.min(LIMIT,count());
			setLayout(new BorderLayout());
			
			tabbedPane.removeAll();
			tabbedPane.setTabPlacement(JTabbedPane.LEFT);
			for(int i=0;i<nb;i++)
			tabbedPane.addTab(""+(i+1),get(i));
			
			if(tabPosition>=nb)tabPosition = nb-1;
			tabbedPane.setSelectedIndex(tabPosition);
			
			add(tabbedPane,BorderLayout.CENTER);
			revalidate();
		}
		
		
		private void rebuildGridView(int x, int y)
		{
			removeAll();
			repaint();
			int nb = Math.min(LIMIT,count());
			setLayout(new GridLayout(x,y));
			
			for(int i=0;i<nb;i++)
			add(get(i));
			revalidate();
		}
	}
}
