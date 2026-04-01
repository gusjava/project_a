package a.entity.gus06.dir.explorer.simple.tree;

import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import javax.swing.tree.TreePath;

public class ExplorerTreeModelCache extends AbstractTreeModel {

	private Map cache;
	
	public ExplorerTreeModelCache()
	{
		super();
		cache = new HashMap();
	}
	
	public ExplorerTreeModelCache(File root)
	{
		super(root);
		cache = new HashMap();
	}
	
	
	protected synchronized File[] toChildrenSorted(File f)
	{
		if(f==null) return null;
		if(!cache.containsKey(f))
		{
			File[] children = f.listFiles();
			if(children==null) return null;
			Arrays.sort(children);
			cache.put(f,children);
		}
		return (File[]) cache.get(f);
	}
	
	protected File[] toChildrenUnsorted(File f)
	{
		return toChildrenSorted(f);
	}
	
	
	public synchronized void valueForPathChanged(TreePath path, Object newValue)
	{
		cache.clear();
		super.valueForPathChanged(path,newValue);
	}
}
