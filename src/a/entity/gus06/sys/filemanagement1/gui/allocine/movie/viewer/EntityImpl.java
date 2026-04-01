package a.entity.gus06.sys.filemanagement1.gui.allocine.movie.viewer;

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
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.Set;
import javax.swing.border.TitledBorder;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201101";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();

	public static final int POSTER_WIDTH = 190;
	public static final int HEIGHT = 250;
	public static final int TITLE_FONTSIZE = 22;
	public static final int SUMMARY_FONTSIZE = 13;
	

	private Service formatDate;
	private Service formatNumber;
	private Service formatActors;
	private Service formatSynopsis;
	
	private Service posterScreen;
	private Service linkLabel;
	private Service findMd5Set;
	private Service showMd5Set;
	private Service repaintLabel;
	private Service onClick;
	
	private JPanel panel;
	private JLabel labelTitle;
	private JLabel labelLink;
	private JLabel labelFiles;
	private JTextArea area1;
	private JTextArea area2;

	private Object engine;
	private Map prop;
	private Object poster;
	private String code;
	private String title;
	private Set md5Set;
	

	public EntityImpl() throws Exception
	{
		formatDate = Outside.service(this,"gus06.string.transform.format.timestamp.locale.french");
		formatNumber = Outside.service(this,"gus06.string.transform.format.number.decimal2");
		formatActors = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.format.actors");
		formatSynopsis = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.format.synopsis");
		
		showMd5Set = Outside.service(this,"gus06.sys.filemanagement1.gui.md5set.panel.show");
		posterScreen = Outside.service(this,"*gus06.sys.filemanagement1.gui.allocine.posterscreen");
		linkLabel = Outside.service(this,"*gus06.swing.label.hold.link.web");
		repaintLabel = Outside.service(this,"gus06.swing.label.cust2.display");
		onClick = Outside.service(this,"gus06.swing.label.cust3.onclick.execute");
		
		
		JComponent imageComp = (JComponent) posterScreen.i();
		imageComp.setPreferredSize(new Dimension(POSTER_WIDTH,0));
		imageComp.setBorder(BorderFactory.createCompoundBorder(EMPTY,BEVEL));
		
		labelTitle = new JLabel(" ");
		labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD).deriveFont((float)TITLE_FONTSIZE));
		
		labelLink = (JLabel) linkLabel.i();
		labelLink.setText("Voir la fiche Allocine");
		
		labelFiles = new JLabel(" ");
		onClick.p(new Object[]{labelFiles,(E)this::showMd5Set});
		
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
		area2.setFont(area2.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		JPanel panel1 = new JPanel(new GridLayout(1,2));
		panel1.add(area1);
		panel1.add(area2);
		
		JPanel panel2 = ncs(ec(labelFiles,labelTitle),panel1,labelLink);
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
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		prop = (Map) o[1];
		poster = o[2];
		md5Set = (Set) o[3];
		
		code = getProp("code");
		title = title();
		
		String link = getProp("link");
		String synopsis = synopsis();
		String summary = buildSummary();
		
		posterScreen.p(poster);
		linkLabel.p(link);
		labelLink.setToolTipText("Code: "+code);
		
		repaintLabel.v(buildLabelFilesDisplay(),labelFiles);
		
		labelTitle.setText(display(title));
		area1.setText(summary);
		area2.setText(display(synopsis));
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
		String genre = genre();
		String productionYear = getProp("productionyear");
		String directors = directors();
		String actors = actors();
		String releasedate = getProp("releasedate");
		String pressRating = getProp("pressrating");
		String userRating = getProp("userrating");
		
		StringBuffer b = new StringBuffer();
		
		if(genre!=null) b.append(display(genre)+"\n");
		
		b.append("R\u00e9alis\u00e9");
		if(directors!=null) b.append(" par "+display(directors));
		if(productionYear!=null) b.append(" en "+productionYear);
		b.append("\n");
		
		if(actors!=null) b.append("Avec "+display(actors)+"\n");
		
		if(releasedate!=null) b.append("Sortie le "+formatDate.t(releasedate)+"\n");
		
		if(pressRating!=null) b.append("Presse: "+formatNumber.t(pressRating)+"\n");
		if(userRating!=null) b.append("Spectateurs: "+formatNumber.t(userRating)+"\n");
		
		return b.toString();
	}
	
	
	
	private String buildLabelFilesDisplay()
	{
		if(md5Set==null || md5Set.isEmpty()) return " ";
		return "CLIPBOARD_file#"+md5Set.size();
	}
	
	
	
	private String title()
	{
		String title = getProp("title");
		if(title!=null) return title;
		return getProp("originaltitle");
	}
	
	private String genre() throws Exception
	{return (String) formatActors.t(getProp("genre"));}
	
	private String actors() throws Exception
	{return (String) formatActors.t(getProp("actors"));}
	
	private String directors() throws Exception
	{return (String) formatActors.t(getProp("directors"));}
	
	private String synopsis() throws Exception
	{return (String) formatSynopsis.t(getProp("synopsisshort"));}
	
	
	
	private void showMd5Set()
	{
		try
		{
			if(engine==null || md5Set==null || title==null) return;
			showMd5Set.p(new Object[]{engine,md5Set,"MOVIE#"+title});
		}
		catch(Exception e)
		{Outside.err(this,"showMd5Set()",e);}
	}

}