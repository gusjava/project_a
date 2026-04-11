package a.entity.gus.y.roadmapdb1.cx.initdb;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P, G {
	public String creationDate() {return "20260411";}

	public static final String STRUCT_LAST_UPDATE = "2026-04-11 00:00:00";
	public static final boolean ALWAYS_RESET = false;

	private Service initObjective;
	private Service initObjectiveTag;
	private Service initTask;
	private Service initTaskTag;
	private Service initSprint;
	private Service initSprintEntry;
	private Service initNote;
	private Service initNoteTag;
	private Service initFK;

	public EntityImpl() throws Exception {
		initObjective = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.objective");
		initObjectiveTag = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.objective_tag");
		initTask = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.task");
		initTaskTag = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.task_tag");
		initSprint = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.sprint");
		initSprintEntry = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.sprint_entry");
		initNote = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.note");
		initNoteTag = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.note_tag");
		initFK = Outside.service(this, "gus.y.roadmapdb1.cx.initdb.fk");
	}

	public Object g() throws Exception {
		if (ALWAYS_RESET) return null;
		return STRUCT_LAST_UPDATE;
	}

	public void p(Object obj) throws Exception {
		initObjective.p(obj);
		initObjectiveTag.p(obj);
		initTask.p(obj);
		initTaskTag.p(obj);
		initSprint.p(obj);
		initSprintEntry.p(obj);
		initNote.p(obj);
		initNoteTag.p(obj);
		initFK.p(obj);
	}
}
