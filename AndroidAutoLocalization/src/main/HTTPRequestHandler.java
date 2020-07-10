package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class HTTPRequestHandler {

	private final String USER_AGENT = "Mozilla/5.0";

	public HTTPRequestHandler() {
		super();
	}

	// HTTP GET request
	Map<String, String> getLanguageList() throws Exception {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(Const.BING_SUPPORTED_LANGUAGES_URL);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Accept-Language", "en");

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + Const.BING_SUPPORTED_LANGUAGES_URL);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println("Recived languages as " + result.toString());

		Gson gson = new Gson();
		JsonObject jsonObject = (gson.fromJson(result.toString(), JsonObject.class)).get("translation").getAsJsonObject();
		LinkedHashMap<String, String> abbNamePair = new LinkedHashMap<String, String>();
		for (String key : jsonObject.keySet()) {
			// System.out.println(key);
			// abbNamePair.put(Util.exchangeProblematicCountryCode(key),
			// map.get(key).getName());
			abbNamePair.put(key, jsonObject.get(key).getAsJsonObject().get("name").getAsString());
		}

		for (String key : abbNamePair.keySet()) {
			System.out.println(key + " : " + abbNamePair.get(key));
		}

		return abbNamePair;
	}

	public String getTranslation(String fromLang, String toLang, String word) {
		try {
			HttpClient client = new DefaultHttpClient();
//			 Add texttype html for translating html + "&textType=html"
			HttpPost httpPost = new HttpPost(Const.TRANSLATE_URL + "&from="
					+ fromLang + "&to=" + toLang);

			JsonArray jsonArray = new JsonArray();
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("Text", word);
			jsonArray.add(jsonObject);
		    httpPost.setEntity(new StringEntity(jsonArray.toString()));
            httpPost.addHeader("Ocp-Apim-Subscription-Key", Const.API_KEY);
            httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
            System.out.println(jsonArray.toString());

			// System.out.println(request.getURI());

			HttpResponse response = client.execute(httpPost);

		    assert(response.getStatusLine().getStatusCode()==200);


			BufferedReader rd;
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			Gson gson = new Gson();
			String resultString = gson.fromJson(result.toString(), JsonArray.class).get(0).getAsJsonObject().get("translations").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
			resultString = Util.fixnewlines(resultString);
			 System.out.println(resultString);
			return resultString;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
