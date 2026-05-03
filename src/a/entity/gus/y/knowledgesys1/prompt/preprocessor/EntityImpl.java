package a.entity.gus.y.knowledgesys1.prompt.preprocessor;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260503";}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		String preprocessorEntity = (String) json.get("preprocessor_entity");
		String description = (String) json.get("description");

		Map result = new LinkedHashMap();
		result.put("codes", new ArrayList());
		result.put("description", description == null ? "" : description);

		if (preprocessorEntity == null || preprocessorEntity.isEmpty()) return result;

		Service pre = Outside.service(this, preprocessorEntity);
		Map input = new LinkedHashMap();
		input.put("description", description);
		Object output = pre.t(input);
		if (output instanceof Map) {
			Map out = (Map) output;
			if (out.get("codes") != null) result.put("codes", out.get("codes"));
			if (out.get("description") != null) result.put("description", out.get("description"));
		}
		return result;
	}
}