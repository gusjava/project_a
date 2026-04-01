package a.entity.gus06.file.editor.string.table.csv2.editor;

import a.framework.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, P, V
{
	public String creationDate() {return "20150702";}

	private Service csvParser;
	private Service csvGenerator;
	private Service string2Editor;
	
	private File file;
	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
		csvParser = Outside.service(this,"gus06.file.convert.csv2.parser");
		csvGenerator = Outside.service(this,"gus06.file.convert.csv2.generator");
		string2Editor = Outside.service(this,"gus06.data.editor.string2.editor1");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) string2Editor.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}


	

	public void p(Object obj) throws Exception
	{
		file = (File)obj;
		String[][] tab = buildContent();
		
		string2Editor.p(tab);
		string2Editor.v("editable", editable());
	}


	

	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("save"))
		{
			saveFile();
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	private void saveFile()
	{
		try
		{
			String[][] tab = (String[][]) string2Editor.g();
			String text = (String) csvGenerator.t(tab);
			PrintStream p = new PrintStream(file);
			p.print(text);
			p.close();
		}
		catch(Exception e)
		{Outside.err(this,"saveFile()",e);}
	}
	
	
	
	
	
	private String editable()
	{
		if(file==null || !file.exists()) return "false";
		return file.canWrite()?"true":"false";
	}
	
	
	
	private String[][] buildContent() throws Exception
	{
		if(file==null || !file.exists()) return new String[0][0];
		String content = readFile();
		if(content.equals("")) return new String[0][0];
		return (String[][])csvParser.t(content);
	}
	
	
	
	
	private String readFile() throws Exception
	{
		FileReader fr = new FileReader(file);	
		char[] a= new char[(int)file.length()];
		fr.read(a,0,(int)file.length());
		fr.close();
		return new String(a); 
	}
}
