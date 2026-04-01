package a.entity.gus06.sys.countmap1.gui.maingui;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl extends S1 implements Entity, ActionListener, I, V, P, G, R {

	public String creationDate() {return "20201027";}


	private Service fieldGui;
	private Service tableGui;
	private Service filterString;
	private Service filterMap;
	private Service linker;
	
	private JLabel label;
	private JPanel panel;
	private JTable table;
	private JComponent field;
	
	private Map map;


	public EntityImpl() throws Exception
	{
		fieldGui = Outside.service(this,"*gus06.data.editor.string.textfield.editor1");
		tableGui = Outside.service(this,"*gus06.sys.countmap1.gui.table");
		filterString = Outside.service(this,"gus06.filter.string.build.allofthem_n");
		filterMap = Outside.service(this,"gus06.map.key.filter");
		linker = Outside.service(this,"gus06.swing.table.textfield.linker");
		
		table = (JTable) tableGui.i();
		field = (JComponent) fieldGui.i();
		label = new JLabel(" ");
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
		
		fieldGui.addActionListener(this);
		linker.p(new Object[]{table,field});
		
		tableGui.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selected();}
		});
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return tableGui.g();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		refresh();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("table")) return table;
		if(key.equals("keys")) return new String[]{"table"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		
	}


	public void actionPerformed(ActionEvent e)
	{refresh();}
	
	
	
	private void refresh()
	{
		try
		{
			if(map==null){reset();return;}
			
			String input = (String) fieldGui.g();
			F filter = (F) filterString.t(input);
			Map m = (Map) filterMap.t(new Object[]{map,filter});
			
			updateLabel(m);
			tableGui.p(m);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	
	private void reset() throws Exception
	{
		updateLabel(null);
		tableGui.p(null);
	}

	
	
	private void updateLabel(Map m)
	{
		if(m==null) label.setText(" ");
		else label.setText(" Number: "+m.size());
	}
	
	
	private void selected()
	{send(this,"selected()");}

}