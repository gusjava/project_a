package a.entity.gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}


	private Service buildForMd5;
	private Service buildForName;
	
	public EntityImpl() throws Exception
	{
		buildForMd5 = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters.formd5");
		buildForName = Outside.service(this,"gus06.sys.filemanagement1.gui.gui1_3.search.buildfilters.forname");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		F rootFilter = null;
		F locationFilter = (F) buildForName.t(input);
		F nameFilter = (F) buildForName.t(input);
		F sizeFilter = null;
		F modifiedFilter = null;
		F md5Filter = (F) buildForMd5.t(input);
		F mimeFilter = null;
		
		return new F[]{
			rootFilter, 
			locationFilter, 
			nameFilter, 
			sizeFilter, 
			modifiedFilter,
			md5Filter,
			mimeFilter};
	}
}