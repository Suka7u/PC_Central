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

@WebServlet("/CPUCoolerSearch")
public class CPUCoolerSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CPUCoolerSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyword1 = request.getParameter("keyword1"); // keyword1: Air
      String keyword2 = request.getParameter("keyword2"); // keyword2: Water
      String keywordPrice = request.getParameter("keywordPrice"); // keywordPrice: price
      search(keyword1, keyword2, keywordPrice, response);
   }

   void search(String keyword1, String keyword2, String keywordPrice, HttpServletResponse response) throws IOException {
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

         if (!(keyword1 == null) && !(keyword2 == null)) {
        	//checkVal = 3;
            String selectSQL = "SELECT * FROM cpucoolerTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
        	if (!(keyword1 == null)) {
        		//checkVal = 1;
        		String selectSQL = "SELECT * FROM cpucoolerTable WHERE AIRORWATER LIKE ?";
                String coolingType = "%" + keyword1 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, coolingType);
        	} else if (!(keyword2 == null)) {
        		//checkVal = 2;
        		String selectSQL = "SELECT * FROM cpucoolerTable WHERE AIRORWATER LIKE ?";
                String coolingType = "%" + keyword2 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, coolingType);
        	}
         }
         
         ResultSet rs = preparedStatement.executeQuery();
         
         out.println("<table>");
         
         out.println("<tr>");
         out.println("<th>ID</th>");
         out.println("<th>Product Name</th>");
         out.println("<th>Manufacturer</th>");
         out.println("<th>Air/Water</th>");
         out.println("<th>RPM</th>");
         out.println("<th>Price</th>");
         out.println("</tr>");

         while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("PRODUCT_NAME").trim();
            String manufacturer = rs.getString("MANUFACTURER").trim();
            String airOrWater = rs.getString("AIRORWATER").trim();
            String rpm = rs.getString("RPM").trim();
            String price = rs.getString("PRICE").trim();
            
            String actualPrice = price.substring(1); // Removing the leading '$'
            float integerPrice = Float.parseFloat(actualPrice);
            float sliderPrice = Float.parseFloat(keywordPrice);
            
            if (integerPrice < sliderPrice) {
	            out.println("<tr>");
	            out.println("<td>" + id + "</td>");
	            out.println("<td>" + productName + "</td>");
	            out.println("<td>" + manufacturer + "</td>");
	            out.println("<td>" + airOrWater + "</td>");
	            out.println("<td>" + rpm + "</td>");
	            out.println("<td>" + price + "</td>");
	            out.println("</tr>");
            }
         }
         
         out.println("</table>");
         
         out.println("<a href=/webproject/cpuCoolerSearch.html>Search Data</a> <br>");
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
