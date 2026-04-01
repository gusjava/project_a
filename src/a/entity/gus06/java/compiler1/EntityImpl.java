package a.entity.gus06.java.compiler1;

import a.framework.*;
import java.io.File;

public class EntityImpl extends S1 implements Entity, V, P, E {

	public String creationDate() {return "20140725";}

	private Service findCompiler;

	private File srcDir;
	private File binDir;
	private File libDir;


	public EntityImpl() throws Exception
	{
		findCompiler = Outside.service(this,"gus06.java.compiler.vx");

		srcDir = (File) Outside.resource(this,"path#path.dev.srcdir");
		binDir = (File) Outside.resource(this,"path#path.dev.bindir");
		libDir = (File) Outside.resource(this,"path#path.dev.libdir");
	}
	
	
	public void e() throws Exception
	{
		Object compiler = findCompiler.g();
		compile(compiler,null);
	}


	public void p(Object obj) throws Exception
	{
		Object compiler = findCompiler.g();
		compile(compiler,(F) obj);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		Object compiler = findCompiler.r(key);
		compile(compiler,(F) obj);
	}



	private void compile(Object compiler, F filter) throws Exception
	{
		start();
		
		try
		{
			((V) compiler).v("srcDir",srcDir);
			((V) compiler).v("binDir",binDir);
			((V) compiler).v("libDir",libDir);
			((V) compiler).v("filter",filter);
			((E) compiler).e();
		}
		finally {end();}
	}


	
	
	private void start()
	{send(this,"start()");}
	
	private void end()
	{send(this,"end()");}
}
