package a.entity.gus06.file.editor.ext.srt;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P, G {

	public String creationDate() {return "20230103";}

	
	private Service readFile;
	private Service writeFile;
	private Service gui;
	
	private File file;
	private List data;


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.sys.filesrt1.read");
		writeFile = Outside.service(this,"gus06.sys.filesrt1.write");
		gui = Outside.service(this,"*gus06.sys.filesrt1.gui.maingui");
		
		gui.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return gui.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui() throws Exception
	{gui.p(null);}
	
	
	private void updateGui() throws Exception
	{
		data = (List) readFile.t(file);
		gui.p(data);
	}


	public void actionPerformed(ActionEvent e)
	{save();}
	
	
	private void save()
	{
		try
		{
			if(data==null) return;
			writeFile.p(new Object[]{file,data});
		}
		catch(Exception e)
		{Outside.err(this,"save()",e);}
	}

}