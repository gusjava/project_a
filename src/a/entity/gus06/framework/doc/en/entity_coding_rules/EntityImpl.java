package a.entity.gus06.framework.doc.en.entity_coding_rules;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20251130";}
	
	public Object g() throws Exception
	{
		return """
Every entity is identified by its name, which must be package-compatible.
Entity names must begin with "gus.".
Example of a valid entity name: "gus.web.openrouter.api.chat.query"
The entity source code is stored in a single .java file.
The entity Java package must be "gus06.entity.<entityName>".
The entity class must be named EntityImpl.
The EntityImpl class must implement the gus06.framework.Entity interface
The method "creationDate()" must return the entity Java file creation date in the format : "yyyyMMdd"
The entity source code must include: import a.framework.*; and no other gus06 packages may be imported.
An entity may implement one or more of the following framework feature interfaces: E, G, P, T, F, R, V, S, H, I
The EntityImpl class may define either: A default constructor, or A no-argument constructor that throws an Exception type.
""";
	}
}