package ru.babobka.nodeserials;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dolgopolov.a on 08.07.15.
 */

public final class NodeRequest implements Serializable {

	private static final long serialVersionUID = 7L;

	private final UUID taskId;

	private final UUID requestId;

	private final boolean stoppingRequest;

	private final boolean raceStyle;

	private final String taskName;

	private final long timeStamp;

	private final Map<String, Serializable> addition = new HashMap<>();

	public NodeRequest(UUID taskId, UUID requestId, String taskName, Map<String, Serializable> addition,
			boolean stoppingRequest, boolean raceStyle) {
		this.taskId = taskId;
		this.requestId = requestId;
		this.taskName = taskName;
		if (addition != null) {
			this.addition.putAll(addition);
		}
		this.stoppingRequest = stoppingRequest;
		this.raceStyle = raceStyle;
		this.timeStamp = System.currentTimeMillis();
	}

	public NodeRequest(UUID taskId, UUID requestId, boolean stoppingRequest, String taskName) {
		this(taskId, requestId, taskName, null, stoppingRequest, false);

	}

	public NodeRequest(UUID taskId, boolean stoppingRequest, String taskName) {
		this(taskId, UUID.randomUUID(), taskName, null, stoppingRequest, false);
	}


	public String getStringAdditionValue(String key) {
		Serializable value = addition.get(key);
		if (value != null)
			return value.toString();
		return "";
	}

	public static NodeRequest heartBeatRequest() {
		return new NodeRequest(UUID.randomUUID(), UUID.randomUUID(), Mappings.HEART_BEAT_TASK_NAME, null, false, false);
	}

	public UUID getTaskId() {
		return taskId;
	}

	public UUID getRequestId() {
		return requestId;
	}

	public boolean isAuthRequest() {
		if (taskName.equals(Mappings.AUTH_TASK_NAME)) {
			return true;
		}
		return false;
	}

	public boolean isHeartBeatingRequest() {
		if (taskName.equals(Mappings.HEART_BEAT_TASK_NAME)) {
			return true;
		}
		return false;
	}

	public String getTaskName() {
		return taskName;
	}

	public boolean isRaceStyle() {
		return raceStyle;
	}

	public boolean isStoppingRequest() {
		return stoppingRequest;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public Map<String, Serializable> getAddition() {
		return addition;
	}

	@Override
	public String toString() {
		return "NodeRequest [taskId=" + taskId + ", requestId=" + requestId + ", stoppingRequest=" + stoppingRequest
				+ ", raceStyle=" + raceStyle + ", taskName=" + taskName + ", timeStamp=" + timeStamp + ", addition="
				+ addition + "]";
	}

}