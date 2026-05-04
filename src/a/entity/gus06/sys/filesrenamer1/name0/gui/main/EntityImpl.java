package a.entity.gus06.sys.filesrenamer1.name0.gui.main;

import a.framework.*;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.text.JTextComponent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl extends S1 implements Entity, I, P, E, R {

	public String creationDate() {return "20250210";}


	private Service renameAll;
	private Service viewer1;
	private Service editor2;
	private Service titled;
	private Service name0Ext;
	private Service scrollSync;
	private Service onCtrlQ;
	private Service onEscape;

	private JPanel panel;
	private File dir;
	private File[] files;
	private String[] exts;
	private int nb = -1;

	public EntityImpl() throws Exception
	{
		renameAll = Outside.service(this,"gus06.sys.filesrenamer1.name0.op.renameall");
		viewer1 = Outside.service(this,"gus06.data.viewer.string.textarea.editor1");
		editor2 = Outside.service(this,"*gus06.data.editor.string.textarea.editor1");
		titled = Outside.service(this,"gus06.swing.comp.build.titledpanel");
		name0Ext = Outside.service(this,"gus06.file.getname0ext");
		scrollSync = Outside.service(this,"gus06.swing.scrollpane.scrollsynchronizer.v");
		onCtrlQ = Outside.service(this,"gus.x.swing.comp.cust3.execute.ctrl_q");
		onEscape = Outside.service(this,"gus.x.swing.comp.cust3.execute.escape");
		
		panel = new JPanel(new GridLayout(1,2));
		panel.add(titled("Initial",(JComponent) viewer1.i()));
		panel.add(titled("Renamed",(JComponent) editor2.i()));
		
		JScrollPane scroll1 = (JScrollPane) viewer1.r("scroll");
		JScrollPane scroll2 = (JScrollPane) editor2.r("scroll");
		
		scrollSync.p(new JScrollPane[]{scroll1, scroll2});
		
		JTextComponent comp1 = (JTextComponent) viewer1.r("comp");
		JTextComponent comp2 = (JTextComponent) editor2.r("comp");
		
		comp1.setBackground(Color.LIGHT_GRAY);
		comp1.setCaretColor(Color.LIGHT_GRAY);
		
		onCtrlQ.p(new Object[]{comp2,(E) this::perform});
		onEscape.p(new Object[]{comp2,(E) this::canceled});
	}
	
	
	private JPanel titled(String title, JComponent c) throws Exception
	{return (JPanel) titled.t(new Object[]{c, title});}
	
	
	public void e() throws Exception
	{perform();}
	
	private void perform()
	{
		try
		{
			if(nb<=0) return;
		
			String text = (String) editor2.g();
			String[] n = text.split("\n");
			
			List fileList = new ArrayList();
			List nameList = new ArrayList();
			for(int i=0;i<Math.min(nb,n.length);i++)
			{
				File file = files[i];
				String name = n[i]+"."+exts[i];
				
				fileList.add(file);
				nameList.add(name);
			}
			
			renameAll.p(new Object[]{fileList, nameList});
			done();
		}
		catch(Exception e)
		{Outside.err(this,"",e);}
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		if(dir==null || !dir.isDirectory())
		{reset();return;}
		
		files = dir.listFiles();
		nb = files.length;
		
		exts = new String[nb];
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		for(int i=0;i<nb;i++)
		{
			String name = files[i].getName();
			String[] n = (String[]) name0Ext.t(name);
			String name0 = n[0];
			String ext = n[1];
			
			exts[i] = ext;
			sb1.append(name);
			sb2.append(name0);
			
			if(i<nb-1)
			{
				sb1.append("\n");
				sb2.append("\n");
			}
		}
		
		viewer1.p(sb1.toString());
		editor2.p(sb2.toString());
		
	}
	
	private void reset() throws Exception
	{
		nb = -1;
		viewer1.p("");
		editor2.p("");
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("area1")) return viewer1.r("comp");
		if(key.equals("area2")) return editor2.r("comp");
		if(key.equals("keys")) return new String[]{"area1","area2"};
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void done()
	{send(this,"done()");}
	
	public void canceled()
	{send(this,"canceled()");}
}