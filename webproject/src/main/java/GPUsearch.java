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
	  String keyword1 = request.getParameter("keyword1"); // keyword1: AMDRadeon
      String keyword2 = request.getParameter("keyword2"); // keyword2: NVIDIA
      String keyword3 = request.getParameter("keyword3"); // keyword3: cheapest to most expensive
      String keyword4 = request.getParameter("keyword4"); // keyword4: most expensive to cheapest
      search(keyword1, keyword2, keyword3, keyword4, response);
   }

   void search(String keyword1, String keyword2, String keyword3, String keyword4, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
    		  "<html>\n" + //
              "<head><title>" + title + "</title>\n" + //
              "<style>table {\n" + //
              "border-collapse: collapse;\n" + //
              "}\n" + //
              "td, th {\n" + //
              "border: 1px solid black;\n" + //
              "text-align: left;\n" + //
              "padding: 10px;\n" + //
              "}</style>\n" + //
              "</head>\n" + //
              "<body bgcolor=\"#f0f0f0\">\n" + //
              "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         
         // DEBUG
         // out.println("keyword1: " + keyword1 + "<br>");
         // out.println("keyword2: " + keyword2 + "<br>");
         // out.println("keyword3: " + keyword3 + "<br>");
         // out.println("keyword4: " + keyword4 + "<br>");
         //
         
         // 0: no keywords,   1: only keyword 1,   2: only keyword 2,   3: both keywords
         //int checkVal = 0;

         if (!(keyword1 == null) && !(keyword2 == null)) {
        	//checkVal = 3;
            String selectSQL = "SELECT * FROM gpuTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
        	if (!(keyword1 == null)) {
        		//checkVal = 1;
        		String selectSQL = "SELECT * FROM gpuTable WHERE CHIPSET LIKE ?";
                String chip_set = "%" + keyword1 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, chip_set);
        	} else if (!(keyword2 == null)) {
        		//checkVal = 2;
        		String selectSQL = "SELECT * FROM gpuTable WHERE CHIPSET LIKE ?";
                String chip_set = "%" + keyword2 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, chip_set);
        	}
         }
         
         /*
         // Print cheapest to most expensive
         if (keyword3 != null) {
        	 String selectSQL = "SELECT * FROM gpuTable";
        	 preparedStatement = connection.prepareStatement(selectSQL);
         }
         */

      // DEBUG
         // out.println("checkVal: " + checkVal + "<br>");
      //
         
         ResultSet rs = preparedStatement.executeQuery();
         
         out.println("<table>");
         
         out.println("<tr>");
         out.println("<th>ID</th>");
         out.println("<th>Product Name</th>");
         out.println("<th>Manufacturer</th>");
         out.println("<th>Chipset</th>");
         out.println("<th>Memory</th>");
         out.println("<th>Size</th>");
         out.println("<th>Price</th>");
         out.println("</tr>");

         while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("PRODUCT_NAME").trim();
            String manufacturer = rs.getString("MANUFACTURER").trim();
            String chipSet = rs.getString("CHIPSET").trim();
            String memory = rs.getString("MEMORY").trim();
            String size = rs.getString("SIZE").trim();
            String price = rs.getString("PRICE").trim();
            
            out.println("<tr>");
            out.println("<td>" + id + "</td>");
            out.println("<td>" + productName + "</td>");
            out.println("<td>" + manufacturer + "</td>");
            out.println("<td>" + chipSet + "</td>");
            out.println("<td>" + memory + "</td>");
            out.println("<td>" + size + "</td>");
            out.println("<td>" + price + "</td>");
            out.println("</tr>");
         }
         
         out.println("</table>");
         
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
