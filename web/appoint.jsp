
<%@page import="databaseClasses.customer"%>
<%
customer cust=(customer)session.getAttribute("cust");
if(cust==null)
{
    session.setAttribute("msg","please login first");
    response.sendRedirect("home.jsp");
}
else
{
%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="jquery-ui.min.css">
<style>
body{
	background-image:url(image//clock.jpg);
	background-repeat: no-repeat;
	background-size:1370px 660px;
	background-attachment:fixed;
	font-family:new time roman;
}
label{
font-size:30px;
	font-family:cursive;
	color:grey;
        padding-left: 10px;
}
input{
	 background: transparent;
	 border-radius:0px;
	 border:0px;
	 border-bottom: 1px solid white;
	 color:white;
}
.dropin {
	display: block;
	font-size: 16px;
	font-family: sans-serif;
	font-weight: 700;
	color: #444;
	line-height: 1.0;
	padding: .6em 1.4em .5em .8em;
	width: 100%;
	max-width: 100%;
	box-sizing: border-box;
	margin: 0;
	border: 1px solid #aaa;
	box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
	border-radius: .5em;
	-moz-appearance: none;
	-webkit-appearance: none;
	appearance: none;
}
</style>
</head>

<body>
<div class="container" style="padding-left:100px;padding-top:100px;">
   <%
           String msg=(String)session.getAttribute("msg");
        if(msg!=null)
        {
    %>
    <p style="color:red;"><%=msg%></p>
    <%
               }
            msg=null;
            session.setAttribute("msg",msg);
    %>
    <form action="appointCustomer" method="POST">
        <div class="row">
<label>Name: </label>
<label><%=cust.getName() %></label><br><br>
        </div>
        <div class="row">
            <label>Age:</label><label><%=cust.getAge() %></label><br><br></div>
            <div class="row">
                <label>Email:</label><label><%=cust.getEmail() %></label><br><br></div>
                <div class="row">
                    <label>Gender:</label><label><%=cust.getGender() %></label><br><br></div>
                    <div class="row">
                        <div class="col-md-2">
                            <label>Date:</label></div>
                        <div class="col-md-4">
                        <input placeholder="Selected date" type="text" name="dob" id="date-picker-example" class="form-control" style="margin-top:5px;" size="10" required/>
                        </div>
                        </div>
                    <div class="row">
                        <div class="col-md-2">
                            <label>Time:</label></div>
                        <div class="col-md-4">
                        <select id="inputState" class="dropin" name="time" style="background:transparent;background-color:white;border-radius:0px;border:0px;border-bottom: 1px solid white;margin-top:5px; width:220px;">
        <option selected>12:00</option>
        <option>12:30</option>
		<option>13:00</option>
		<option>13:30</option>
		<option>14:00</option>
		<option>14:30</option>
      </select>
                        </div>
                        </div>
                    <div class="row">
                        <label style="padding-top:25px;">Description:</label>

<textarea  name="prob" placeholder="Problem/Illness" cols="35" style="background: transparent;border-radius:0px;border:0px;border-bottom: 1px solid white;
	 color:white;margin-left:20px;" rows="3"></textarea>
         <br><br></div>
                    <br>
<input class="btn btn-lg btn-success" type="submit" style="border-radius:15px;margin-left:200px;" value="Submit" />

    </form>
</div>
<script src="jquery-3.4.1.min.js"></script>
<script src="jquery-ui.min.js"></script>
<script>
$(document).ready(function(){
$('#date-picker-example').datepicker({
dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true,
minDate: new Date(new Date().getTime()+(24*60*60*1000)),
maxDate: new Date(new Date().getTime()+(31*24*60*60*1000))
});
})
</script>
</body>
</html>

<%
}
%>