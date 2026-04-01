package a.entity.gus06.swing.tree.perform.file.search.fromdir;

import a.framework.*;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220207";}


	private Service isHexaMD5;
	private Service handle;
	
	private Service buildFilterStar;
	private Service buildFilterStarN;
	private Service buildFilterContains;
	private Service buildFilterContainsN;
	
	private Service buildFileFilterFromMd5;
	private Service buildFileFilterFromName;
	private Service buildFileFilterFromContent;
	private Service buildFileFilterAdvanced;
	private Service buildFileFilterGlobal;
	



	public EntityImpl() throws Exception
	{
		isHexaMD5 = Outside.service(this,"gus06.filter.string.is.hexa.md5");
		handle = Outside.service(this,"gus06.swing.tree.perform.file.search.handle");
		
		buildFilterStar = Outside.service(this,"gus06.filter.string.build.mstars2");
		buildFilterStarN = Outside.service(this,"gus06.filter.string.build.mstars2_n");
		buildFilterContains = Outside.service(this,"gus06.filter.string.build.contains");
		buildFilterContainsN = Outside.service(this,"gus06.filter.string.build.contains_n");
		
		buildFileFilterFromMd5 = Outside.service(this,"gus06.file.buildfilter.md5");
		buildFileFilterFromName = Outside.service(this,"gus06.file.buildfilter.namefilter");
		buildFileFilterFromContent = Outside.service(this,"gus06.file.buildfilter.txtcontentfilter");
		buildFileFilterAdvanced = Outside.service(this,"gus06.file.buildfilter.advanced");
		buildFileFilterGlobal = Outside.service(this,"gus06.file.buildfilter.global");
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map search = (Map) o[0];
		List roots = (List) o[1];
		
		String exp = getExpression();
		if(exp==null || exp.equals("")) return;
		
		F filter = buildFilter(exp, roots);
		for(int i=0;i<roots.size();i++)
		{
			File root = (File) roots.get(i);
			handle.p(new Object[]{search, root, filter});
		}
	}
	
	
	private F buildFilter(String exp, List roots) throws Exception
	{
		if(exp.startsWith("�"))
		{
			return (F) buildFileFilterGlobal.t(new Object[]{exp.substring(1), roots});
		}
		if(exp.startsWith(">"))
		{
			return (F) buildFileFilterAdvanced.t(exp.substring(1));
		}
		if(exp.startsWith("&!")) 
		{
			F stringFilter = (F) buildFilterContains.t(exp.substring(2));
			return (F) buildFileFilterFromContent.t(stringFilter);
		}
		if(exp.startsWith("&")) 
		{
			F stringFilter = (F) buildFilterContainsN.t(exp.substring(1));
			return (F) buildFileFilterFromContent.t(stringFilter);
		}
		if(exp.startsWith("!")) 
		{
			F stringFilter = (F) buildFilterStar.t(exp.substring(1));
			return (F) buildFileFilterFromName.t(stringFilter);
		}
		if(isHexaMD5.f(exp))
		{
			return (F) buildFileFilterFromMd5.t(exp);
		}
		
		F stringFilter = (F) buildFilterStarN.t(exp);
		return (F) buildFileFilterFromName.t(stringFilter);
	}
	
	
	
	private String getExpression()
	{
           	return JOptionPane.showInputDialog(null,"Please, type a query:",
		"File search",JOptionPane.PLAIN_MESSAGE);
	}
}