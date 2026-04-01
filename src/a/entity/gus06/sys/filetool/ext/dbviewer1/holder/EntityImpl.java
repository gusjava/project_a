package a.entity.gus06.sys.filetool.ext.dbviewer1.holder;

import a.framework.*;
import java.util.Map;
import javax.swing.text.JTextComponent;
import java.io.File;

public class EntityImpl implements Entity, I, P, Runnable {

	public String creationDate() {return "20161114";}

	public static final String URL = "url";
	public static final String USER = "user";
	public static final String PWD = "pwd";
	public static final String INFOS_BUILDER = "infos_builder";
	public static final String PERSIST_SQL = "persist.sql";
	
	public static final String PATH_MYSQL_ROOTDIR = "path.mysql.rootdir";
	

	private Service findRoot;
	private Service tab;
	private Service builder;
	private Service viewGui;
	private Service userGui;
	private Service sqlGui;
	private Service varGui;
	private Service sqlPersist;
	private Service buildDump;
	private Service fileProvider;
	private Service buildG;
	
	private Map map;
	private Thread t;
	
	private JTextComponent sqlArea;
	


	public EntityImpl() throws Exception
	{
		findRoot = Outside.service(this,"gus06.sys.filetool.findroot");
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		builder = Outside.service(this,"gus06.jdbc.connection.holder");
		viewGui = Outside.service(this,"*gus06.jdbc.gui.cx1");
		userGui = Outside.service(this,"*gus06.jdbc.gui.user1");
		sqlGui = Outside.service(this,"*gus06.jdbc.gui.sqlquery1");
		varGui = Outside.service(this,"*gus06.jdbc.gui.var1");
		sqlPersist = Outside.service(this,"gus06.swing.textcomp.persister.text.tomap");
		buildDump = Outside.service(this,"gus06.sys.mysqltools1.dump.buildholder");
		fileProvider = Outside.service(this,"m102.r.fileprovider");
		buildG = Outside.service(this,"gus06.sys.script1.build2.g");
		
		tab.v("Explorer",viewGui.i());
		tab.v("SQL query",sqlGui.i());
		tab.v("Users",userGui.i());
		tab.v("Variables",varGui.i());
		
		sqlArea = (JTextComponent) sqlGui.r("inputArea");
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		if(t!=null && t.isAlive()) return;
		
		map = (Map) obj;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	
	public void run()
	{
		try
		{
			
			String[] infos = buildInfos();
			File rootDir = (File) findRoot.t(map);
			File mysqlDir = getDir0(PATH_MYSQL_ROOTDIR);
			
			Object cxHolder = builder.t(infos);
			
			viewGui.p(cxHolder);
			sqlGui.p(cxHolder);
			userGui.p(cxHolder);
			varGui.p(cxHolder);
			
			sqlPersist.p(new Object[]{sqlArea,map,PERSIST_SQL});
			
			if(mysqlDir!=null)
			{
				Object dumpHolder = buildDump.t(new Object[]{rootDir, mysqlDir, map});
				viewGui.v("dumpHolder", dumpHolder);
			}
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	
	private String[] buildInfos() throws Exception
	{
		String src = (String) get0(INFOS_BUILDER);
		if(src!=null)
		{
			G g = (G) buildG.t(new Object[]{src,map});
			return (String[]) g.g();
		}
		
		String url = get0(URL);
		if(url==null) return null;
		
		String user = get0(USER);
		if(user==null) return null;
		
		String pwd = get0(PWD);
		if(pwd==null) return null;
		
		return new String[]{url,user,pwd};
	}
	
	
	
	private String get0(String key) throws Exception
	{
		if(map==null) throw new Exception("Map not initialized yet");
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	private File getDir0(String key) throws Exception
	{
		String path = get0(key);
		if(path==null) return null;
		File dir = (File) fileProvider.r(path);
		if(!dir.isDirectory()) return null;
		return dir;
	}
}