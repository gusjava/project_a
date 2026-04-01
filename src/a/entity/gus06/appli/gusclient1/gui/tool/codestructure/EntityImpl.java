package a.entity.gus06.appli.gusclient1.gui.tool.codestructure;

import a.framework.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.*;


public class EntityImpl implements Entity, I, P, ActionListener, ListSelectionListener {

	public String creationDate() {return "20140823";}


	private Service extractStructure;
	
	private JPanel panel;
	private JButton button;
	private JList list;

	private String entityName;
	private File entityFile;
	private JTextComponent comp;
	
	private Map map;
	

	
	
	public EntityImpl() throws Exception
	{
		extractStructure = Outside.service(this,"gus06.java.srccode.extract.entity.structure1");
		
		button = new JButton("Refresh");
		button.addActionListener(this);
		button.setEnabled(false);
		
		list = new JList();
		list.addListSelectionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(button,BorderLayout.EAST);
		panel.add(new JScrollPane(list),BorderLayout.CENTER);
	
		setSize(new Dimension(0,150));
	}
	
	
	private void setSize(Dimension d)
	{
		panel.setMaximumSize(d);
		panel.setMinimumSize(d);
		panel.setPreferredSize(d);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		entityName = (String) o[0];
		entityFile = (File) o[1];
		comp = (JTextComponent) o[2];
		
		list.setListData(new Vector());
		button.setEnabled(!comp.getText().equals(""));
	}
	
	
	public void actionPerformed(ActionEvent e)
	{refresh();}


	public void valueChanged(ListSelectionEvent e)
	{selectionChanged();}
	
	
	
	
	
	private void refresh()
	{
		try
		{
			String text = comp.getText();
			map = (Map) extractStructure.t(text);
			
			Vector keys = new Vector(map.keySet());
			Collections.sort(keys,new Comparator0(map));
			
			list.setListData(keys);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			String key = (String) list.getSelectedValue();
			int[] range = (int[]) map.get(key);
			
			PlainDocument document = (PlainDocument) comp.getDocument();
			Element root = document.getDefaultRootElement();
			
			Element element1 = root.getElement(range[0]);
			Element element2 = root.getElement(range[1]);
			
			final int start = element1.getStartOffset();
			final int end = element2.getEndOffset();
			
			SwingUtilities.invokeLater(new Runnable(){
				public void run()
				{
					comp.requestFocusInWindow();
					comp.getCaret().setDot(end);
					comp.getCaret().moveDot(start);
				}
			});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	
	
	private class Comparator0 implements Comparator
	{
		private Map m;
		public Comparator0(Map m) {this.m = m;}
		
		public int compare(Object o1, Object o2)
		{
			int[] t1 = (int[]) m.get(o1);
			int[] t2 = (int[]) m.get(o2);
			
			Integer n1 = Integer.valueOf(t1[0]);
			Integer n2 = Integer.valueOf(t2[0]);
			
			return n1.compareTo(n2);
		}
	}
}
