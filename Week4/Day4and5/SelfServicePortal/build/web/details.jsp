<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<% 
    if(session.getAttribute("email") == null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <title>Welcome - Self Service Portal</title>
        <link rel="stylesheet" href="css/styles.css"/>
        <script src="js/jquery-3.3.1.min.js"></script>
    </head>
    <style>
        body{
            display: block;
            margin: 0px;
        }
    </style>
    <body>
        <div class="ssp-toolbar">
            <a class="ssp-toolbar-title" href="details.jsp">Self Service Portal</a>
            <div id="toolbar_greet" class="ssp-toolbar-item"></div>
            <a class="ssp-toolbar-item" href="deactivate">Deactivate account</a>
            <a class="ssp-toolbar-item" href="logout.jsp">Logout</a>
        </div>
        <br><br><br>
        <div class="content">
            <div class="ssp-title">Welcome to the Self Service Portal</div>
            <br><br>
            <table>
                <tr>
                    <td>
                        <div class="ssp-subheading" style="padding: 8px;">NAME</div>
                    </td>
                    <td>
                        <div id="name" class="ssp-heading"style="padding: 12px;"></div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="ssp-subheading" style="padding: 8px;">EMAIL</div>
                    </td>
                    <td>
                        <div id="email" class="ssp-heading"style="padding: 12px;"></div>
                    </td>
                </tr>
                <tr>
                    <td style="vertical-align: top; padding: 10px;">
                        <div class="ssp-subheading" style="padding: 8px;">ADDRESS</div>
                    </td>
                    <td>
                        <div id="address" class="ssp-heading"style="padding: 12px;"></div>
                    </td>
                </tr>
            </table>
            <br><br>
            <button id="edit_btn" class="ssp-button">Edit details</button>
        </div>
    </body>
    <script>
        $(function(){
           $.post("getdetails", function(data){
                var arr = data.split("~");
                $("#toolbar_greet").html("Hi, "+arr[0]);
                $("#name").html(arr[0] + " " + arr[1]);
                $("#email").html(arr[2]);
                var temp = arr[3].split("$");
                if(temp.length===0){
                    $("#address").html('-- NA --');
                }else{
                    var addr = "";
                    for(i=0; i<temp.length; i++){
                        if(temp[i]==="null" || temp[i]==="0" || temp[i]===''){
                            continue;
                        }
                        addr += temp[i]+"<br>";
                    }
                    if(addr.length === 0){
                        addr = '-- NA --';
                    }
                        $("#address").html(addr);
                }   
            }); 
        });
        $("#edit_btn").click(function(){
            $(location).attr('href', 'editdetails.jsp');
        });
    </script>
</html>