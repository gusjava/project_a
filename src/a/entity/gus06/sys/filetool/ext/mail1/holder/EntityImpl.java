package a.entity.gus06.sys.filetool.ext.mail1.holder;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.awt.Color;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201112";}
	
	public static final String KEY_MAIL_TYPE = "mail.type";
	
	
	private Service findRoot;
	private Service findFile;
	private Service mainGui;
	private Service loginPwdBuilder;
	
	private Map map;
	private File root;
	private File file;
	private Object loginPwd;
	private String type;

	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		findFile = Outside.service(this,"gus06.sys.filetool.findfile");
		mainGui = Outside.service(this,"*gus06.sys.mailclient1.gui.maingui");
		loginPwdBuilder = Outside.service(this,"gus06.sys.loginpwd1.persister.propfile");
	}
	
	
	public Object i() throws Exception
	{return mainGui.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		root = (File) findRoot.t(map);
		file = (File) findFile.t(map);
		loginPwd = loginPwdBuilder.t(file);
		type = (String) get(KEY_MAIL_TYPE);
		
		mainGui.v("root",root);
		mainGui.v("loginPwd",loginPwd);
		mainGui.v("type",type);
	}
	
	
	
	private String get(String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
}