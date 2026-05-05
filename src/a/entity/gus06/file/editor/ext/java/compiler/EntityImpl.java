package a.entity.gus06.file.editor.ext.java.compiler;

import a.framework.*;

import java.io.File;
import java.io.PrintStream;
import javax.swing.text.JTextComponent;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Event;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140728";}

	public static final String KEYSTROKE_COMPILE = "ctrl m";


	private Service putAction;
	private Service buildAction;
	private Service compileFile;

	public EntityImpl() throws Exception
	{
		putAction = Outside.service(this,"gus06.swing.textcomp.cust.putaction");
		buildAction = Outside.service(this,"gus.y.convert1.executetoaction.th");
		compileFile = Outside.service(this,"gus06.java.compiler.file");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((JTextComponent) obj);}
	
	
	
	
	private class Holder implements P, G, R, E
	{
		private JTextComponent comp;
		private File file;
		private Action action;
		
		public Holder(JTextComponent comp) throws Exception
		{
			this.comp = comp;
			
			action = (Action) buildAction.t(new E(){
				public void e() throws Exception {compileFile1(file);}
			});
			putAction.p(new Object[]{comp,action,KEYSTROKE_COMPILE});
		}
		
		public void e() throws Exception
		{compileFile(file);}
		
		public void p(Object obj) throws Exception
		{file = (File) obj;}
		
		public Object g() throws Exception
		{return file;}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("comp")) return comp;
			if(key.equals("file")) return file;
			if(key.equals("action")) return action;
			if(key.equals("keys")) return new String[]{"comp","file","action"};
			
			throw new Exception("Unknown key: "+key);
		}
	}
	
	
	
	
	
	private void compileFile1(File file)
	{
		try{compileFile(file);}
		catch(Exception e)
		{Outside.err(this,"compileFile1(File)",e);}
	}
	
	private void compileFile(File file) throws Exception
	{
		if(file==null || !file.exists()) return;
		compileFile.p(file);
	}
}
