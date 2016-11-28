package word.counter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordCounter
 */
@WebServlet("/WordCounter")
public class WordCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordCounter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("command: "+request.getParameter("command"));
		//HTML FORM
	    response.setContentType("text/html");

	    java.io.PrintWriter out = response.getWriter();

	    out.println("<html><head>");

	    out.println("<title>Help Page</title></head><body>");
	    out.println("<form method=\"post\" action =\"" + request.getContextPath() +"/WordCounter\" >");
	    out.println("<table border=\"0\"><tr><td valign=\"top\">");
	    out.println("Enter URL Address: </td>  <td valign=\"top\">");
	    out.println("<input type=\"text\" name=\"firstname\" size=\"20\">");
	    out.println("<input type=\"submit\" value=\"Submit Info\"></td></tr>");
	    out.println("</table></form>");
	    out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String web;
		java.io.PrintWriter out = response.getWriter();
		String parName;
		Enumeration paramNames = request.getParameterNames();
		parName = (String) paramNames.nextElement();
		//Save the URL name entered by user
		web = request.getParameter(parName);
		
		out.println("<h5>Given URL</h5>"+web);
		//Get the HTML text of the entered URL
		URL oracle = new URL(web);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        //Save the text in file for further processing
        File file = new File("/users/saeeda1/filename.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
        if (!file.exists()) {
			file.createNewFile();
		}
        String inputLine;
        String inp;
        int counter = 0;
		while ((inputLine = in.readLine()) != null){
			//Replace Non Alpha-Numeric character with Spaces
			inp = inputLine.replaceAll("[^A-Za-z0-9 ]", " ");
			bw.write(inp);
        	}
		bw.close();
        in.close();
        
        double length =0;
        double total_no_words = 0;
        Scanner sc2 = null;
        response.getWriter().println("<h6>HTML Non Alpha-Numeric Text</h6>");
      //now reading from the file and count each word and its length
		sc2 = new Scanner(new File("/users/saeeda1/filename.txt"));
		while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
        while (s2.hasNext()) {
            String s = s2.next();
            length += (s.length());
            total_no_words += 1;
            response.getWriter().println(s+"<br />");
        }
		}
		double avg = length/total_no_words;
		response.getWriter().println("<br /><h3>Total No of Words</h3>"+total_no_words);
		response.getWriter().println("<br /><h3>Average Word Length</h3>"+avg);
		
	}

}
