package a.entity.gus.y.entityhash1.src.build;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260419";}

	private Service anonymizeCalls;
	private Service buildAst;
	private Service buildJson;
	private Service buildMd5;
	private Service buildHexa;

	public EntityImpl() throws Exception
	{
		anonymizeCalls = Outside.service(this,"gus.y.entityhash1.src.anonymizecalls");
		buildAst = Outside.service(this,"gus.y.entityhash1.src.buildast");
		buildJson = Outside.service(this,"gus.x.json.build1");
		buildMd5 = Outside.service(this,"gus.x.crypto.hash.md5");
		buildHexa = Outside.service(this,"gus.x.bytearraytohexa1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		src = (String) anonymizeCalls.t(src);
		Map ast = (Map) buildAst.t(src);
		String json = (String) buildJson.t(ast);
		byte[] md5 = (byte[]) buildMd5.t(json);
		return buildHexa.t(md5);
	}
}
