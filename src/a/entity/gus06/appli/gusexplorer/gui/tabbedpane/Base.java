package a.entity.gus06.appli.gusexplorer.gui.tabbedpane;

import java.io.File;
import java.util.*;
import a.framework.*;

public class Base {

	private Map<File,P> labels;
	private Map<File,P> holders;
	
	public Base()
	{
		labels = new HashMap<>();
		holders = new HashMap<>();
	}
	
	protected P labelAt(File file) throws Exception
	{
		if(!labels.containsKey(file))
			throw new Exception("Label not found for file : "+file);
		return labels.get(file);
	}
	
	protected P holderAt(File file) throws Exception
	{
		if(!holders.containsKey(file))
			throw new Exception("Holder not found for file : "+file);
		return holders.get(file);
	}
	
	protected boolean hasFile(File file)
	{
		return holders.containsKey(file);
	}
	
	protected void clear() throws Exception
	{
		clear(labels);
		clear(holders);
	}
	
	private void clear(Map<File,P> m) throws Exception
	{
		Iterator<File> it = m.keySet().iterator();
		while(it.hasNext()) m.get(it.next()).p(null);
		m.clear();
	}
	
	protected void exchange(File file1, File file2) throws Exception
	{
		exchange(labels,file1,file2);
		exchange(holders,file1,file2);
	}
	
	private void exchange(Map<File,P> m, File file1, File file2) throws Exception
	{
		P p1 = m.get(file1);
		P p2 = m.get(file2);
		
		p1.p(file2);
		p2.p(file1);
		
		m.put(file2,p1);
		m.put(file1,p2);
	}

	protected void modify(File file1, File file2) throws Exception
	{
		modify(labels,file1,file2);
		modify(holders,file1,file2);
	}
	
	private void modify(Map<File,P> m, File file1, File file2) throws Exception
	{
		P p1 = m.get(file1);
		p1.p(file2);
		
		m.put(file2,p1);
		m.remove(file1);
	}
	
	protected void remove(File file) throws Exception
	{
		remove(labels,file);
		remove(holders,file);
	}
	
	private void remove(Map<File,P> m, File file) throws Exception
	{
		if(!m.containsKey(file)) return;
		
		m.get(file).p(null);
		m.remove(file);
	}
	
	protected void keep(File file) throws Exception
	{
		keep(labels,file);
		keep(holders,file);
	}
	
	private void keep(Map<File,P> m, File file) throws Exception
	{
		Iterator<File> it = m.keySet().iterator();
		while(it.hasNext())
		{
			File f = it.next();
			if(!f.equals(file))
			{
				m.get(f).p(null);
				it.remove();
			}
		}
	}
	
	protected void add(File file, P label, P holder)
	{
		labels.put(file,label);
		holders.put(file,holder);
	}
	
	protected void reload(File file) throws Exception
	{
		labelAt(file).p(file);
		holderAt(file).p(file);
	}
	
	protected int int_(Object obj)
	{return Integer.parseInt((String) obj);}
	
	protected int toIndex(Object obj) throws Exception
	{
		int index = int_(obj);
		if(index<0) throw new Exception("Invalid index value: "+index);
		return index;
	}
}
