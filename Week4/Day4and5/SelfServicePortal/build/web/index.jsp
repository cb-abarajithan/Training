<% 
    if(session.getAttribute("email") !=null){
        response.sendRedirect("details.jsp");
    }
%>
<html>
    <head>
        <title>Login - Self Service Portal</title>
        <link rel="stylesheet" href="styles.css"/>
        <script src="jquery-3.3.1.min.js"></script>
    </head>
  
    <style>
        
        .ssp-button{
            margin-bottom: 24px;
        }
        
        .ssp-title{
            padding-top: 24px;
        }
        
        .ssp-desc {
            padding-bottom: 24px;
            padding-top: 4px;
        }
        
        .login-box{
            width: 300px;
            padding: 16px;
            border-radius: 4px 4px 0px 0px;
            border: 1px solid #AAAAAA;
            background-color: #FDFDFD;
        }
        
        .a-register{
            width: 300px;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 24px 17px;
            text-align: center;
            text-decoration: none;
            text-align: center;
            background: #8888ff;
            color: #FFFFFF;
            font-weight: 600;
            font-size: 12px;
            border-radius: 0px 0px 4px 4px;
        }
        
        .ssp-error-text{
            width: 300px;
            margin-top: 4px;
            margin-right: 54px;
            text-align: right;
        }
        
    </style>

    <body>
        <div class="content">
            <div class="content login-box">
                <div class="ssp-title">Self Service Portal</div>
                <div class="ssp-desc">Login</div>
                <input id="email" class="ssp-text-box-line" type="text" placeholder="Email" required><br>
                <input id="pass" class="ssp-text-box-line" type="password" placeholder="Password" required>
                <div id="message" class="ssp-error-text"></div><br>
                <button id="login_btn" class="ssp-button">LOGIN</button>
            </div>
            <a class="a-register" href="register.jsp">New around here?</a>
        </div>
    </body>
    <script>
        $("#login_btn").click(function (){
            var email = $("#email").val();
            var pass = $("#pass").val();
            $.post("login", {email: email, pass: pass}, function(res){
                if(res==="SUCCESS"){
                    $(location).attr('href', 'details.jsp');
                }else{
                    $("#message").html(res);
                }
            });
        });
    </script>
</html>