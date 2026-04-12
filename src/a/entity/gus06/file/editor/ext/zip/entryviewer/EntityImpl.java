package a.entity.gus06.file.editor.ext.zip.entryviewer;

import a.framework.*;
import javax.swing.JComponent;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class EntityImpl implements Entity, P, I, G {

	public String creationDate() {return "20141024";}

	public static final String DELIM = "/";
	
	private Service entryLabel;
	private Service entryPanel;
	private Service writeToFile1;
	private Service writeToFile2;
	private Service buildZipFile;
	private Service wrapCloseable;
    
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	
	private File file;
	private String entry;

	public EntityImpl() throws Exception
	{
		entryLabel = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.label");
		entryPanel = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel");
		writeToFile1 = Outside.service(this,"gus06.io.transfer.writetofile.choosedir");
		writeToFile2 = Outside.service(this,"gus06.file.write.inputstream");
		buildZipFile = Outside.service(this,"gus06.file.zip.build.zipfile");
		wrapCloseable = Outside.service(this,"gus06.io.inputstream.wrap.withcloseable");
		
		button1 = new JButton("Extract to");
		button2 = new JButton("Extract in parent");

		JPanel panelButtons = new JPanel(new GridLayout(1,2));
		panelButtons.add(button1);
		panelButtons.add(button2);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) entryLabel.i(),BorderLayout.NORTH);
		panel.add((JComponent) entryPanel.i(),BorderLayout.CENTER);
		panel.add(panelButtons,BorderLayout.SOUTH);
		
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{extract1();}
		});
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{extract2();}
		});
		
		setEnabled(false);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {resetGui();return;}
	
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		file = (File) o[0];
		entry = (String) o[1];
		updateGui();
	}
	
	public Object g() throws Exception
	{
		ZipFile zipFile = (ZipFile) buildZipFile.t(file);
		ZipEntry zipEntry = zipFile.getEntry(entry);
		if(zipEntry==null) throw new Exception("ZipEntry not found: "+entry+" for file "+file);
		
		if(zipEntry.isDirectory()) return null;
		
		InputStream is = zipFile.getInputStream(zipEntry);
		return wrapCloseable.t(new Object[]{is,zipFile});
	}
	
	private void updateGui() throws Exception
	{
		entryLabel.p(entry);
		String ext = getExtension();
		
		if(ext==null || ext.equals("#"))
		{
			setEnabled(false);
			entryPanel.v(null,null);
		}
		else
		{
			setEnabled(true);
			entryPanel.v(ext,this);
		}
	}
	
	private void setEnabled(boolean enabled)
	{
		button1.setEnabled(enabled);
		button2.setEnabled(enabled);
	}
	
	private void resetGui() throws Exception
	{
		file = null;
		entry = null;
		entryLabel.p(null);
		entryPanel.v(null,null);
		setEnabled(false);
	}
	
	private void extract1()
	{
		try
		{
			if(file==null) return;
			
			ZipFile zipFile = (ZipFile) buildZipFile.t(file);
			ZipEntry zipEntry = zipFile.getEntry(entry);
			InputStream is = zipFile.getInputStream(zipEntry);
			
			if(is!=null)
			{
				String fileName = getFileName();
				writeToFile1.v(fileName,is);
				is.close();
			}
			zipFile.close();
		}
		catch(Exception e)
		{Outside.err(this,"extract1()",e);}
	}
	
	private void extract2()
	{
		try
		{
			if(file==null) return;
			
			ZipFile zipFile = (ZipFile) buildZipFile.t(file);
			ZipEntry zipEntry = zipFile.getEntry(entry);
			InputStream is = zipFile.getInputStream(zipEntry);
			
			if(is!=null)
			{
				String fileName = getFileName();
				File outFile = new File(file.getParentFile(), fileName);
				writeToFile2.p(new Object[]{outFile,is});
				is.close();
			}
			zipFile.close();
		}
		catch(Exception e)
		{Outside.err(this,"extract2()",e);}
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
