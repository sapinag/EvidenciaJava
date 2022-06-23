<%@ page import="java.sql.*" %>  

<%
if(session.getAttribute("login")!=null)
{
	response.sendRedirect("main.jsp");
}
%>


<%
try
{
	Class.forName("com.mysql.jdbc.Driver");
	
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/imc","root","");
	
	if(request.getParameter("btn_register")!=null)
	{
		String firstname,lastname,gender,username,password;
		int age;
                float weight,height;
                Date dob;
                
		firstname=request.getParameter("txt_firstname");
		lastname=request.getParameter("txt_lastname");
                age=Integer.parseInt(request.getParameter("txt_age"));
                gender=request.getParameter("txt_gender");
                height=Float.parseFloat(request.getParameter("txt_height"));
                weight=Float.parseFloat(request.getParameter("txt_weight"));
		dob= Date.valueOf(request.getParameter("txt_dateofbirth"));
                username=request.getParameter("txt_username");
		password=request.getParameter("txt_password");
		
		PreparedStatement pstmt=null;
                pstmt=con.prepareStatement("CALL `imc`.`insert_person_user`(?,?,?,?,?,?,?,?,?)");
                
                pstmt.setString(1,firstname);
		pstmt.setString(2,lastname);
                pstmt.setInt(3,age);
                pstmt.setString(4,gender);
                pstmt.setFloat(5,height);
                pstmt.setFloat(6,weight);
                pstmt.setDate(7,dob);
		pstmt.setString(8,username);
		pstmt.setString(9,password);
		
		pstmt.executeUpdate();
		
		request.setAttribute("successMsg","Register Successfully...! Please login");

		con.close(); //close connection
	}
}
catch(Exception e)
{
	out.println(e);
}
%>  
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
		
	<title>Register</title>

	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/login-register.css">
	
	<script>	
	
		function validate()
		{
			var first_name= /^[a-z A-Z]+$/; //solo letras
			var last_name= /^[a-z A-Z]+$/; //solo letras
			var password_valid=/^[A-Z a-z 0-9 !@#$%&*()<>]{6,12}$/; //letras, caracteres y numeros
			
			var fname = document.getElementById("fname");
                        var lname = document.getElementById("lname");
                        var age = document.getElementById("age");
                        var height = document.getElementById("height");
                        var weight = document.getElementById("weight");
                        var dob = document.getElementById("dob");
                        var uname = document.getElementById("uname");
                        var password = document.getElementById("password");
			
			if(!first_name.test(fname.value) || fname.value==='') 
                        {
                            alert("Favor de escribir nombre solo con letras");
                            fname.focus();
                            fname.style.background = '#f08080';
                            return false;                    
                        }
			if(!last_name.test(lname.value) || lname.value==='') 
                        {
                            alert("Favor de escribir apellido solo con letras");
                            lname.focus();
                            lname.style.background = '#f08080';
                            return false;                    
                        }
                        if(age.value==='' || age.value < 15) 
                        {
                            alert("Edad invalida");
                            age.focus();
                            age.style.background = '#f08080';
                            return false;                    
                        }
			if(height.value==='' || height.value<1.5 || height.value>2.5) 
                        {
                            alert("Altura invalida");
                            height.focus();
                            height.style.background = '#f08080';
                            return false;                    
                        }
                        if(weight.value==='') 
                        {
                            alert("weight invalido");
                            weight.focus();
                            weight.style.background = '#f08080';
                            return false;                    
                        }
                        if(dob.value==='') 
                        {
                            alert("fecha de nacimiento invalida");
                            dob.focus();
                            dob.style.background = '#f08080';
                            return false;                    
                        }                        
			if(!password_valid.test(password.value) || password.value==='') 
                        {
                            alert("Contraseña debe de ser 6 a 12 caracteres con caracteres !@#$%&*()<>");
                            password.focus();
                            password.style.background = '#f08080';
                            return false;                    
                        }
		}
	</script>	

</head>

<body>
    <div class="main-content">
        <form class="form-register" method="post" onsubmit="return validate();">
            <div class="form-register-with-email">
                <div class="form-white-background">
                    <div class="form-title-row">
                        <h1>Register</h1>
                    </div>
				   
					<p style="color:green">				   		
					<%
					if(request.getAttribute("successMsg")!=null)
					{
						out.println(request.getAttribute("successMsg"));
					}
					%>
					</p>
				   
				   </br>
				   
                    <div class="form-row">
                        <label>
                            <span>Nombre</span>
                            <input type="text" name="txt_firstname" id="fname" placeholder="escribe nombre">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Apellidos</span>
                            <input type="text" name="txt_lastname" id="lname" placeholder="escribe apellidos">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Edad</span>
                            <input type="number" name="txt_age" id="age" placeholder="escribe tu edad">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Sexo</span>
                            <input type="text" name="txt_gender" id="gender" placeholder="sexo">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Altura</span>
                            <input type="number" name="txt_height" id="height" step=".01" placeholder="escribe tu altura">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Peso</span>
                            <input type="number" name="txt_weight" id="weight" step=".01" placeholder="escribe tu peso">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Fecha de nacimiento</span>
                            <input type="date" name="txt_dateofbirth" id="dob" placeholder="escribe tu fecha de nacimiento">
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span>Usuario</span>
                            <input type="text" name="txt_username" id="uname" placeholder="escribe usuario">
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span>Password</span>
                            <input type="password" name="txt_password" id="password" placeholder="escribe password">
                        </label>
                    </div>
                    <input type="submit" name="btn_register" value="Registrarte">	
                </div>	
                <a href="index.jsp" class="form-log-in-with-existing">Ya tienes una cuenta? <b> Inicia sesión aquí!</b></a>
            </div>
        </form>
    </div>
</body>
</html>
