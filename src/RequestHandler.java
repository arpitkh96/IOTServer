import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestHandler {
	public static void doSomethingForAndroid(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} finally {
			reader.close();
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String ip = sb.toString();
		Gson gson=new Gson();
		DataIncomingHolder d=gson.fromJson(ip, A.class).getDataIncomingHolder();
		DataHolder dh=new DataHolder(d.getPin(),d.getMode());
		String id=null;
		try {
			id = DataManager.generateIp(Integer.parseInt(d.getCode()));
			out.println(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	 	 String a=HttpRequestForwarder.forwardRequest(id, dh.toString());
	 	 out.println(a);
	}
	class A{
	private DataIncomingHolder dataIncomingHolder;

	public DataIncomingHolder getDataIncomingHolder() {
		return dataIncomingHolder;
	}

	public void setDataIncomingHolder(DataIncomingHolder dataIncomingHolder) {
		this.dataIncomingHolder = dataIncomingHolder;
	}	
	}
	public static void doSomethingForWeb(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
/*		if ("search".equalsIgnoreCase(action)) {

			int id;
			String ip = "";
			try {
				id = Integer.parseInt(request.getParameter("id"));
				ip = DataManager.generateIp(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Set response content type
			session.setAttribute("ip", ip);

			
			 * RequestDispatcher rs = request
			 * .getRequestDispatcher("index.jsp"); rs.forward(request,
			 * response);
			 
			} else {
*/			doSomethingForAndroid(request, response);
		//}
	}
}
