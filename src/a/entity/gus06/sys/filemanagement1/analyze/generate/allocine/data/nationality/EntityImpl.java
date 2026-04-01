package a.entity.gus06.sys.filemanagement1.analyze.generate.allocine.data.nationality;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20210201";}


	private Service emptyDir;
	private Service generateSeq;
	private Service generateFull;
	private Service generateMd5;
	
	public EntityImpl() throws Exception
	{
		emptyDir = Outside.service(this,"gus06.dir.perform.empty");
		generateSeq = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen2.allocine.sequence");
		generateFull = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen2.allocine.full");
		generateMd5 = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen2.allocine.codemd5");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		// GENERATED DIR
		
		File dirGen = (File) ((R) engine).r("dirGenerated_allocine");
		emptyDir.p(dirGen);
		
		// ALLOCINE DIRS
		
		File dirAllocine = (File) ((R) engine).r("dirAllocine");
		File propDir = new File(dirAllocine,"code_prop");
		File md5Dir = new File(dirAllocine,"md5_code");
		
		propDir.mkdirs();
		md5Dir.mkdirs();
		
		File[] ff1 = md5Dir.listFiles();
		if(ff1!=null)
		{
			p.println("CODE -> MD5");
			Map codesMap = (Map) generateMd5.t(new Object[]{dirGen,ff1,"code_md5"});
			Set codes = codesMap.keySet();
			
			File[] ff2 = propDir.listFiles();
			if(ff2!=null)
			{
				p.println("NATIONALITY -> CODE");
				generateSeq.p(new Object[]{dirGen,ff2,codes,"nationality_code","nationality"});
			}
		}
		p.println("______________");
		
		((V)engine).v("allocineGenerated",null);
	}
}