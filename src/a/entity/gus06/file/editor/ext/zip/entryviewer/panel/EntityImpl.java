package a.entity.gus06.file.editor.ext.zip.entryviewer.panel;

import a.framework.*;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, V {

	public String creationDate() {return "20140807";}

	private Service shiftPanel;
	
	private Service panelJava;
	private Service panelClass;
	private Service panelGif;
	private Service panelImage;
	private Service panelWebp;
	private Service panelMp3;
	private Service panelWav;
	private Service panelDefault;
	

	public EntityImpl() throws Exception
	{
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
	
		panelJava = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.java");
		panelClass = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.class1");
		panelGif = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.gif");
		panelImage = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.image");
		panelWebp = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.webp");
		panelMp3 = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.mp3");
		panelWav = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.wav");
		panelDefault = Outside.service(this,"*gus06.file.editor.ext.zip.entryviewer.panel.default1");
	}
	
	
	public Object i() throws Exception
	{return shiftPanel.i();}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key==null) {resetGui();return;}
		
		Service s = findService(key);
		s.p(obj);
		shiftPanel.p(s);
	}
	
	
	private void resetGui() throws Exception
	{shiftPanel.p(null);}
	
	
	private Service findService(String key)
	{
		if(key.equals("java")) return panelJava;
		if(key.equals("class")) return panelClass;
		
		if(key.equals("gif")) return panelGif;
		if(key.equals("png")) return panelGif;
		
		if(key.equals("jpg")) return panelImage;
		if(key.equals("jpeg")) return panelImage;
		if(key.equals("bmp")) return panelImage;
		if(key.equals("webp")) return panelWebp;
		
		if(key.equals("mp3")) return panelMp3;
		if(key.equals("wav")) return panelWav;
		
		return panelDefault;
	}
}