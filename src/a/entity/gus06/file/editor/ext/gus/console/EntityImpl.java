package a.entity.gus06.file.editor.ext.gus.console;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.io.File;
import java.io.PrintStream;
import java.awt.Font;
import java.awt.Color;
import javax.swing.text.JTextComponent;
import java.awt.Insets;
import java.util.Map;

public class EntityImpl implements Entity, I, P, V {

	public String creationDate() {return "20151101";}
	
	public static final Font FONT = new Font("Courier New", Font.PLAIN, 15);
	
	private Service compHolder;
	private Service engine;
	private Service printDelayed;
	private Service autoScroll;

	private JPanel panel;
	private JTextComponent console;
	private JScrollPane scroll;
	
	private File inputFile;
	private File outputFile;
	
	private String output;
	private long lapse = -1;
	

	public EntityImpl() throws Exception
	{
		compHolder = Outside.service(this,"*gus06.swing.textpane.holder.printstreamcomp");
		engine = Outside.service(this,"gus06.sys.script1.main.main1");
		printDelayed = Outside.service(this,"gus06.io.printstream.delayed1");
		autoScroll = Outside.service(this,"gus.x.swing.scroll.autoposition1");
		
		console = (JTextComponent) compHolder.i();
		
		console.setMargin(new Insets(3,3,3,3));
		console.setBackground(Color.BLACK);
		console.setEditable(false);
		console.setFont(FONT);
		
		scroll = new JScrollPane(console);
		autoScroll.p(scroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		console.setText("");
		if(obj==null) return;
		
		inputFile = (File) obj;
		executeScript();
		recordOutput();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("lapse")) {lapse = toLong(obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private long toLong(Object obj)
	{return Long.parseLong(""+obj);}
	
	
	private void executeScript() throws Exception
	{
		try
		{
			PrintStream p_out = p_out();
			engine.p(new Object[]{inputFile,p_out});
			p_out.close();
		}
		catch(Exception e)
		{
			Outside.err(this,"executeScript()",e);
			e.printStackTrace(p_err());
		}
	}
	
	private void recordOutput() throws Exception
	{
		output = console.getText().trim();
		outputFile = new File(inputFile.getAbsolutePath()+"_output.txt");
		
		if(!output.equals(""))
			print(outputFile,output);
		else clear(outputFile);
	}
	
	private PrintStream p_out() throws Exception
	{
		Object p = compHolder.r("white");
		return (PrintStream) printDelayed.t(new Object[]{p,Long.valueOf(lapse)});
	}
	
	private PrintStream p_err() throws Exception
	{
		return (PrintStream) compHolder.r("red");
	}
	
	private void print(File f, String s) throws Exception
	{
		PrintStream p = new PrintStream(f,"UTF-8");
		p.print(s);
		p.close();
	}
	
	private void clear(File f)
	{if(f.exists()) f.delete();}
		
}