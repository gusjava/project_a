package a.entity.gus.y.server1.engine.cmd.k.nj.create_k;

import java.sql.Connection;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260417";}

	private Service knowledgeCx;
	private Service knowledgeInsert;

	public EntityImpl() throws Exception
	{
		knowledgeCx     = Outside.service(this, "gus.y.knowledgedb1.cx.main");
		knowledgeInsert = Outside.service(this, "gus.y.knowledgedb1.knowledge.insert");
	}

	public Object t(Object obj) throws Exception
	{
		Map json = (Map) obj;
		completeMap(json);
		return knowledgeInsert.t(new Object[]{cx(), json});
	}

	private Connection cx() throws Exception
	{return (Connection) knowledgeCx.r("cx");}
	
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
