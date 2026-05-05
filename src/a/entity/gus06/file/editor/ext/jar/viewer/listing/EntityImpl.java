package a.entity.gus06.file.editor.ext.jar.viewer.listing;

import a.framework.*;

import java.io.File;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, P, ActionListener, ListSelectionListener {

	public String creationDate() {return "20170223";}

	
	private Service fieldHolder;
	private Service findEntries;
	private Service listRenderer;
	private Service entryViewer;
	private Service listFilter;
	private Service linkerListField;
	private Service splitCust;
	
	private JComponent field;
	private JList list;
	private JLabel label;
	private JSplitPane split;
	
	private File file;
	private List entries;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		findEntries = Outside.service(this,"gus.x.file.jar.findentries");
		listRenderer = Outside.service(this,"gus06.file.editor.ext.zip.listrenderer");
		entryViewer = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer");
		listFilter = Outside.service(this,"gus06.list.filter.rule.one");
		linkerListField = Outside.service(this,"gus.x.swing.list.textfield.linker");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		list = new JList();
		listRenderer.p(list);
    		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
				
		JPanel p_left = new JPanel(new BorderLayout());
		p_left.add(field,BorderLayout.NORTH);
		p_left.add(new JScrollPane(list),BorderLayout.CENTER);
		p_left.add(label,BorderLayout.SOUTH);
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(p_left);
		split.setRightComponent((JComponent) entryViewer.i());
		
		list.addListSelectionListener(this);
		fieldHolder.addActionListener(this);
		linkerListField.p(new Object[]{list,field});
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh1();}
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		initEntries();
		refresh();
	}
	
	
	private void initEntries() throws Exception
	{
		entries = file!=null ? (List) findEntries.t(file) : null;
	}
	
	
	private void refresh() throws Exception
	{
		if(file==null) resetGui();
		else updateGui();
	}
	
	
	private void refresh1()
	{
		try{refresh();}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void updateGui() throws Exception
	{
		Vector v = buildKeys();
		list.setListData(v);
		label.setText("number: "+v.size());
		entryViewer.p(null);
	}
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		entryViewer.p(null);
	}
	
	
	private Vector buildKeys() throws Exception
	{
		String rule = (String) fieldHolder.g();
		List entries_ = (List) listFilter.t(new Object[]{entries,rule});
		return new Vector(entries_);
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			String entry = (String) list.getSelectedValue();
			if(file==null || entry==null) entryViewer.p(null);
			else entryViewer.p(new Object[]{file,entry});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}