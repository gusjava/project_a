package a.entity.gus06.sys.treetable1.extmap;


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
		renderer = Outside.service(this,"gus06.sys.treetable1.extmap.renderer");
	}
	


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JTree tree = (JTree) o[0];
		Object dataHolder = o[1];
		
		FileTreeTableModel1 model = new FileTreeTableModel1(tree,dataHolder);
		FileTreeJTable table = new FileTreeJTable(model,tree);
		
		renderer.p(table);
		
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
		
		JLabel treeLabel = new JLabel();
		treeLabel.setText("Explorer");
		treeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		treeLabel.setFont(treeLabel.getFont().deriveFont(Font.PLAIN));
		treeLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		JPanel treePanel = new JPanel(new BorderLayout());
		treePanel.add(treeLabel,BorderLayout.NORTH);
		treePanel.add(treeScroll,BorderLayout.CENTER);
		
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
		
		split.setLeftComponent(treePanel);
		split.setRightComponent(tableScroll);
		
		tableTooltip.p(table);
		return split;
	}
	
	
	
	private class FileTreeTableModel1 extends FileTreeTableModel implements ActionListener, Runnable
	{
		private G g;
		private Map map;
		private String[] col;
		
		public FileTreeTableModel1(JTree tree, Object dataHolder) throws Exception
		{
			super(tree);
			g = (G) dataHolder;
			
			Object[] o = (Object[]) get(g);
			col = o!=null ? (String[]) o[0] : null;
			map = o!=null ? (Map) o[1] : null; 
			
			if(dataHolder instanceof S)
			((S) dataHolder).addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent e)
		{
			SwingUtilities.invokeLater(this);
		}
		
		public void run()
		{
			Object[] o = (Object[]) get(g);
			col = o!=null ? (String[]) o[0] : null;
			map = o!=null ? (Map) o[1] : null;
			
			fireTableStructureChanged();
		}
		
		public int getColumnCount(){return col!=null ? col.length : 0;}
		public String getColumnName(int column) {return col!=null ? col[column] : null;}
		public Class getColumnClass(int column) {return Integer.class;}
		
		protected Object getValueAt(File file, int columnIndex)
		{
			if(col==null) return null;
			return findCount(map,file,col,columnIndex);
		}
	}



	
	private Integer findCount(Map map, File file, String[] col, int index)
	{
		if(file==null) return null;
		if(map==null) return null;
		if(!map.containsKey(file)) return null;
		
		String ext = col[index];
		Object value = map.get(file);
		
		if(ext.equals("?"))
			return findCountLeft(value,col);
		
		if(value instanceof String)
			return  Integer.valueOf(value.equals(ext) ? 1 : 0);
		
		Map m = (Map) value;
		return m.containsKey(ext) ? (Integer) m.get(ext) : Integer.valueOf(0);
	}
	
	
	
	
	private Integer findCountLeft(Object value, String[] col)
	{
		if(value instanceof String)
			return Integer.valueOf(contains(value,col) ? 0 : 1);
		
		Map m = (Map) value;
		if(!m.containsKey("*")) return Integer.valueOf(0);
		
		int total = ((Integer) m.get("*")).intValue();
		for(String c:col) if(m.containsKey(c) && !c.equals("*"))
		total -= ((Integer) m.get(c)).intValue();
		
		return Integer.valueOf(total);
	}
	
	
	private boolean contains(Object value, String[] col)
	{
		for(String c:col) if(c.equals(value)) return true;
		return false;
	}
	
	
	
	private Object get(G g)
	{
		try{return g.g();}
		catch(Exception e)
		{Outside.err(this,"get(G)",e);}
		return null;
	}
}
