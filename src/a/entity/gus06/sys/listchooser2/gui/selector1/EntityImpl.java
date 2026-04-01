package a.entity.gus06.sys.listchooser2.gui.selector1;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class EntityImpl extends S1 implements Entity, I, P, G, V, R, ActionListener, ListSelectionListener {

	public String creationDate() {return "20220613";}
	

	private Service fieldHolder;
	private Service listFilter;
	private Service findList;
	private Service findDim;
	private Service findBorder;
	
	
	private JPanel panel;
	private JTextField field;
	private JScrollPane scroll;
	private JPanel1 panel1;
	private JLabel label;
	
	private List input;
	private List results;
	private Map mapping;
	
	private Map keyToComp;
	private T builder;
	private T builder2;
	
	private Dimension cellSize = new Dimension(200,50);
	private Border borderSelected = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	private Border borderUnselected = null;
	private int scrollableUnitIncrement = 10;
	private int scrollableBlockIncrement = 10;



	public EntityImpl() throws Exception
	{
		fieldHolder = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		listFilter = Outside.service(this,"gus06.list.filter.rule.all");
		findList = Outside.service(this,"gus06.find.list");
		findDim = Outside.service(this,"gus06.find.dimension");
		findBorder = Outside.service(this,"gus06.find.border");
		
		keyToComp = new HashMap();
		label = new JLabel(" ");
		field = (JTextField) fieldHolder.i();
		panel1 = new JPanel1();
		
		scroll = new JScrollPane(panel1, 
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field, BorderLayout.NORTH);
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
		
		fieldHolder.addActionListener(this);
		
		panel1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();
				
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
	{return panel1.getSelected();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof Map)
		{
			mapping = (Map) obj;
			input = new ArrayList(mapping.keySet());
			Collections.sort(input);
		}
		else
		{
			mapping = null;
			input = (List) findList.t(obj);
		}
		refresh();
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select(obj);return;}
		if(key.equals("search")) {setSearch(obj);return;}
		if(key.equals("builder")) {setBuilder(obj);return;}
		if(key.equals("builder2")) {setBuilder2(obj);return;}
		if(key.equals("colNb")) {setColNb(toInt(obj));return;}
		if(key.equals("cellSize")) {setCellSize(obj);return;}
		if(key.equals("borderSelected")) {setBorderSelected(obj);return;}
		if(key.equals("borderUnselected")) {setBorderUnselected(obj);return;}
		if(key.equals("scrollableUnitIncrement")) {scrollableUnitIncrement = toInt(obj);return;}
		if(key.equals("scrollableBlockIncrement")) {scrollableBlockIncrement = toInt(obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("query")) return fieldHolder.g();
		if(key.equals("panel")) return panel1;
		
		if(key.equals("keys")) return new String[]{"query", "panel"};
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
	
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private void setColNb(int nb)
	{panel1.setLayout(new GridLayout(0,nb));}
	
	
	private void setCellSize(Object obj) throws Exception
	{cellSize = (Dimension) findDim.t(obj);}
	
	
	private void setBorderSelected(Object obj) throws Exception
	{borderSelected = (Border) findBorder.t(obj);}
	
	
	private void setBorderUnselected(Object obj) throws Exception
	{borderUnselected = (Border) findBorder.t(obj);}
	
	
	
	private Map toMap(String key, Object data)
	{
		Map m = new HashMap();
		m.put("key",key);
		m.put("data",data);
		return m;
	}
	
	
	private void refresh()
	{
		try
		{
			results = (List) listFilter.t(new Object[]{input,query()});
			panel1.setListData(results);
			
			if(hasResults()) selectFirstRow();
			label.setText(" number: "+resultNumber());
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void select(Object obj)
	{
		panel1.setSelected((String) obj);
		
	}
	
	private void selectFirstRow()
	{
		panel1.setSelectedIndex(0);
	}
	
	
	private void setSearch(Object obj) throws Exception
	{fieldHolder.p(obj);}
	
	
	private void setBuilder(Object obj) throws Exception
	{builder = (T) obj;}
	
	private void setBuilder2(Object obj) throws Exception
	{builder2 = (T) obj;}
	
	
	private String query() throws Exception
	{return (String) fieldHolder.g();}
	
	private boolean hasResults()
	{return results!=null && results.size()>0;}
	
	private int resultNumber()
	{return results!=null ? results.size() : 0;}
	
	
	
	private JComponent keyToComp(String key) throws Exception
	{
		if(!keyToComp.containsKey(key)) 
			keyToComp.put(key,buildComp(key));
		return (JComponent) keyToComp.get(key);
	}
	
	private JComponent buildComp(String key) throws Exception
	{
		Object data = mapping.get(key);
		if(builder2!=null) return (JComponent) builder2.t(toMap(key,data));
		if(builder!=null) return (JComponent) builder.t(data);
		return (JComponent) data;
	}
	
	
	private class JPanel1 extends JPanel implements Scrollable
	{
		private String selected;
		
		private List keys;
		private Map keyComp;
		private Map keyClick;
		
		public JPanel1()
		{
			super(new GridLayout(0,1));
			
			keys = new ArrayList();
			keyComp = new HashMap();
			keyClick = new HashMap();
		}
		
		
		
		public String getSelected()
		{return selected;}
		
		public void setSelected(String selected)
		{this.selected = selected;}
		
		public void setSelectedIndex(int index)
		{
			
		}
		
		public void setListData(List list) throws Exception
		{
			for(int i=0;i<keys.size();i++)
			{
				String key = (String) keys.get(i);
				JComponent comp = (JComponent) keyComp.get(key);
				Clicker clicker = (Clicker) keyClick.get(key);
				comp.removeMouseListener(clicker);
			}
			keys.clear();
			keyComp.clear();
			keyClick.clear();
			removeAll();
			
			for(int i=0;i<list.size();i++)
			{
				String key = (String) list.get(i);
				JComponent comp = keyToComp(key);
				Clicker clicker = new Clicker(key);
				
				keys.add(key);
				keyComp.put(key,comp);
				keyClick.put(key,clicker);
				
				comp.setPreferredSize(cellSize);
				comp.setBorder(borderUnselected);
				comp.addMouseListener(clicker);
				
				add(comp);
			}
		}
		
		
		private void changeSelected(String newSelected)
		{
			if(Objects.equals(newSelected, selected)) return;
			
			JComponent comp0 = compFor(selected);
			JComponent comp1 = compFor(newSelected);
			
			if(comp0!=null) comp0.setBorder(borderUnselected);
			if(comp1!=null) comp1.setBorder(borderSelected);
			
			selected = newSelected;
			selectionChanged();
		}
		
		
		private JComponent compFor(String key)
		{
			if(key==null) return null;
			return (JComponent) keyComp.get(key);
		}
		
		public Dimension getPreferredScrollableViewportSize()
		{
			return getPreferredSize();
		}
		
		public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction)
		{
			return scrollableBlockIncrement;
		}
		
		public boolean getScrollableTracksViewportHeight()
		{
			return false;
		}
		
		public boolean getScrollableTracksViewportWidth()
		{
			return false;
		}
		
		public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction)
		{
			return scrollableUnitIncrement;
		}
		
		
		private class Clicker implements MouseListener
		{
			private String key;
			public Clicker(String key) {this.key = key;}
			
			public void mouseClicked(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mousePressed(MouseEvent e){changeSelected(key);}
		}
	}
}