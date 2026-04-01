package a.entity.gus06.sys.filemanagement1.gui.detailpanel.dir.summary.preview.onclicked2;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210306";}
	
	public static final String KEY_SELECTED_BY = "selectedBy";
	public static final String KEY_PARENT = "parent";
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		Map selected = (Map) o[1];
		Map child = (Map) o[2];
		
		final JTree tree = (JTree) selected.get(KEY_SELECTED_BY);
		final List paths = childToPaths(child);
				
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				TreePath treePath = new TreePath(paths.toArray());
				tree.setSelectionPath(treePath);
				tree.scrollPathToVisible(treePath);
				tree.repaint();
			}
		});
	}
	
	
	private List childToPaths(Map child)
	{
		List paths = new ArrayList();
		Map node = child;
		while(node!=null)
		{
			paths.add(0,node);
			node = (Map) get(node,KEY_PARENT);
		}
		return paths;
	}
	
	private Object get(Map map, String key)
	{return map.containsKey(key) ? map.get(key) : null;}
}