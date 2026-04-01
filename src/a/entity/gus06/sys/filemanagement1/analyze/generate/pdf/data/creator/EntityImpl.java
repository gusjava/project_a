package a.entity.gus06.sys.filemanagement1.analyze.generate.pdf.data.creator;

import a.framework.*;
import java.io.PrintStream;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201110";}


	private Service generate;
	private Service retrieveMd5Set;
	
	public EntityImpl() throws Exception
	{
		generate = Outside.service(this,"gus06.sys.filemanagement1.tool.generate.gen1.full");
		retrieveMd5Set = Outside.service(this,"gus06.sys.filemanagement1.tool.pdf.retrieve.md5set");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object engine = o[0];
		PrintStream p = (PrintStream) o[1];
		
		// GENERATED DIR
		
		File dirGen = (File) ((R) engine).r("dirGenerated_pdf");

		// RETRIEVE MD5 SET
		
		Set md5Set = (Set) retrieveMd5Set.t(engine);
		
		p.println("Detected pdf: "+md5Set.size());
		p.println("______________");
		
		if(md5Set.isEmpty()) return;
		
		p.println("CREATOR -> MD5");
		generate.p(new Object[]{engine,dirGen,md5Set,"creator_md5","pdf.creator",null});
		
		p.println("______________");
		
		((V)engine).v("pdfGenerated",null);
	}
}