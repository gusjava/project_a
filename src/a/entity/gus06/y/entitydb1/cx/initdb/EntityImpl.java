package a.entity.gus06.y.entitydb1.cx.initdb;

import a.framework.*;

public class EntityImpl implements Entity, P, G {

	public String creationDate() {return "20251111";}

	public static final String STRUCT_LAST_UPDATE = "2026-01-01 22:21:00"; // yyyy-MM-dd HH:mm:ss
	public static final boolean ALWAYS_RESET = false;

	private Service initEntity;
	private Service initEntitySrc;
	private Service initEntityService;
	private Service initEntityResource;
	private Service initEntityImport;
	private Service initEntityLink;
	private Service initEntityMissingLink;
	private Service initEntityCompileErr;
	private Service initJar;
	private Service initJarClass;
	private Service initJarImport;
	private Service initJarJar;
	private Service initFK;

	public EntityImpl() throws Exception
	{
		initEntity = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity");
		initEntitySrc = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_src");
		initEntityService = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_service");
		initEntityResource = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_resource");
		initEntityImport = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_import");
		initEntityLink = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_link");
		initEntityMissingLink = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_missing_link");
		initEntityCompileErr = Outside.service(this, "gus.y.entitydb1.cx.initdb.entity_compile_err");
		initJar = Outside.service(this, "gus.y.entitydb1.cx.initdb.jar");
		initJarClass = Outside.service(this, "gus.y.entitydb1.cx.initdb.jar_class");
		initJarImport = Outside.service(this, "gus.y.entitydb1.cx.initdb.jar_import");
		initJarJar = Outside.service(this, "gus.y.entitydb1.cx.initdb.jar_jar");
		initFK = Outside.service(this, "gus.y.entitydb1.cx.initdb.fk");
	}
	
	public Object g() throws Exception
	{
		if (ALWAYS_RESET) return null;
		return STRUCT_LAST_UPDATE;
	}
	
	public void p(Object obj) throws Exception
	{
		initEntity.p(obj);
		initEntitySrc.p(obj);
		initEntityService.p(obj);
		initEntityResource.p(obj);
		initEntityImport.p(obj);
		initEntityLink.p(obj);
		initEntityMissingLink.p(obj);
		initEntityCompileErr.p(obj);
		initJar.p(obj);
		initJarClass.p(obj);
		initJarImport.p(obj);
		initJarJar.p(obj);
		initFK.p(obj);
	}
}