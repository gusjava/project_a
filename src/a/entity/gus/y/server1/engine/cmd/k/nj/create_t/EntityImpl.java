package a.entity.gus.y.server1.engine.cmd.k.nj.create_t;

import java.sql.Connection;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260426";}

	private Service perform;
	private Service engine;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this, "gus.y.knowledgedb1.todo.insert");
		engine = Outside.service(this, "gus.y.knowledgesys1.engine");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		completeMap(json);
		return perform.t(new Object[]{cx(), json});
	}

	private Connection cx() throws Exception
	{return (Connection) engine.r("cx");}

	private void completeMap(Map m) throws Exception
	{
		String code = (String) m.get("code");
		if(code.startsWith("K"))
		{
			String ctxFileName = (String) m.get("ctxfilename");
			File ctxFile = new File("C:/GUS/A/.claude/ctx", ctxFileName);
			String content = Files.readString(ctxFile.toPath(), StandardCharsets.UTF_8).replace(System.lineSeparator(), "\n");
			m.put("description", content);
		}
	}
}