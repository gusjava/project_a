package a.entity.gus06.appli.gusclient1.gui.space.projects.analyzer.entities;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.JTree;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20150311";}


	private Service findDependencies;
	private Service viewer;
	private Service renderer;
	private Service manager;


	private JPanel panel;
	private JButton button;
	
	

	public EntityImpl() throws Exception
	{
		findDependencies = Outside.service(this,"gus06.appli.gusclient1.project.config.entities.finddependencies");
		viewer = Outside.service(this,"*gus06.data.viewer.map.depmap");
		renderer = Outside.service(this,"gus06.swing.tree.cust.renderer1");
		manager = Outside.service(this,"gus06.appli.gusclient1.project.manager");
		
		button = new JButton("Analyze");
		button.addActionListener(this);
		
		JTree tree = (JTree) viewer.r("tree");
		
		renderer.v("icon","entity");
		renderer.p(tree);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) viewer.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		manager.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){reset();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			Map map = (Map) findDependencies.g();
			viewer.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	private void reset()
	{
		try{viewer.p(null);}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
}
