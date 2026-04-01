package a.entity.gus06.sys.allocinesearch.movie.viewer;

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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class EntityImpl extends S1 implements Entity, I, P, G, V, MouseListener {

	public String creationDate() {return "20200920";}
	
	public static final Border EMPTY = BorderFactory.createEmptyBorder(10,10,10,10);
	public static final Border BEVEL = BorderFactory.createRaisedBevelBorder();
	
	public static final int POSTER_WIDTH = 190;
	public static final int HEIGHT = 250;
	public static final int TITLE_FONTSIZE = 22;
	public static final int SUMMARY_FONTSIZE = 16;
	
	public static final Color COLOR_SELECTED = Color.LIGHT_GRAY;
	public static final Color COLOR_UNSELECTED = new JLabel().getBackground();



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
		formatData = Outside.service(this,"gus06.sys.allocinesearch.movie.formatdata");
		posterScreen = Outside.service(this,"*gus06.sys.allocinesearch.movie.posterscreen");
		summary = Outside.service(this,"gus06.sys.allocinesearch.movie.buildsummary");
		linkLabel = Outside.service(this,"*gus06.swing.label.hold.link.web");
		
		labelTitle = new JLabel(" ");
		labelTitle.setFont(labelTitle.getFont().deriveFont(Font.BOLD).deriveFont((float)TITLE_FONTSIZE));
		
		area = new JTextArea();
		area.setEditable(false);
		area.setOpaque(false);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setMargin(new Insets(5,5,5,5));
		area.setBackground(labelTitle.getBackground());
		area.setFont(area.getFont().deriveFont((float)SUMMARY_FONTSIZE));
		
		JComponent imageComp = (JComponent) posterScreen.i();
		JLabel linkComp = (JLabel) linkLabel.i();
		
		linkComp.setText("Voir la fiche Allocine");
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setOpaque(false);
		panel1.setBorder(EMPTY);
		panel1.add(labelTitle,BorderLayout.NORTH);
		panel1.add(area,BorderLayout.CENTER);
		panel1.add(linkComp,BorderLayout.SOUTH);
		
		panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(0,HEIGHT));
		panel.setBorder(BorderFactory.createEtchedBorder());
		panel.add(imageComp,BorderLayout.WEST);
		panel.add(panel1,BorderLayout.CENTER);
		
		imageComp.setOpaque(false);
		imageComp.setPreferredSize(new Dimension(POSTER_WIDTH,0));
		imageComp.setBorder(BorderFactory.createCompoundBorder(EMPTY,BEVEL));
		
		
		initMouseListener(labelTitle);
		initMouseListener(area);
		initMouseListener(imageComp);
		initMouseListener(panel);
		initMouseListener(panel1);
	}
	
	
	
	private void initMouseListener(JComponent comp)
	{
		comp.setFocusable(true);
		comp.addMouseListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	

	public Object g() throws Exception
	{return movie;}
	
	
	
	public void p(Object obj) throws Exception
	{
		movie = (Map) formatData.t(obj);
		updateGui();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("unselect")) {unselect();return;}
		if(key.equals("select")) {select();return;}
		
		throw new Exception("Unknown key: "+key);
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
	
	
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	public void mousePressed(MouseEvent e)
	{select();}
	
	
	
	
	private void select()
	{
		panel.setBackground(COLOR_SELECTED);
		selected();
	}
	
	private void unselect()
	{
		panel.setBackground(COLOR_UNSELECTED);
	}
	
	
	
	private void selected()
	{send(this,"selected()");}
}