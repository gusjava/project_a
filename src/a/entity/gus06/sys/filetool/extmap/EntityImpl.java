package a.entity.gus06.sys.filetool.extmap;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20150324";}

	public static final String EXT_APPMONITORING1 = "gus.sys.filetool.ext.appmonitoring1";
	public static final String EXT_BASE1 = "gus.sys.filetool.ext.base1";
	public static final String EXT_BASE2 = "gus.sys.filetool.ext.base2";
	public static final String EXT_CHATGPT1 = "gus.sys.filetool.ext.chatgpt";
	public static final String EXT_CONSOLE = "gus.sys.filetool.ext.console";
	public static final String EXT_DBVIEWER1 = "gus.sys.filetool.ext.dbviewer1";
	public static final String EXT_DESKTOP1 = "gus.sys.filetool.ext.desktop1";
	public static final String EXT_DIRDOUBLOONVIEWER1 = "gus.sys.filetool.ext.dirdoubloonviewer1";
	public static final String EXT_DIREXTVIEWER1 = "gus.sys.filetool.ext.dirextviewer1";
	public static final String EXT_DIRSIZEVIEWER1 = "gus.sys.filetool.ext.dirsizeviewer1";
	public static final String EXT_DIRWORDVIEWER1 = "gus.sys.filetool.ext.dirwordviewer1";
	public static final String EXT_ENTITYEDITOR1 = "gus.sys.filetool.ext.entityeditor1";
	public static final String EXT_ENTITYIMPORTER1 = "gus.sys.filetool.ext.entityimporter1";
	public static final String EXT_ENTITYSRCVIEWER1 = "gus.sys.filetool.ext.entitysrcviewer1";
	public static final String EXT_FILEMANAGEMENT1 = "gus.sys.filetool.ext.filemanagement1";
	public static final String EXT_GITVIEWER1 = "gus.sys.filetool.ext.gitviewer1";
	public static final String EXT_GUIBUILDER1 = "gus.sys.filetool.ext.guibuilder1";
	public static final String EXT_GUSVAULT = "gus.sys.filetool.ext.gusvault";
	public static final String EXT_HDDMANAGEMENT1 = "gus.sys.filetool.ext.hddmanagement1";
	public static final String EXT_IDEA1 = "gus.sys.filetool.ext.idea1";
	public static final String EXT_JAVACOMPILER1 = "gus.sys.filetool.ext.javacompiler1";
	public static final String EXT_JAVAPROJECT1 = "gus.sys.filetool.ext.javaproject1";
	public static final String EXT_JAVATOOLBOX1 = "gus.sys.filetool.ext.javatoolbox1";
	public static final String EXT_LEARNING1 = "gus.sys.filetool.ext.learning1";
	public static final String EXT_LIBRARY1 = "gus.sys.filetool.ext.library1";
	public static final String EXT_LINGDIR1 = "gus.sys.filetool.ext.lingdir1";
	public static final String EXT_MAIL1 = "gus.sys.filetool.ext.mail1";
	public static final String EXT_MESSENGER1 = "gus.sys.filetool.ext.messenger1";
	public static final String EXT_NOTES1 = "gus.sys.filetool.ext.notes1";
	public static final String EXT_REPARTITION1 = "gus.sys.filetool.ext.repartition1";
	public static final String EXT_RUNTASK1 = "gus.sys.filetool.ext.runtask1";
	public static final String EXT_SCRIPTLAUNCHER1 = "gus.sys.filetool.ext.scriptlauncher1";
	public static final String EXT_SCRIPTTESTING1 = "gus.sys.filetool.ext.scripttesting1";
	public static final String EXT_SEARCH1 = "gus.sys.filetool.ext.search1";
	public static final String EXT_SOCKET1 = "gus.sys.filetool.ext.socket1";
	public static final String EXT_SPLITPANE = "gus.sys.filetool.ext.splitpane";
	public static final String EXT_SSH = "gus.sys.filetool.ext.ssh";
	public static final String EXT_SYMFONY1 = "gus.sys.filetool.ext.symfony1";
	public static final String EXT_TEXTNAV1 = "gus.sys.filetool.ext.textnav1";
	public static final String EXT_TOOLMANAGER = "gus.sys.filetool.ext.toolmanager";
	public static final String EXT_WEBSERVER1 = "gus.sys.filetool.ext.webserver1";
	public static final String EXT_MAVEN1 = "gus.sys.filetool.ext.maven1";
	public static final String EXT_OPENROUTER = "gus.sys.filetool.ext.openrouter";
	public static final String EXT_SPREADSHEET1 = "gus.sys.filetool.ext.spreadsheet1";
	public static final String EXT_COMPTA1 = "gus.sys.filetool.ext.compta1";
	
	
	public static final String[] LIST = new String[]{
		EXT_APPMONITORING1,
		EXT_BASE1,
		EXT_BASE2,
		EXT_CHATGPT1,
		EXT_CONSOLE,
		EXT_DBVIEWER1,
		EXT_DESKTOP1,
		EXT_DIRDOUBLOONVIEWER1,
		EXT_DIREXTVIEWER1,
		EXT_DIRSIZEVIEWER1,
		EXT_DIRWORDVIEWER1,
		EXT_ENTITYEDITOR1,
		EXT_ENTITYIMPORTER1,
		EXT_ENTITYSRCVIEWER1,
		EXT_FILEMANAGEMENT1,
		EXT_GITVIEWER1,
		EXT_GUIBUILDER1,
		EXT_GUSVAULT,
		EXT_HDDMANAGEMENT1,
		EXT_IDEA1,
		EXT_JAVACOMPILER1,
		EXT_JAVAPROJECT1,
		EXT_JAVATOOLBOX1,
		EXT_LEARNING1,
		EXT_LIBRARY1,
		EXT_LINGDIR1,
		EXT_MAIL1,
		EXT_MESSENGER1,
		EXT_NOTES1,
		EXT_REPARTITION1,
		EXT_RUNTASK1,
		EXT_SCRIPTLAUNCHER1,
		EXT_SCRIPTTESTING1,
		EXT_SEARCH1,
		EXT_SOCKET1,
		EXT_SPLITPANE,
		EXT_SSH,
		EXT_SYMFONY1,
		EXT_TEXTNAV1,
		EXT_TOOLMANAGER,
		EXT_WEBSERVER1,
		EXT_MAVEN1,
		EXT_OPENROUTER,
		EXT_SPREADSHEET1,
		EXT_COMPTA1
	};
	
	
	private Map map;

	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("toolmanager",EXT_TOOLMANAGER);
		put("console",EXT_CONSOLE);
		put("search",EXT_SEARCH1);
		put("base",EXT_BASE1);
		put("base2",EXT_BASE2);
		put("symfony",EXT_SYMFONY1);
		put("repartition",EXT_REPARTITION1);
		put("library",EXT_LIBRARY1);
		put("idea",EXT_IDEA1);
		put("scriptlauncher",EXT_SCRIPTLAUNCHER1);
		put("scripttesting",EXT_SCRIPTTESTING1);
		put("appmonitoring",EXT_APPMONITORING1);
		put("javatoolbox",EXT_JAVATOOLBOX1);
		put("javacompiler",EXT_JAVACOMPILER1);
		put("javaproject",EXT_JAVAPROJECT1);
		put("dbviewer",EXT_DBVIEWER1);
		put("dirsizeviewer",EXT_DIRSIZEVIEWER1);
		put("dirextviewer",EXT_DIREXTVIEWER1);
		put("dirwordviewer",EXT_DIRWORDVIEWER1);
		put("dirdoubloonviewer",EXT_DIRDOUBLOONVIEWER1);
		put("webserver",EXT_WEBSERVER1);
		put("guibuilder",EXT_GUIBUILDER1);
		put("lingdir",EXT_LINGDIR1);
		put("ssh",EXT_SSH);
		put("desktop",EXT_DESKTOP1);
		put("entityeditor1",EXT_ENTITYEDITOR1);
		put("entitysrcviewer1",EXT_ENTITYSRCVIEWER1);
		put("entityimporter1",EXT_ENTITYIMPORTER1);
		put("filemanagement1",EXT_FILEMANAGEMENT1);
		put("hddmanagement1",EXT_HDDMANAGEMENT1);
		put("gitviewer1",EXT_GITVIEWER1);
		put("splitpane",EXT_SPLITPANE);
		put("gusvault",EXT_GUSVAULT);
		put("mail1",EXT_MAIL1);
		put("notes1",EXT_NOTES1);
		put("textnav1",EXT_TEXTNAV1);
		put("socket1",EXT_SOCKET1);
		put("chatGPT1",EXT_CHATGPT1);
		put("messenger1",EXT_MESSENGER1);
		put("learning1",EXT_LEARNING1);
		put("maven1",EXT_MAVEN1);
		put("openrouter",EXT_OPENROUTER);
		put("spreadsheet1",EXT_SPREADSHEET1);
		put("compta1",EXT_COMPTA1);
	}
	
	
	
	
	
	public Object g() throws Exception
	{return LIST;}
	
	
	public Object r(String key) throws Exception
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private void put(String key, String value)
	{map.put(key,value);}
}