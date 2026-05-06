package a.entity.gus06.file.editor.ext.csv;

import a.framework.*;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, P, G
{
	public String creationDate() {return "20150702";}

	private Service readFile;
	private Service csvParser;
	private Service string2Editor;
	
	private File file;
	private JPanel panel;
	
	
	
	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.string.read.v1");
		csvParser = Outside.service(this,"gus06.file.convert.csv.parser");
		string2Editor = Outside.service(this,"*gus06.data.editor.string2.editor1");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) string2Editor.i(),BorderLayout.CENTER);
	}

	public Object i() throws Exception
	{return panel;}
	
	
	public Object g() throws Exception
	{return file;}


	

	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		String[][] tab = buildContent();
		
		string2Editor.p(tab);
		string2Editor.v("editable","false");
	}
	
	
	
	
	private String[][] buildContent() throws Exception
	{
		if(file==null || !file.isFile() || file.length()==0) return new String[0][0];
		String s = (String) readFile.t(file);
		if(s.equals("")) return new String[0][0];
		return (String[][]) csvParser.t(s);
	}
}