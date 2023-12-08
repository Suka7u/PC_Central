import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckIfLoggedIn")
public class CheckIfLoggedIn extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CheckIfLoggedIn() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      addRow(request, response);
   }

   void addRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
      PrintWriter out = response.getWriter();
      
      if (request.getCookies() == null) {
          String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
                "transitional//en\">\n"; //
          
          out.println(docType + //
    	  		  "<html>\n" + //
    	            "<body>\n" + //
    	            "<script>\n" + //
    	            "alert(\"You need to login before you can use this resource.\");\n" + //
    	            "location.href = \"/webproject/loginOrSignUp.html\"\n" + //
    	            "</script>\n");
      } else {
    	  String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
                  "transitional//en\">\n"; //
            
            out.println(docType + //
      	  		  "<html>\n" + //
      	            "<body>\n" + //
      	            "<script>\n" + //
      	            "location.href = \"/webproject/presetBuilds.html\"\n" + //
      	            "</script>\n");
      }
      
      out.println(
       		"</body>\n" + //
    		"</html>");
      
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
