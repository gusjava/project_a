package a.entity.gus06.file.info.pagenumber.asstring;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151006";}


	private Service pageNumber;
	
	
	public EntityImpl() throws Exception
	{
		pageNumber = Outside.service(this,"gus06.file.info.pagenumber");
	}



	public Object t(Object obj) throws Exception
	{
		Integer nb = (Integer) pageNumber.t(obj);
		return nb!=null?""+nb:"";
	}
}
