package a.entity.gus06.appli.gusdbmanager.gui.cx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}

	public static final boolean CLOSE_DISCONNECTED = false;
	public static final Color COLOR_ACTIVE = Color.BLACK;
	public static final Color COLOR_INACTIVE = Color.GRAY;


	
	private Service setForeground;
	private Service buildBottombar;
	private Service buildDataPane;

	public EntityImpl() throws Exception
	{
		setForeground = Outside.service(this,"gus.x.swing.comp.cust3.foreground.full");
		buildBottombar = Outside.service(this,"gus06.appli.gusdbmanager.gui.cx.bottombar");
		buildDataPane = Outside.service(this,"gus06.appli.gusdbmanager.gui.cx.data");
	}



	public Object t(Object obj) throws Exception
	{return new JPanel1(obj);}

	
	
	
	
	private class JPanel1 extends JPanel implements ActionListener
	{
		private Object holder;
		
		public JPanel1(Object holder) throws Exception
		{
			super(new BorderLayout());
			this.holder = holder;
			
			JComponent bottomBar = (JComponent) buildBottombar.t(holder);
			JComponent dataPane = (JComponent) buildDataPane.t(holder);
			
			JTabbedPane tabbed = new JTabbedPane();
			tabbed.addTab("Data explorer",dataPane);
			tabbed.addTab("SQL query",new JPanel());
			tabbed.addTab("User settings",new JPanel());
			tabbed.addTab("Analyze",new JPanel());
			tabbed.addTab("Gus Tools",new JPanel());
			
			add(tabbed,BorderLayout.CENTER);
			add(bottomBar,BorderLayout.SOUTH);
			
			((S)holder).addActionListener(this);
			updateGui(this,holder);
		}

		public void actionPerformed(ActionEvent e)
		{statusChanged();}
		
		private void statusChanged()
		{updateGui(this,holder);}
	}
	
	
	
	
	private void updateGui(JPanel1 panel, Object holder)
	{
		try
		{
			if(connected(holder))
			{
				setTitleColor(panel,COLOR_ACTIVE);
				return;
			}
			if(CLOSE_DISCONNECTED)
			{
				JTabbedPane tab = (JTabbedPane) panel.getParent();
				if(tab!=null) tab.remove(panel);
				return;
			}
			setTitleColor(panel,COLOR_INACTIVE);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui(JPanel1,Object)",e);}
	}
	
	
	
	
	private void setTitleColor(JPanel1 panel, Color color) throws Exception
	{
		JTabbedPane tab = (JTabbedPane) panel.getParent();
		if(tab==null) return;
		
		int index = tab.indexOfComponent(panel);
		Object tabComp = tab.getTabComponentAt(index);
		setForeground.p(new Object[]{tabComp,color});
	}
	
	
	private boolean connected(Object holder) throws Exception
	{return ((F) holder).f(null);}
}
