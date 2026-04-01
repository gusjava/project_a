package a.entity.gus06.sys.dirdoubloon1.gui.md5viewer1;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Color;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20221220";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();

	public static final int HEIGHT = 250;
	public static final int POSTER_WIDTH = 190;
	
	public static final Color COLOR_UNIQUE_PARENT = new Color(153,153,255);
	public static final Color COLOR_UNIQUE_NAME = new Color(0,204,102);
	public static final Color COLOR_DEDUPLICATED = new Color(153,0,153);
	

	
	private Service screen;
	private Service formatSize;
	private Service selector;
	private Service findIcon;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JLabel labelMd5;
	private JLabel labelSize;
	private JLabel labelLost;
	private JLabel labelNb;
	
	private Map map;
	

	

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		formatSize = Outside.service(this,"gus06.string.transform.format.datasize.en");
		selector = Outside.service(this,"*gus06.sys.dirdoubloon1.gui.md5viewer1.selector");
		findIcon = Outside.service(this,"gus06.file.icon.t1");
		
		JComponent imageComp = (JComponent) screen.i();
		imageComp.setPreferredSize(new Dimension(POSTER_WIDTH,0));
		imageComp.setBorder(BorderFactory.createCompoundBorder(EMPTY,BEVEL));
		
		JComponent selectorComp = (JComponent) selector.i();
		selectorComp.setBorder(emptyBorder(10));
		
		labelTitle = new JLabel(" ");
		labelMd5 = new JLabel(" ");
		labelSize = new JLabel(" ");
		labelLost = new JLabel(" ");
		labelNb = new JLabel(" ");
		
		bold(labelTitle, 15);
		plain(labelSize, 15);
		plain(labelLost, 15);
		plain(labelNb, 15);
		plain(labelMd5, 10);
		
		labelTitle.setBorder(emptyBorder(2));
		labelMd5.setBorder(emptyBorder(2));
		labelSize.setBorder(emptyBorder(2));
		labelLost.setBorder(emptyBorder(2));
		labelNb.setBorder(emptyBorder(2));
		
		JPanel infoPanel = wc(labelNb, wc(labelSize, labelLost));
		
		JPanel panel0 = nc(selectorComp, null);
		JPanel panel1 = nc(labelMd5, panel0);
		JPanel panel2 = nc(infoPanel, panel1);
		JPanel panel3 = nc(labelTitle, panel2);
		
		panel = wc(imageComp,panel3);
		panel.setPreferredSize(new Dimension(0,HEIGHT));
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		selector.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		selector.p(map);
		updateGui();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("selected()")) updateGui();
		else if(s.equals("deduplicated()")) updateGui();
	}
	
	
	private void updateGui()
	{
		try
		{
			String md5 = (String) map.get("md5");
			Long size = (Long) map.get("size");
			Integer nb = (Integer) map.get("nb");
			Long lost = (Long) map.get("lost");
			Object image = map.get("image");
			File file = (File) map.get("file");
			
			String name = file.getName();
			Icon icon = (Icon) findIcon.t(file);
			
			String sizeS = (String) formatSize.t(size);
			String lostS = (String) formatSize.t(lost);
			
			screen.p(image);
			
			labelTitle.setIcon(icon);
			labelTitle.setText(name);
			labelTitle.setToolTipText(name);
			labelTitle.setForeground(titleForeground(map));
			
			labelMd5.setText(" "+md5+" ");
			labelMd5.setToolTipText(md5);
			
			labelSize.setText(" size: "+sizeS+" ");
			labelSize.setToolTipText(""+size);
			
			labelLost.setText(" lost: "+lostS+" ");
			labelLost.setToolTipText(""+lost);
			
			labelNb.setText(" nb: "+nb+" ");
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	
	private JPanel wc(JComponent w, JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(w!=null) p.add(w,BorderLayout.WEST);
		if(c!=null) p.add(c,BorderLayout.CENTER);
		return p;
	}
	
	private JPanel ec(JComponent e, JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(e!=null) p.add(e,BorderLayout.EAST);
		if(c!=null) p.add(c,BorderLayout.CENTER);
		return p;
	}
	
	private JPanel nc(JComponent n, JComponent c)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(n!=null) p.add(n,BorderLayout.NORTH);
		if(c!=null) p.add(c,BorderLayout.CENTER);
		return p;
	}
	
	private JPanel ncs(JComponent n, JComponent c, JComponent s)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(n!=null) p.add(n,BorderLayout.NORTH);
		if(c!=null) p.add(c,BorderLayout.CENTER);
		if(s!=null) p.add(s,BorderLayout.SOUTH);
		return p;
	}
	
	private Border emptyBorder(int gap)
	{return BorderFactory.createEmptyBorder(gap,gap,gap,gap);}
	
	private void bold(JLabel label, int fontSize)
	{label.setFont(label.getFont().deriveFont(Font.BOLD).deriveFont((float) fontSize));}
	
	private void plain(JLabel label, int fontSize)
	{label.setFont(label.getFont().deriveFont(Font.PLAIN).deriveFont((float) fontSize));}
	
	
	
	private Color titleForeground(Map map)
	{
		if(map.containsKey("deduplicated")) return COLOR_DEDUPLICATED;
		if(map.containsKey("uniqueParent")) return COLOR_UNIQUE_PARENT;
		if(map.containsKey("uniqueName")) return COLOR_UNIQUE_NAME;
		return Color.BLACK;
	}
}