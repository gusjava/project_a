package a.entity.gus06.sys.filemanagement1.gui.pdfs.pdf.viewer;

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
import java.util.HashSet;
import java.util.Set;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201104";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();

	public static final int POSTER_WIDTH = 190;
	public static final int HEIGHT = 250;
	public static final int TITLE_FONTSIZE = 22;
	public static final int SUMMARY_FONTSIZE = 14;
	

	private Service formatDate;
	private Service cleanHtml;
	private Service previewScreen;
	private Service showMd5Set;
	private Service repaintLabel;
	private Service onClick;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JLabel labelFiles;
	private JTextArea area;

	private Object engine;
	private Map prop;
	private Object preview;
	
	private String md5;
	private String title;
	private String summary;
	

	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.string.transform.format.timestamp.locale.french");
		cleanHtml = Outside.service(this,"gus06.string.transform.regexp.remove.tag");
		previewScreen = Outside.service(this,"*gus06.sys.filemanagement1.gui.allocine.posterscreen");
		showMd5Set = Outside.service(this,"gus06.sys.filemanagement1.gui.md5set.panel.show");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		onClick = Outside.service(this,"gus06.swing.label.cust3.onclick.execute");
		
		
		JComponent imageComp = (JComponent) previewScreen.i();
		imageComp.setPreferredSize(new Dimension(POSTER_WIDTH,0));
		imageComp.setBorder(BorderFactory.createCompoundBorder(EMPTY,BEVEL));
		
		labelTitle = new JLabel(" ");
		labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD).deriveFont((float)TITLE_FONTSIZE));
		
		labelFiles = new JLabel(" ");
		repaintLabel.v("CLIPBOARD_file#",labelFiles);
		onClick.p(new Object[]{labelFiles,(E)this::showFile});
		
		
		area = new JTextArea();
		area.setEditable(false);
		area.setOpaque(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setMargin(new Insets(5,0,0,0));
		area.setBackground(labelTitle.getBackground());
		area.setFont(area.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		JPanel panel1 = new JPanel(new GridLayout(1,1));
		panel1.add(area);
		
		JPanel panel2 = nc(ec(labelFiles,labelTitle),panel1);
		panel2.setOpaque(false);
		panel2.setBorder(EMPTY);
		
		panel = wc(imageComp,panel2);
		panel.setPreferredSize(new Dimension(0,HEIGHT));
		panel.setBorder(BorderFactory.createEtchedBorder());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
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
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		prop = (Map) o[1];
		preview = o[2];
		
		md5 = getProp("md5");
		title = getProp("pdf.title");
		summary = buildSummary();
		
		previewScreen.p(preview);
		
		labelTitle.setText(display(title));
		area.setText(summary);
	}
	
	
	
	
	private String getProp(String key)
	{
		if(prop==null || !prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
	
	private String display(String s)
	{
		if(s==null) return " ";
		return s.trim()
			.replaceAll("[\n\t ]+"," ")
			.replaceAll(" *, *",", ")
			.replaceAll(" *; *",", ");
	}
	
	
	
	private String buildSummary() throws Exception
	{
		String md5 = getProp("md5");
		String name0 = getProp("name0");
		String ext = getProp("ext");
		String size = getProp("size");
		
		String pagenb = getProp("ebook.pagenb");
		String author = getProp("pdf.author");
		String error = getProp("pdf.error");
		String creator = getProp("pdf.creator");
		String creationDate = getProp("pdf.creationdate");
		String producer = getProp("pdf.producer");
		String subject = getProp("pdf.subject");
		String title = getProp("pdf.title");
		
		StringBuffer b = new StringBuffer();
		
		b.append("name: "+name0+"."+ext+"\n");
		b.append("md5: "+md5+" size: "+size+"\n\n");
		
		if(title!=null) b.append("title: "+title+"\n");
		if(subject!=null) b.append("subject: "+subject+"\n");
		if(author!=null) b.append("author: "+author+"\n");
		if(pagenb!=null) b.append("page nb: "+pagenb+"\n");
		if(creator!=null) b.append("creator: "+creator+"\n");
		if(producer!=null) b.append("producer: "+producer+"\n");
		
		return b.toString();
	}
	
	
	
	private void showFile()
	{
		try
		{
			if(engine==null || md5==null || title==null) return;
			Set md5Set = new HashSet();
			md5Set.add(md5);
			showMd5Set.p(new Object[]{engine,md5Set,"FILE_pdf#"+title});
		}
		catch(Exception e)
		{Outside.err(this,"showFile()",e);}
	}
}
