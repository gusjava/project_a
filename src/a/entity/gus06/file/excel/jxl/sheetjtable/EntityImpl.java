package a.entity.gus06.file.excel.jxl.sheetjtable;

import a.framework.*;
import jxl.Sheet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150511";}

	public Object t(Object obj) throws Exception
	{return new SheetJTable((Sheet)obj);}
}
