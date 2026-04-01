package a.entity.gus06.file.editor.ext.cbz;

import a.framework.*;

import java.io.File;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.util.Vector;
import java.util.List;

public class EntityImpl implements Entity, I, P, G, ListSelectionListener {

	public String creationDate() {return "20200305";}

	
	private Service findEntries;
	private Service listRenderer;
	private Service entryViewer;
	
	private JPanel panel;
	private JLabel label;
	private JList list;
	
	private File file;
	

	public EntityImpl() throws Exception
	{
		findEntries = Outside.service(this,"gus06.file.zip.findentries");
		listRenderer = Outside.service(this,"gus06.file.editor.ext.cbz.listrenderer");
		entryViewer = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer");
		
		list = new JList();
		listRenderer.p(list);
    		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		label = new JLabel(" ");
				
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list),BorderLayout.WEST);
		panel.add((JComponent) entryViewer.i(),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		list.addListSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	private void updateGui() throws Exception
	{
		Vector v = buildKeys();
		list.setListData(v);
		label.setText("number: "+v.size());
		if(v.size()>0) select((String) v.get(0));
	}
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		entryViewer.p(null);
	}
	
	
	
	private Vector buildKeys() throws Exception
	{
		List entries = (List) findEntries.t(file);
		Vector keys = new Vector();
		keys.addAll(entries);
		return keys;
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			String entry = (String) list.getSelectedValue();
			select(entry);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	private void select(String entry) throws Exception
	{
		if(file==null || entry==null) entryViewer.p(null);
		else entryViewer.p(new Object[]{file,entry});
	}
}