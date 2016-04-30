
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestForwarder {
	public static String forwardRequest(String address, String data) throws IOException {
		String errors = null;
		String response = "";
			try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream os = conn.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.write(data);
			writer.flush();
			writer.close();
			os.close();
			int responseCode = conn.getResponseCode();
		if (responseCode == HttpsURLConnection.HTTP_OK) {
				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = br.readLine()) != null) {
					response += line;
				}
			} else {
				response = "";

			}
		} catch (Exception e) {
			e.printStackTrace();
			errors = e.getStackTrace().toString();
		}
		StringBuilder string = new StringBuilder();
		string.append("\n"+"Error " + errors);
		string.append("\n"+"Response " + response);
		Logger.log(string.toString());
		return string.toString();
	}

	public static void forwardRequestWithThread(String address, String data) throws IOException {
	new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				forwardRequest(address,data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}).run();
	}
}
