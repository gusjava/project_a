package a.entity.gus06.sys.filetool.ext.dirwordviewer1.holder;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JTree;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20210623";}
	
	public static final String KEY_WORDS = "words";
	public static final String KEY_OPTIONS = "options";
	
	
	private Service findRoot;
	private Service buildButton;
	private Service buildTreeTable;
	private Service treeExplorer;
	private Service treeRenderer;
	private Service dataHolder;
	private Service computeWord;
	private Service computeWord_i;
	private Service computeWord_n;
	private Service normalize;
	
	private JPanel panel;
	private JButton button;
	
	private Map map;
	private File root;
	
	private Thread t;
	


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		buildButton = Outside.service(this,"gus06.swing.button.build.runnable");
		buildTreeTable = Outside.service(this,"gus06.sys.treetable1.extmap");
		treeExplorer = Outside.service(this,"*gus06.dir.explorer.simple.tree");
		treeRenderer = Outside.service(this,"gus06.dir.explorer.treerenderer1");
		dataHolder = Outside.service(this,"*gus06.data.holder0");
		computeWord = Outside.service(this,"gus06.dir.listing.dirtomap.file_wordmap");
		computeWord_i = Outside.service(this,"gus06.dir.listing.dirtomap.file_wordmap_i");
		computeWord_n = Outside.service(this,"gus06.dir.listing.dirtomap.file_wordmap_n");
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		
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
			
			String words_ = get0(KEY_WORDS);
			if(words_==null) return;
			
			String options = get0(KEY_OPTIONS);
			T computer = findComputer(options);
			
			String[] words = words_.split(";");
			
			if(options==null || options.equals(""))
			{
				Map map = (Map) computeWord.t(new Object[]{root,words});
				dataHolder.p(new Object[]{words,map});
			}
			else if(options.equals("i"))
			{
				int nb = words.length;
				String[] words_i = new String[nb];
				for(int i=0;i<nb;i++) words_i[i] = words[i].toLowerCase();
		
				Map map = (Map) computeWord_i.t(new Object[]{root,words_i});
				dataHolder.p(new Object[]{words_i,map});
			}
			else if(options.equals("n"))
			{
				int nb = words.length;
				String[] words_n = new String[nb];
				for(int i=0;i<nb;i++) words_n[i] = (String) normalize.t(words[i]);
		
				Map map = (Map) computeWord_n.t(new Object[]{root,words_n});
				dataHolder.p(new Object[]{words_n,map});
			}
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
	
	
	private T findComputer(String options) throws Exception
	{
		if(options==null || options.equals("")) return computeWord;
		if(options.equals("i")) return computeWord_i;
		if(options.equals("n")) return computeWord_n;
		
		throw new Exception("Invalid options: "+options);
	}
}