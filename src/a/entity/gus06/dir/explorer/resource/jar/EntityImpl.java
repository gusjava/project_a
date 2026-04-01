package a.entity.gus06.dir.explorer.resource.jar;

import a.framework.*;
import javax.swing.JList;
import javax.swing.JComponent;
import java.io.File;
import java.util.Map;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class EntityImpl implements Entity, P, I, V, E {

	public String creationDate() {return "20150615";}
	
	public static final String EXTENSION = "jar";
	
	
	private Service dirToMap;
	private Service mapViewer;
	private Service clearCPC;
	
	private Service opDelete;
	private Service opLaunch;
	private Service opRename;
	private Service opDuplicate;
	private Service opCopy;
	private Service opPaste;
	
	private JList list;
	private File dir;
	private G mapBuilder;
	
	
	
	public EntityImpl() throws Exception
	{
		dirToMap = Outside.service(this,"gus06.dir.dirtoiconmap.jar");
		mapViewer = Outside.service(this,"*gus06.data.viewer.map.iconmap");
		clearCPC = Outside.service(this,"gus06.swing.comp.action.clearcopypastecut");
		
		opDelete = Outside.service(this,"gus06.file.op.delete.confirm.en");
		opLaunch = Outside.service(this,"gus06.awt.desktop.open");
		opRename = Outside.service(this,"gus06.file.perform.rename.ask.keepext");
		opDuplicate = Outside.service(this,"gus06.file.perform.duplicate.ask.keepext");
		opCopy = Outside.service(this,"gus06.clipboard.access");
		opPaste = Outside.service(this,"gus06.dir.perform.copyfiles.fromclipboard");
		
		
		list = (JList) mapViewer.r("list");
		clearCPC.p(list);
		
		list.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{handleKeyEvent(e);}
		});
	}
	
	
	
	public Object i() throws Exception
	{return mapViewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		updateGui();
	}
	
	public void e() throws Exception
	{
		updateGui();
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("mapBuilder")) {mapBuilder = (G) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	private void handleKeyEvent(KeyEvent e)
	{
		if((e.getModifiers() & KeyEvent.CTRL_MASK)!=0)
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_C:perform1(opCopy);break;
				case KeyEvent.VK_V:paste();break;
			}
		}
		else
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_F2:perform2(opRename);break;
				case KeyEvent.VK_F3:perform2(opDuplicate);break;
				case KeyEvent.VK_F5:refresh();break;
				case KeyEvent.VK_DELETE:perform2(opDelete);break;
				case KeyEvent.VK_SPACE:perform1(opLaunch);break;
			}
		}
	}
	
	
	
	
	
	
	private void updateGui() throws Exception
	{
		Map map = buildMap();
		mapViewer.p(map);
	}
	
	
	
	private Map buildMap() throws Exception
	{
		if(dir==null) return null;
		if(mapBuilder!=null) return (Map) mapBuilder.g();
		return (Map) dirToMap.t(dir);
	}
	
	
	
	private void refresh()
	{
		try{updateGui();}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void paste()
	{
		try
		{
			if(dir==null) return;
			opPaste.p(dir);
			updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"paste()",e);}
	}
	
	
	
	
	
	
	private File idToFile(String id)
	{
		if(id==null || dir==null) return null;
		return new File(dir,id+"."+EXTENSION);
	}
	
	private File selectedFile()
	{
		String id = (String) list.getSelectedValue();
		return idToFile(id);
	}

	
	
	
	
	
	private void perform1(Service s)
	{
		try
		{
			File f = selectedFile();
			if(f==null || !f.isFile()) return;
			s.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"perform1(Service)",e);}
	}
	
	
	
	
	private void perform2(Service s)
	{
		try
		{
			File f = selectedFile();
			if(f==null || !f.isFile()) return;
			s.p(f);
			updateGui();
		}
		catch(Exception e)
		{Outside.err(this,"perform2(Service)",e);}
	}

}
