package a.entity.gus06.sys.filetool.ext.library1.gui.list;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.io.File;
import javax.swing.JList;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl extends S1 implements Entity, ActionListener, I, P, G, R, E {

	public String creationDate() {return "20200311";}

	public static final String STRUCT = "struct";
	public static final String DISPLAY = "display";
	public static final String CONTENT = "content";
	

	private Service listHolder;
	private Service renderList;
	private Service listTooltip;
	private Service dnd;
	private Service clearCPC;
	private Service fileToMark;
	private Service fileProvider;

	private JList list;
	
	private List keys;
	private List files;
	private List displays;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		listHolder = Outside.service(this,"*gus06.swing.list.holder.collection");
		renderList = Outside.service(this,"gus06.sys.filetool.ext.library1.gui.list.renderer");
		listTooltip = Outside.service(this,"gus06.swing.list.cust3.tooltip1");
		dnd = Outside.service(this,"gus06.awt.dnd");
		clearCPC = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
		fileToMark = Outside.service(this,"gus06.file.findcolor1.filetomark");
		fileProvider = Outside.service(this,"m102.r.fileprovider");
		
		list = (JList) listHolder.i();
		renderList.p(list);
		clearCPC.p(list);
		
		T toolTipBuilder = this::buildTooltip;
		listTooltip.p(new Object[]{list,toolTipBuilder});
		
		dnd.p(new Object[]{list,null,this});
		
		listHolder.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return list;}
	
	
	
	public void p(Object obj) throws Exception
	{map = (Map) obj;}
	
	
	
	public Object g() throws Exception
	{return selectedFile();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("selectedKey")) return selectedKey();
		if(key.equals("selectedKeys")) return selectedKeys();
		if(key.equals("selectedFiles")) return selectedFiles();
		if(key.equals("selectedItems")) return selectedItems();
		if(key.equals("files")) return files;
		
		if(key.equals("keys")) 
			return new String[]{
				"selectedKey",
				"selectedKeys",
				"selectedFiles",
				"selectedItems",
				"files"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	public void e() throws Exception
	{refresh();}
	
	
	
	private void refresh() throws Exception
	{
		listHolder.removeActionListener(this);
		List selectedKeys = selectedKeys();
		
		keys = new ArrayList();
		files = new ArrayList();
		displays = new ArrayList();
		
		String struct = get0(STRUCT);
		if(struct!=null && !struct.equals(""))
		{
			String[] nn = struct.split(";");
			for(String n:nn) if(!n.equals(""))
			{
				String display = get1(DISPLAY+"."+n);
				String path = get1(CONTENT+"."+n);
				File file = (File) fileProvider.r(path);
				String marker = (String) fileToMark.t(file);
				
				keys.add(n);
				files.add(file);
				displays.add(marker+display);
			}
		}
		
		listHolder.p(displays);
		selectKeys(selectedKeys);
		listHolder.addActionListener(this);
	}
	
	private boolean isEmpty(File file)
	{
		if(file.isFile()) return file.length()==0;
		if(file.isDirectory()) return file.list().length==0;
		return true;
	}
	
	
	
	
	private String selectedKey() throws Exception
	{
		Integer indexObj = selectedIndex();
		return indexObj!=null ? key(indexObj.intValue()) : null;
	}
	
	private List selectedKeys() throws Exception
	{
		int[] indices = selectedIndices();
		return indices!=null ? toKeyList(indices) : null;
	}
	
	private List toKeyList(int[] indices)
	{
		List list = new ArrayList();
		for(int i=0;i<indices.length;i++)
		list.add(key(indices[i]));
		return list;
	}
	
	
	
	private File selectedFile() throws Exception
	{
		Integer indexObj = selectedIndex();
		return indexObj!=null ? file(indexObj.intValue()) : null;
	}
	
	private List selectedFiles() throws Exception
	{
		int[] indices = selectedIndices();
		return indices!=null ? toFileList(indices) : null;
	}
	
	private List selectedItems() throws Exception
	{
		int[] indices = selectedIndices();
		return indices!=null ? toItemList(indices) : null;
	}
	
	
	
	private List toFileList(int[] indices)
	{
		List list = new ArrayList();
		for(int i=0;i<indices.length;i++)
		list.add(file(indices[i]));
		return list;
	}
	
	private List toItemList(int[] indices)
	{
		List list = new ArrayList();
		for(int i=0;i<indices.length;i++)
		list.add(item(indices[i]));
		return list;
	}
	
	
	
	
	
	
	
	private void selectKey(String key)
	{
		if(key==null || keys==null || !keys.contains(key)) return;
		int index = keys.indexOf(key);
		list.setSelectedIndex(index);
	}
	
	
	private void selectKeys(List keys0)
	{
		if(keys0==null || keys==null) return;
		List keys0_ = new ArrayList(keys0);
		keys0_.retainAll(keys);
		if(keys0_.isEmpty()) return;
		
		int[] indices = new int[keys0_.size()];
		for(int i=0;i<indices.length;i++)
		indices[i] = keys.indexOf(keys0_.get(i));
		
		list.setSelectedIndices(indices);
	}
	
	
	
	
	private Object buildTooltip(Object indexObj)
	{
		if(indexObj==null) return null;
		int index = ((Integer) indexObj).intValue();
		if(index<0 || index>=files.size()) return null;
		File file = file(index);
		return file!=null ? file.getAbsolutePath() : "null";
	}
	
	
	
	
	
	
	private Integer selectedIndex() throws Exception
	{return (Integer) listHolder.r("selectedIndex");}
	
	private int[] selectedIndices() throws Exception
	{return (int[]) listHolder.r("selectedIndices");}
	
	
	
	
	private String key(int index)
	{return (String) keys.get(index);}
	
	private File file(int index)
	{return (File) files.get(index);}
	
	private String display(int index)
	{return ((String) displays.get(index)).substring(1);}
	
	private String item(int index)
	{return display(index)+"\t"+file(index);}
	
	
	
	
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private String get1(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) throw new Exception("Key not found inside tool: "+key);
		return (String) map.get(key);
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
}