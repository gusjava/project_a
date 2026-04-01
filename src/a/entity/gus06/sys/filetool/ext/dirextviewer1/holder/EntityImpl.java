package a.entity.gus06.sys.filetool.ext.dirextviewer1.holder;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JTree;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20161117";}
	
	public static final String KEY_EXT = "ext";
	
	
	private Service findRoot;
	private Service buildButton;
	private Service computeExt;
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
		computeExt = Outside.service(this,"gus06.dir.listing.dirtomap.file_extmap");
		buildTreeTable = Outside.service(this,"gus06.sys.treetable1.extmap");
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
			
			String ext_ = get0(KEY_EXT);
			if(ext_==null) return;
			
			String[] ext = ext_.split(";");
			
			Map map = (Map) computeExt.t(root);
			dataHolder.p(new Object[]{ext,map});
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}
