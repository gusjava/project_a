package a.entity.gus06.appli.gusclient1.gui.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140730";}

	private Service pseudoLabel;
	private Service compilerLabel;
	private Service jarBuilderLabel;
	private Service backupLabel;
	
	private Service alertBar;

	private JPanel panel;
	private JToolBar mainBar;


	public EntityImpl() throws Exception
	{
		pseudoLabel = Outside.service(this,"*gus06.entitydev.pseudo.label");
		compilerLabel = Outside.service(this,"*gus06.java.compiler1.label");
		jarBuilderLabel = Outside.service(this,"*gus06.file.jar.builder1.label");
		backupLabel = Outside.service(this,"*gus06.command.backupapp.label");
		
		alertBar = Outside.service(this,"gus06.appli.gusclient1.alert.toolbar");
		
		
		mainBar = new JToolBar();
		mainBar.setFloatable(false);
		
		mainBar.add((JComponent) compilerLabel.i());
		mainBar.add((JComponent) jarBuilderLabel.i());
		mainBar.add((JComponent) backupLabel.i());
	
		panel = wce(pseudoLabel,mainBar,alertBar);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	private JPanel wc(Object w, Object c) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		if(w!=null) p.add(comp(w),BorderLayout.WEST);
		if(c!=null) p.add(comp(c),BorderLayout.CENTER);
		return p;
	}
	
	
	private JPanel wce(Object w, Object c, Object e) throws Exception
	{
		JPanel p = new JPanel(new BorderLayout());
		if(w!=null) p.add(comp(w),BorderLayout.WEST);
		if(c!=null) p.add(comp(c),BorderLayout.CENTER);
		if(e!=null) p.add(comp(e),BorderLayout.EAST);
		return p;
	}
	
	
	private JComponent comp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I)obj).i();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
