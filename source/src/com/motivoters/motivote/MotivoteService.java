package com.motivoters.motivote;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

public abstract class MotivoteService {
	private final String baseURL;
	private final String apiKey;
	
	public MotivoteService(String baseURL, String apiKey) {
		this.baseURL = baseURL;
		this.apiKey = apiKey;
	}
	
	public JSONObject callAPI(int version, String artifact, String parameter) throws UnirestException {
		return callAPI(version, artifact, parameter, null);
	}
	
	public JSONObject callAPI(int version, String artifact, String parameter, String action) throws UnirestException {
		String url = baseURL + "v" + version + "/{artifact}/{param}";
		
		if (action != null && !action.isEmpty()) {
			url += "/" + action;
		}
		
		GetRequest r = Unirest.get(url)
			.routeParam("param", parameter)
			.routeParam("artifact", artifact)
			.header("api-key", apiKey);
		
		HttpResponse<JsonNode> response = r.asJson();
		return response.getBody().getObject();
	}
	
	public Vote[] getVotesForIP(String ip) {
		try {
			JSONObject obj = callAPI(1, "ip", ip);
			
			if (obj.has("vote")) {
				JSONArray a = obj.getJSONArray("vote");
				Vote[] votes = new Vote[a.length()];
				
				for (int i = 0; i < a.length(); i++) {
					JSONObject o = a.getJSONObject(i);
					int id = o.getInt("id");
					String auth = o.getString("auth");
					String ipz = o.getString("ip");
					boolean verified = o.getBoolean("verified");
					boolean fulfilled = o.getBoolean("fulfilled");
					
					votes[i] = new Vote(auth, id, ipz, verified, fulfilled, 0);
				}
				
				return votes;
			}
			
			return new Vote[0];
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public Vote getVoteInfo(String authOrIP) {
		try {
			JSONObject obj = callAPI(1, "vote", authOrIP);
			
			if (obj.has("vote")) {
				JSONObject o = obj.getJSONObject("vote");
				int id = o.getInt("id");
				String auth = o.getString("auth");
				String ip = o.getString("ip");
				boolean verified = o.getBoolean("verified");
				boolean fulfilled = o.getBoolean("fulfilled");
				
				return new Vote(auth, id, ip, verified, fulfilled, 0);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public boolean redeemVote(String auth) {
		try {
			JSONObject obj = callAPI(1, "vote", auth, "redeem");
			System.out.println(obj);
			
			if (obj.getString("success").equalsIgnoreCase("true")) {
				return true;
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	
	public boolean redeemVote(Vote vote) {
		if (vote.isFulfilled() || !vote.isVerified()) {
			return false;
		}
		
		return redeemVote(vote.auth());
	}
}
