<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %><%--
  Created by IntelliJ IDEA.
  User: gratchuvalsky
  Date: 28.08.2022
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>PhoneBook</title>
  </head>
  <body>
  <a href="NewPhone.html">Add New</a>
  <hr>
  
  <%HashMap<String, ArrayList<String>> phonelist = (HashMap<String, ArrayList<String>>)request.getAttribute("phonelist");
  
  if(phonelist.size() != 0){
  
  for (String key : phonelist.keySet()) {
  	StringBuilder sb = new StringBuilder();
  	String prefix = "";
  	sb.append(key).append(": ");
  	ArrayList<String> tmp = phonelist.get(key);
  	if(tmp.size() != 0){
  		for(int i = 0; i < tmp.size(); i++){
  			sb.append(prefix).append(tmp.get(i));
  			prefix = ", ";
  		}
  	}
  	String txt = sb.toString();
  %>
	<p><%=txt%></p>
<%}
}
else{
%>
  Empty!
  <%}%>
  </body>
</html>
