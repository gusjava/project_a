package a.entity.gus06.data.viewer.filearray.files;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;

public class EntityImpl implements Entity, I, P, ListSelectionListener {

	public String creationDate() {return "20221023";}


	private Service shiftPanel;
	private Service viewerFile1;
	private Service viewerFile2;
	private Service splitCust;
	private Service listRenderer;
	private Service find;
	private Service handleCtrlC;
	private Service clearCPC;

	private JSplitPane split;
	private JPanel panel;
	private JList list;
	private JLabel label;
    
	private File[] data;


	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		viewerFile1 = Outside.service(this,"*gus06.data.viewer.filearray.file1");
		viewerFile2 = Outside.service(this,"*gus06.data.viewer.filearray.file2");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		listRenderer = Outside.service(this,"gus06.swing.list.cust.renderer.file");
		find = Outside.service(this,"gus06.find.filearray");
		handleCtrlC = Outside.service(this,"gus06.data.viewer.filearray.files.ctrl_c");
		clearCPC = Outside.service(this,"gus.x.swing.comp.action.clear.copypastecut");
		
		list = new JList();
		listRenderer.p(list);
		clearCPC.p(list);
		handleCtrlC.p(list);
		
		label = new JLabel(" ");
        
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		split.setLeftComponent(panel);
		split.setRightComponent((JComponent) shiftPanel.i());
		
		splitCust.p(split);
		list.addListSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		data = (File[]) obj;
		if(data==null) resetGui();
		else updateGui();
	}
	
	private void updateGui() throws Exception
	{
		if(data.length<=2) throw new Exception("Invalid file number: "+data.length);
		
		Vector vec = new Vector();
		for(File f : data) vec.add(f);
		
		list.setListData(vec);
		label.setText(" "+data.length);
		resetDetails();
	}
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		resetDetails();
	}
		
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
    
    

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty())
			{resetDetails();return;}
			
			File[] selection = (File[]) find.t(list.getSelectedValues());
			shiftPanel.p(initDetails(selection));
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	private void resetDetails() throws Exception
	{
		viewerFile1.p(null);
		viewerFile2.p(null);
		shiftPanel.p(null);
	}
	
	private Object initDetails(Object[] data) throws Exception
	{
		if(data==null) return null;
		if(data.length==0) return null;
		
		if(data.length==1)
		{
			viewerFile1.p(data);
			return viewerFile1;
		}
		viewerFile2.p(data);
		return viewerFile2;
	}
}