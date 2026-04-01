package a.entity.gus06.sys.filetool.ext.dirdoubloonviewer1.holder;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JTree;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20220715";}
	
	
	private Service findRoot;
	private Service buildButton;
	private Service compute;
	private Service buildTreeTable;
	private Service treeExplorer;
	private Service treeRenderer;
	private Service dataHolder;
	
	private JPanel panel;
	private JButton button;
	
	private Map map;
	private File root;
	
	private Thread t;
	


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		compute = Outside.service(this,"gus06.dir.listing.dirtomap.file_doublooninfo");
		buildTreeTable = Outside.service(this,"gus06.sys.treetable1.filesize");
		treeExplorer = Outside.service(this,"*gus06.dir.explorer.simple.tree");
		treeRenderer = Outside.service(this,"gus06.dir.explorer.treerenderer1");
		dataHolder = Outside.service(this,"*gus06.data.holder0");
		
		JTree tree = (JTree) treeExplorer.i();
		treeRenderer.p(tree);
		
		JComponent comp = (JComponent) buildTreeTable.t(new Object[]{tree,dataHolder});
		
		button = (JButton) buildButton.t(this);
		button.setText("analyze");
		
		panel = new JPanel(new BorderLayout());
		panel.add(comp,BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		
		treeExplorer.p(root);
		dataHolder.p(null);
	}
	
	
	public void run()
	{perform();}
	
	
	
	
	private void perform()
	{
		try
		{
			if(root==null || !root.isDirectory()) return;
			
			Map map = (Map) compute.t(root);
			dataHolder.p(map);
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
}