package com.motivoters.motivote;

import java.util.Scanner;

import org.json.JSONObject;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.motivoters.motivote.service.MotivoteRS;

public class TestAPI {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Hello, welcome to the MotivoteRS API Tester.");
		System.out.print("Please enter your service path: ");
		String path = scanner.nextLine();
		System.out.print("Please enter your API key: ");
		String apiKey = scanner.nextLine();
		
		MotivoteRS rs = new MotivoteRS(path, apiKey);
		
		System.out.println(rs.redeemVote("1062cf2e"));
		
		while (true) {
			System.out.print("Enter auth: ");
			String auth = scanner.nextLine();
			
			if (auth.equalsIgnoreCase("exit")) {
				scanner.close();
				return;
			}
			
			System.out.println("What do you want to do with it? [info (default), redeem]: ");
			String action = scanner.nextLine();
			
			try {
				JSONObject obj = rs.callAPI(1, "vote", auth, action);
				System.out.println("Response: " + obj.toString());
			}
			catch (UnirestException e) {
				e.printStackTrace();
			}
		}
	}
}
