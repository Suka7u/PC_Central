import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
              "<head>\n" + //
              "<style>\n" + //
              
              
              "body {\n"
              + "	background-image: url(\"wood.jpeg\");\n"
              + "	background-repeat: no-repeat;\n"
              + "	background-size: cover;\n"
              + "	background-position: center;\n"
              + "}\n"
              + "header {\n"
              + "    background-image: url(\"redPlaid.jpeg\");\n"
              + "    background-repeat: repeat;\n"
              + "    background-size: 225px;\n"
              + "    color:white;\n"
              + "    text-align:center;\n"
              + "    padding:5px;	\n"
              + "    height:15%; \n"
              + "    padding-top: 25px;\n"
              + "    font-size: 35pt;\n"
              + "}\n"
              + "#title {\n"
              + "	margin-top: 0px;\n"
              + "}\n"
              + "nav {\n"
              + "    line-height:30px;\n"
              + "    background-image: url(\"greenPlaid.jpeg\");\n"
              + "    background-repeat: repeat;\n"
              + "    background-size: 75px;\n"
              + "    height:79.7%;\n"
              + "    width:200px;\n"
              + "    float:left;\n"
              + "    padding:5px;	  \n"
              + "}\n"
              + "section {\n"
              + "    width:350px;\n"
              + "    float:left;\n"
              + "    padding:10px;	 	 \n"
              + "}\n"
              + "footer {\n"
              + "    background-color:black;\n"
              + "    color:white;\n"
              + "    clear:both;\n"
              + "    text-align:center;\n"
              + "    padding:5px;	 	 \n"
              + "}\n"
              + ".button-29 {\n"
              + "  align-items: center;\n"
              + "  appearance: none;\n"
              + "  background-image: radial-gradient(100% 100% at 100% 0, white 0, rgb(195, 31, 51) 100%);\n"
              + "  border: 0;\n"
              + "  border-radius: 6px;\n"
              + "  box-shadow: rgba(66, 9, 9, .4) 0 2px 4px,rgba(66, 9, 9, .3) 0 7px 13px -3px,rgb(111, 50, 56) 0 -3px 0 inset;\n"
              + "  box-sizing: border-box;\n"
              + "  color: #fff;\n"
              + "  cursor: pointer;\n"
              + "  display: inline-flex;\n"
              + "  width: 180px;\n"
              + "  height: 48px;\n"
              + "  justify-content: center;\n"
              + "  line-height: 1;\n"
              + "  list-style: none;\n"
              + "  overflow: hidden;\n"
              + "  padding-left: 16px;\n"
              + "  padding-right: 16px;\n"
              + "  position: relative;\n"
              + "  text-align: left;\n"
              + "  text-decoration: none;\n"
              + "  transition: box-shadow .15s,transform .15s;\n"
              + "  user-select: none;\n"
              + "  -webkit-user-select: none;\n"
              + "  touch-action: manipulation;\n"
              + "  white-space: nowrap;\n"
              + "  will-change: box-shadow,transform;\n"
              + "  font-size: 18px;\n"
              + "}\n"
              + "\n"
              + ".button-29:focus {\n"
              + "  box-shadow: rgb(195, 31, 51) 0 0 0 1.5px inset, rgba(66, 9, 9, .4) 0 2px 4px, rgba(66, 9, 9, .3) 0 7px 13px -3px, rgb(195, 31, 51) 0 -3px 0 inset;\n"
              + "}\n"
              + "\n"
              + ".button-29:hover {\n"
              + "  box-shadow: white 0 4px 8px, rgba(66, 9, 9, .3) 0 7px 13px -3px, rgb(195, 31, 51) 0 -3px 0 inset;\n"
              + "  transform: translateY(-2px);\n"
              + "}\n"
              + "\n"
              + ".button-29:active {\n"
              + "  box-shadow: rgb(195, 31, 51) 0 3px 7px inset;\n"
              + "  transform: translateY(2px);\n"
              + "}\n"
              + ".button-submit {\n"
              + "  align-items: center;\n"
              + "  appearance: none;\n"
              + "  background-image: radial-gradient(100% 100% at 100% 0, white 0, rgb(195, 31, 51) 100%);\n"
              + "  border: 0;\n"
              + "  border-radius: 6px;\n"
              + "  box-shadow: rgba(66, 9, 9, .4) 0 2px 4px,rgba(66, 9, 9, .3) 0 7px 13px -3px,rgb(111, 50, 56) 0 -3px 0 inset;\n"
              + "  box-sizing: border-box;\n"
              + "  color: #fff;\n"
              + "  cursor: pointer;\n"
              + "  display: inline-flex;\n"
              + "  width: 40px;\n"
              + "  height: 40px;\n"
              + "  justify-content: center;\n"
              + "  line-height: 1;\n"
              + "  list-style: none;\n"
              + "  overflow: hidden;\n"
              + "  padding-left: 16px;\n"
              + "  padding-right: 16px;\n"
              + "  position: relative;\n"
              + "  text-align: left;\n"
              + "  text-decoration: none;\n"
              + "  transition: box-shadow .15s,transform .15s;\n"
              + "  user-select: none;\n"
              + "  -webkit-user-select: none;\n"
              + "  touch-action: manipulation;\n"
              + "  white-space: nowrap;\n"
              + "  will-change: box-shadow,transform;\n"
              + "  font-size: 18px;\n"
              + "}\n"
              + "\n"
              + ".button-submit:focus {\n"
              + "  box-shadow: rgb(195, 31, 51) 0 0 0 1.5px inset, rgba(66, 9, 9, .4) 0 2px 4px, rgba(66, 9, 9, .3) 0 7px 13px -3px, rgb(195, 31, 51) 0 -3px 0 inset;\n"
              + "}\n"
              + "\n"
              + ".button-submit:hover {\n"
              + "  box-shadow: white 0 4px 8px, rgba(66, 9, 9, .3) 0 7px 13px -3px, rgb(195, 31, 51) 0 -3px 0 inset;\n"
              + "  transform: translateY(-2px);\n"
              + "}\n"
              + "\n"
              + ".button-submit:active {\n"
              + "  box-shadow: rgb(195, 31, 51) 0 3px 7px inset;\n"
              + "  transform: translateY(2px);\n"
              + "}\n"
              + "label {\n"
              + "	color: white;\n"
              + "	font-size: 17pt;\n"
              + "}\n"
              + "#mainSection {\n"
              + "	width: 80vw;\n"
              + "	color: white;\n"
              + "}\n"
              + "#loginORlogout:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#myRange {\n"
              + "  -webkit-appearance: none;\n"
              + "  width: 275px;\n"
              + "  height: 20px;\n"
              + "  border-radius: 10px;\n"
              + "  background: white;\n"
              + "  outline: none;\n"
              + "  -webkit-transition: .2s;\n"
              + "  transition: opacity .2s;\n"
              + "}\n"
              + "\n"
              + "#myRange:hover {\n"
              + "  opacity: 1;\n"
              + "}\n"
              + "\n"
              + "#myRange::-webkit-slider-thumb {\n"
              + "  -webkit-appearance: none;\n"
              + "  appearance: none;\n"
              + "  border: none;\n"
              + "  width: 40px;\n"
              + "  height: 40px;\n"
              + "  background: rgba(0, 0, 0, 0);\n"
              + "  background-image: url(\"tree.jpeg\");\n"
              + "  background-repeat: no-repeat;\n"
              + "  background-size: cover;\n"
              + "  cursor: pointer;\n"
              + "}\n"
              + "\n"
              + "#myRange::-moz-range-thumb {\n"
              + "  width: 25px;\n"
              + "  height: 25px;\n"
              + "  background: #04AA6D;\n"
              + "  cursor: pointer;\n"
              + "}\n"
              + "#loginORlogout:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "table {\n"
              + "	border-collapse: collapse;\n"
              + "	width: 85vw;\n"
              + "}\n"
              + "td, th {\n"
              + "	border: 3px solid rgb(64, 162, 102);\n"
              + "	text-align: left;\n"
              + "	padding: 10px;\n"
              + "}\n"
              + "#loginORlogout:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#loginORlogout:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:link {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:visited {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:hover {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}\n"
              + "#backHome:active {\n"
              + "	color: black;\n"
              + "	text-decoration: none;\n"
              + "}" + //
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
              // "		// window.onload = alert(\"Please Login!\");\n" + //
              "		document.getElementById(\"loginOrSignUp\").style.visibility = \"visible\";\n" + //
              "		\n" + //
              "	} else {\n" + //
              // "		// window.onload = alert(\"You are signed in as: \" + usernameWord);\n" + //
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
              // "		// window.onload = alert(\"Please Login!\");\n" + //
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
              // "		// window.onload = alert(\"Please Login!\");\n" + //
              "\n" + //
              "	} else {\n" + //
              // "		//window.onload = alert(\"You are signed in as: \" + usernameWord);\n" + //
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
              "<h1 id=\"title\"> Monitors </h1>\n" + //
              "</header>\n" + //
              "<nav>\n" + //
              "<section>\n" + //
              
              
			"<button class=\"button-29\" role=\"button\"><a style=\"color:white\" href=\"/webproject/loginOrSignUp.html\" onclick=\"\" id=\"loginORlogout\">Login or Sign up</a></button><p></p>\n"
			+ "\n"
			+ "<button class=\"button-29\" role=\"button\"><a style=\"color:white\" href=\"/webproject/homePage.html\" id=\"backHome\">Back to Home Page</a></button><p></p>\n"
			+ "\n"
			+ "<form action=\"DisplaySavedUserBuilds\" method=\"POST\" on>\n"
			+ "<input class=\"button-29\" role=\"button\" type=\"submit\" name=\"keyword1\" value=\"Your Saved Builds\" onclick=\"savedBuildLoginCheck()\">\n"
			+ "</form>" + //
			"<form action=\"CheckIfLoggedIn\" method=\"POST\" on>\n" + //
			"<input class=\"button-29\" role=\"button\" type=\"submit\" name=\"keyword1\" value=\"Preset Builds\" onclick=\"LoginCheck()\">\n" + //
			"</form>\n" + //
			"<button class=\"button-29\" role=\"button\"><a style=\"color:white; text-decoration:none;\" href=\"/webproject/monitorSearch.html\" id=\"backToFilter\">Back to Monitor Filter</a></button><p></p>\n" + //
    		  "</section>\n" + //
    		  "</nav>\n");
      
      Cookie partTypeCookie = new Cookie("partType","monitor");
      response.addCookie(partTypeCookie);
      
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
    	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
	                out.println("<td>" + id + "</td>");
	                out.println("<td>" + productName + "</td>");
	                out.println("<td>" + manufacturer + "</td>");
	                out.println("<td>" + size + "</td>");
	                out.println("<td>" + resolution + "</td>");
	                out.println("<td>" + refreshRate + "</td>");
	                out.println("<td>" + panelType + "</td>");
	                out.println("<td>" + price + "</td>");
	                
	                out.println("<td>");
		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
		            out.println("</form>");
		            out.println("</td>");
		            out.println("</tr>");
                }
             }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
             
             out.println("<section id=\"mainSection\">");
             out.println("<table style=\"width:1364px;\">");
             
             out.println("<tr>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">ID</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Product Name</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Manufacturer</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Size</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Resolution</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Refresh Rate</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Panel Type</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Price</th>");
             out.println("<th style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">Add Part to Build</th>");
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
     	            out.println("<tr style=\"background-color:rgba(0, 0, 0, 0.6); color:white; font-family:'Franklin Gothic Medium', 'Arial', Arial, sans-serif\">");
 	                out.println("<td>" + id + "</td>");
 	                out.println("<td>" + productName + "</td>");
 	                out.println("<td>" + manufacturer + "</td>");
 	                out.println("<td>" + size + "</td>");
 	                out.println("<td>" + resolution + "</td>");
 	                out.println("<td>" + refreshRate + "</td>");
 	                out.println("<td>" + panelType + "</td>");
 	                out.println("<td>" + price + "</td>");
 	                
 	                out.println("<td>");
 		            out.println("<form action=\"AddProductName\" method=\"POST\" on>");
		            out.println("<input class=\"button-submit\" style=\"font-size:0pt;\" type=\"submit\" name=\"keywordID\" value=" + id + "    Add Part>");
 		            out.println("</form>");
 		            out.println("</td>");
 		            out.println("</tr>");
                 }
              }
             
             out.println("</table>");
             out.println("</section>");
             
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
