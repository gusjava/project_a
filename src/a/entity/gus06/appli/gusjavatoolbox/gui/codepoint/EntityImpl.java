package a.entity.gus06.appli.gusjavatoolbox.gui.codepoint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import a.framework.*;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20160501";}


	public static final int GAP = 30;
	public static final int FONTSIZE = 18;
	public static final int TOOLTIPSIZE = 25;
	
	public static final Border BORDER0 = BorderFactory.createEmptyBorder();
	public static final Border BORDER1 = BorderFactory.createRaisedBevelBorder();
	
	

	private Service fontCombo;

	private JPanel panel;
	private JTable table;
	private CodePointTableCellRenderer renderer;
	
	
	private JButton nextButton;
	private JButton previousButton;
	private JComboBox offsetCombo;
	private JComboBox combo;
	
	private int offset = 0;
	
	

	public EntityImpl() throws Exception
	{
		fontCombo = Outside.service(this,"gus06.font.availablefonts.buildcombo");
		
		table = new JTable0(new CodePointTableModel());
		renderer = new CodePointTableCellRenderer();
		table.setDefaultRenderer(Object.class, renderer);
		table.setIntercellSpacing(new Dimension(3,3));
		table.setShowGrid(false);
		
		combo = (JComboBox) fontCombo.i();
		combo.setSelectedItem(renderer.getFont().getFontName());
		combo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{updateFont();}});
		
		nextButton = new JButton("next >>");
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {next();}
		});
		previousButton = new JButton("<< previous");
		previousButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {previous();}
		});
		
		offsetCombo = new JComboBox();
		for(int i=0;i<300;i++)
		offsetCombo.addItem(Integer.valueOf(i*GAP*GAP));
			
		offsetCombo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{setOffset();}
		});
		
		JPanel p_top = new JPanel(new GridLayout(1,4));
		p_top.add(previousButton);
		p_top.add(offsetCombo);
		p_top.add(nextButton);
		
		panel = new JPanel(new BorderLayout());
		panel.add(p_top,BorderLayout.NORTH);
		panel.add(table,BorderLayout.CENTER);
		panel.add(combo,BorderLayout.SOUTH);
		
		table.addMouseMotionListener(new TooltipHandler());
	}



	public Object i() throws Exception
	{return panel;}

	
	
	
	private void updateFont()
	{
		String fontName = (String) combo.getSelectedItem();
		Font oldFont = renderer.getFont();
		Font newFont = new Font(fontName,oldFont.getStyle(),oldFont.getSize());
		
		renderer.setFont(newFont);
		table.repaint();
	}
	
	
	
	private void next()
	{
		offset++;
		offsetCombo.setSelectedIndex(offset);
		table.repaint();
	}
	
	
	private void previous()
	{
		if(offset>0) offset--;
		offsetCombo.setSelectedIndex(offset);
		table.repaint();
	}
	
	
	private void setOffset()
	{
		offset = offsetCombo.getSelectedIndex();
		table.repaint();
	}
	
	
	private Color findColor(int codePoint)
	{
		Character.UnicodeBlock block = Character.UnicodeBlock.of(codePoint);
		if(block==null) return Color.LIGHT_GRAY;
		return Color.WHITE;
	}
	
	
	
	private Border findBorder(int codePoint)
	{
		if(Character.isMirrored(codePoint)) return BORDER1;
		return BORDER0;
	}
	
	
	
	
	private class CodePointTableModel extends AbstractTableModel
	{
		public int getRowCount() {return GAP;}
		public int getColumnCount() {return GAP;}

		public Object getValueAt(int x, int y) {
			return Integer.valueOf(offset*GAP*GAP+x*GAP+y);
		}
		public boolean isCellEditable(int x, int y) {
			return false;
		}
	}
	
	
	
	private class CodePointTableCellRenderer extends JLabel implements TableCellRenderer
	{
		public CodePointTableCellRenderer()
		{
			setOpaque(true);
			setFont(getFont().deriveFont(Font.PLAIN).deriveFont((float)FONTSIZE));
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if(value==null)
			{
				setText("");
				return this;
			}
			
			int n = ((Integer)value).intValue();
			setText(new String(Character.toChars(n)));
			
			Color color = findColor(n);
			setBackground(color);
			
			Border border = findBorder(n);
			setBorder(border);
			
			return this;
		}
		
	}
	
	
	private String tooltipFor(int x, int y)
	{
		int n = ((Integer) table.getValueAt(x,y)).intValue();
		if(!Character.isDefined(n)) return "UNDEFINED ("+n+", "+uu(n)+")";
		String c = new String(Character.toChars(n));
		return c+" ("+n+", "+uu(n)+")";
	}
	
	
	private String uu(int c)
	{
		String hexa = Integer.toHexString(c);
		while(hexa.length() < 4) hexa="0"+hexa;
		return "\\u"+hexa;
	}
	
	
	
	
	
	private class TooltipHandler extends MouseMotionAdapter
	{
		public void mouseMoved(MouseEvent evt)
		{
			Point p = evt.getPoint();
			
			int x = table.rowAtPoint(p);
			int y = table.columnAtPoint(p);
			
			if(table.getValueAt(x,y)!=null)
			table.setToolTipText(tooltipFor(x,y));
			else table.setToolTipText(null);
			
			if(table.getColumnName(y)!=null)
			table.getTableHeader().setToolTipText(table.getColumnName(y));
			else table.getTableHeader().setToolTipText(null);
		}
	}
	
	
	private class JTable0 extends JTable
	{
		public JTable0(TableModel model)
		{super(model);}
		
		public JToolTip createToolTip()
		{
			JToolTip tip = super.createToolTip();
			tip.setFont(tip.getFont().deriveFont((float)TOOLTIPSIZE));
			return tip;
		}
	}
	
}