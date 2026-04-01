package a.entity.gus06.appli.allocinesearch.movie.buildpanel.holder;

import a.framework.*;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150214";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();
	
	public static final int POSTER_WIDTH = 190;
	public static final int HEIGHT = 250;
	public static final int TITLE_FONTSIZE = 22;
	public static final int SUMMARY_FONTSIZE = 16;



	private Service formatData;
	private Service posterScreen;
	private Service summary;
	private Service linkLabel;
	

	private Map movie;
	private JPanel panel;
	private JLabel labelTitle;
	private JTextArea area;
	
	
	

	public EntityImpl() throws Exception
	{
		formatData = Outside.service(this,"gus06.appli.allocinesearch.movie.formatdata");
		posterScreen = Outside.service(this,"*gus06.appli.allocinesearch.movie.posterscreen");
		summary = Outside.service(this,"gus06.appli.allocinesearch.movie.buildsummary");
		linkLabel = Outside.service(this,"*gus06.swing.label.hold.link.web");
		
		labelTitle = new JLabel(" ");
		labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD).deriveFont((float)TITLE_FONTSIZE));
		
		area = new JTextArea();
		area.setEditable(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setMargin(new Insets(5,5,5,5));
		area.setBackground(labelTitle.getBackground());
		area.setFont(area.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		JComponent imageComp = (JComponent) posterScreen.i();
		JLabel linkComp = (JLabel) linkLabel.i();
		
		linkComp.setText("Voir la fiche Allocine");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(labelTitle,BorderLayout.NORTH);	
		panel1.add(area,BorderLayout.CENTER);
		panel1.add(linkComp,BorderLayout.SOUTH);
		
		panel = new JPanel(new BorderLayout());
		panel.add(imageComp,BorderLayout.WEST);
		panel.add(panel1,BorderLayout.CENTER);
		
		imageComp.setPreferredSize(new Dimension(POSTER_WIDTH,0));
		imageComp.setBorder(BorderFactory.createCompoundBorder(EMPTY,BEVEL));
		
		panel.setPreferredSize(new Dimension(0,HEIGHT));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel1.setBorder(EMPTY);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		movie = (Map) formatData.t(obj);
		updateGui();
	}
	
	
	
	private void updateGui()
	{
		try
		{
			posterScreen.p(get("poster"));
			linkLabel.p(get("link"));
			labelTitle.setText((String) get("title"));
			area.setText(summary());
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
	
	
	
	private Object get(String key)
	{
		if(!movie.containsKey(key)) return null;
		return movie.get(key);
	}
	
	
	private String summary() throws Exception
	{return (String) summary.t(movie);}

}
