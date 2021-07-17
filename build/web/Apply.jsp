
<%
String id=request.getParameter("id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<link rel="stylesheet" type="text/css" href="stile.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<style>
body{
background : url("image\\tree.jpg");
background-size: 1380px 660px;
background-attachment:fixed;
background-repeat: no-repeat;
background-position: center center;
}
#reg{
	background: rgba(0,0,0,0.4);
}   
label{
    color: white;
}
.custom-file-upload {
    border: 1px solid #ccc;
    display: inline-block;
    padding: 6px 12px;
    cursor: pointer;
    color: white;
    background-color: white;
}         
</style>
<script>
function myNum(){
var a=document.getElementById("phone").value;
if(a.length<10||a.length>10){
//console.log("hhhhhhhhhhhhhhhhhhhhhh");
document.getElementById("messages").innerHTML="enter valid phone number";
return false;
}

var str=document.getElementById("name").value.trim();
if(str.length<1){
document.getElementById("messagesName").innerHTML="Enter Valid name";
return false;
}
}
    function validateFileType(){
        var fileName = document.getElementById("fileName").value;
        var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="pdf"){
            //TO DO
        }else{
            alert("only jpg files are allowed!");
        }   
    }
</script>
    </head>
    
    <body>
        
<div class="container" style="padding-left:0px;">
<form id="reg" style="margin-top:30px;" onsubmit="return myNum()" action="Applied" method="POST" enctype="multipart/form-data" >
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
<div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">First Name</label>
      <input type="text" name="fname" class="form-control" id="inputEmail4" placeholder="First Name" required>
      <input type="hidden" name="id" value=<%=id%>/>
    </div>
    <div class="form-group col-md-6" >
      <label for="inputPassword4">Last Name</label>
      <input type="text" class="form-control" name="lname" id="inputPassword4" placeholder="Last Name" required>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label >email</label>
      <input type="email" class="form-control"  name="email" placeholder="ab@gmail.com" required>
	 
    </div>
    <div class="form-group col-md-6" >
      <label for="inputPassword4">Mobile</label>
      <input type="number" class="form-control" id="phone" name="mobile" placeholder="1234567890" required>
	  <span id="messages" style="color:red;"></span>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md" >
      <label for="inputPassword4">Address</label>
      <input type="text" class="form-control" id="inputPassword4" name="address" placeholder="123 Street" required>
    </div>
  </div>
  <div class="form-row">
  <div class="col-md-6" >
  <label>City:</label>
      <input type="text" class="form-control" name="city"  placeholder="City" required>
</div>
  <div class="col-md-6" >
<label>State:</label>
      <input type="text" class="form-control" name="state" placeholder="State" required>
</div>
      
  </div>
  <div class="form-row">

    <div class="form-group col-md-4" >
      <label >Experience</label>
      <input type="number" class="form-control"  name="exp" placeholder="0" required>
    </div>

      <div class="col-md-4 md-form" >
<label>Resume</label>
<label class="custom-file-upload" style="margin-top:0px;"><input style="color:black;" name="resume" type="file" id="fileName" accept=".pdf" onchange="validateFileType()"/></label>

</div>
      
  	<div class="form-group col-md-4">
      <label for="inputEmail4">Gender:</label><br>
	  <div class="form-check form-check-inline" style="margin-left:15px;">
  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="male" checked>
  <label class="form-check-label" for="inlineRadio1">M</label>
</div>
<div class="form-check form-check-inline" style="margin-left:55px;">
  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="female">
  <label class="form-check-label" for="inlineRadio2">F</label>
</div>
<div class="form-check form-check-inline" style="margin-left:55px;">
  <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3" value="other" >
  <label class="form-check-label" for="inlineRadio3">O</label>
</div>
    </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
  <input type="submit" class="btn btn-primary btn-lg btn-block" value="SUBMIT">
  </div>
  <div class="form-group col-md-6">
  <input type="reset" class="btn btn-danger btn-lg btn-block" value="CANCEL">
  </div>
  </div>
</form>
</div>


    </body>
</html>
