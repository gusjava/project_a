package a.entity.gus06.sys.dirdoubloon1.gui.md5viewer1.selector;

import a.framework.*;
import javax.swing.JPanel;
import java.util.Map;
import java.awt.GridLayout;
import java.io.File;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20221220";}

	public static final String KEY_COPY = "ctrl c";
	public static final String KEY_OPEN = "space";
	public static final String KEY_DEDUPLICATE = "F1";
	
	public static final Color COLOR_DELETED = Color.LIGHT_GRAY;
	public static final Color COLOR_UNSELECTED = Color.GRAY;
	public static final Color COLOR_SELECTED = Color.BLACK;
	public static final Color COLOR_DEDUPLICATED = new Color(153,0,153);

	private Service findIcon;
	private Service iconsToIcon;
	private Service onKey;
	private Service copy;
	private Service open;
	private Service confirm;
	private Service deduplicate;

	private Icon iconEmpty;
	private Icon iconSelected;
	private Icon iconDelete;

	private JPanel panel;
	private List items;
	
	private Map map;
	

	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.file.icon.t1");
		iconsToIcon = Outside.service(this,"gus06.convert.iconstoicon");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		copy = Outside.service(this,"gus06.clipboard.access.file");
		open = Outside.service(this,"gus06.awt.desktop.open");
		confirm = Outside.service(this,"gus06.input.confirm.dialog");
		deduplicate = Outside.service(this,"gus06.sys.dirdoubloon1.perform.deduplicate.group");
		
		iconEmpty = (Icon) Outside.resource(this,"icon#PART_empty");
		iconSelected = (Icon) Outside.resource(this,"icon#PART_connected");
		iconDelete = (Icon) Outside.resource(this,"icon#PART_remove");
		
		panel = new JPanel(new GridLayout(0,1));
		items = new ArrayList();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		
		List l = files();
		if(l!=null) for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			Item1 check = new Item1(f);
			
			panel.add(check);
			items.add(check);
		}
	}
	
	
	private File file()
	{return map!=null ? (File) map.get("file") : null;}
	
	
	private List files()
	{return map!=null ? (List) map.get("files") : null;}
	
	
	private boolean isDeduplicated()
	{return map!=null && map.containsKey("deduplicated");}
	
	
	private void selectFile(File file)
	{
		map.put("file",file);
		refreshAll();
		selected();
	}
	
	
	
	private void refreshAll()
	{
		for(int i=0;i<items.size();i++)
		{
			Item1 check = (Item1) items.get(i);
			check.refresh();
		}
	}
	
	
	
	private class Item1 extends JLabel implements MouseListener
	{
		private File f;
		private Icon iconS;
		private Icon iconC;
		private Icon iconD;
		private Font fontBold;
		private Font fontPlain;
		
		public Item1(File f) throws Exception
		{
			super();
			setFocusable(true);
			this.f = f;
			
			Icon icon = (Icon) findIcon.t(f);
			iconS = (Icon) iconsToIcon.t(new Icon[]{icon, iconSelected});
			iconC = (Icon) iconsToIcon.t(new Icon[]{icon, iconEmpty});
			iconD = (Icon) iconsToIcon.t(new Icon[]{icon, iconDelete});
			
			fontBold = getFont().deriveFont(Font.BOLD);
			fontPlain = getFont().deriveFont(Font.PLAIN);
			
			setText(f.getAbsolutePath());
			setToolTipText(f.getAbsolutePath());
			setIcon(icon);
			
			onKey.p(new Object[]{this, KEY_COPY, (E) this::copy});
			onKey.p(new Object[]{this, KEY_OPEN, (E) this::open});
			onKey.p(new Object[]{this, KEY_DEDUPLICATE, (E) this::deduplicate});
			
			addMouseListener(this);
			refresh();
		}
		
		public void mousePressed(MouseEvent e)
		{
			if(isDeduplicated()) return;
			
			selectFile(f);
			requestFocusInWindow();
		}
		
		public void mouseReleased(MouseEvent e){}
		public void mouseClicked(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		
		
		
		public void refresh()
		{
			setFont(findFont());
			setIcon(findIcon());
			setForeground(findForeground());
			setFocusable(findFocusable());
		}
		
		
		private Font findFont()
		{return isSelected() ? fontBold : fontPlain;}
		
		private Icon findIcon()
		{
			if(isDeleted()) return iconD;
			if(isSelected()) return iconS;
			return iconC;
		}
		
		private Color findForeground()
		{
			if(isDeleted()) return COLOR_DELETED;
			if(isDeduplicated()) return COLOR_DEDUPLICATED;
			if(isSelected()) return COLOR_SELECTED;
			return COLOR_UNSELECTED;
		}
		
		private boolean findFocusable()
		{return !isDeleted();}
		
		
		
		public boolean isSelected()
		{return Objects.equals(f, file());}
		
		public boolean isDeleted()
		{return !f.isFile();}
		
		
		
		private void copy() throws Exception
		{
			if(!isDeleted())
			copy.p(f);
		}
		
		private void open() throws Exception
		{
			if(!isDeleted())
			open.p(f);
		}
		
		private void deduplicate() throws Exception
		{
			if(isDeduplicated()) return;
			
			boolean ok = confirm.f("Please, confirm group deduplication");
			if(!ok) return;
			
			boolean done = deduplicate.f(map);
			if(!done) return;
			
			refreshAll();
			deduplicated();
		}
	}
	
	
	private void selected()
	{send(this,"selected()");}
	
	private void deduplicated()
	{send(this,"deduplicated()");}
}