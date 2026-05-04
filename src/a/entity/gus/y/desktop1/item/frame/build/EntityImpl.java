package a.entity.gus.y.desktop1.item.frame.build;

import a.framework.*;
import javax.swing.JInternalFrame;
import java.util.Map;
import java.io.File;
import java.awt.Rectangle;
import java.util.HashMap;
import java.awt.Container;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191121";}

	public static final String KEY_DIR_ITEM_DEF = "dir_item_def";
	public static final String KEY_DIR_ITEM_POS = "dir_item_pos";
	public static final String KEY_OBJECT_FACTORY = "object_factory";
	
	public static final String KEY_CONTENT = "content";

	

	private Service accessProp;
	private Service accessString;
	private Service rectToString;
	private Service stringToRect;
	private Service custFrame;

	public EntityImpl() throws Exception
	{
		accessProp = Outside.service(this,"gus06.file.access.properties");
		accessString = Outside.service(this,"gus06.file.access.string");
		rectToString = Outside.service(this,"gus06.tostring.rectangle");
		stringToRect = Outside.service(this,"gus06.convert.stringtorectangle");
		custFrame = Outside.service(this,"gus.y.desktop1.item.frame.cust");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map main = (Map) o[0];
		String id = (String) o[1];
		
		return new JInternalFrame1(main,id);
	}
	
	
	
	
	private void loadPos(JInternalFrame1 frame) throws Exception
	{
		String pos = (String) ((G) frame.posAccess).g();
		if(pos==null || pos.equals("")) return;
		
		Rectangle rect = (Rectangle) stringToRect.t(pos);
		frame.setBounds(rect);
	}
	
	private void loadDef(JInternalFrame1 frame) throws Exception
	{
		frame.def = (Map) ((G) frame.defAccess).g();
		if(frame.def==null || frame.def.isEmpty())
		{
			frame.def = new HashMap();
			frame.def.put("id",frame.id);
			saveDef(frame);
		}
	}
	
	private void custFrame(JInternalFrame1 frame) throws Exception
	{
		setContent(frame);
		custFrame.p(new Object[]{frame,frame.def});
	}
	
	private void setContent(JInternalFrame1 frame) throws Exception
	{
		if(!frame.def.containsKey(KEY_CONTENT)) return;
		String contentRule = (String) frame.def.get(KEY_CONTENT);
		Container content = (Container) frame.objectFactory.r(contentRule);
		frame.setContentPane(content);
	}
	
	
	
	
	private void savePos(JInternalFrame1 frame) throws Exception
	{
		Rectangle rect = frame.getBounds();
		String pos = (String) rectToString.t(rect);
		((P) frame.posAccess).p(pos);
	}
	
	private void saveDef(JInternalFrame1 frame) throws Exception
	{
		((P) frame.defAccess).p(frame.def);
	}
	
	private void clear(JInternalFrame1 frame) throws Exception
	{
		frame.fileDef.delete();
		frame.filePos.delete();
	}
	
	
	private class JInternalFrame1 extends JInternalFrame implements R, P, V
	{
		private Map main;
		private String id;
		
		private File fileDef;
		private File filePos;
		
		private Map def;
		
		private Object defAccess;
		private Object posAccess;
		private R objectFactory;
		
		public JInternalFrame1(Map main, String id) throws Exception
		{
			super();
			this.main = main;
			this.id = id;
			
			File dirItemDef = (File) main.get(KEY_DIR_ITEM_DEF);
			File dirItemPos = (File) main.get(KEY_DIR_ITEM_POS);
			
			fileDef = new File(dirItemDef,id+".properties");
			filePos = new File(dirItemPos,id+".txt");
			
			defAccess = accessProp.t(fileDef);
			posAccess = accessString.t(filePos);
		
			objectFactory = (R) main.get(KEY_OBJECT_FACTORY);
			
			setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			setResizable(true);
			setLocation(20,20);
			setSize(100,100);
			setVisible(true);
			setSelected(true);
			
			loadDef(this);
			custFrame(this);
			
			loadPos(this);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("id")) return id;
			if(key.equals("def")) return def;
			
			if(key.equals("keys")) return new String[]{"id","def"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			def.put(key,(String) obj);
			saveDef(this);
		}
		
		public void p(Object obj) throws Exception
		{
			String cmd = (String) obj;
			
			if(cmd.equals("loadPos")) {loadPos(this);return;}
			if(cmd.equals("savePos")) {savePos(this);return;}
			if(cmd.equals("loadDef")) {loadDef(this);return;}
			if(cmd.equals("saveDef")) {saveDef(this);return;}
			if(cmd.equals("clear")) {clear(this);return;}
			
			throw new Exception("Unknown command: "+cmd);
		}
	}
}
