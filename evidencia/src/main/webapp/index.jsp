<%@ page import="java.sql.*" %>  

<%
    if (session.getAttribute("login") != null) {
        response.sendRedirect("main.jsp");
    }
%>

<%
    try {
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imc", "root", "");

        if (request.getParameter("btn_login") != null) {
            String username, dbpassword;
            Float weight, height;
            int id;

            String email, password;

            email = request.getParameter("txt_email");
            password = request.getParameter("txt_password");

            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement("select * "
                    + "from user_imc a "
                    + "inner join person_imc b "
                    + "on a.idperson = b.idperson "
                    + "where username=? AND password=?");
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                username = rs.getString("username");
                dbpassword = rs.getString("password");

                if (email.equals(username) && password.equals(dbpassword)) {
                    session.setAttribute("user-weight", rs.getString("weight"));
                    session.setAttribute("user-height", rs.getString("height"));
                    session.setAttribute("user-id", rs.getString("iduser"));
                    session.setAttribute("login", username);
                    response.sendRedirect("main.jsp");
                }
            } else {
                request.setAttribute("errorMsg", "usuario o contraseña incorrectos");
            }

            con.close();
        }

    } catch (Exception e) {
        out.println(e);
    }
%>

<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/login-register.css">

        <script>

            function validate()
            {
                var email = document.myform.txt_email;
                var password = document.myform.txt_password;

                if (email.value === null || email.value === "")
                {
                    window.alert("Favor de escribir su email");
                    email.style.background = '#f08080';
                    email.focus();
                    return false;
                }
                if (password.value === null || password.value === "")
                {
                    window.alert("Favor de escribir su contraseña");
                    password.style.background = '#f08080';
                    password.focus();
                    return false;
                }
            }

        </script>

    </head>

    <body>
        <div class="main-content">
            <form class="form-register" method="post" name="myform" onsubmit="return validate();">
                <div class="form-register-with-email">
                    <div class="form-white-background">
                        <div class="form-title-row">
                            <h1>Bienvenido</h1>
                        </div>
                        <p style="color:red">				   		
                            <%
                                if (request.getAttribute("errorMsg") != null) {
                                    out.println(request.getAttribute("errorMsg"));
                                }
                            %>
                        </p>
                        </br>
                        <div class="form-row">
                            <label>
                                <span>Usuario</span>
                                <input type="text" name="txt_email" id="email" placeholder="escribe usuario">
                            </label>
                        </div>
                        <div class="form-row">
                            <label>
                                <span>Contraseña</span>
                                <input type="password" name="txt_password" id="password" placeholder="escribe contraseña">
                            </label>
                        </div>
                        <input type="submit" name="btn_login" value="Entrar">
                    </div>
                    <a href="register.jsp" class="form-log-in-with-existing">Aún no tienes una cuenta? <b> Registrate aquí! </b></a>
                </div>
            </form>
        </div>
    </body>
</html>
