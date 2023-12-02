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

@WebServlet("/AddRowInUserBuildsTable")
public class AddRowInUserBuildsTable extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AddRowInUserBuildsTable() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      addRow(response);
   }

   void addRow(HttpServletResponse response) throws IOException {
      PrintWriter out = response.getWriter();

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      
      // FIXME: FIGURE OUT HOW TO GET THE USERNAME FROM THE LOGIN SESSION IN HTML
      String username = "Sanjay";
      String insertSQL = "INSERT INTO userBuildsTable (id, USERNAME, CPU, CPUCOOLER, MOTHERBOARD, MEMORY, STORAGE, GPU, PCCASE, POWERSUPPLY, MONITOR) VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         PreparedStatement preparedStmt = connection.prepareStatement(insertSQL);
         preparedStmt.setString(1, username);
         preparedStmt.setString(2, "");
         preparedStmt.setString(3, "");
         preparedStmt.setString(4, "");
         preparedStmt.setString(5, "");
         preparedStmt.setString(6, "");
         preparedStmt.setString(7, "");
         preparedStmt.setString(8, "");
         preparedStmt.setString(9, "");
         preparedStmt.setString(10, "");
         
         preparedStmt.execute();
         
         preparedStmt.close();
         connection.close();
        
         String title = "Database Result";
         String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
               "transitional//en\">\n"; //
         
         out.println(docType + //
	  		  "<html>\n" + //
	            "<head><title>" + title + "</title>\n" + //
	            "</head>\n" + //
	            "<body>\n" + //
	            "<script>location.href = \"/webproject/createNewBuild.html\"</script>\n" + //
         		"</body>\n" + //
	            "</html>");
      
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
