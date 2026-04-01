package a.entity.gus06.dir.viewer.list.listgui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EntityImpl extends S1 implements Entity, I, P, G, R, ListSelectionListener {

	public String creationDate() {return "20170924";}


	private Service rendering;
	
	private JList list;
	private JLabel label;
	private JPanel panel;

	private File[] files;


	
	public EntityImpl() throws Exception
	{
		rendering = Outside.service(this,"gus06.swing.list.cust.renderer.file");
		
		list = new JList();
		list.addListSelectionListener(this);
		
		rendering.v("color",new Color(102,204,255));
		rendering.p(list);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}



	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			files = null;
			list.setListData(new Object[0]);
			label.setText(" ");
			return;
		}
		files = toFiles(obj);
		list.setListData(files);
		label.setText(" Number: "+files.length);
	}

	
	public Object g() throws Exception
	{return list.isSelectionEmpty()?null:list.getSelectedValue();}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("list")) return list;
		if(key.equals("keys")) return new String[]{"list"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private File[] toFiles(Object obj) throws Exception
	{
		if(obj instanceof File[]) return (File[]) obj;
		if(obj instanceof File) return ((File)obj).listFiles();
		if(obj instanceof List) return listToFiles((List)obj);
		if(obj instanceof Set) return setToFiles((Set)obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	
	private File[] listToFiles(List l)
	{
		File[] f = new File[l.size()];
		for(int i=0;i<f.length;i++) f[i] = (File) l.get(i);
		return f;
	}
	
	
	private File[] setToFiles(Set s)
	{
		ArrayList l = new ArrayList(s);
		Collections.sort(l);
		return listToFiles(l);
	}
}
