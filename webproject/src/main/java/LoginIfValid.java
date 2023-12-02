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

@WebServlet("/LoginIfValid")
public class LoginIfValid extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public LoginIfValid() {
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
             
             String userNameInTable = rs.getString("USERNAME").trim();
             
             if (username.equals(userNameInTable)) {
            	 checkVal = 1; // username exists in the table
             } else {
            	 continue;
             }
         }
         
         if (checkVal == 1) { // username exists in the table
        	 
        	 rs.close();
    		 preparedStatement.close();
    		 connection.close();
        	 
    		 out.println(docType + //
		    		 "<html>\n" + //
			            "<head><title>" + title + "</title>\n" + //
			            "</head>\n" + //
			            "<body>\n" + //
			            "<script>\n" + //
			            
						"var checkValue = \"1\"\n" + //
						"sessionStorage.setItem(\"checkValue\", checkValue)\n" + //
			            
			            "var userName = " + username + " \n" + //
			            "sessionStorage.setItem(\"userName\", userName)\n" + //
			            "</script>\n" + //

			            "<script>location.href = \"/webproject/homePage.html\"</script>\n" + //

		        	"</body>\n" + //
			            "</html>");
             		
         } else { // username DNE in the table
        	 
        	 rs.close();
    		 preparedStatement.close();
    		 connection.close();
        	 
    		 out.println(docType + //
		    		 "<html>\n" + //
			            "<head><title>" + title + "</title>\n" + //
			            "</head>\n" + //
			            "<body>\n" + //
			            
						"<script>var checkValue = \"0\"\n" + //
						"sessionStorage.setItem(\"checkValue\", checkValue)\n" + //
						"</script>\n" + //

			            "<script>location.href = \"/webproject/loginOrSignUp.html\"</script>\n" + //

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