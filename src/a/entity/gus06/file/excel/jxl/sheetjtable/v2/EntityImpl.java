package a.entity.gus06.file.excel.jxl.sheetjtable.v2;

import a.framework.*;
import jxl.Sheet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150522";}

	public Object t(Object obj) throws Exception
	{return new SheetJTable((Sheet)obj);}
}
