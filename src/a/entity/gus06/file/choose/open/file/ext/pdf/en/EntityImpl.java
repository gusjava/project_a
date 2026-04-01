package a.entity.gus06.file.choose.open.file.ext.pdf.en;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150616";}

	public static final String EXTENSION = "pdf";
	public static final String FILEDESC = "PDF files";

	private Service builder;
	private G g;


	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.file.choose.open.file.builder.ext");
		g = (G) builder.t(new String[]{EXTENSION,FILEDESC});
	}
	
	public Object g() throws Exception
	{return g.g();}
}
