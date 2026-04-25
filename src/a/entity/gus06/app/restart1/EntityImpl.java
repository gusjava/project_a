package a.entity.gus06.app.restart1;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150626";}
	
	public static final String LAUNCHER = "launcher";


	private Service copy;
	private Service appJar;
	private Service launchJar;
	private Service exit;

	public EntityImpl() throws Exception
	{
		copy = Outside.service(this,"gus06.file.op.copy.replace");
		appJar = Outside.service(this,"gus06.app.jarfile");
		launchJar = Outside.service(this,"gus06.java.launchjar");
		exit = Outside.service(this,"gus.y.app1.execute.exit");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File out = (File) obj;
		File in = (File) appJar.g();
		
		String in_ = in.getAbsolutePath();
		String out_ = out.getAbsolutePath();
		
		if(in_.equals(out_)) return;
		
		copy.p(new File[]{in,out});
		
		String arg = LAUNCHER+"="+in_;
		launchJar.p(new Object[]{out,arg});
		
		exit.e();
		System.exit(0);
	}
}
