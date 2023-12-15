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

@WebServlet("/AddProductName")
public class AddProductName extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public AddProductName() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String keywordID = request.getParameter("keywordID");
	   search(keywordID, response, request);
   }

   void search(String keywordID, HttpServletResponse response, HttpServletRequest request) throws IOException {
	   
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   
	   // DEBUG: out.println(keywordID);
	   
	   Connection connection = null;
	   PreparedStatement preparedStatement = null;
	   try {
		   DBConnection.getDBConnection();
		   connection = DBConnection.connection;
		   //ResultSet rs = preparedStatement.executeQuery();
		   
		   //out.println("why are you not working!");
		   
		   Cookie allCookies[] = request.getCookies();
		   
		   String partType = "";
		   
           for (int i = 0; i < allCookies.length; i++ ) {
        	   String cookieName = allCookies[i].getName();
        	   if (cookieName.equals("partType")) {
        		   partType = allCookies[i].getValue();
        		   //out.println(partType);
     
        	   }
           }
          
           //out.println("why are you not working!");
           
		   String selectSQL = "SELECT * FROM "+ partType +"Table";
		   
		   //out.println("why are you not working!");
		   
           //String id = keywordID;
           preparedStatement = connection.prepareStatement(selectSQL);
           // preparedStatement.setString(1, id);
           
           ResultSet rs = preparedStatement.executeQuery();
           
           String productName = "";
           
           //out.println(keywordID);
           
           while(rs.next()) {
        	   int id = rs.getInt("id");
        	   
        	   if(id == Integer.parseInt(keywordID)) {
        		   productName = rs.getString("PRODUCT_NAME").trim();
        		   //out.println(productName);
        		   break;
        	   }
           }
           
           //out.println(productName);
           
           rs.close();
           preparedStatement.close();
           
           //Cookie cookies[] = request.getCookies();
           // String userID = cookies["id"].getValue();
           
           //String cookieName = keywordID;
           String userID = "";
           	           
           for (int i = 0; i < allCookies.length; i++ ) {
        	   String cookieIDName = allCookies[i].getName();
        	   if (cookieIDName.equals("ID")) {
        		   userID = allCookies[i].getValue();
        		   // out.println(userID);
        	   }
           }
           
           //out.println(userID);
           
           
           
           //out.println(productName);
           
          String updateSQL;
           
           if(partType.equals("case")) {
        	   updateSQL = "UPDATE userBuildsTable SET pc" + partType + " = ? WHERE id = ? ";
           } else {
        	   updateSQL = "UPDATE userBuildsTable SET " + partType + " = ? WHERE id = ? ";
           }

           //String updateSQL = "UPDATE userBuildsTable SET " + partType + " = ? WHERE id = ? ";
           preparedStatement = connection.prepareStatement(updateSQL);
           preparedStatement.setString(1, productName);
           preparedStatement.setString(2, userID);
           preparedStatement.execute();
		   
	       preparedStatement.close();
	       connection.close();
	       
	       
	       String title = "Database Result";
	       String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	                "transitional//en\">\n"; //
	       
	       //out.println("why are you not working!");
	          
	          out.println(docType + //
	    	  		  "<html>\n" + //
	    	            "<head><title>" + title + "</title>\n" + //
	    	            "</head>\n" + //
	    	            "<body>\n" + //
	    	            "<script>\n" + //
	    	            "location.href = \"/webproject/createNewBuild.html\"\n" + //
	    	            "document.cookie = \" " + partType + " =" + productName + "\";\n" + //
	    	            "</script>\n" + //
	    	            "</body>\n" + //
	        		  	"</html>");
			   
		   
		   
	          
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