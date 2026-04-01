package a.entity.gus06.dir.explorer.simple.tree;

import java.io.File;
import java.util.Arrays;

public class ExplorerTreeModelDyn extends AbstractTreeModel {
    
	public ExplorerTreeModelDyn()
	{super();}
	
	public ExplorerTreeModelDyn(File root)
	{super(root);}
	


	protected File[] toChildrenSorted(File f)
	{
		if(f==null) return null;
		File[] children = f.listFiles();
		if(children==null) return null;
		Arrays.sort(children);
		return children;
	}
	protected File[] toChildrenUnsorted(File f)
	{
		if(f==null) return null;
		File[] children = f.listFiles();
		return children;
	}
}
