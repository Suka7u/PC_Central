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
import javax.servlet.http.Cookie;

@WebServlet("/CPUSearch")
public class CPUSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CPUSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyword1 = request.getParameter("keyword1"); // keyword1: AMD
      String keyword2 = request.getParameter("keyword2"); // keyword2: Intel
      String keywordPrice = request.getParameter("keywordPrice"); // keywordPrice: price
      search(keyword1, keyword2, keywordPrice, response, request);
   }

   void search(String keyword1, String keyword2, String keywordPrice, HttpServletResponse response, HttpServletRequest request) throws IOException {
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
      
      Cookie partTypeCookie = new Cookie("partType","cpu");
      response.addCookie(partTypeCookie);

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;

         if (!(keyword1 == null) && !(keyword2 == null)) {
        	//checkVal = 3;
            String selectSQL = "SELECT * FROM cpuTable";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
        	if (!(keyword1 == null)) {
        		//checkVal = 1;
        		String selectSQL = "SELECT * FROM cpuTable WHERE PRODUCT_NAME LIKE ?";
                String product_name = keyword1 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, product_name);
        	} else if (!(keyword2 == null)) {
        		//checkVal = 2;
        		String selectSQL = "SELECT * FROM cpuTable WHERE PRODUCT_NAME LIKE ?";
                String product_name = keyword2 + "%";
                preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, product_name);
        	}
         }
         
         ResultSet rs = preparedStatement.executeQuery();
         
         out.println("<table>");
         
         out.println("<tr>");
         out.println("<th>ID</th>");
         out.println("<th>Product Name</th>");
         out.println("<th>Model name</th>");
         out.println("<th>Core Count</th>");
         out.println("<th>TDP Wattage</th>");
         out.println("<th>Price</th>");
         out.println("</tr>");

         while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("PRODUCT_NAME").trim();
            String modelName = rs.getString("MODEL_NAME").trim();
            String coreCount = rs.getString("CORE_COUNT").trim();
            String tdpWattage = rs.getString("TDP_WATTAGE").trim();
            String price = rs.getString("PRICE").trim();
            
            String actualPrice = price.substring(1); // Removing the leading '$'
            float integerPrice = Float.parseFloat(actualPrice);
            float sliderPrice = Float.parseFloat(keywordPrice);
            
            if (integerPrice < sliderPrice) {
	            
	            //out.println("productName = " + productName + "\n");
            	
            	// out.println(productName.getClass());
            	
            	// Cookie productNameCookie = new Cookie("productName",productName);
            	// Cookie productNameCookie = new Cookie("productName","nameOfProduct");
                // response.addCookie(productNameCookie);

                /*
                Cookie cookies[] = request.getCookies();
                String idFromCookie = cookies[1].getValue();
                String selectSQL = "UPDATE userBuildsTable SET CPU = " + productName + " WHERE id = " + idFromCookie + "";
                preparedStatement = connection.prepareStatement(selectSQL);
                //preparedStatement.setString(1, productName);
                preparedStatement.execute();
                */
                
	            out.println("<tr>");
	            out.println("<td>" + id + "</td>");
	            out.println("<td>" + productName + "</td>");
	            out.println("<td>" + modelName + "</td>");
	            out.println("<td>" + coreCount + "</td>");
	            out.println("<td>" + tdpWattage + "</td>");
	            out.println("<td>" + price + "</td>");
	            
	            //out.println("<td><a href=\"/webproject/createNewBuild.html\" onclick=\"addPart();\">Add Part</a></td>");
	            //out.println("</tr>");
	            
	            out.println("<td>");
	            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
	            out.println("<input type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
	            out.println("</form>");
	            out.println("</td>");
	            out.println("</tr>");
	            
	            //out.println("<td> <form action=\"AddProductName\" method=\"POST\" on> <input type=\"submit\" name=\"keyword1\" value=\"Add Part\"> </form> </td> </tr>");
	            
            }
         }
         
         out.println("</table>");
         
         out.println("<a href=/webproject/cpuSearch.html>Search Data</a> <br>");
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