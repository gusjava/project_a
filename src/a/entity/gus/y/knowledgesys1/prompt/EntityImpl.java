package a.entity.gus.y.knowledgesys1.prompt;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260503";}

	private Service rootfinder;
	private Service rootexpander;
	private Service preprocessor;
	private Service result;

	public EntityImpl() throws Exception
	{
		rootfinder = Outside.service(this, "gus.y.knowledgesys1.prompt.rootfinder");
		rootexpander = Outside.service(this, "gus.y.knowledgesys1.prompt.rootexpander");
		preprocessor = Outside.service(this, "gus.y.knowledgesys1.prompt.preprocessor");
		result = Outside.service(this, "gus.y.knowledgesys1.prompt.result");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		List keywords = (List) json.get("keywords");
		String description = (String) json.get("description");

		Map root = (Map) rootfinder.t(keywords);
		if (root == null) return new ArrayList();

		List cluster = (List) rootexpander.t(root);
		List extraCodes = (List) preprocessor.t(new Object[]{root, description});
		return result.t(new Object[]{cluster, extraCodes});
	}
}