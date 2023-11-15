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

@WebServlet("/MemorySearch")
public class MemorySearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MemorySearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyword1 = request.getParameter("keyword1"); // keyword1: 16GB
      String keyword2 = request.getParameter("keyword2"); // keyword2: 32GB
      String keyword3 = request.getParameter("keyword3"); // keyword2: 64GB
      search(keyword1, keyword2, keyword3, response);
   }

   void search(String keyword1, String keyword2, String keyword3, HttpServletResponse response) throws IOException {
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
         
         boolean check1 = !(keyword1 == null);
         boolean check2 = !(keyword2 == null);
         boolean check3 = !(keyword3 == null);
         
         if (check1 && check2 && check3) {
        	 String selectSQL = "SELECT * FROM memoryTable";
             preparedStatement = connection.prepareStatement(selectSQL);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("PRODUCT_NAME").trim();
                String manufacturer = rs.getString("MANUFACTURER").trim();
                String size = rs.getString("SIZE").trim();
                String speed = rs.getString("SPEED").trim();
                String latency = rs.getString("LATENCY").trim();
                String price = rs.getString("PRICE").trim();
                
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + productName + "</td>");
                out.println("<td>" + manufacturer + "</td>");
                out.println("<td>" + size + "</td>");
                out.println("<td>" + speed + "</td>");
                out.println("<td>" + latency + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1 && check2) {
        	 
        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size1 = keyword1 + "%";
             String Size2 = keyword2 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size1);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1 && check3) {

        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size1 = keyword1 + "%";
             String Size2 = keyword3 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size1);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check2 && check3) {

        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size1 = keyword2 + "%";
             String Size2 = keyword3 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size1);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1) {
        	 
        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size = keyword1 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check2) {
        	 
        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size = keyword2 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check3) {
        	 
        	 String selectSQL = "SELECT * FROM memoryTable WHERE SIZE LIKE ?";
             String Size = keyword3 + "%";
             preparedStatement = connection.prepareStatement(selectSQL);
             preparedStatement.setString(1, Size);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Speed</th>");
             out.println("<th>Latency</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String speed = rs.getString("SPEED").trim();
                 String latency = rs.getString("LATENCY").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 out.println("<tr>");
                 out.println("<td>" + id + "</td>");
                 out.println("<td>" + productName + "</td>");
                 out.println("<td>" + manufacturer + "</td>");
                 out.println("<td>" + size + "</td>");
                 out.println("<td>" + speed + "</td>");
                 out.println("<td>" + latency + "</td>");
                 out.println("<td>" + price + "</td>");
                 out.println("</tr>");
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/memorySearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         }

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
