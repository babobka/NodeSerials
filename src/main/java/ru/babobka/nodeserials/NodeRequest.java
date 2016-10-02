package ru.babobka.nodeserials;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dolgopolov.a on 08.07.15.
 */

public final class NodeRequest implements Serializable {

	private static final long serialVersionUID = 6L;

	private final long taskId;

	private final long requestId;

	private final boolean stoppingRequest;

	private final boolean raceStyle;

	private final String taskName;

	private final long time;

	private final Map<String, Serializable> addition = new HashMap<>();

	public NodeRequest(long taskId, long requestId, String taskName, Map<String, Serializable> addition,
			boolean stoppingRequest, boolean raceStyle) {
		this.taskId = taskId;
		this.requestId = requestId;
		this.taskName = taskName;
		if (addition != null) {
			this.addition.putAll(addition);
		}
		this.stoppingRequest = stoppingRequest;
		this.raceStyle = raceStyle;
		this.time = System.currentTimeMillis();
	}

	public NodeRequest(long taskId, long requestId, boolean stoppingRequest, String taskName) {
		this(taskId, requestId, taskName, null, stoppingRequest, false);

	}

	public NodeRequest(long taskId, boolean stoppingRequest, String taskName) {
		this(taskId, (long) (Math.random() * Integer.MAX_VALUE), taskName, null, stoppingRequest, false);
	}

	public static NodeRequest heartBeatRequest() {
		return new NodeRequest(0L, 0L, Mappings.HEART_BEAT_TASK_NAME, null, false, false);
	}

	public Map<String, Serializable> getAddition() {
		return addition;
	}

	public long getTaskId() {
		return taskId;
	}

	public long getRequestId() {
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

	public long getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "NodeRequest [taskId=" + taskId + ", requestId=" + requestId + ", stoppingRequest=" + stoppingRequest
				+ ", raceStyle=" + raceStyle + ", taskName=" + taskName + ", time=" + time + ", addition=" + addition
				+ "]";
	}

}