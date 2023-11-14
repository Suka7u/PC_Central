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

@WebServlet("/GPUsearch")
public class GPUsearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public GPUsearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String keyword1 = request.getParameter("keyword1");
      String keyword2 = request.getParameter("keyword2");
      search(keyword1, keyword2, response);
   }

   void search(String keyword1, String keyword2, HttpServletResponse response) throws IOException {
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
         
         // DEBUG
         out.println("keyword1: " + keyword1 + "<br>");
         out.println("keyword2: " + keyword2 + "<br>");
         //
         
         // 0: no keywords,   1: only keyword 1,   2: only keyword 2,   3: both keywords
         int checkVal = 0;

         if (!(keyword1 == null) && !(keyword2 == null)) {
        	checkVal = 3;
            String selectSQL = "SELECT * FROM gpuTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
        	if (!(keyword1 == null)) {
        		checkVal = 1;
        		String selectSQL = "SELECT * FROM gpuTable WHERE CHIPSET LIKE ?";
                String chip_set = "%" + keyword1 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, chip_set);
        	} else if (!(keyword2 == null)) {
        		checkVal = 2;
        		String selectSQL = "SELECT * FROM gpuTable WHERE CHIPSET LIKE ?";
                String chip_set = "%" + keyword2 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, chip_set);
        	}
         }

      // DEBUG
         out.println("checkVal: " + checkVal + "<br>");
      //
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("PRODUCT_NAME").trim();
            String manufacturer = rs.getString("MANUFACTURER").trim();
            String chipSet = rs.getString("CHIPSET").trim();
            String memory = rs.getString("MEMORY").trim();
            String size = rs.getString("SIZE").trim();
            String price = rs.getString("PRICE").trim();

        	out.println("ID: " + id + ", ");
            out.println("Product Name: " + productName + ", ");
            out.println("Manufacturer: " + manufacturer + ", ");
            out.println("Chipset: " + chipSet + ", ");
            out.println("Memory: " + memory + ", ");
            out.println("Size: " + size + ", ");
            out.println("Price: " + price + "<br>");
         }
         out.println("<a href=/webproject/gpuSearch.html>Search Data</a> <br>");
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
