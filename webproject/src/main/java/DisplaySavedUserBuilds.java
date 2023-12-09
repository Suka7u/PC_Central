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

@WebServlet("/DisplaySavedUserBuilds")
public class DisplaySavedUserBuilds extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DisplaySavedUserBuilds() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String keyword1 = request.getParameter("keyword1"); // keyword1: AMD
      search(keyword1, response, request);
   }

   void search(String keyword1, HttpServletResponse response, HttpServletRequest request) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
    		  "<html>\n" + //
              "<head>\n" + //
              "<style>table {\n" + //
              "border-collapse: collapse;\n" + //
              "}\n" + //
              "td, th {\n" + //
              "border: 1px solid black;\n" + //
              "text-align: left;\n" + //
              "padding: 10px;\n" + //
              "}\n" + //
              
			  "header {\n" + //
			  "background-color:black;\n" + //
			  "color:white;\n" + //
			  "text-align:center;\n" + //
			  "padding:5px;\n" + //
			  "}\n" + //
			  "nav {\n" + //
			  "line-height:30px;\n" + //
			  "background-color:#eeeeee;\n" + //
			  "height:300px;\n" + //
			  "width:200px;\n" + //
			  "float:left;\n" + //
			  "padding:5px;\n" + //
              "}\n" + //
              "section {\n" + //
              "width:350px;\n" + //
              "float:left;\n" + //
              "padding:10px;\n" + //	 	 
              "}\n" + //
              "footer {\n" + //
              "background-color:black;\n" + //
              "color:white;\n" + //
              "clear:both;\n" + //
              "text-align:center;\n" + //
              "padding:5px;\n" + //
              "}\n" + //
              "#loginORlogout:link {\n" + //
              "color: black;\n" + //
              "text-decoration: none;\n" + //
              "}\n" + //
              "#loginORlogout:visited {\n" + //
              "color: black;\n" + //
              "text-decoration: none;\n" + //
              "}\n" + //
              "#loginORlogout:hover {\n" + //
              "color: black;\n" + //
              "text-decoration: none;\n" + //
              "}\n" + //
              "#loginORlogout:active {\n" + //
              "color: black;\n" + //
              "text-decoration: none;\n" + //
              "}\n" + //      
			  "#backHome:link {\n" + //
        	  "color: black;\n" + //
        	  "text-decoration: none;\n" + //
        	  "}\n" + //
        	  "#backHome:visited {\n" + //
        	  "color: black;\n" + //
        	  "text-decoration: none;\n" + //
        	  "}\n" + //
        	  "#backHome:hover {\n" + //
        	  "color: black;\n" + //
        	  "text-decoration: none;\n" + //
        	  "}\n" + //
        	  "#backHome:active {\n" + //
        	  "color: black;\n" + //
        	  "text-decoration: none;\n" + //
        	  "}\n" + //
              "</style>\n" + //
        	  
              "<script>\n" + //
              "	\n" + //
              "function checkForUsername() {\n" + //
              "	let username = document.cookie;\n" + //
              "	const myArray = username.split(\"=\");\n" + //
              "	let word = myArray[1];\n" + //
              "	\n" + //
              "	const userWord = word.split(\";\");\n" + //
              "	let usernameWord = userWord[0];\n" + //
              "	\n" + //
              "	if (typeof word === \"undefined\") {\n" + //
              "		document.getElementById(\"loginORlogout\").setAttribute(\"onclick\", \"\");\n" + //
              "		document.getElementById(\"loginORlogout\").setAttribute(\"href\", \"/webproject/loginOrSignUp.html\");\n" + //
              "		document.getElementById(\"loginORlogout\").innerHTML = \"Login or Sign up\";\n" + //
              "		//document.getElementById(\"loginOrSignUp\").style.visibility = \"visible\";\n" + //
              "		//document.getElementById(\"logout\").style.visibility = \"hidden\";\n" + //
              "	} else {\n" + //
              "		document.getElementById(\"loginORlogout\").setAttribute(\"onclick\", \"logOut()\");\n" + //
              "		document.getElementById(\"loginORlogout\").setAttribute(\"href\", \"/webproject/homePage.html\");\n" + //
              "		document.getElementById(\"loginORlogout\").innerHTML = \"Log Out\";\n" + //
              "		//document.getElementById(\"loginOrSignUp\").style.visibility = \"hidden\";\n" + //
              "		//document.getElementById(\"logout\").style.visibility = \"visible\";\n" + //
              "	}\n" + //
              "	\n" + //
              "}\n" + //
              "\n" + //
              "function LoginCheck() {\n" + //
              "	let username = document.cookie;\n" + //
              "	const myArray = username.split(\"=\");\n" + //
              "	let word = myArray[1];\n" + //
              "	\n" + //
              "	const userWord = word.split(\";\");\n" + //
              "	let usernameWord = userWord[0];\n" + //
              "	\n" + //
              "	if (typeof word === \"undefined\") {\n" + //
              "		location.href = \"/webproject/loginOrSignUp.html\";\n" + //
              "		// window.onload = alert(\"Please Login!\");\n" + //
              "		document.getElementById(\"loginOrSignUp\").style.visibility = \"visible\";\n" + //
              "		\n" + //
              "	} else {\n" + //
              "		// window.onload = alert(\"You are signed in as: \" + usernameWord);\n" + //
              "		document.getElementById(\"loginOrSignUp\").style.visibility = \"visible\";\n" + //
              "	}\n" + //
              "	\n" + //
              "}\n" + //
              "\n" + //
              "function savedBuildLoginCheck() {\n" + //
              "	let username = document.cookie;\n" + //
              "	const myArray = username.split(\"=\");\n" + //
              "	let word = myArray[1];\n" + //
              "	\n" + //
              "	if(typeof word === \"undefined\"){\n" + //
              "		location.href = \"/webproject/loginOrSignUp.html\";\n" + //
              "	}\n" + //
              "	\n" + //
              "	const userWord = word.split(\";\");\n" + //
              "	let usernameWord = userWord[0];\n" + //
              "	\n" + //
              "	if(typeof word === \"undefined\"){\n" + //
              "		location.href = \"/webproject/loginOrSignUp.html\";\n" + //
              "		// window.onload = alert(\"Please Login!\");\n" + //
              "\n" + //
              "	} else {\n" + //
              "		//window.onload = alert(\"You are signed in as: \" + usernameWord);\n" + //
              "		location.href = \"/webproject/yourSavedBuilds.html\";\n" + //
              "	}\n" + //
              "	\n" + //
              "}\n" + //
              "\n" + //
              "function allBuildsLoginCheck() {\n" + //
              "	let username = document.cookie;\n" + //
              "	const myArray = username.split(\"=\");\n" + //
              "	let word = myArray[1];\n" + //
              "	\n" + //
              "	if(typeof word === \"undefined\"){\n" + //
              "		location.href = \"/webproject/loginOrSignUp.html\";\n" + //
              "	}\n" + //
              "	\n" + //
              "	const userWord = word.split(\";\");\n" + //
              "	let usernameWord = userWord[0];\n" + //
              "	\n" + //
              "	if(typeof word === \"undefined\"){\n" + //
              "		location.href = \"/webproject/loginOrSignUp.html\";\n" + //
              "		// window.onload = alert(\"Please Login!\");\n" + //
              "\n" + //
              "	} else {\n" + //
              "		//window.onload = alert(\"You are signed in as: \" + usernameWord);\n" + //
              "		location.href = \"/webproject/allUserBuilds.html\";\n" + //
              "	}\n" + //
              "	\n" + //
              "}\n" + //
              "\n" + //
              "function logOut(){\n" + //
              "		let allCookies = document.cookie;\n" + //
              "		let allCookieArray = allCookies.split(';');\n" + //
              "		\n" + //
              "		for (let i = 0; i < allCookieArray.length; i++) {\n" + //
              "			let eachCookie = allCookieArray[i];\n" + //
              "			const cookieNameArray = eachCookie.split(\"=\");\n" + //
              "			var cookieName = cookieNameArray[0];\n" + //
              "			//console.log(cookieName);\n" + //
              "			\n" + //
              "\n" + //
              "			document.cookie = \"\"+ cookieName + \"= ; expires = Thu, 01 Jan 1970 00:00:00 GMT\"\n" + //
              "			\n" + //
              "		}\n" + //
              "		\n" + //
              "}\n" + //
              "	\n" + //
              "</script>" + //
              
              "</head>\n" + //
              "<body onload=\"checkForUsername()\">\n" + //
              "<header>\n" + //
              "<h1> Your Saved Builds </h1>\n" + //
              "</header>\n" + //
              "<nav>\n" + //
              "<section>\n" + //
              "<button><a href=\"/webproject/loginOrSignUp.html\" onclick=\"\" id=\"loginORlogout\">Login or Sign up</a></button><p></p>\n" + //
              "<button><a href=\"/webproject/homePage.html\" id=\"backHome\">Back to Home Page</a></button><p></p>\n" + //
              "<form action=\"DisplayAllBuilds\" method=\"POST\" on>\n" + //
              "<input type=\"submit\" name=\"keyword1\" value=\"All Community Builds\" onclick=\"allBuildsLoginCheck()\">\n" + //
              "</form>\n" + //
    		  "<form action=\"CheckIfLoggedIn\" method=\"POST\" on>\n" + //
    		  "<input type=\"submit\" name=\"keyword1\" value=\"Preset Builds\" onclick=\"LoginCheck()\">\n" + //
    		  "</form>\n" + //
    		  "</section>\n" + //
    		  "</nav>\n");
      
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
    	 DBConnection.getDBConnection();
         connection = DBConnection.connection;
         
         Cookie[] cookies = request.getCookies();
         String cookieName = "username";
         String selectSQL = "";
         
         for ( int i = 0; i < cookies.length; i++) {
        	 Cookie cookie = cookies[i];
        	 if (cookieName.equals(cookie.getName())) {
        		 selectSQL = "SELECT * FROM userBuildsTable WHERE username = \"" + cookie.getValue() + "\";";
        		 break;
        	 }
         }

         preparedStatement = connection.prepareStatement(selectSQL);
         preparedStatement.execute();
         
         ResultSet rs = preparedStatement.executeQuery();
         
         out.println("<table>");
         
         out.println("<tr>");
         out.println("<th>CPU</th>");
         out.println("<th>CPU Cooler</th>");
         out.println("<th>GPU</th>");
         out.println("<th>Memory</th>");
         out.println("<th>Motherboard</th>");
         out.println("<th>Storage</th>");
         out.println("<th>Power Supply</th>");
         out.println("<th>Case</th>");
         out.println("<th>Monitor</th>");
         out.println("</tr>");

         while (rs.next()) {
            String cpu = rs.getString("CPU").trim();
            String cpuCooler = rs.getString("CPUCOOLER").trim();
            String gpu = rs.getString("GPU").trim();
            String memory = rs.getString("MEMORY").trim();
            String motherboard = rs.getString("MOTHERBOARD").trim();
            String storage = rs.getString("STORAGE").trim();
            String powerSupply = rs.getString("POWERSUPPLY").trim();
            String pcCase = rs.getString("PCCASE").trim();
            String monitor = rs.getString("MONITOR").trim();
                
            out.println("<tr>");
            out.println("<td>" + cpu + "</td>");
            out.println("<td>" + cpuCooler + "</td>");
            out.println("<td>" + gpu + "</td>");
            out.println("<td>" + memory + "</td>");
            out.println("<td>" + motherboard + "</td>");
            out.println("<td>" + storage + "</td>");
            out.println("<td>" + powerSupply + "</td>");
            out.println("<td>" + pcCase + "</td>");
            out.println("<td>" + monitor + "</td>");
            out.println("</tr>");
	            	            
            }
         
         out.println("</table>");
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