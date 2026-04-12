package a.entity.gus06.y.maven1.gui1;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, V, R, G, ActionListener {

	public String creationDate() {return "20251220";}

	private Service treeView;
	private Service detailView;
	
	private Object engine;
	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		treeView = Outside.service(this,"*gus06.y.maven1.gui1.tree");
		detailView = Outside.service(this,"*gus06.y.maven1.gui1.detail");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(500);
		
		split.setLeftComponent((JComponent) treeView.i());
		split.setRightComponent((JComponent) detailView.i());
		
		treeView.addActionListener(this);
	}
	
	public Object i() throws Exception
	{return split;}
	
	public Object g() throws Exception
	{return treeView.g();}
	
	
	public Object r(String key) throws Exception
	{
		
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("engine"))
		{
			treeView.v("engine", obj);
			detailView.v("engine", obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("selected()")) {selected();return;}
	}
	
	private void selected()
	{
		try
		{
			detailView.p(treeView.g());
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
