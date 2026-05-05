package a.entity.gus06.sys.filemanagement1.gui.detailpanel.file.handling.prop;

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

	private Service propEditor;
	private Service buildButton;
	private Service deleteFile;
	
	private JPanel panel;
	
	private JButton button_resetProps;
	
	private Object engine;
	private Map selected;
	private Map prop;
	
	private File propFile;
	private String md5;
	
	
	public EntityImpl() throws Exception
	{
		propEditor = Outside.service(this,"*gus06.file.editor.ext.properties");
		buildButton = Outside.service(this,"gus06.swing.button.build2.execute");
		deleteFile = Outside.service(this,"gus.x.file.op.delete");
		
		button_resetProps = build(this::resetProps,"CLIPBOARD_text_empty#Reset props");
		
		button_resetProps.setEnabled(false);
		
		JPanel panel_buttons = new JPanel(new GridLayout(1,0,5,5));
		panel_buttons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel_buttons.add(button_resetProps);
		
		JComponent editorComp = (JComponent) propEditor.i();
		
		panel = cs(editorComp,panel_buttons);
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
			if(engine==null || selected==null) 
			{reset();return;}
			
			md5 = (String) selected.get(KEY_MD5);
			propFile = (File) ((R) engine).r("propFile:"+md5);
			propEditor.p(propFile);
			
			button_resetProps.setEnabled(prop!=null);
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
			propFile = null;
			propEditor.p(null);
			button_resetProps.setEnabled(false);
		}
		catch(Exception e)
		{Outside.err(this,"reset()",e);}
	}
	
	
	private JButton build(E execute, String display) throws Exception
	{return (JButton) buildButton.t(new Object[]{execute,display});}
	
	
	
	private void resetProps()
	{
		try
		{
			if(engine==null || selected==null) return;
			String md5 = (String) selected.get(KEY_MD5);
			File propFile = (File) ((R) engine).r("propFile:"+md5);
			if(propFile==null) return;
			
			deleteFile.p(propFile);
			propEditor.p(null);
			updated();
		}
		catch(Exception e)
		{Outside.err(this,"resetProps()",e);}
	}
	
	private void updated()
	{send(this,"updated()");}
}