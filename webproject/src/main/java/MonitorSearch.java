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

@WebServlet("/MonitorSearch")
public class MonitorSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MonitorSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyword1 = request.getParameter("keyword1"); // keyword1: 1080p
      String keyword2 = request.getParameter("keyword2"); // keyword2: 2K
      String keyword3 = request.getParameter("keyword3"); // keyword2: 4K
      String keywordPrice = request.getParameter("keywordPrice"); // keywordPrice: price
      search(keyword1, keyword2, keyword3, keywordPrice, response);
   }

   void search(String keyword1, String keyword2, String keyword3, String keywordPrice, HttpServletResponse response) throws IOException {
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
        	 String selectSQL = "SELECT * FROM monitorTable";
             preparedStatement = connection.prepareStatement(selectSQL);
             
             ResultSet rs = preparedStatement.executeQuery();
             
             out.println("<table>");
             
             out.println("<tr>");
             out.println("<th>ID</th>");
             out.println("<th>Product Name</th>");
             out.println("<th>Manufacturer</th>");
             out.println("<th>Size</th>");
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                int id = rs.getInt("id");
                String productName = rs.getString("PRODUCT_NAME").trim();
                String manufacturer = rs.getString("MANUFACTURER").trim();
                String size = rs.getString("SIZE").trim();
                String resolution = rs.getString("RESOLUTION").trim();
                String refreshRate = rs.getString("REFRESH_RATE").trim();
                String panelType = rs.getString("PANEL_TYPE").trim();
                String price = rs.getString("PRICE").trim();
                
                String actualPrice = price.substring(1); // Removing the leading '$'
                float integerPrice = Float.parseFloat(actualPrice);
                float sliderPrice = Float.parseFloat(keywordPrice);
                
                if (integerPrice < sliderPrice) {
	                out.println("<tr>");
	                out.println("<td>" + id + "</td>");
	                out.println("<td>" + productName + "</td>");
	                out.println("<td>" + manufacturer + "</td>");
	                out.println("<td>" + size + "</td>");
	                out.println("<td>" + resolution + "</td>");
	                out.println("<td>" + refreshRate + "</td>");
	                out.println("<td>" + panelType + "</td>");
	                out.println("<td>" + price + "</td>");
	                out.println("</tr>");
                }
             }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1 && check2) {
        	 
        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();
             
             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1 && check3) {

        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check2 && check3) {

        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             rs.close();
             
             preparedStatement.setString(1, Size2);
             
             rs = preparedStatement.executeQuery();

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check1) {
        	 
        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check2) {
        	 
        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
             out.println("</body></html>");
             rs.close();
             preparedStatement.close();
             connection.close();
             
         } else if (check3) {
        	 
        	 String selectSQL = "SELECT * FROM monitorTable WHERE RESOLUTION LIKE ?";
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
             out.println("<th>Resolution</th>");
             out.println("<th>Refresh Rate</th>");
             out.println("<th>Panel Type</th>");
             out.println("<th>Price</th>");
             out.println("</tr>");

             while (rs.next()) {
                 int id = rs.getInt("id");
                 String productName = rs.getString("PRODUCT_NAME").trim();
                 String manufacturer = rs.getString("MANUFACTURER").trim();
                 String size = rs.getString("SIZE").trim();
                 String resolution = rs.getString("RESOLUTION").trim();
                 String refreshRate = rs.getString("REFRESH_RATE").trim();
                 String panelType = rs.getString("PANEL_TYPE").trim();
                 String price = rs.getString("PRICE").trim();
                 
                 String actualPrice = price.substring(1); // Removing the leading '$'
                 float integerPrice = Float.parseFloat(actualPrice);
                 float sliderPrice = Float.parseFloat(keywordPrice);
                 
                 if (integerPrice < sliderPrice) {
 	                out.println("<tr>");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             
             out.println("<a href=/webproject/monitorSearch.html>Search Data</a> <br>");
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