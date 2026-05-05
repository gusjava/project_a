package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.handling.allocine;

import a.framework.*;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.io.File;
import javax.swing.JLabel;
import java.awt.Font;

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20201018";}
	
	public static final String KEY_MD5 = "md5";
	public static final String KEY_CODE = "allocine.code";
	public static final String KEY_LINK = "link";
	

	private Service screen1;
	private Service screen2;
	private Service linkLabelHolder;
	
	private Service query;
	private Service findPosterImage;
	private Service findMovieMap;
	private Service modifyCode;
	
	private Service buildButton;
	private Service deleteFile;
	private Service showData;
	private Service titled;
	private Service textDialog;
	
	private JPanel panel;
	private JLabel linkLabel;
	
	private JButton button_query;
	private JButton button_modifyCode;
	private JButton button_showMovie;
	
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	private String md5;
	private String code;
	private Object preview;
	private Object poster;
	private Map movie;
	private String link;
	
	
	private Thread thread;
	
	
	public EntityImpl() throws Exception
	{
		screen1 = Outside.service(this,"*gus06.swing.panel.screen.image.copy-1");
		screen2 = Outside.service(this,"*gus06.swing.panel.screen.image.copy-2");
		linkLabelHolder = Outside.service(this,"*gus06.swing.label.hold.link.web");
		
		query = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.file.query");
		findPosterImage = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.poster.find.image");
		findMovieMap = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.prop.find.map");
		modifyCode = Outside.service(this,"gus06.sys.filemanagement1.tool.allocine.code.change");
		
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		deleteFile = Outside.service(this,"gus.x.file.op.delete");
		showData = Outside.service(this,"gus06.swing.frame.show.data");
		titled = Outside.service(this,"gus06.swing.label.cust.title");
		textDialog = Outside.service(this,"gus06.input.text.dialog.from");
		
		
		button_query = build(this::query,"UTIL_search#Query Web service");
		button_modifyCode = build(this::modifyCode,"UTIL_edit#Modify code");
		button_showMovie = build(this::showMovie,"WEBSITE_allocine_file#Show movie data");
		
		button_query.setEnabled(false);
		button_modifyCode.setEnabled(false);
		button_showMovie.setEnabled(false);
		
		JPanel panel_buttons = new JPanel(new GridLayout(1,0,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel_buttons.add(button_query);
		panel_buttons.add(button_modifyCode);
		panel_buttons.add(button_showMovie);
		
		linkLabel = (JLabel) linkLabelHolder.i();
		titled.p(linkLabel);
		linkLabel.setFont(linkLabel.getFont().deriveFont((float) 14));
		
		JComponent screenComp1 = (JComponent) screen1.i();
		JComponent screenComp2 = (JComponent) screen2.i();
		
		screenComp1 = cn(screenComp1,label("File preview"));
		screenComp2 = cn(screenComp2,label("Movie poster"));
		
		JPanel panelScreens = hh(screenComp1,screenComp2);
		JPanel panelCenter = cn(panelScreens,linkLabel);
		panel = cs(panelCenter,panel_buttons);
	}
	
	private JPanel cs(JComponent c, JComponent s)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(c!=null) p.add(c,BorderLayout.CENTER);
		if(s!=null) p.add(s,BorderLayout.SOUTH);
		return p;
	}
	
	private JPanel cn(JComponent c, JComponent n)
	{
		JPanel p = new JPanel(new BorderLayout());
		if(c!=null) p.add(c,BorderLayout.CENTER);
		if(n!=null) p.add(n,BorderLayout.NORTH);
		return p;
	}
	
	private JPanel hh(JComponent h1, JComponent h2)
	{
		JPanel p = new JPanel(new GridLayout(1,2));
		if(h1!=null) p.add(h1);
		if(h2!=null) p.add(h2);
		return p;
	}
	
	private JLabel label(String title)
	{
		JLabel l = new JLabel(title);
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setFont(l.getFont().deriveFont(Font.BOLD));
		return l;
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		selected = (Map) o[1];
		prop = (Map) o[2];
		
		refresh();
	}
	
	
	
	private void refresh()
	{
		try
		{
			md5 = getProp(prop,KEY_MD5);
			code = getProp(prop,KEY_CODE);
			
			preview = ((R)engine).r("preview:"+md5);
			poster = findPosterImage.t(new Object[]{engine,code});
			movie = (Map) findMovieMap.t(new Object[]{engine,code});
			link = getProp(movie,KEY_LINK);
			
			linkLabelHolder.p(link);
			linkLabel.setText(getCodeDisplay());
			
			screen1.p(preview);
			screen2.p(poster);
			
			button_query.setEnabled(prop!=null);
			button_modifyCode.setEnabled(prop!=null);
			button_showMovie.setEnabled(code!=null);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	private String getCodeDisplay()
	{
		if(code==null) return " ";
		return "Allocine Code: "+code;
	}
	
	private String getProp(Map map, String key)
	{
		if(map==null || !map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
			prop = null;
			
			md5 = null;
			code = null;
			preview = null;
			poster = null;
			movie = null;
			link = null;
			
			linkLabelHolder.p(null);
			linkLabel.setText(" ");
			screen1.p(null);
			screen2.p(null);
			
			button_query.setEnabled(false);
			button_modifyCode.setEnabled(false);
			button_showMovie.setEnabled(false);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	private void query()
	{
		thread = new Thread(this::_query,"THREAD_"+getClass().getName());
		thread.start();
	}
	
	private void _query()
	{
		button_query.setEnabled(false);
		try
		{
			boolean updated = query.f(new Object[]{engine,selected,prop});
			if(updated) updated();
		}
		catch(Exception e)
		{Outside.err(this,"_query()",e);}
		button_query.setEnabled(true);
	}
	
	
	
	
	private void modifyCode()
	{
		try
		{
			String newCode = (String) textDialog.t(code);
			if(newCode==null) return;
			
			boolean updated = modifyCode.f(new Object[]{engine,md5,newCode});
			if(updated) updated();
		}
		catch(Exception e)
		{Outside.err(this,"modifyCode()",e);}
	}
	
	
	
	private void showMovie()
	{
		try
		{
			if(movie!=null)
			showData.p(movie);
		}
		catch(Exception e)
		{Outside.err(this,"showMovie()",e);}
	}

	
	private void updated()
	{send(this,"updated()");}
}
