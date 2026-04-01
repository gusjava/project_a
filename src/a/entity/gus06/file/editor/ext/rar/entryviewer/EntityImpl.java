package a.entity.gus06.file.editor.ext.rar.entryviewer;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, P, I, G, ActionListener {

	public String creationDate() {return "20200304";}


	public static final String DELIM = "/";
	
	
	private Service entryLabel;
	private Service entryPanel;
	private Service writeToFile;
	private Service buildInputStream;
    
	private JPanel panel;
	private JButton button;
	
	private File file;
	private String entry;
	
	

	public EntityImpl() throws Exception
	{
		entryLabel = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.label");
		entryPanel = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel");
		writeToFile = Outside.service(this,"gus06.io.transfer.writetofile.choosedir");
		buildInputStream = Outside.service(this,"gus06.zzz.file.rar.innosystec.getentry.inputstream");
		
		button = new JButton("Extract");
		button.setEnabled(false);

		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) entryLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) entryPanel.i(),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		button.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		return (InputStream) buildInputStream.t(new Object[]{file,entry});
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {resetGui();return;}
	
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		file = (File) o[0];
		entry = (String) o[1];
		
		updateGui();
	}
	
	
	
	
	
	private void updateGui() throws Exception
	{
		entryLabel.p(entry);
		String ext = getExtension();
		
		if(ext==null || ext.equals("#"))
		{
			button.setEnabled(false);
			entryPanel.v(null,null);
		}
		else
		{
			button.setEnabled(true);
			entryPanel.v(ext,this);
		}
	}
	
	
	
	
	private void resetGui() throws Exception
	{
		file = null;
		entry = null;
		entryLabel.p(null);
		entryPanel.v(null,null);
		button.setEnabled(false);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{extract();}
	
	
	
	private void extract()
	{
		if(file==null) return;
		try(InputStream is = (InputStream) buildInputStream.t(new Object[] {file, entry}))
		{
			if(is!=null)
			{
				String fileName = getFileName();
				writeToFile.v(fileName, is);
			}
		}
		catch(Exception e)
		{Outside.err(this,"extract()",e);}
	}
	
	
	
	
	
	private String getExtension()
	{
		if(entry==null) return null;
		if(entry.endsWith(DELIM)) return "#";
		if(!entry.contains(".")) return "";
		
		String[] n = entry.split("\\."); 
		return n[n.length-1];
	}
	
	
	private String getFileName()
	{
		if(entry==null) return null;
		if(entry.endsWith(DELIM)) return "#";
		if(!entry.contains(DELIM)) return entry;
		
		String[] n = entry.split(DELIM); 
		return n[n.length-1];
	}
}