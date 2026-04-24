package a.entity.gus06.sys.entitysrcviewer1.gui.listing.group;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;


public class EntityImpl extends S1 implements Entity, P, I, G, ActionListener, ListSelectionListener {

	public String creationDate() {return "20191007";}

	public static final String ICONID = "entity";

	private Service fieldHolder;
	private Service buildJList;
	private Service linkerListField;
	private Service buildActionCopy;

	private JPanel panel;
	private JComponent field;
	private JList list;
	private JLabel label;
	
	private List listing;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildJList = Outside.service(this,"gus06.swing.list.build.fromicon");
		linkerListField = Outside.service(this,"gus.x.swing.list.textfield.linker");
		buildActionCopy = Outside.service(this,"gus06.swing.list.build.action.copy");
		
		label = new JLabel(" ");
		field = (JComponent) fieldHolder.i();
		list = (JList) buildJList.t(ICONID);
		
		Action copyAction = (Action) buildActionCopy.t(list);
		JPanel p_bottom = new JPanel(new BorderLayout());
		p_bottom.add(new JButton(copyAction),BorderLayout.EAST);
		p_bottom.add(label,BorderLayout.CENTER);

		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(p_bottom,BorderLayout.SOUTH);
		

		field.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0),new AbstractAction() {
			public void actionPerformed(ActionEvent e) {reload();}
		});

		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				if(code==KeyEvent.VK_F5) reload();
			}
		});

		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
	}
	
	
	public Object g() throws Exception
	{return list.getSelectedValue();}
	
	
	public Object i() throws Exception
	{return panel;}


	
	public void p(Object obj) throws Exception
	{
		listing = (List) obj;
		refresh();
	}


	public void actionPerformed(ActionEvent e)
	{refresh();}


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}

	

	private void refresh()
	{
		try
		{
			String input = (String) fieldHolder.g();
			
			Map map = new HashMap();
			for(int i=0;i<listing.size();i++)
			{
				String entityName = (String) listing.get(i);
				String group = findGroup(entityName,input);
				if(!group.equals("")) increase(map,group);
			}
			
			list.setListData(toVector(map));
			label.setText(buildNumberDisplay(map));
			
			field.requestFocusInWindow();
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private String findGroup(String entityName, String input)
	{
		int len = input.length();
		String[] nn = entityName.split("\\.");
		String s = "";
		
		for(int i=0;i<nn.length;i++)
		{
			if(i>0) s += ".";	
			s += nn[i];
			if(s.length()>len) return s.startsWith(input) ? s : "";
		}
		return s.equals(input) ? s : "";
	}
	
	
	
	private void increase(Map map, String key)
	{
		if(!map.containsKey(key)) {map.put(key,1);return;}
		int nb = (int) map.get(key);
		map.put(key,nb+1);
	}
	
	
	private Vector toVector(Map map)
	{
		if(map==null) return new Vector();
		Vector vec = new Vector();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String group = (String) it.next();
			int nb = (int) map.get(group);
			vec.add(group+" ("+nb+")");
		}
		Collections.sort(vec);
		return vec;
	}
	
	
	private String buildNumberDisplay(Map map)
	{
		if(map==null) return "?";
		return " number: "+map.size();
	}
	
	

	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	private void reload()
	{send(this,"reload()");}
}
