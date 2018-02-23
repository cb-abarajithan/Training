<%@page import="java.sql.*" %>
<%@page import="portal.service.*" %>
<% 
    if(session.getAttribute("email") == null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <title>Edit Details - Self Service Portal</title>
        <link rel="stylesheet" href="styles.css"/>
        <script src="jquery-3.3.1.min.js"></script>
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
            <div class="ssp-title">Edit Details</div>
            <br><br>
            <table>
                <tr>
                    <td>
                        <div class="ssp-subheading" style="padding: 8px;">NAME</div>
                    </td>
                    <td>
                        <div class="ssp-row">
                            <input class="ssp-text-box" type="text" id="fname" style="width: 122px;">
                            <input class="ssp-text-box" type="text" id="lname" style="width: 122px;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="ssp-subheading" style="padding: 8px;">EMAIL</div>
                    </td>
                    <td>
                        <div class="ssp-row">
                            <input id="email" class="ssp-text-box" style="background-color: #E5E5E5" type="text" disabled="true">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="ssp-subheading" style="padding: 8px;">ADDRESS</div>
                    </td>
                    <td>
                        <div class="ssp-row">
                            <input id="addr" class="ssp-text-box" type="text">    
                        </div>
                    </td>
                </tr>
            </table>
            <div id="status" class="ssp-pass-text">${update_status}</div>
            <br>
            <div>
                <button id="save_btn" class="ssp-button">Save</button>
                <a class="ssp-a" href="details.jsp">Discard</a>
            </div>
        </div>
    </body>
    <script>
        $(function(){
           $.post("getdetails", function(data){
                var arr = data.split("&");
                $("#toolbar_greet").html("Hi, "+arr[0]);
                $("#fname").val(arr[0]);
                $("#lname").val(arr[1]);
                $("#email").val(arr[2]);
                if(arr[3]===null || arr[3]==='null')
                    $("#addr").val('');
                else
                    $("#addr").val(arr[3]);
            }); 
        });
        $("#save_btn").click(function(){
            $.post("editdetails", {
                fname: $("#fname").val(),
                lname: $("#lname").val(),
                addr: $("#addr").val()
            }, 
            function(res){
                $("#status").html(res);
            }); 
        });
    </script>
</html>
