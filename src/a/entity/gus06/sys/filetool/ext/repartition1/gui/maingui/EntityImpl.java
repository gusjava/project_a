package a.entity.gus06.sys.filetool.ext.repartition1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalLabelUI;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20151020";}


	private Service loader;
	private Service table;

	private Color[] colors;
	private File[] files;

	private JPanel panel;
	
	


	public EntityImpl() throws Exception
	{
		loader = Outside.service(this,"*gus06.sys.filetool.ext.repartition1.gui.loader");
		table = Outside.service(this,"*gus06.sys.filetool.ext.repartition1.gui.table");
		
		JComponent loaderComp = (JComponent) loader.i();
		JComponent tableComp = (JComponent) table.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tableComp),BorderLayout.CENTER);
		panel.add(loaderComp,BorderLayout.SOUTH);
		
		loader.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		File dir = (File) obj;
		files = dir.listFiles();
		
		loader.p(files);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("colors")) {colors = (Color[]) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	


	public void actionPerformed(ActionEvent e)
	{dataLoaded();}
	
	
	
	private void dataLoaded()
	{
		try
		{
			Map map = (Map) loader.g();
			
			table.v("map",map);
			table.v("colors",colors);
			table.v("files",files);
			
			table.e();
		}
		catch(Exception e)
		{Outside.err(this,"dataLoaded()",e);}
	}

}
