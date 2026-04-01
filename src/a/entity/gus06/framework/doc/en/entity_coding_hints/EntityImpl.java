package a.entity.gus06.framework.doc.en.entity_coding_hints;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
When implementing entities in gus06, the following practical hints help ensure consistency, readability, and correct framework usage:

Explicit casts are required when passing objects into feature methods or when handling the return values of service calls.
Avoid assuming automatic type conversions; always cast to the expected type.

Using generics (e.g., List<String>) provides no additional benefit within gus06 entities.
Simple raw types (e.g., List) are preferred for simplicity.

Use tab characters for indentation. Consistent tab-based formatting improves readability across the entire gus06 codebase.

Adding @Override annotations is unnecessary within gus06 entities and should be avoided.
""";
	}
}