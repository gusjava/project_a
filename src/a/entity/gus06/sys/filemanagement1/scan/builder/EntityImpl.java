package a.entity.gus06.sys.filemanagement1.scan.builder;

import a.framework.*;
import java.util.Date;
import java.util.Map;
import java.io.File;
import java.io.PrintStream;
import javax.swing.JLabel;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191127";}
	
	public static final String KEY_STARTTIME = "STARTTIME";
	public static final String KEY_DURATION = "DURATION";
	public static final String KEY_FILENB = "FILENB";
	public static final String KEY_SPACE = "SPACE";
	public static final String KEY_ERROR = "ERROR";
	
	public static final String CHARSET = "UTF-8";
	


	private Service buildTimeStamp;
	private Service handleFile;
	private Service findPrevious;
	private Service readPrevious;
	private Service mapToDir;
	private Service buildRow;
	private Service printlnRow;
	private Service moveFile;
	private Service buildConsole;
	private Service emptyDir;
	private Service findAborted;
	private Service getModified;
	private Service onComplete;

	public EntityImpl() throws Exception
	{
		buildTimeStamp = Outside.service(this,"gus06.time.date.yyyymmdd_hhmmss");
		handleFile = Outside.service(this,"gus06.sys.filemanagement1.scan.handlefile");
		findPrevious = Outside.service(this,"gus06.sys.filemanagement1.scan.previous.find");
		readPrevious = Outside.service(this,"gus06.sys.filemanagement1.scan.previous.read");
		mapToDir = Outside.service(this,"gus06.sys.filemanagement1.tool.rootmap.maptodir");
		buildRow = Outside.service(this,"gus06.sys.filemanagement1.scan.build.row");
		printlnRow = Outside.service(this,"gus06.io.printstream.println.row.tab");
		moveFile = Outside.service(this,"gus06.file.op.move");
		buildConsole = Outside.service(this,"gus06.sys.filemanagement1.scan.builder.buildconsole");
		emptyDir = Outside.service(this,"gus06.dir.op.empty");
		findAborted = Outside.service(this,"gus06.sys.filemanagement1.scan.builder.findaborted");
		getModified = Outside.service(this,"gus06.file.lastmodifiedtime.timestamp.s");
		onComplete = Outside.service(this,"gus06.sys.filemanagement1.scan.builder.oncomplete");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		String rootName = (String) o[1];
		
		return new Scanner(engine,rootName);
	}
	
	
	
	
	private class Scanner extends S1 implements Runnable, R
	{
		private Object engine;
		private Object access;
		private File dirPreviews;
		private File dirProps;
		
		private String rootName;
		private File rootDir;
		private int rootOffset;
		private Map map;
		
		
		
		private Exception e;
		
		private Date startDate;
		private long duration;
		
		private PrintStream p;
		private PrintStream out;
		private JLabel labelOut;
		
		private int fileNb = 0;
		private long space = 0;
		private String fileName;
		private Map previousMap;
		
		private File previousFile;
		private File currentFileT;
		private File currentFileF;
		private File abortedFile;
		
		
		
		private Object r1(String key) throws Exception
		{return ((R) engine).r(key);}
		
		private void v1(String key, Object obj) throws Exception
		{((V) access).v(key,obj);}
		
		private void loadMap() throws Exception
		{map = (Map) ((R) access).r(rootName);}
		
		private void storeMap() throws Exception
		{((V) access).v(rootName,map);}
		
		private String buildTimeStamp(Date date) throws Exception
		{return (String) buildTimeStamp.t(date);}
		
		
		
		public Scanner(Object engine, String rootName) throws Exception
		{
			this.engine = engine;
			this.rootName = rootName;
			
			access = r1("accessRoots");
			dirPreviews = (File) r1("dirPreviews");
			dirProps = (File) r1("dirProps");
			
			loadMap();
			if(map==null) throw new Exception("Failed to load rootMap for "+rootName);
		}
		
		public void run()
		{
			try
			{
				if(startDate!=null) return;
				
				init();
				started();
				perform();
				complete();
				done();
			}
			catch(Exception e)
			{handleFailed(e);}
		}
		
		
		
		
		private void handleFailed(Exception e)
		{
			this.e = e;
			if(out!=null)
			{
				out.println("ERROR OCCURED");
				e.printStackTrace(out);
				out.close();
				out = null;
			}
			map.put(KEY_ERROR,e.toString());
			try{storeMap();}catch(Exception e1){}
			failed();
		}
		
		
		
		
		
		private void init() throws Exception
		{
			rootDir = (File) mapToDir.t(map);
			if(rootDir==null) throw new Exception("Root dir is not available");
			if(!rootDir.isDirectory()) throw new Exception("Root dir does not exist: "+rootDir);
			rootOffset = rootDir.getAbsolutePath().length();
			
			duration = -1;
			fileNb = 0;
			space = 0;
			
			abortedFile = null;
			previousFile = null;
			previousMap = null;
			
			startDate = new Date();
			String timeStamp = buildTimeStamp(startDate);
			fileName = timeStamp+".txt";
			
			map.put(KEY_STARTTIME,timeStamp);
			map.put(KEY_DURATION,"");
			map.put(KEY_FILENB,"");
			map.put(KEY_SPACE,"");
			map.put(KEY_ERROR,"");
			
			storeMap();
			
			R console = (R) buildConsole.t("SCAN: "+rootName);
			
			out = (PrintStream) console.r("printstream");
			labelOut = (JLabel) console.r("label");
			
			out.println("Scanning: "+rootName);
			out.println("Root dir: "+rootDir);
			out.println("Start time: "+timeStamp);
			out.println();
		}
		
		
		
		
		
		private void perform() throws Exception
		{
			File dir0 = (File) r1("dirScans");
			File dir1 = new File(dir0,rootName);
			File dir2 = new File(dir1,"running");
			
			dir2.mkdirs();
			
			abortedFile = (File) findAborted.t(dir2);
			previousFile = (File) findPrevious.t(dir1);
			
			if(abortedFile!=null)
			out.println("aborted file: "+abortedFile);
			
			if(previousFile!=null)
			out.println("previous file: "+previousFile);
			
			currentFileT = new File(dir2,fileName);
			currentFileF = new File(dir1,fileName);
			
			out.println("temporary file: "+currentFileT);
			out.println("final file: "+currentFileF);
			out.println();
			
			previousMap = (Map) readPrevious.t(previousFile);
			
			if(abortedFile!=null)
			{
				Map abortedMap = (Map) readPrevious.t(abortedFile);
				previousMap.putAll(abortedMap);
			}
			
			try
			{
				p = new PrintStream(currentFileT,CHARSET);
				handleRootDir();
			}
			finally
			{if(p!=null) p.close();}
			
			abortedFile = null;
			previousFile = null;
			previousMap = null;
			
			moveFile.p(new File[]{currentFileT,currentFileF});
			if(!currentFileF.isFile()) throw new Exception("Failed to move temp file: "+currentFileT);
			emptyDir.p(dir2);
		}
		
		
		
		private void complete() throws Exception
		{
			duration = System.currentTimeMillis()-startDate.getTime();
			
			map.put(KEY_DURATION,""+duration);
			map.put(KEY_FILENB,""+fileNb);
			map.put(KEY_SPACE,""+space);
			
			storeMap();
			
			out.println("-------------");
			out.println("Scan complete");
			out.println("- report file: "+currentFileF);
			out.println("- line nb: "+fileNb);
			out.println("- space: "+space);
			out.close();
			
			out = null;
			
			onComplete.p(engine);
			((V)engine).v("scanCompleted",null);
		}
		
		
		
		
		private void handleRootDir() throws Exception
		{
			File[] ff = rootDir.listFiles();
			if(ff!=null) for(File f : ff)
			{
				if(f.isDirectory()) handleDir(f);
				else if(f.isFile()) handleFile(f);
			}
		}
		
		
		private void handleDir(File dir) throws Exception
		{
			File[] ff = dir.listFiles();
			if(ff==null || ff.length==0) handleDirEmpty(dir);
			
			if(ff!=null) for(File f : ff)
			{
				if(f.isDirectory()) handleDir(f);
				else if(f.isFile()) handleFile(f);
			}
		}
		
		
		private void handleDirEmpty(File dir) throws Exception
		{
			String location = dir.getParentFile().getAbsolutePath().substring(rootOffset);
			String name = dir.getName();
			String modified = (String) getModified.t(dir);
			
			String[] row = new String[]{location,name,"",modified,"",""};
			printlnRow.p(new Object[]{p,row});
		}
		
		
		private void handleFile(File file) throws Exception
		{
			String[] row = (String[]) buildRow.t(new Object[]{file,rootOffset,previousMap,fileNb});
			boolean dataCreated = handleFile.f(new Object[]{engine,file,row,labelOut});
			
			printlnRow.p(new Object[]{p,row});
			if(dataCreated) out.println(fileNb+": "+file.getAbsolutePath());
			
			fileNb++;
			long fileSize = Long.parseLong(row[2]);
			space+=fileSize;
		}
		
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("exception")) return e;
			if(key.equals("rootName")) return rootName;
			if(key.equals("rootDir")) return rootDir;
			if(key.equals("startDate")) return startDate;
			if(key.equals("duration")) return duration!=-1 ? Long.valueOf(duration) : null;
			if(key.equals("fileNb")) return fileNb;
			if(key.equals("space")) return space;
			
			if(key.equals("keys")) return new String[]{
				"exception","rootName","rootDir","startDate","duration","fileNb","space"};
			
			throw new Exception("Unknown key: "+key);
		}
		
		private void started()
		{send(this,"started()");}
		
		private void done()
		{send(this,"done()");}
		
		private void failed()
		{send(this,"failed()");}
	}
}