package a.entity.gus06.sys.treetable1.filesize;


import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161117";}

	public final static Border EMPTY = BorderFactory.createEmptyBorder();
	

	private Service synchronizer;
	private Service tableTooltip;
	private Service renderer;
	
	public EntityImpl() throws Exception
	{
		synchronizer = Outside.service(this,"gus06.swing.scrollpane.scrollsynchronizer");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip1");
		renderer = Outside.service(this,"gus06.sys.treetable1.filesize.renderer");
	}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTree tree = (JTree) o[0];
		Object dataHolder = o[1];
		
		FileTreeTableModel1 model = new FileTreeTableModel1(tree,dataHolder);
		FileTreeJTable table = new FileTreeJTable(model,tree);
		
		table.setTableHeader(null);
		renderer.p(table);
		
		table.getColumnModel().getColumn(0).setMinWidth(120);
		table.getColumnModel().getColumn(0).setMaxWidth(120);
		
		JScrollPane treeScroll = new JScrollPane(tree);
		JScrollPane tableScroll = new JScrollPane(table);
		synchronizer.p(new JScrollPane[]{treeScroll,tableScroll});
		
		treeScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		treeScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tree.setBorder(EMPTY);
		table.setBorder(EMPTY);
		treeScroll.setBorder(EMPTY);
		tableScroll.setBorder(EMPTY);
		
		tableScroll.getViewport().setBackground(tree.getBackground());
		
		JSplitPane split = new JSplitPane();
		split.setDividerSize(2);
		split.setResizeWeight(1);
		split.setDividerLocation(300);
		split.setContinuousLayout(true);
		
		split.setOpaque(true);
		split.setBackground(tree.getBackground());
		
		BasicSplitPaneUI ui = (BasicSplitPaneUI) split.getUI();
		ui.getDivider().setBorder(EMPTY);
		ui.getDivider().setBackground(tree.getBackground());
		
		split.setLeftComponent(treeScroll);
		split.setRightComponent(tableScroll);
		
		tableTooltip.p(table);
		return split;
	}
	
	
	
	private class FileTreeTableModel1 extends FileTreeTableModel implements ActionListener, Runnable
	{
		private G g;
		private Map map;
		
		public FileTreeTableModel1(JTree tree, Object dataHolder) throws Exception
		{
			super(tree);
			g = (G) dataHolder;
			map = (Map) get(g);
			
			if(dataHolder instanceof S)
			((S) dataHolder).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			SwingUtilities.invokeLater(this);
		}
		
		public void run()
		{
			map = (Map) get(g);
			fireTableDataChanged();
		}
		
		public int getColumnCount(){return 2;}
		public String getColumnName(int column) {return null;}
		public Class getColumnClass(int column) {return Long.class;}
		
		protected Object getValueAt(File file, int columnIndex)
		{return findSize(map,file);}
	}


	
	private Long findSize(Map map, File file)
	{
		if(file==null) return null;
		if(map==null) return null;
		if(!map.containsKey(file)) return null;
		return (Long) map.get(file);
	}
	
	
	private Object get(G g)
	{
		try{return g.g();}
		catch(Exception e)
		{Outside.err(this,"get(G)",e);}
		return null;
	}
}
