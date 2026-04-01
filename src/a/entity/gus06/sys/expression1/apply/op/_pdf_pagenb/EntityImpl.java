package a.entity.gus06.sys.expression1.apply.op._pdf_pagenb;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190201";}


	private Service findNb;
	
	public EntityImpl() throws Exception
	{
		findNb = Outside.service(this,"gus06.file.pdf.lowagie.pdfpage.number");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File)
		{
			File file = (File) obj;
			return file.isFile()?findNb.t(file):null;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
