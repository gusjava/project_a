package a.entity.gus06.sys.git1.tool.src.normalizer.xhtml;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201202";}


	private Service indentXhtml;
	private Service convertTab;
	private Service removeBlank;
	private Service reduceTags;
	
	public EntityImpl() throws Exception
	{
		indentXhtml = Outside.service(this,"gus06.string.transform.xhtml.indent");
		convertTab = Outside.service(this,"gus06.string.transform.indent.convert.tabtospace4");
		removeBlank = Outside.service(this,"gus06.string.transform.line.remove.blank");
		reduceTags = Outside.service(this,"gus06.string.transform.xhtml.jsf.reducetags");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		
		src = (String) indentXhtml.t(src);
		src = (String) reduceTags.t(src);
		src = (String) removeBlank.t(src);
		src = (String) convertTab.t(src);
		
		return src;
	}
}
