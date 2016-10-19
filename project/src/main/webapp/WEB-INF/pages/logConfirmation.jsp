<%-- 
    Document   : logConfirmation
    Created on : 2 oct. 2016, 08:34:44
    Author     : Pascal Sekley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Simple Login Form a Flat Responsive Widget Template :: w3layouts</title>
        <link rel="stylesheet" href="inc/css/style.css">

        <link href='//fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

        <!-- For-Mobile-Apps-and-Meta-Tags -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Simple Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //For-Mobile-Apps-and-Meta-Tags -->
        
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>
        </head>
        
        <body>

            <div style="text-align:right;">
                <button class="button" onclick="window.location.href='LogoutServlet'">Log Out</button>
            </div>
            <div style="text-align:right;">
                    <button class="button" onclick="window.location.href='RestrictedPagesServlet'">Restricted Page</button>
            <h1>Welcome ! Here you can see our content</h1>
            
            <div class="container">
                <div class="row">
                    <div class="col-md-1"></div>
                        <div class="col-md-11">
                        <table id="example2" class="table table-bordered table-hover" role="grid" aria-describedby="example2_info">
                            <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending">Name</th>
                                    <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Lastname</th>
                                    <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Username</th>
                                    <th class="sorting" tabindex="0" aria-controls="example2" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending">Email</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
  
            <script>
      $(function () {
        $("#example2").DataTable({
          ajax: {
            url: "api/people",
            dataSrc: ""
          },
          columns: [
            {data: "name"},
            {data: "lastname"},
            {data: "username"},
            {data: "email"}
          ]
        });
      });
            </script>
        </body>
        

</html>
