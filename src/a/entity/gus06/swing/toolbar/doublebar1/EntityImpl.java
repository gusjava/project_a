package a.entity.gus06.swing.toolbar.doublebar1;

import a.framework.*;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class EntityImpl implements Entity, I, V {

	public String creationDate() {return "20190501";}
	
	
	private Service actionBuilder;

	private JPanel0 panel;

	private JToolBar2 bar1;
	private JToolBar2 bar2;
	
	private JToolBar2 current;



	public EntityImpl() throws Exception
	{
		actionBuilder = Outside.service(this,"gus06.swing.action.builder0");
		bar1 = new JToolBar2();
		current = bar1;
		
		panel = new JPanel0();
		panel.rebuild();
	}



	public Object i()
	{return panel;}



	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("action"))
		{addAction((Action)obj);return;}
		
		if(key.equals("group"))
		{addGroup((Action)obj);return;}
		
		if(key.equals("toggle"))
		{addToggle((Action)obj);return;}
		
		if(key.equals("comp"))
		{addComp((JComponent)obj);return;}
		
		if(key.equals("separator"))
		{addSeparator();return;}
		
		if(key.equals("start"))
		{start((String)obj);return;}
		
		if(key.equals("end"))
		{end();return;}
		
		throw new Exception("Unknown key: "+key);
	}


	
	private void addAction(Action action)
	{current.add(action);}
	
	private void addToggle(Action action)
	{current.addToggle(action);}
	
	private void addGroup(Action action)
	{current.addGroup(action);}
	
	private void addComp(JComponent comp)
	{current.add(comp);}
	
	private void addSeparator()
	{current.addSeparator();}
	
	
	
	
	
	
	private void start(String actionId) throws Exception
	{
		current = new JToolBar2();
		E execute = new ExecuteBar2(current);
		Action action = (Action) actionBuilder.t(new Object[]{actionId,execute});
		bar1.addGroup(action);
	}
	
	
	
	private void end()
	{
		current = bar1;
	}
	
	
	
	private class ExecuteBar2 implements E
	{
		JToolBar2 bar;
		public ExecuteBar2(JToolBar2 bar)
		{this.bar = bar;}
		
		public void e() throws Exception
		{
			if(bar2==bar) bar2 = null;
			else bar2 = bar;
			panel.rebuild();
		}
	}
	
	
	
	private class JPanel0 extends JPanel
	{
		public JPanel0()
		{super(new BorderLayout());}

		public void rebuild()
		{
			removeAll();
			if(bar2==null)
			{
				setLayout(new GridLayout(1,1));
				add(bar1);
				revalidate();
			}
			else
			{
				setLayout(new GridLayout(2,1));
				add(bar2);
				add(bar1);
				revalidate();
			}
			
			//validateTree();
			validate();
			repaint();
		}
	}
}
