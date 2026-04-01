package a.entity.gus06.command.explore;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140704";}


	private Service appJar;
	private Service printObject;
	private Service md5Hexa;
	private Service parseArgs;
	private Service rebuildPath;
	private Service readFile;
	private Service copyFile;
	
	private File dir;
	private File dir0;
	
	
	public EntityImpl() throws Exception
	{
		appJar = Outside.service(this,"gus06.app.jarfile");
		printObject = Outside.service(this,"gus06.print.object");
		md5Hexa = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		parseArgs = Outside.service(this,"gus06.command.explore.parseargs");
		rebuildPath = Outside.service(this,"gus06.file.absolute.rebuild");
		readFile = Outside.service(this,"gus06.file.read.string");
		copyFile = Outside.service(this,"gus06.file.op.copy");
		
		File file = (File) appJar.g();
		dir0 = file.getParentFile();
	}
	
	
	public void p(Object obj) throws Exception
	{
		String cmd = (String) obj;
		
		if(!cmd.contains(" ") && cmd.endsWith(".."))
			cmd = cmd.substring(0,cmd.length()-2)+" ..";
		
		if(dir==null) dir = dir0;
		
		String[] n = cmd.split(" ",2);
		String op = n[0];
		String info = n.length==2?n[1]:null;
		String[] args = (String[]) parseArgs.t(info);
		
		if(op.equals("reset")) {op_reset();return;}
		if(op.equals("pwd")) {op_pwd(args);return;}
		if(op.equals("info")) {op_info(args);return;}
		if(op.equals("dir")) {op_dir(args);return;}
		if(op.equals("ls")) {op_dir(args);return;}
		if(op.equals("cd")) {op_cd(args);return;}
		if(op.equals("mkdir")) {op_mkdir(args);return;}
		if(op.equals("mkfile")) {op_mkfile(args);return;}
		if(op.equals("rm")) {op_rm(args);return;}
		if(op.equals("remove")) {op_rm(args);return;}
		if(op.equals("del")) {op_rm(args);return;}
		if(op.equals("mv")) {op_mv(args);return;}
		if(op.equals("move")) {op_mv(args);return;}
		if(op.equals("cp")) {op_cp(args);return;}
		if(op.equals("copy")) {op_cp(args);return;}
		if(op.equals("rename")) {op_mv(args);return;}
		if(op.equals("md5")) {op_md5(args);return;}
		if(op.equals("read")) {op_read(args);return;}
		if(op.equals("path")) {op_path(args);return;}
		
		throw new Exception("Unsupported explore command: "+cmd);
	}
	
	
	
	private void op_reset()
	{dir = dir0;}
	
	
	private void op_pwd(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		print(rebuildPath(path));
	}
	
	
	
	private void op_info(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		print(info(path));
	}
	
	
	private void op_dir(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		File[] ff = path.listFiles();
		for(File f:ff) print(getName(f));
	}
	
	
	private void op_cd(String[] args) throws Exception
	{
		if(args==null) args = new String[] {"#0"};
		File path = choosePath(args,0,1);
		if(path.isDirectory()) dir = path;
		else print("Directory not found: "+path);
	}
	
	
	private void op_mkdir(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		if(!path.exists()) path.mkdir();
	}
	
	
	private void op_mkfile(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		if(!path.exists()) path.createNewFile();
	}

	
	
	private void op_rm(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		if(!path.exists()) return;
		boolean r = path.delete();
		if(!r) print("failed to delete: "+path);
	}
	
	
	private void op_mv(String[] args) throws Exception
	{
		File path1 = choosePath(args,0,2);
		File path2 = choosePath(args,1,2);
		
		if(!path1.exists()) print("input not found: "+path1);
		if(path2.exists()) print("output already exists: "+path2);
		
		boolean r = path1.renameTo(path2);
		if(!r) print("failed to move: "+path1+" to "+path2);
	}
	
	
	private void op_cp(String[] args) throws Exception
	{
		File path1 = choosePath(args,0,2);
		File path2 = choosePath(args,1,2);
		
		if(!path1.exists()) print("input not found: "+path1);
		if(path2.exists()) print("output already exists: "+path2);
		
		copyFile.p(new File[]{path1,path2});
	}

	
	
	private void op_md5(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		print(md5(path));
	}
	
	
	private void op_read(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		print(read(path));
	}
	
	
	private void op_path(String[] args) throws Exception
	{
		File path = choosePath(args,0,1);
		print(path.getAbsolutePath());
	}



	
	
	
	
	

	private void print(Object obj) throws Exception
	{printObject.p(obj);}
	
	
	
	private String info(File path)
	{
		if(path.isDirectory()) return "directory ["+path.list().length+"]";
		if(path.isFile()) return "file ["+path.length()+"]";
		return "does not exist";
	}
	
	
	private String md5(File file) throws Exception
	{return (String) md5Hexa.t(file);}
	
	
	private String read(File file) throws Exception
	{return (String) readFile.t(file);}
	
	
	private File rebuildPath(File f) throws Exception
	{return (File) rebuildPath.t(f);}
	
	
	
	private String getName(File f)
	{return f!=null?f.getName():"null";}
	
	
	
	private File choosePath(String[] args, int index, int total) throws Exception
	{
		if(args==null) return dir;
		String arg = check(args,total)[index];
		return choosePath(dir,arg);
	}
	
	
	
	private File choosePath(File dir, String info) throws Exception
	{
		if(info.matches("#[0-9]+"))
		{
			int index = Integer.parseInt(info.substring(1));
			File[] f = dir.listFiles();
			if(index >= f.length) throw new Exception("Invalid file index: "+info);
			return f[index];
		}
		
		File f = new File(info);
		if(f.isAbsolute()) return f;
		return new File(dir,info);
	}
	
	
	
	
	private String[] check(String[] args, int length) throws Exception
	{
		if(args==null) throw new Exception("invalid args number: 0");
		if(args.length!=length) throw new Exception("invalid args number: "+args.length);
		return args;
	}
}
