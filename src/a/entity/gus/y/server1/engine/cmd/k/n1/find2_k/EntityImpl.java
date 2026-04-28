package a.entity.gus.y.server1.engine.cmd.k.n1.find2_k;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260427";}

	private Service findInfos;
	private Service findTags;
	private Service findLinksUp;
	private Service findLinksDown;
	private Service engine;

	public EntityImpl() throws Exception
	{
		findInfos = Outside.service(this, "gus.y.knowledgedb1.knowledge.find");
		findTags = Outside.service(this, "gus.y.knowledgedb1.knowledge_tag.find1");
		findLinksUp = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.find1");
		findLinksDown = Outside.service(this, "gus.y.knowledgedb1.knowledge_link.find2");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		if(list.size() != 1) throw new Exception("Invalid arg number: "+list.size());
		
		Long id = Long.parseLong(""+list.get(0));
		
		Map infos = (Map) findInfos.t(new Object[]{cx(), id});
		List tags = sort((Set) findTags.t(new Object[]{cx(), id}));
		List up = (List) findLinksUp.t(new Object[]{cx(), id});
		List down = (List) findLinksDown.t(new Object[]{cx(), id});
		
		infos.put("tags",tags);
		infos.put("up",up);
		infos.put("down",down);
		
		return infos;
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}
	
	private List sort(Set set)
	{
		List list = new ArrayList(set);
		Collections.sort(list);
		return list;
	}
}
