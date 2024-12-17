package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.json.JSONObject;

@ManagedBean(name = "subscriberBean")
@ViewScoped
public class SubscriberBean {

	private static final String API_KEY = "AIzaSyDEUvSXc6-UHdQJbd6bQkmd5r0845cdKv0";
	private static final String CHANNEL_ID = "UCrkNtPltRjtWwax1-WRHgcA";
	private int subscriberCount;
	private String lastUpdated;

	public int getSubscriberCount() {
		return subscriberCount;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	@PostConstruct
	public void updateSubscriberCount() {
		try {
			this.subscriberCount = fetchSubscriberCount();
			// Atualiza a hora da última atualização
			this.lastUpdated = getCurrentTime();
		} catch (Exception e) {
			this.lastUpdated = "Erro ao atualizar";
		}
	}

	private int fetchSubscriberCount() throws IOException {
		String urlString = String.format("https://www.googleapis.com/youtube/v3/channels?part=statistics&id=%s&key=%s",
				CHANNEL_ID, API_KEY);

		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
			throw new IOException("Erro na requisição HTTP: " + responseCode);
		}

		Scanner scanner = new Scanner(url.openStream());
		StringBuilder response = new StringBuilder();
		while (scanner.hasNext()) {
			response.append(scanner.nextLine());
		}
		scanner.close();

		JSONObject jsonResponse = new JSONObject(response.toString());
		JSONObject statistics = jsonResponse.getJSONArray("items").getJSONObject(0).getJSONObject("statistics");

		return statistics.getInt("subscriberCount");
	}

	private String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(new Date());
	}
}