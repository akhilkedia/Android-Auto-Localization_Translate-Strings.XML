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
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import object.GsonResponse;

public class HTTPRequestHandler {

	private final String USER_AGENT = "Mozilla/5.0";
	private String accessToken;

	public HTTPRequestHandler() {
		super();
		getAccessTokenFromService();
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
		GsonResponse gsonResult = gson.fromJson(result.toString(), GsonResponse.class);
		Map<String, GsonResponse.Language> map = (Map<String, GsonResponse.Language>) gsonResult.getText();
		LinkedHashMap<String, String> abbNamePair = new LinkedHashMap<String, String>();
		for (String key : map.keySet()) {
			// System.out.println(key);
			// abbNamePair.put(Util.exchangeProblematicCountryCode(key),
			// map.get(key).getName());
			abbNamePair.put(key, map.get(key).getName());
		}

		for (String key : abbNamePair.keySet()) {
			System.out.println(key + " : " + abbNamePair.get(key));
		}

		return abbNamePair;
	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://translate.google.com/#en/tr/mouse";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		// System.out.println(response.toString());

	}

	public void getAccessTokenFromService() {

		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(Const.BING_TOKEN_ADRESS);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			request.addHeader("Ocp-Apim-Subscription-Key", Const.API_KEY);

			HttpResponse response = client.execute(request);

			System.out.println("\nSending 'POST' request to URL : " + Const.BING_SUPPORTED_LANGUAGES_URL);

			BufferedReader rd;
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			System.out.println("Access Token is - " + result);
			accessToken = result.toString();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}

	public String getTranslation(String fromLang, String toLang, String word) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(Const.TRANSLATE_URL + "text=" + URLEncoder.encode(word, "UTF-8") + "&from="
					+ fromLang + "&to=" + toLang);
			// toLang = "tr";
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			request.addHeader("Authorization", "Bearer " + accessToken);
			// HttpParams params = new BasicHttpParams();
			// params.setParameter("from", fromLang);
			// params.setParameter("to", toLang);
			// params.setParameter("text", word);
			// request.setParams(params);
			HttpPost post = new HttpPost();

			// System.out.println(request.getURI());

			HttpResponse response = client.execute(request);

			// System.out.println("\nSending 'GET' request to URL : "
			// + Const.TRANSLATE_URL);

			BufferedReader rd;
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			String resultString = Util.splitXmlResult(result.toString());
			resultString = Util.fixnewlines(resultString);
			// System.out.println(resultString);
			return resultString;
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
