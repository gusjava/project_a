package a.entity.gus06.file.jar.gusapp.entity.comparator.gui1;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.io.File;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JList;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20200208";}
	
	public static final String ICONID = "entity";


	private Service detailPanel;
	private Service buildJList;
	private Service fileToMap;
	private Service map2ToMap4;
	private Service onKey;
	private Service clipboard;
	private Service focusOnClick;


	private JSplitPane split;
	
	private JLabel label_new;
	private JLabel label_removed;
	private JLabel label_modified;
	
	private JList list_new;
	private JList list_removed;
	private JList list_modified;
	
	private File jar1;
	private File jar2;
	
	private Map map1;
	private Map map2;
	
	
	public EntityImpl() throws Exception
	{
		detailPanel = Outside.service(this,"*gus06.file.jar.gusapp.entity.comparator.gui1.detailpanel");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		fileToMap = Outside.service(this,"gus06.file.jar.gusapp.entity.srcmap");
		map2ToMap4 = Outside.service(this,"gus06.map.map2tomap4");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		clipboard = Outside.service(this,"gus06.swing.list.perform.copy");
		focusOnClick = Outside.service(this,"gus06.swing.comp.cust.focusonclicked");
		
		list_new = (JList) buildJList.t(ICONID);
		list_removed = (JList) buildJList.t(ICONID);
		list_modified = (JList) buildJList.t(ICONID);
		
		label_new = initLabel("New entities",Color.GREEN);
		label_removed = initLabel("Removed entities",Color.RED);
		label_modified = initLabel("Modified entities",Color.ORANGE);
		
		onKey.p(new Object[]{label_new,"ctrl c",(E) this::copyNew});
		onKey.p(new Object[]{label_removed,"ctrl c",(E) this::copyRemoved});
		onKey.p(new Object[]{label_modified,"ctrl c",(E) this::copyModified});
		
		JPanel panel_new = initPanel(list_new,label_new);
		JPanel panel_removed = initPanel(list_removed,label_removed);
		JPanel panel_modified = initPanel(list_modified,label_modified);
		
		JPanel panelLeft = new JPanel(new GridLayout(3,1));
		panelLeft.add(panel_new);
		panelLeft.add(panel_removed);
		panelLeft.add(panel_modified);
		
		split = new JSplitPane();
		split.setLeftComponent(panelLeft);
		split.setRightComponent((JComponent) detailPanel.i());
		
		list_new.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{selectionChanged(list_new);}
		});
		list_removed.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{selectionChanged(list_removed);}
		});
		list_modified.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{selectionChanged(list_modified);}
		});
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	private JLabel initLabel(String title, Color color) throws Exception
	{
		JLabel label = new JLabel(title);
		label.setOpaque(true);
		label.setForeground(color);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
		focusOnClick.p(label);
		return label;
	}
	
	
	private JPanel initPanel(JList list, JLabel label)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		jar1 = o[0];
		jar2 = o[1];
		
		map1 = (Map) fileToMap.t(jar1);
		map2 = (Map) fileToMap.t(jar2);
		
		if(map1==null)
		{
			addToList(list_removed,null);
			addToList(list_new,map2);
			addToList(list_modified,null);
			
			label_removed.setText("Removed entities (0)");
			label_new.setText("New entities ("+map2.size()+")");
			label_modified.setText("Modified entities (0)");
		}
		else
		{
			Map[] m = (Map[]) map2ToMap4.t(new Map[]{map1,map2});
			
			addToList(list_removed,m[0]);
			addToList(list_new,m[3]);
			addToList(list_modified,m[1]);
			
			label_removed.setText("Removed entities ("+m[0].size()+")");
			label_new.setText("New entities ("+m[3].size()+")");
			label_modified.setText("Modified entities ("+m[1].size()+")");
		}
	}
	
	
	private void reset()
	{
		try
		{
			map1 = null;
			map2 = null;
			
			addToList(list_removed,null);
			addToList(list_new,null);
			addToList(list_modified,null);
			
			label_removed.setText("Removed entities (0)");
			label_new.setText("New entities (0)");
			label_modified.setText("Modified entities (0)");
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	
	private void addToList(JList list, Map map)
	{
		Vector keys = map!=null ? new Vector(map.keySet()) : new Vector();
		Collections.sort(keys);
		list.setListData(keys);
	}


	private void selectionChanged(JList list)
	{
		try
		{
			String key = (String) list.getSelectedValue();
			String src1 = get(map1,key);
			String src2 = get(map2,key);
			detailPanel.p(new String[]{src1,src2});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private String get(Map map, String key)
	{
		if(map==null || !map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	
	private void copyNew()
	{
		try{clipboard.p(list_new);}
		catch(Exception e)
		{Outside.err(this,"copyNew()",e);}
	}
	
	
	private void copyRemoved()
	{
		try{clipboard.p(list_removed);}
		catch(Exception e)
		{Outside.err(this,"copyRemoved()",e);}
	}
	
	
	private void copyModified()
	{
		try{clipboard.p(list_modified);}
		catch(Exception e)
		{Outside.err(this,"copyModified()",e);}
	}
}
