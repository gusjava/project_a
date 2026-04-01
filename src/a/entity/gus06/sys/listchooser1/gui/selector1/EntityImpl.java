package a.entity.gus06.sys.listchooser1.gui.selector1;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EntityImpl extends S1 implements Entity, I, P, G, V, R, ActionListener, ListSelectionListener {

	public String creationDate() {return "20160504";}

	public static final String ICONID = "ELEMENT";
	public static final String MODE_ALL = "all";

	private Service fieldHolder;
	private Service buildRenderer;
	private Service linkerListField;
	private Service listFilter;
	private Service findList;
	private Service clearCPC;
	
	
	private JPanel panel;
	private JTextField field;
	private JList list;
	private JLabel label;
	private T filter;
	private String mode = MODE_ALL;
	
	private List values;
	private List results;
	private Map mapping;
	private Object renderer;


	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		buildRenderer = Outside.service(this,"gus06.swing.list.build.renderer1");
		linkerListField = Outside.service(this,"gus06.swing.list.textfield.linker");
		listFilter = Outside.service(this,"gus06.list.filter.rule");
		findList = Outside.service(this,"gus06.find.list");
		clearCPC = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
		
		Icon icon = (Icon) Outside.resource(this,"icon#ELEMENT");
		
		label = new JLabel(" ");
		field = (JTextField) fieldHolder.i();
		
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		renderer = buildRenderer.t(list);
		((V) renderer).v("icon", icon);

		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		fieldHolder.addActionListener(this);
		list.addListSelectionListener(this);
		linkerListField.p(new Object[]{list,field});
		clearCPC.p(list);
		
		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
				if(e.isControlDown())
				{
					if(code==KeyEvent.VK_C)			typed_ctrl_c();
					else if(code==KeyEvent.VK_V)		typed_ctrl_v();
					else if(code==KeyEvent.VK_X)		typed_ctrl_x();
				}
				else
				{
					if(code==KeyEvent.VK_F1)		typed_F1();
					else if(code==KeyEvent.VK_F2)		typed_F2();
					else if(code==KeyEvent.VK_F3)		typed_F3();
					else if(code==KeyEvent.VK_F4)		typed_F4();
					else if(code==KeyEvent.VK_F5)		typed_F5();
					else if(code==KeyEvent.VK_F6)		typed_F6();
					else if(code==KeyEvent.VK_F7)		typed_F7();
					else if(code==KeyEvent.VK_F8)		typed_F8();
					else if(code==KeyEvent.VK_F9)		typed_F9();
					else if(code==KeyEvent.VK_F10)		typed_F10();
					else if(code==KeyEvent.VK_F11)		typed_F11();
					else if(code==KeyEvent.VK_F12)		typed_F12();
					
					else if(code==KeyEvent.VK_DELETE)	typed_delete();
					else if(code==KeyEvent.VK_SPACE)	typed_space();
					else if(code==KeyEvent.VK_ENTER)	typed_enter();
				}
			}
		});
		
		field.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
				if(code==KeyEvent.VK_ENTER) 		fieldEntered();
				
				else if(code==KeyEvent.VK_F1)		typed_F1();
				else if(code==KeyEvent.VK_F2)		typed_F2();
				else if(code==KeyEvent.VK_F3)		typed_F3();
				else if(code==KeyEvent.VK_F4)		typed_F4();
				else if(code==KeyEvent.VK_F5)		typed_F5();
				else if(code==KeyEvent.VK_F6)		typed_F6();
				else if(code==KeyEvent.VK_F7)		typed_F7();
				else if(code==KeyEvent.VK_F8)		typed_F8();
				else if(code==KeyEvent.VK_F9)		typed_F9();
				else if(code==KeyEvent.VK_F10)		typed_F10();
				else if(code==KeyEvent.VK_F11)		typed_F11();
				else if(code==KeyEvent.VK_F12)		typed_F12();
			}
		});
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		Object selected = list.getSelectedValue();
		if(mapping==null) return selected;
		
		if(!mapping.containsKey(selected)) return null;
		return mapping.get(selected);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map)
		{
			mapping = (Map) obj;
			values = new ArrayList(mapping.keySet());
			Collections.sort(values);
		}
		else
		{
			mapping = null;
			values = (List) findList.t(obj);
		}
		refresh();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select(obj);return;}
		if(key.equals("select1")) {select1((String) obj);return;}
		if(key.equals("search")) {search(obj);return;}
		if(key.equals("mode")) {setMode((String) obj);return;}
		if(key.equals("filter")) {setFilter((T) obj);return;}
		if(key.equals("icon")) {setIcon((Icon) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("query")) return fieldHolder.g();
		if(key.equals("selected")) return getSelectedIndex();
		if(key.equals("selectedIndex")) return list.getSelectedIndex();
		if(key.equals("list")) return list;
		if(key.equals("field")) return field;
		if(key.equals("renderer")) return renderer;
		
		if(key.equals("keys")) return new String[]{
			"query", "selected", "selectedIndex", 
			"list", "field", "renderer"};
		throw new Exception("Unknown key: "+key);
	}
	
	

	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("changed()")) changed();
		else if(s.equals("cleared()")) cleared();
	}
	
	
	
	private void changed()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {refresh();}
		});
	}
	
	
	
	
	
	private void fieldEntered()
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				if(!hasResults()) return;
				selectFirstRow();
				typed_enter();
			}
		});
	}
	
	
	
	private void selectionChanged()
	{send(this,"selectionChanged()");}
	
	
	
	private void cleared()
	{send(this,"cleared()");}
	
	
	
	private void typed_F1()
	{send(this,"typed_F1()");}
	
	private void typed_F2()
	{send(this,"typed_F2()");}
	
	private void typed_F3()
	{send(this,"typed_F3()");}
	
	private void typed_F4()
	{send(this,"typed_F4()");}
	
	private void typed_F5()
	{send(this,"typed_F5()");}
	
	private void typed_F6()
	{send(this,"typed_F6()");}
	
	private void typed_F7()
	{send(this,"typed_F7()");}
	
	private void typed_F8()
	{send(this,"typed_F8()");}
	
	private void typed_F9()
	{send(this,"typed_F9()");}
	
	private void typed_F10()
	{send(this,"typed_F10()");}
	
	private void typed_F11()
	{send(this,"typed_F11()");}
	
	private void typed_F12()
	{send(this,"typed_F12()");}
	
	private void typed_space()
	{send(this,"typed_space()");}

	private void typed_delete()
	{send(this,"typed_delete()");}

	private void typed_enter()
	{send(this,"typed_enter()");}
	
	
	

	private void typed_ctrl_c()
	{send(this,"typed_ctrl_c()");}

	private void typed_ctrl_v()
	{send(this,"typed_ctrl_v()");}

	private void typed_ctrl_x()
	{send(this,"typed_ctrl_x()");}
	
	
	
	
	
	private void refresh()
	{
		try
		{
			results = buildResults();
			list.setListData(toVector(results));
			
			if(hasResults()) selectFirstRow();
			label.setText(" number: "+resultNumber());
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	private List buildResults() throws Exception
	{
		if(filter!=null)
		{
			Map data = new HashMap();
			data.put("values",values);
			data.put("query",query());
			data.put("mapping",mapping);
			return (List) filter.t(data);
		}
		Object[] data = new Object[]{values,query(),mode};
		return (List) listFilter.t(data);
	}
	
	
	private Integer getSelectedIndex()
	{
		if(list.isSelectionEmpty()) return null;
		return Integer.valueOf(list.getSelectedIndex());
	}
	
	
	private void select(Object obj)
	{
		if(!hasResults()) return;
		list.setSelectedValue(obj,true);
	}
	
	private void selectFirstRow()
	{
		if(!hasResults()) return;
		list.setSelectedIndex(0);
	}
	
	private void selectLastRow()
	{
		if(!hasResults()) return;
		list.setSelectedIndex(resultNumber()-1);
	}
	
	private void selectNextRow()
	{
		if(!hasResults()) return;
		Integer index = getSelectedIndex();
		if(index==null) return;
		int last = resultNumber()-1;
		if(index.intValue()==last) return;
		
		list.setSelectedIndex(index.intValue()+1);
	}
	
	private void selectPreviousRow()
	{
		if(!hasResults()) return;
		Integer index = getSelectedIndex();
		if(index==null) return;
		if(index.intValue()==0) return;
		
		list.setSelectedIndex(index.intValue()-1);
	}
	
	private void setMode(String mode)
	{
		this.mode = mode;
		refresh();
	}
	
	private void setFilter(T filter)
	{
		this.filter = filter;
		refresh();
	}
	
	private void setIcon(Icon icon) throws Exception
	{
		((V)renderer).v("icon",icon);
	}
	
	
	private void search(Object obj) throws Exception
	{fieldHolder.p(obj);}
	
	
	
	private Vector toVector(List keys)
	{return keys!=null ? new Vector(keys) : new Vector();}
	
	private String query() throws Exception
	{return (String) fieldHolder.g();}
	
	private boolean hasResults()
	{return results!=null && results.size()>0;}
	
	private int resultNumber()
	{return results!=null ? results.size() : 0;}
	
	
	
	
	
	private void select1(String rule) throws Exception
	{
		if(rule.equals("first")) {selectFirstRow();return;}
		if(rule.equals("last")) {selectLastRow();return;}
		if(rule.equals("next")) {selectNextRow();return;}
		if(rule.equals("previous")) {selectPreviousRow();return;}
		
		throw new Exception("Unsupported rule: "+rule);
	}
	
}