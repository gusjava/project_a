package a.entity.gus.y.knowledgesys1.prompt;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

		Map root = (Map) rootfinder.t(keywords);
		if (root == null) return new ArrayList();

		List cluster = (List) rootexpander.t(root);
		
		String description = (String) json.get("description");
		String preprocessorEntity = (String) root.get("preprocessor");
		Map preInput = new LinkedHashMap();
		preInput.put("preprocessor_entity", preprocessorEntity);
		preInput.put("description", description);
		
		Map preOutput = (Map) preprocessor.t(preInput);
		List extraCodes = (List) preOutput.get("codes");

		Map resultInput = new LinkedHashMap();
		resultInput.put("cluster", cluster);
		resultInput.put("extra_codes", extraCodes);
		return result.t(resultInput);
	}
}