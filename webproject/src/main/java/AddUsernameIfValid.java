import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddUsernameIfValid")
public class AddUsernameIfValid extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AddUsernameIfValid() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String username = request.getParameter("username");
      search(username, response);
   }

   void search(String username, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         
         String selectSQL = "SELECT * FROM usernamesTable";
         preparedStatement = connection.prepareStatement(selectSQL);
         ResultSet rs = preparedStatement.executeQuery();
         
         int checkVal = 0; // username DNE in the table

         while (rs.next()) {
        	 
        	 // out.println("checkVal:" + checkVal + "\n");
             // out.println("username:" + username + "\n");
             
             String userNameInTable = rs.getString("USERNAME").trim();
             
             //out.println("userNameInTable:" + userNameInTable + "\n");
             
             if (username.equals(userNameInTable)) {
            	 checkVal = 1; // username exists in the table
             } else {
            	 continue;
             }
         }
         
         // out.println("checkVal:" + checkVal + "\n");
         
         if (checkVal == 1) { // username exists in the table
        	 
        	 // out.println("checkVal:" + checkVal + "\n");
        	 
        	 rs.close();
    		 preparedStatement.close();
    		 connection.close();
        	 
    		 out.println(docType + //
		    		 "<html>\n" + //
			            "<head><title>" + title + "</title>\n" + //
			            "</head>\n" + //
			            "<body>\n" + //
			            "<script>location.href = \"/webproject/loginOrSignUp.html?checkVal=1\"</script>\n" + //
			            // "<script>document.cookie = \"checkValue=1\";\n" + //
			            // "<script>document.cookie = \"1\"\n" + //
			            // "<script>var loginOrSignUpPage = window.open(\"/webproject/loginOrSignUp.html\");\n" + //
			            // "loginOrSignUpPage.onload = function() {\n" + //
			            // "<script>window.onload = function() {\n" + //
			            //"sessionStorage.setItem(\"checkValue\", " + checkVal + ")}\n" + //
			            //"loginOrSignUpPage.onload = alert(localStorage.getItem(\"checkValue\"));\n" + //
			            // "</script>\n" + //
		        	"</body>\n" + //
			            "</html>");
             		
         } else { // username DNE in the table
        	 
        	 // FIXME: somehow send checkVal to HTML page
        	 
        	 // out.println("checkVal:" + checkVal + "\n");
        	 
        	 rs.close();
    		 preparedStatement.close();
    		 
             String insertSQL = "INSERT INTO usernamesTable (id, USERNAME) VALUES (default, ?)";
             
             PreparedStatement preparedStmt = connection.prepareStatement(insertSQL);
             preparedStmt.setString(1, username);
             
             preparedStmt.execute();
             
             rs.close();
    		 preparedStatement.close();
    		 connection.close();
    		 
    		 out.println(docType + //
		    		 "<html>\n" + //
			            "<head><title>" + title + "</title>\n" + //
			            "</head>\n" + //
			            "<body>\n" + //
			            "<script>\n" + //
			            "window.onload = function() {\n" + //
			            "localStorage.setItem(\"checkValue\", \"Username added successfully.\");\n" + //
			            "}\n" + //
			            "</script>\n" + //
			            //"<script>location.href = \"/webproject/loginOrSignUp.html?checkVal=0\"</script>\n" + //
			            // "<script>document.cookie = \"checkValue=0\";\n" + //
			            // "<script>document.cookie = \"0\"\n" + //
			            // "<script>var loginOrSignUpPage = window.open(\"/webproject/loginOrSignUp.html\");\n" + //
			            // "loginOrSignUpPage.onload = function() {\n" + //
			            // "<script>window.onload = function() {\n" + //
			            //"sessionStorage.setItem(\"checkValue\", " + checkVal + ")}\n" + //
			            //"loginOrSignUpPage.onload = alert(localStorage.getItem(\"checkValue\"));\n" + //
			            // "</script>\n" + //
		        	"</body>\n" + //
			            "</html>");
             
         }
         
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}