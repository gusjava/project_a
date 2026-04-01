package a.entity.gus06.appli.entityhistory.gui.history.selector;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTree;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class EntityImpl extends S1 implements Entity, I, G {

	public String creationDate() {return "20150501";}


	private Service mapHolder;
	private Service treeHolder;
	private Service treeRenderer;

	private JPanel panel;
	private JTree tree;
	private JLabel labelNumber;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		mapHolder = Outside.service(this,"gus06.appli.entityhistory.map.holder");
		treeHolder = Outside.service(this,"*gus06.swing.tree.holder.setfilter");
		treeRenderer = Outside.service(this,"gus06.swing.tree.cust.renderer1");
		
		tree = (JTree) treeHolder.i();
		treeRenderer.v("icon","entity");
		treeRenderer.p(tree);
		
		labelNumber = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree),BorderLayout.CENTER);
		panel.add(labelNumber,BorderLayout.SOUTH);
		
		mapHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{updateGui();}
		});
		treeHolder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selected();}
		});
		
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public Object g() throws Exception
	{
		Set set = (Set) treeHolder.g();
		if(set==null) return null;
		return filterMap(set);
	}


	
	
	
	
	private void updateGui()
	{
		try
		{
			map = (Map) mapHolder.g();
			if(map==null)
			{
				treeHolder.p(null);
				labelNumber.setText(" ");
				return;
			}
			treeHolder.p(map.keySet());
			labelNumber.setText(" Number: "+map.size());
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	private Map filterMap(Set set)
	{
		Map m = new HashMap();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			Object key = it.next();
			if(set.contains(key)) m.put(key,map.get(key));
		}
		return m;
	}


	private void selected()
	{send(this,"selected()");}
}
