package a.entity.gus06.dir.explorer.treerenderer1;

import a.framework.*;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileSystemView;
import javax.swing.border.Border;
import java.awt.FontMetrics;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140723";}

	public static final Color SELECTION_COLOR = new Color(210,235,235);
	public static final Color COLOR_SEARCH = new Color(0,153,255);
	public static final Color COLOR_UNSELECTABLE = new Color(255,102,102);
	public static final Color COLOR_SEARCHING = new Color(0,153,153);
	
	
	public static Border BORDER_EMPTY = BorderFactory.createEmptyBorder(0,0,0,2);
	public static int GAP = 50;
	
	
	
	private Service custUI;
	private Service findForeground;
	private Service findIcon;
	
	private Icon iconDir;
	private Icon iconDir_;
	
	
	
	public EntityImpl() throws Exception
	{
		custUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons2");
		findForeground = Outside.service(this,"gus06.dir.explorer.treerenderer1.findforeground");
		findIcon = Outside.service(this,"gus06.file.icon.t1");
		
		iconDir = (Icon) Outside.resource(this,"icon#dir");
		iconDir_ = (Icon) Outside.resource(this,"icon#dir_");
	}



	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		Map search = (Map) ((R) tree).r("search");
		
		tree.setCellRenderer(new ExplorerTreeCellRenderer(search));
		custUI.p(tree);
	}
	
	
	
	
	private boolean isDriver(File file)
	{return FileSystemView.getFileSystemView().isDrive(file);}
	
	
	private Icon findIcon(File f, boolean expanded)
	{
		try
		{
			if(f.isDirectory())  return expanded ? iconDir_ : iconDir;
			return (Icon) findIcon.t(f);
		}
		catch(Exception e) {Outside.err(this,"findIcon(File,boolean)",e);}
		return null;
	}
	
	
	private Color findForeground(File f, String searchState, boolean selectable, boolean searching)
	{
		if(searching) return COLOR_SEARCHING;
		if(!selectable) return COLOR_UNSELECTABLE;
		if(searchState!=null && searchState.endsWith("*")) return COLOR_SEARCH;
		
		try{return (Color)findForeground.t(f);}
		catch(Exception e) {Outside.err(this,"findForeground(File,String,boolean,boolean)",e);}
		return Color.BLACK;
	}
	
	
	private Color findBackground(boolean selected)
	{
		if(selected) return SELECTION_COLOR;
		return Color.WHITE;
	}
	
	
	private String findDisplay(File f)
	{
		StringBuffer b = new StringBuffer();
		if(isDriver(f)) return f.getAbsolutePath();
		return f.getName();
	}
	
	
	private int searchNb(String searchState)
	{
		if(searchState==null) return 0;
		if(searchState.equals("*")) return 0;
		
		if(searchState.endsWith("*"))
		searchState = searchState.substring(0,searchState.length()-1);
		return Integer.parseInt(searchState);
	}
	
	
	private boolean isSelectable(JTree tree)
	{
		if(!(tree instanceof F)) return true;
		try{return ((F)tree).f("selectable");}
		catch(Exception e){}
		return true;
	}
	
	private boolean isSearching(JTree tree)
	{
		if(!(tree instanceof F)) return true;
		try{return ((F)tree).f("searching");}
		catch(Exception e){}
		return true;
	}
	
	
	
	
	private class ExplorerTreeCellRenderer extends JLabel implements TreeCellRenderer
	{
		private Map search;
		
		private Font font_p;
		private Font font_b;
		private Font font_i;
		
		private FontMetrics fm_p;
		private FontMetrics fm_b;
		
		public ExplorerTreeCellRenderer(Map search)
		{
			super();
			setOpaque(true);
			this.search = search;
			
			font_p = getFont().deriveFont(Font.PLAIN);
			font_b = getFont().deriveFont(Font.BOLD);
			font_i = getFont().deriveFont(Font.ITALIC);
			
			fm_p = getFontMetrics(font_p);
			fm_b = getFontMetrics(font_b);
		}
		
		private String findSearchState(String path)
		{
			if(!search.containsKey(path)) return null;
			return (String) search.get(path);
		}
		
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
		{
			if(value==null) return this; 
			if(!(value instanceof File)) return this;
			
			File file = (File) value;
			String path = file.getAbsolutePath();
			
			String searchState = findSearchState(path);
			int searchNb = searchNb(searchState);
			boolean searchFound = searchState!=null;
			boolean selectable = isSelectable(tree);
			boolean searching = isSearching(tree);
			
			setIcon(findIcon(file, expanded));
			setForeground(findForeground(file,searchState, selectable, searching));
			setBackground(findBackground(sel));
			
			String text0 = findDisplay(file);
			String text1 = searchNb>0 ? (text0+" ["+searchNb+"]") : text0;
			
			Font font = findFont(file,searchFound);
			Border border = findBorder(text0,text1,searchFound);
			
			setFont(font);
			setText(text1);
			setBorder(border);
			
			return this;
		}
		
		
		
		private Font findFont(File f, boolean searchFound)
		{
			if(searchFound) return font_b;
			if(!f.exists()) return font_p;
			if(!f.canWrite()) return font_i;
			return font_p;
		}
		
		private Border findBorder(String text0, String text1, boolean searchFound)
		{
			if(!searchFound) return buildBorder(GAP);
			
			int width0 = fm_p.stringWidth(text0);
			int width1 = fm_b.stringWidth(text1);
			
			int dw = width0-width1-4;
			
			return buildBorder(GAP+dw);
		}
		
		private Border buildBorder(int gap)
		{
			Border b1 = BorderFactory.createMatteBorder(0,0,0,gap,Color.WHITE);
			return BorderFactory.createCompoundBorder(b1,BORDER_EMPTY);
		}
	}
}