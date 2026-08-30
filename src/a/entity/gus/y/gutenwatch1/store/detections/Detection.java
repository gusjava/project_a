package a.entity.gus.y.gutenwatch1.store.detections;

import a.entity.gus.y.gutenwatch1.parse.Entry;

public class Detection {
	public final String detectedAt;
	public final Entry entry;

	public Detection(String detectedAt, Entry entry) {
		this.detectedAt = detectedAt;
		this.entry = entry;
	}
}
