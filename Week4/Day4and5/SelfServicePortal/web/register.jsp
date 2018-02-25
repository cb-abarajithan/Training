<% 
    if(session.getAttribute("email") != null){
        response.sendRedirect("details.jsp");
    }
%>
<html>
  <head>
    <title>Register - Self Service Portal</title>
    <link rel="stylesheet" href="css/styles.css"/>
    <script src="js/jquery-3.3.1.min.js"></script>
  </head>
  
  <style>
      .ssp-a{
          margin-top: 16px;
      }
  </style>

    <body>
        <div class="content">
            <div class="ssp-desc">Register to</div>
            <div class="ssp-title">Self Service Portal</div>
            <br><br><br>
            <div>
                <input class="ssp-text-box" type="text" id="fname" placeholder="First name">
                <input class="ssp-text-box" type="text" id="lname" placeholder="Last name">
            </div>
            <div>
                <input class="ssp-text-box" type="text" id="email" placeholder="Email">
                <input class="ssp-text-box" type="text" id="c_email" placeholder="Confirm email">
            </div>
            <div>
                <input class="ssp-text-box" type="password" id="pass" placeholder="Password">
                <input class="ssp-text-box" type="password" id="c_pass" placeholder="Confirm password">
            </div>
            <br><div id="msg" class="ssp-error-text"></div><br>
            <div>
                <button id="register_btn" class="ssp-button">Create an account</button>
            </div>
            <a class="ssp-a" href="index.jsp">Already have an account?</a>
        </div>
    </body>
    <script>
        $("#register_btn").click(function(){
            $.post("registeruser",{
                fname: $("#fname").val(),
                lname: $("#lname").val(),
                email: $("#email").val(),
                c_email: $("#c_email").val(),
                pass: $("#pass").val(),
                c_pass: $("#c_pass").val()
            },
            function(res){
                if(res==="SUCCESS"){
                    $(location).attr('href', 'index.jsp');
                }else{
                    $("#msg").html(res);
                }
            });
        });
    </script>
</html>