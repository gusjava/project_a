package a.entity.gus.y.knowledgesys1.prompt.preprocessor;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260503";}

	public Object t(Object obj) throws Exception
	{
		Object[] args = (Object[]) obj;
		Map root = (Map) args[0];
		String description = (String) args[1];
		String preprocessorEntity = (String) root.get("preprocessor");

		if (preprocessorEntity == null || preprocessorEntity.isEmpty()) return new ArrayList();

		Service pre = Outside.service(this, preprocessorEntity);
		return pre.t(new Object[]{root, description});
	}
}