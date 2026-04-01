package a.entity.gus06.sys.filemanagement1.perform.root.edit.dialog;

import a.framework.*;
import java.io.File;
import javax.swing.JTextField;
import java.util.Map;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20191127";}

	public static final String KEY_HDD = "HDD";
	public static final String KEY_PATH = "PATH";
	

	private Service fileAccess;
	private Service formPanel;
	private Service dialogOkCancel;
	
	private JTextField fieldHddName;
	private JTextField fieldPath;

	public EntityImpl() throws Exception
	{
		fileAccess = Outside.service(this,"gus06.file.access.properties");
		formPanel = Outside.service(this,"*gus06.swing.panel.formpanel");
		dialogOkCancel = Outside.service(this,"gus06.swing.dialog.blocked1.okcancel");
		
		fieldHddName = new JTextField();
		fieldPath = new JTextField();
		
		formPanel.v("HDD name",fieldHddName);
		formPanel.v("Root path",fieldPath);
	}
	
	
	public boolean f(Object obj) throws Exception
	{
		File file = (File) obj;
		Object access = fileAccess.t(file);
		Map map = (Map) ((G) access).g();
		
		String hddName = get(map,KEY_HDD);
		String path = get(map,KEY_PATH);
		
		fieldHddName.setText(hddName);
		fieldPath.setText(path);
		
		boolean ok = dialogOkCancel.f(formPanel.i());
		if(!ok) return false;
		
		hddName = fieldHddName.getText();
		path = fieldPath.getText();
		
		if(isEmpty(hddName)) return false;
		if(isEmpty(path)) return false;
	
		map.put(KEY_HDD,hddName);
		map.put(KEY_PATH,path);
		
		((P) access).p(map);
		return true;
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(map.containsKey(key)) return (String) map.get(key);
		throw new Exception("Key not found: "+key);
	}
	
	private boolean isEmpty(String s)
	{return s==null || s.trim().equals("");}
}
