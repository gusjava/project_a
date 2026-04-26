package a.entity.gus06.sys.xhtml1.ids.gui.detail;

import a.framework.*;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.util.Vector;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, I, P, ListSelectionListener {

	public String creationDate() {return "20220909";}

	
	private Service shiftPanel;
	private Service viewer;
	private Service renderer;
	private Service splitCust;
	
	private JSplitPane split;
	private JPanel panelList;
	private JList list;
	private JLabel label;
	private JComponent comp;
    
	private List infos;
	
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		viewer = Outside.service(this,"*gus06.sys.xhtml1.ids.gui.detail.viewer");
		renderer = Outside.service(this,"gus06.sys.xhtml1.ids.gui.detail.renderer");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		comp = (JComponent) viewer.i();
	
		list = new JList();
		list.addListSelectionListener(this);
		renderer.p(list);
        
		label = new JLabel(" ");
        
		panelList = new JPanel(new BorderLayout());
		panelList.add(new JScrollPane(list),BorderLayout.CENTER);
		panelList.add(label,BorderLayout.SOUTH);
        
		split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitCust.p(split);
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		infos = (List) obj;
		if(infos==null) resetGui();
		else updateGui();
	}
	
	
	private void updateGui() throws Exception
	{
		if(infos.size()==1)
		{
			viewer.p(infos.get(0));
			shiftPanel.p(viewer);
		}
		else
		{
			Vector vec = new Vector();
			for(int i=0;i<infos.size();i++)
			vec.add(infos.get(0));
			
			list.setListData(vec);
			label.setText(" "+infos.size());
			
			split.setLeftComponent(panelList);
			split.setRightComponent(comp);
			split.setDividerLocation(100);
			
			shiftPanel.p(split);
			
			if(infos.isEmpty()) viewer.p(null);
			else list.setSelectedIndex(0);
		}
	}
	
	
	private void resetGui() throws Exception
	{
		list.setListData(new Vector());
		label.setText(" ");
		viewer.p(null);
		shiftPanel.p(null);
	}
		
	
	
	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
    
    
    

	private void selectionChanged()
	{
		try
		{
			if(list.isSelectionEmpty()) {viewer.p(null);return;}
			Object value = list.getSelectedValue();
			viewer.p(value);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}