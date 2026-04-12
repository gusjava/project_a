package a.entity.gus06.jdbc.postgresql.format.iskeyword;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20260107";}

	public boolean f(Object obj) throws Exception
	{
		String name = (String) obj;
		return POSTGRESQL_RESERVED_WORDS.isReservedWord(name);
	}
}