package a.entity.gus06.file.pdf.infomap;

import a.framework.*;
import java.util.Map;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191219";}

	public static final String PAGENB = "PAGENB";

	private Service getPdfPageNb;
	private Service getPdfProps;

	public EntityImpl() throws Exception
	{
		getPdfPageNb = Outside.service(this,"gus06.file.pdf.lowagie.pdfpage.number");
		getPdfProps = Outside.service(this,"gus06.file.pdf.pdfbox.extract.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map infoMap = (Map) getPdfProps.t(file);
		
		if(!infoMap.containsKey(PAGENB))
		{
			Integer pageNb = (Integer) getPdfPageNb.t(file);
			if(pageNb!=null) infoMap.put(PAGENB,""+pageNb);
		}
		return infoMap;
	}
}