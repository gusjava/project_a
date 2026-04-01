package a.entity.gus06.appli.gusappmonitor.gui.applis;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JLabel;
import java.awt.Color;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20190408";}
	
	public static final String STATE_CONNECTED = "connected";


	private Service closeable;
	private Service manager;
	private Service buildTab;
	private Service stateToColor;
	
	private Map mapComp;
	private Map mapLabel;


	public EntityImpl() throws Exception
	{
		closeable = Outside.service(this,"*gus06.swing.tabbedpane.build.closeable");
		manager = Outside.service(this,"gus06.appli.gusappmonitor.manager");
		buildTab = Outside.service(this,"gus06.appli.gusappmonitor.applitab.build");
		stateToColor = Outside.service(this,"gus06.appli.gusappmonitor.tool.statetocolor");
		
		mapComp = new HashMap();
		mapLabel = new HashMap();
		
		manager.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return closeable.i();}
	
	
	private Color stateToColor(String state)
	{
		try{return (Color) stateToColor.t(state);}
		catch(Exception e){Outside.err(this,"stateToColor(String)",e);}
		return Color.BLACK;
	}


	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("configAdded()")) configAdded();
	}
	
	
	private void configAdded()
	{
		try
		{
			R config = (R) manager.r("latest");
			String buildId = (String) config.r("buildId");
			
			if(mapComp.containsKey(buildId)) throw new Exception("MapComp already contains buildId: "+buildId);
			if(mapLabel.containsKey(buildId)) throw new Exception("MapLabel already contains buildId: "+buildId);
			
			JComponent comp = (JComponent) buildTab.t(config);
			JLabel1 label = new JLabel1(config);
			
			mapComp.put(buildId,comp);
			mapLabel.put(buildId,label);
			
			closeable.v("addCTab",new Object[]{label,comp});
		}
		catch(Exception e)
		{Outside.err(this,"configAdded()",e);}
	}
	





	private class JLabel1 extends JLabel implements ActionListener
	{
		private R config;
		
		public JLabel1(R config) throws Exception
		{
			super();
			this.config = config;
			((S) config).addActionListener(this);
			refresh();
		}
		
		public void actionPerformed(ActionEvent e)
		{refresh();}
		
		private void refresh()
		{
			try
			{
				String buildId = (String) config.r("buildId");
				String state = (String) config.r("state");
				Color color = stateToColor(state);
				
				setText(buildId);
				setForeground(color);
				
				if(!state.equals(STATE_CONNECTED))
				{
					mapComp.remove(buildId);
					mapLabel.remove(buildId);
				}
			}
			catch(Exception e)
			{Outside.err(EntityImpl.this,"refresh()",e);}
		}
	}
	
	
	
}
