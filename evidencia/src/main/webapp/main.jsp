<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Welcome</title>
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/login-register.css">
</head>
<body>
    <div class="main-content">
        <form method="post">
            <center>
            <%
            if(session.getAttribute("login")==null || session.getAttribute("login")==" ")
            {
                    response.sendRedirect("index.jsp"); 
            }else
            {
                try
                {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/imc","root","");

                        if(request.getParameter("btn_createImc")!=null)
                        {
                                double calc = Math.pow(Double.parseDouble(session.getAttribute("user-height").toString()),2);
                                float imc = Float.parseFloat(session.getAttribute("user-weight").toString()) / (float)calc;
                                int id = Integer.parseInt(session.getAttribute("user-id").toString());
                                PreparedStatement pstmt=null;
                                pstmt=null;
                                pstmt=con.prepareStatement("CALL `imc`.`insert_imc`(?,?)");
                                pstmt.setInt(1,id);
                                pstmt.setFloat(2,imc);
                                pstmt.executeUpdate();

                                request.setAttribute("successMsg","Register Successfully...! Please login");

                                con.close(); //close connection
                        }
                }
                catch(Exception e)
                {
                        out.println(e);
                }
            }
            %>
            <h1 style="float:right"><a href="logout.jsp">Salir</a></h1>
            <div>
                <h1 style="float:left"> Welcome, <%=session.getAttribute("login")%> </h1>
            </div>
             <p> Peso: <%=session.getAttribute("user-weight")%> </p>      
             <p> Estatura: <%=session.getAttribute("user-height")%> </p>

             <sql:setDataSource
                var="myDS"
                driver="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost:3306/imc"
                user="root" password=""
            />
     
            <sql:query var="listImc" dataSource="${myDS}">
                SELECT * FROM imc.bodymassindex WHERE userid=<%=session.getAttribute("user-id")%>;
            </sql:query>
            <table border="1">
                <thead>
                    <th>Id</th>
                    <th>IMC</th>
                    <th>Fecha creacion</th>
                </thead>
                <tbody>
                    <c:forEach var="record" items="${listImc.rows}">
                        <tr>
                            <td><c:out value="${record.idbodymassindex}"/></td>
                            <td><c:out value="${record.imc}"/></td>
                            <td><c:out value="${record.createdDate}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="submit" name="btn_createImc" value="Generar IMC">
	</center>
        </form>		
    </div>
</body>

</html>
