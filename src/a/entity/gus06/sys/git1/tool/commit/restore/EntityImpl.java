package a.entity.gus06.sys.git1.tool.commit.restore;

import a.framework.*;
import java.util.Map;
import java.io.File;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201211";}


	private Service writeFile;

	public EntityImpl() throws Exception
	{
		writeFile = Outside.service(this,"gus06.file.write.string.autodetect");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List commits = (List) o[0];
		Map commit = (Map) o[1];
		
		if(commits.isEmpty()) throw new Exception("empty commits list");
		Map commit0 = (Map) commits.get(0);
		
		if(!commit.containsKey("src")) throw new Exception("src key not found inside commit map");
		String src = (String) commit.get("src");
		
		updateCommit(commit0,src);
	}
	
	
	
	private void updateCommit(Map m, String src) throws Exception
	{
		if(!m.containsKey("file")) throw new Exception("file key not found inside commit map");
		
		File file = (File) m.get("file");
		writeFile.p(new Object[]{file,src});
		m.put("src",src);
	}
}
