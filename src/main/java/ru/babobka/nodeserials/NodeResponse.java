package ru.babobka.nodeserials;

import java.io.Serializable;
import java.util.Map;

public final class NodeResponse implements Serializable {

	private static final long serialVersionUID = 7L;

	private final String taskName;

	private final long taskId;

	private final long responseId;

	private final long timeTakes;

	private final Status status;

	private final String message;

	private final Map<String, Serializable> addition;

	public NodeResponse(long taskId, long responseId, long timeTakes, Status status, String message,
			Map<String, Serializable> addition, String taskName) {
		this.taskId = taskId;
		this.responseId = responseId;
		this.timeTakes = timeTakes;
		this.status = status;
		this.message = message;
		this.taskName = taskName;
		this.addition = addition;
	}

	public NodeResponse(long taskId, long timeTakes, Status status, String message, Map<String, Serializable> addition,
			String taskName) {
		this(taskId, (long) (Math.random() * Integer.MAX_VALUE), timeTakes, status, message, addition, taskName);
	}

	public NodeResponse(long taskId, Status status) {
		this(taskId, (long) (Math.random() * Integer.MAX_VALUE), -1, status, null, null, null);
	}

	public NodeResponse(long taskId, Status status, String taskName) {
		this(taskId, (long) (Math.random() * Integer.MAX_VALUE), 0, status, null, null, taskName);
	}

	public static NodeResponse badResponse(Long taskId) {
		return new NodeResponse(taskId, NodeResponse.Status.FAILED);
	}

	public static NodeResponse dummyResponse(Long taskId) {
		return new NodeResponse(taskId, NodeResponse.Status.NORMAL);
	}

	public static NodeResponse stoppedResponse(Long taskId) {
		return new NodeResponse(taskId, NodeResponse.Status.STOPPED);
	}

	public enum Status {
		NORMAL, STOPPED, FAILED
	}

	public long getTimeTakes() {
		return timeTakes;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, Serializable> getAddition() {
		return addition;
	}

	public long getTaskId() {
		return taskId;
	}

	public long getResponseId() {
		return responseId;
	}

	public Status getStatus() {
		return status;
	}

	public String getTaskName() {
		return taskName;
	}

	public boolean isHeartBeatingResponse() {
		if (taskName.equals(Mappings.HEART_BEAT_TASK_NAME)) {
			return true;
		}
		return false;
	}

	public boolean isAuthResponse() {
		if (taskName.equals(Mappings.AUTH_TASK_NAME)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Response{" + "taskName='" + taskName + '\'' + ", taskId=" + taskId + ", responseId=" + responseId
				+ ", timeTakes=" + timeTakes + ", status=" + status + ", message='" + message + '\'' + ", addition="
				+ addition + '}';
	}

	public boolean isStopped() {
		if (this.status == Status.STOPPED) {
			return true;
		}
		return false;
	}
}
