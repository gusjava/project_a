package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.handling.preview;

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

public class EntityImpl extends S1 implements Entity, I, P {

	public String creationDate() {return "20201018";}
	
	public static final String KEY_MD5 = "md5";
	

	private Service buildButton;
	private Service deleteFile;
	private Service screen;
	
	private JPanel panel;
	
	private JButton button_resetPreview;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	private String md5;
	private Object preview;
	
	
	
	public EntityImpl() throws Exception
	{
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		deleteFile = Outside.service(this,"gus06.file.op.delete");
		screen = Outside.service(this,"*gus06.swing.panel.screen.image.copy");
		
		button_resetPreview = build(this::resetPreview,"CLIPBOARD_image_empty#Reset preview");
		button_resetPreview.setEnabled(false);
		
		JPanel panel_buttons = new JPanel(new GridLayout(1,0,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		panel_buttons.add(button_resetPreview);
		
		JComponent screenComp = (JComponent) screen.i();
		
		panel = cs(screenComp,panel_buttons);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
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
			md5 = prop!=null ? (String) prop.get("md5") : null;
			
			preview = ((R)engine).r("preview:"+md5);
			screen.p(preview);
		
			button_resetPreview.setEnabled(preview!=null);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void reset()
	{
		try
		{
			engine = null;
			selected = null;
			prop = null;
			
			md5 = null;
			preview = null;
			
			screen.p(null);
			
			button_resetPreview.setEnabled(false);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	

	private void resetPreview()
	{
		try
		{
			if(engine==null || selected==null) return;
			String md5 = (String) selected.get(KEY_MD5);
			File previewFile = (File) ((R) engine).r("previewFile:"+md5);
			if(previewFile==null) return;
			
			deleteFile.p(previewFile);
			updated();
		}
		catch(Exception e)
		{Outside.err(this,"resetPreview()",e);}
	}
	

	
	private void updated()
	{send(this,"updated()");}
}
