package a.entity.gus06.file.editor.holder.text.autosaver;

import a.framework.*;
import java.io.File;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.Date;
import java.util.TimerTask;

public class EntityImpl implements Entity, F, P, V, R {

	public String creationDate() {return "20141215";}

	public static final long LAPSE = 200;


	private Service getTimer;
	private Service readFile;
	private Service writeFile;
	private Service writeFileCs;
	private Service compLoad;
	private Service compSave;
	
	private JTextComponent comp;
	private File file;
	private Charset charset;
	
	private long lastModified0 = -1;
	private long size0 = -1;
	private String text0 = "";
	private int caret0 = -1;
	
	public EntityImpl() throws Exception
	{
		getTimer = Outside.service(this,"gus06.time.timer.unique");
		readFile = Outside.service(this,"gus06.file.read.string.autodetect");
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
		writeFileCs = Outside.service(this,"gus06.file.write.string.cs");
		compLoad = Outside.service(this,"gus06.sys.fileeditorpersister1.textcomp.load");
		compSave = Outside.service(this,"gus06.sys.fileeditorpersister1.textcomp.save");
		
//		Timer swingTimer = new Timer((int) LAPSE, e -> perform());
//		swingTimer.start();

		TimerTask task = new TimerTask(){
			public void run(){perform();}
		};
		
		Timer timer = (Timer) getTimer.g();
		timer.schedule(task, new Date(), LAPSE);
	}
	
	private synchronized void perform()
	{
		if(file==null) return;
		if(comp==null) return;
		
		long lastModified = file.lastModified();
		long size = file.length();
		String text = comp.getText();
		int caret = comp.getCaretPosition();
		
		boolean fileChanged = lastModified!=lastModified0 || size!=size0;
		boolean compChanged = !text.equals(text0);
		boolean caretChanged = caret!=caret0;
		
		if(fileChanged && compChanged)
		{
			//delicat!! il faudrait faire une fusion des modifications
			//pour le moment, on ignore les modifs du fichier
			compToFile();
		}
		else if(compChanged)
		{
			compToFile();
		}
		else if(fileChanged)
		{
			fileToComp();
		}
		else if(caretChanged)
		{
			saveCaret();
		}
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("comp"))		{initComp((JTextComponent) obj);return;}
		if(key.equals("charset"))	{initCharset((Charset) obj);return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	private synchronized void initComp(JTextComponent comp) throws Exception
	{
		if(this.comp!=null) throw new Exception("Comp already initialized");
		this.comp = comp;
	}
	
	private synchronized void initCharset(Charset charset) throws Exception
	{
		this.charset = charset;
	}
	
	public Object r(String key) throws Exception
	{
		if(key.equals("comp")) return comp;
		if(key.equals("charset")) return charset;
		
		if(key.equals("keys")) return new String[]{"comp","charset"};
		throw new Exception("Unknown key: "+key);
	}
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	public synchronized boolean f(Object obj) throws Exception
	{
		if(comp==null) throw new Exception("Comp not initialized yet");
		String text = comp.getText();
		if(file!=null && !text.equals(text0)) compToFile();
		
		file = (File) obj;
		comp.setEditable(file!=null && file.isFile());
		
		if(file==null || !file.isFile())
		{
			lastModified0 = -1;
			size0 = -1;
			text0 = "";
			
			comp.setText("");
			
			if(!comp.getText().isEmpty()) {
				final Object lock = new Object();
				SwingUtilities.invokeLater(() -> {
					comp.setText("");
					synchronized (lock){lock.notify();}
				});
				synchronized (lock) {lock.wait();}
			}
			return false;
		}
		
		String s = readFile();
		
		boolean shouldUpdate = !comp.getText().equals(s);
		if(shouldUpdate) updateCompNow(s);
		return shouldUpdate;
	}
	
	private void compToFile()
	{
		try
		{
			if(file==null) return;
			if(comp==null) return;
			
			String s = comp.getText();
			if(charset!=null) writeFileCs.p(new Object[]{file,charset,s});
			writeFile.p(new Object[]{file,s});
			
			lastModified0 = file.lastModified();
			size0 = file.length();
			text0 = comp.getText();
			
			saveCaret();
		}
		catch(Exception e)
		{Outside.err(this,"compToFile()",e);}
	}
	
	private void fileToComp()
	{
		try
		{
			if(file==null) return;
			if(comp==null) return;
			
			String s = readFile();
			updateCompLater(s);
		}
		catch(Exception e)
		{Outside.err(this,"fileToComp()",e);}
	}
	
	private String readFile() throws Exception
	{
		if(file==null || !file.exists())
		{
			lastModified0 = -1;
			size0 = -1;
			return "";
		}
		lastModified0 = file.lastModified();
		size0 = file.length();
		return (String) readFile.t(file);
	}
	
	private void updateCompLater(final String text)
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){updateCompNow(text);}
		});
	}
	
	private void updateCompNow(String text)
	{
		if(comp == null) return;
		if(comp.getText().equals(text)) return;
		
		comp.setText(text);
		if(!text.equals("")) loadCaret();
		text0 = text;
	}
	
	private void loadCaret()
	{
		try{compLoad.v(file.getAbsolutePath(),comp);}
		catch(Exception e)
		{Outside.err(this,"loadCaret()",e);}
	}
	
	private void saveCaret()
	{
		try{compSave.v(file.getAbsolutePath(),comp);}
		catch(Exception e)
		{Outside.err(this,"saveCaret()",e);}
	}
}
