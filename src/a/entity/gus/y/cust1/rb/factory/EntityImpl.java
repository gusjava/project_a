package a.entity.gus.y.cust1.rb.factory;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231203";}

	private Service newEntity;

	public EntityImpl() throws Exception {
		newEntity = Outside.service(this, "newentity");
	}

	public Object t(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String info = (String) o[1];
		return new Factory(info);
	}

	private class Factory implements G {
		private String entityName;

		public Factory(String entityName) {
			this.entityName = entityName;
		}

		public Object g() throws Exception {
			return newEntity.t(entityName);
		}
	}
}
