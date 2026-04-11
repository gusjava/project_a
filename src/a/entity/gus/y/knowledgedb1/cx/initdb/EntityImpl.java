package a.entity.gus.y.knowledgedb1.cx.initdb;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20260410";}

	public static final String STRUCT_LAST_UPDATE = "2026-04-11 00:00:00";
	public static final boolean ALWAYS_RESET = false;

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
	private Service initFK;

	public EntityImpl() throws Exception {
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
		initFK = Outside.service(this, "gus.y.knowledgedb1.cx.initdb.fk");
	}

	public Object g() throws Exception {
		if (ALWAYS_RESET) return null;
		return STRUCT_LAST_UPDATE;
	}

	public void p(Object obj) throws Exception {
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
		initFK.p(obj);
	}
}
