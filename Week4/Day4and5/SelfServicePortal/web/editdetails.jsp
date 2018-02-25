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
            <div class="ssp-title">Edit Details</div>
            <br><br>
            <table style="padding-right: 54px;">
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
                            <input id="line1" class="ssp-text-box" type="text" placeholder="Address Line 1">    
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="ssp-row">
                            <input id="line2" class="ssp-text-box" type="text" placeholder="Address Line 2">    
                        </div>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="ssp-row">
                            <input id="city" class="ssp-text-box" type="text" style="width: 122px;" placeholder="City">
                            <select id="state" class="ssp-text-box" style="width: 122px; height: 44px;">
                                <option value="State" selected disabled>State</option>
                            </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="ssp-row">
                            <input id="zip" class="ssp-text-box" type="text" style="width: 122px;" placeholder="Zip">
                            <select id="country" class="ssp-text-box" style="width: 122px; height: 44px;">
                                <option value="Country" selected disabled>Country</option>
                                <option value="India">India</option>
                                <option value="United States">United States</option>
                            </select>
                        </div>
                    </td>
                </tr>
            </table>
            <div id="status" class="ssp-pass-text">${update_status}</div>
            <br>
            <div>
                <button id="save_btn" class="ssp-button">Save</button>
                <a class="ssp-a" href="details.jsp" style="padding: 16px;">Discard</a>
            </div>
        </div>
    </body>
    <script>
        var onlyState = ["State"];
        var IndianStates = ["Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka",
                            "Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Orissa","Puducherry","Punjab", "Rajasthan","Sikkim","Tamil Nadu",
                            "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"];
        var usStates = ["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","District of Columbia","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine",
                            "Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania",
                            "Puerto Rico","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"];
        $(function(){
            $.post("getdetails", function(data){
                var arr = data.split("~");
                $("#toolbar_greet").html("Hi, "+arr[0]);
                $("#fname").val(arr[0]);
                $("#lname").val(arr[1]);
                $("#email").val(arr[2]);
                var addr = arr[3].split("$");
                $("#line1").val((addr[0]==='null')?"":addr[0]);
                $("#line2").val((addr[1]==='null')?"":addr[1]);
                $("#city").val((addr[2]==='null')?"":addr[2]);
                $("#zip").val((addr[3]==='0')?"":addr[3]);
                $("#country").val( (addr[5]==='null')?"Country":addr[5] );
                loadStates();
                $("#state").val( (addr[4]==='null')?"State":addr[4] );
            }); 
        });
        function loadStates(){
            var states = onlyState;
            if($("#country").val() === "United States"){
                states = usStates;
            }else if($("#country").val() === "India"){
                states = IndianStates;
            }
            var options = '';
            for(i=0;i<states.length;i++){
                options += "<option value=\""+states[i]+"\">"+states[i]+"</option>";
            }
            $("#state").html(options);
            //$("#state option:first").attr('disabled', 'disabled');
        }
        $("#country").on('change', function(){
            loadStates();
        });
        $("#save_btn").click(function(){
            $.post("editdetails", {
                fname: $("#fname").val(),
                lname: $("#lname").val(),
                line1: $("#line1").val(),
                line2: $("#line2").val(),
                city: $("#city").val(),
                zip: $("#zip").val(),
                state: ($("#state").val()==='State')?'':$("#state").val(),
                country: $("#country").val()
            }, 
            function(res){
                $("#status").html(res);
            }); 
        });
    </script>
</html>
