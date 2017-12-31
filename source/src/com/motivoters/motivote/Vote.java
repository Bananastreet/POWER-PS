package com.motivoters.motivote;

public final class Vote extends MotivoteArtifact {
	private final String auth;
	private final int id;
	private final String ip;
	private final boolean verified;
	private final boolean fulfilled;
	private final long updated;
	
	public Vote(String auth, int id, String ip, boolean verified, boolean fulfilled, long updated) {
		this.auth = auth;
		this.id = id;
		this.ip = ip;
		this.verified = verified;
		this.fulfilled = fulfilled;
		this.updated = updated;
	}
	
	public String auth() {
		return auth;
	}

	public int id() {
		return id;
	}

	public String ip() {
		return ip;
	}

	public boolean isVerified() {
		return verified;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public long updated() {
		return updated;
	}
}