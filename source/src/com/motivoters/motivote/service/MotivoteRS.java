package com.motivoters.motivote.service;

import com.motivoters.motivote.MotivoteService;

public class MotivoteRS extends MotivoteService {
	public MotivoteRS(String name, String apiKey) {
		super("http://" + name + ".motivoters.com/motivote/", apiKey);
	}
}
