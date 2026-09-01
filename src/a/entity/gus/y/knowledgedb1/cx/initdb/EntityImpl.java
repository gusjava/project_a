package a.entity.gus.y.knowledgedb1.cx.initdb;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20260410";}

	public static final String FIXED_DATE = "2026-08-31 00:00:01";

	private Service initKnowledge;
	private Service initKnowledgeLink;
	private Service initKnowledgeTag;
	private Service initKnowledgeFeedback;
	private Service initTodo;
	private Service initTodoLink;
	private Service initTodoKnowledge;
	private Service initTodoTag;
	private Service initDocX;
	private Service initDocY;
	private Service initDocZ;
	private Service initDocXTag;
	private Service initDocYTag;
	private Service initDocZTag;
	private Service initRule;
	private Service initSpec;
	private Service initSpecRule;
	private Service initDocXSpec;
	private Service initDocYSpec;
	private Service initDocYIndex;
	private Service initDocYMember;
	private Service initDocYEntry;
	private Service initDocYExtension;
	private Service initFK;

	public EntityImpl() throws Exception
	{
		initKnowledge = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.knowledge");
		initKnowledgeLink = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.knowledge_link");
		initKnowledgeTag = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.knowledge_tag");
		initKnowledgeFeedback = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.knowledge_feedback");
		initTodo = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.todo");
		initTodoLink = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.todo_link");
		initTodoKnowledge = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.todo_knowledge");
		initTodoTag = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.todo_tag");
		initDocX = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_x");
		initDocY = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y");
		initDocZ = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_z");
		initDocXTag = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_x_tag");
		initDocYTag = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_tag");
		initDocZTag = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_z_tag");
		initRule = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.rule");
		initSpec = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.spec");
		initSpecRule = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.spec_rule");
		initDocXSpec = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_x_spec");
		initDocYSpec = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_spec");
		initDocYIndex = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_index");
		initDocYMember = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_member");
		initDocYEntry = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_entry");
		initDocYExtension = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.doc_y_extension");
		initFK = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.fk");
	}

	public Object g() throws Exception
	{
		return FIXED_DATE;
	}

	public void p(Object obj) throws Exception
	{
		initKnowledge.p(obj);
		initKnowledgeLink.p(obj);
		initKnowledgeTag.p(obj);
		initKnowledgeFeedback.p(obj);
		initTodo.p(obj);
		initTodoLink.p(obj);
		initTodoKnowledge.p(obj);
		initTodoTag.p(obj);
		initDocX.p(obj);
		initDocY.p(obj);
		initDocZ.p(obj);
		initDocXTag.p(obj);
		initDocYTag.p(obj);
		initDocZTag.p(obj);
		initRule.p(obj);
		initSpec.p(obj);
		initSpecRule.p(obj);
		initDocXSpec.p(obj);
		initDocYSpec.p(obj);
		initDocYIndex.p(obj);
		initDocYMember.p(obj);
		initDocYEntry.p(obj);
		initDocYExtension.p(obj);
		initFK.p(obj);
	}
}