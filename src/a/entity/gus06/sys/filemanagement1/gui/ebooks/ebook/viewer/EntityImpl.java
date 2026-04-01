package a.entity.gus06.sys.filemanagement1.gui.ebooks.ebook.viewer;

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
import javax.swing.JScrollPane;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201104";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();

	public static final int POSTER_WIDTH = 190;
	public static final int HEIGHT = 250;
	public static final int TITLE_FONTSIZE = 22;
	public static final int SUMMARY_FONTSIZE = 13;
	

	private Service previewScreen;
	private Service formatDate;
	private Service formatAuthor;
	private Service formatDescription;
	private Service showMd5Set;
	private Service repaintLabel;
	private Service onClick;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JLabel labelFiles;
	private JTextArea area1;
	private JTextArea area2;

	private Object engine;
	private Map prop;
	private Object preview;
	
	private String md5;
	private String title;
	private String description;
	private String summary;
	

	public EntityImpl() throws Exception
	{
		previewScreen = Outside.service(this,"*gus06.sys.filemanagement1.gui.allocine.posterscreen");
		formatDate = Outside.service(this,"gus06.string.transform.format.timestamp.locale.french");
		formatAuthor = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.format.author");
		formatDescription = Outside.service(this,"gus06.sys.filemanagement1.tool.ebook.format.description");
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
		
		
		area1 = new JTextArea();
		area1.setEditable(false);
		area1.setOpaque(false);
		area1.setLineWrap(true);
		area1.setWrapStyleWord(true);
		area1.setMargin(new Insets(5,0,0,0));
		area1.setBackground(labelTitle.getBackground());
		area1.setFont(area1.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		area2 = new JTextArea();
		area2.setEditable(false);
		area2.setOpaque(false);
		area2.setLineWrap(true);
		area2.setWrapStyleWord(true);
		area2.setMargin(new Insets(5,0,0,0));
		area2.setBackground(labelTitle.getBackground());
		area2.setFont(area1.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		JPanel panel1 = new JPanel(new GridLayout(1,2));
		panel1.add(area1);
		panel1.add(area2);
		
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
		title = title();
		description = description();
		summary = buildSummary();
		
		previewScreen.p(preview);
		labelTitle.setText(display(title));
		area1.setText(summary);
		area2.setText(display(description));
	}
	
	
	
	
	private String getProp(String key)
	{
		if(prop==null || !prop.containsKey(key)) return null;
		return (String) prop.get(key);
	}
	
	private String display(String s)
	{return s==null ? " " : s.trim();}
	
	
	
	private String buildSummary() throws Exception
	{
		String md5 = getProp("md5");
		String name0 = getProp("name0");
		String ext = getProp("ext");
		String size = getProp("size");
		
		String asin = getProp("ebook.asin");
		String author = author();
		String error = getProp("ebook.error");
		String isbn = getProp("ebook.isbn");
		String language = getProp("ebook.language");
		String publisher = getProp("ebook.publisher");
		String publishingDate = getProp("ebook.publishing_date");
		String rights = getProp("ebook.rights");
		String subject = getProp("ebook.subject");
		
		StringBuffer b = new StringBuffer();
		
		b.append("name: "+name0+"."+ext+"\n");
		b.append("md5: "+md5+" size: "+size+"\n\n");
		
		if(subject!=null) b.append("subject: "+subject+"\n");
		if(author!=null) b.append("author: "+author+"\n");
		if(isbn!=null) b.append("isbn: "+isbn+"\n");
		if(asin!=null) b.append("asin: "+asin+"\n");
		if(language!=null) b.append("language: "+language+"\n");
		if(publisher!=null) b.append("publisher: "+publisher+"\n");
		if(rights!=null) b.append("rights: "+rights+"\n");
		
		return b.toString();
	}
	
	
	private String title()
	{return getProp("ebook.title");}
	
	private String author() throws Exception
	{return (String) formatAuthor.t(getProp("ebook.author"));}
	
	private String description() throws Exception
	{return (String) formatDescription.t(getProp("ebook.description"));}
	
	
	
	private void showFile()
	{
		try
		{
			if(engine==null || md5==null || title==null) return;
			Set md5Set = new HashSet();
			md5Set.add(md5);
			showMd5Set.p(new Object[]{engine,md5Set,"EBOOK#"+title});
		}
		catch(Exception e)
		{Outside.err(this,"showFile()",e);}
	}
}