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

@WebServlet("/ChoosePart")
public class ChoosePart extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public ChoosePart() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM gpuTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
        	if (keyword.equals("case")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/caseSearch.html");
        	} else if (keyword.equals("cpu")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/cpuSearch.html");
        	} else if (keyword.equals("cpu cooler")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/cpuCoolerSearch.html");
        	} else if (keyword.equals("gpu")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/gpuSearch.html");
        	} else if (keyword.equals("memory")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/memorySearch.html");
        	} else if (keyword.equals("monitor")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/monitorSearch.html");
        	} else if (keyword.equals("motherboard")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/motherboardSearch.html");
        	} else if (keyword.equals("power supply")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/powerSupplySearch.html");
        	} else if (keyword.equals("storage")) {
        		// End user to case html page
        		response.sendRedirect("/webproject/storageSearch.html");
        	}
         }
         
         ResultSet rs = preparedStatement.executeQuery();

         // out.println("<a href=/webproject/simpleFormSearch.html>Search Data</a> <br>");
         out.println("</body></html>");
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
