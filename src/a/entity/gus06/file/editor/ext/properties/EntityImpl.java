package a.entity.gus06.file.editor.ext.properties;

import a.framework.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class EntityImpl implements Entity, I, P, G, ActionListener {

	public String creationDate() {return "20140831";}

	
	private Service readFile;
	private Service writeFile;
	private Service gui;
	
	private JPanel panel;
	
	private File file;
	private Map map;
	

	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus.x.file.prop.read");
		writeFile = Outside.service(this,"gus06.file.write.properties");
		gui = Outside.service(this,"*gus06.map.string.editor1");
		
		map = (Map) Outside.resource(this,"supportmap");
		((S) map).addActionListener(this);
		gui.p(map);
	}
	
	
	
	public Object i() throws Exception
	{return gui.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		
		((S) map).removeActionListener(this);
		
		map.clear();
		Map m = readFile();
		if(m!=null) map.putAll(m);
		
		((S) map).addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{writeFile();}
	
	
	
	private Map readFile() throws Exception
	{return (Map) readFile.t(file);}

	
	
	private void writeFile()
	{
		try
		{
			if(file!=null)
			writeFile.p(new Object[]{file,map});
		}
		catch(Exception e)
		{Outside.err(this,"writeFile()",e);}
	}
}
