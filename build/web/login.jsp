<%-- 
    Document   : login
    Created on : 6 thg 1, 2024, 13:06:36
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
                background-color: #e2e2e2;
                font-family: Arial, Helvetica, sans-serif;
                line-height: 1.8;
                font-size: 15px;
                color: #444444;
            }
            .container {
                background-color: #ffffff;
                margin: 0 auto;
                padding: 60px;
                width: 500px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.3);
                text-align: center;
            }
            h2 {
                color: #F37022;
                font-size: 30px;
                font-weight: 700;
                margin-bottom: 20px;
            }
            input[type=text], input[type=password] {
                padding: 15px;
                width: 100%;
                border: none;
                border-bottom: 2px solid #004c9e;
                border-radius: 0px;
                margin-bottom: 20px;
                font-size: 16px;
                color: #444444;
                font-weight: 600;
                background-color: #f2f2f2;
                outline: none;
                transition: all .3s ease-in-out;
            }
            input[type=text]:focus, input[type=password]:focus {
                border-bottom: 2px solid #ff6600;
            }
            input[type=submit] {
                background-color: #004c9e;
                color: #ffffff;
                padding: 15px;
                border: none;
                border-radius: 30px;
                cursor: pointer;
                font-size: 16px;
                font-weight: 600;
                transition: all .3s ease-in-out;
            }
            input[type=submit]:hover {
                background-color: #ff6600;

            }
            body {
                background-image: url(https://scontent.fhan14-3.fna.fbcdn.net/v/t39.30808-6/301991372_6149278151768401_86129550538882234_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=730e14&_nc_ohc=gd7jzupWLOkAX-ewOQo&_nc_ht=scontent.fhan14-3.fna&oh=00_AfAqRYLhPgq2RDT9d40jutpfk1XwwcO-S7nPRGbYxwwSCw&oe=6416A893);
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
    </head>
    <body>
        <div class="container">  
            <img width="160px" src="img/Logo_FPT_Education.png" alt="FPT University logo"/>
            <h3 style="color: red"> ${requestScope.error} </h3>
            <form action="login" method="post" style="display: flex;   justify-content: center; margin-right: 55px;">

                <table>
                    <tbody>
                        <tr>
                            <td>Username</td>
                            <td>
                                <input type="text" name="username" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td>
                                <input type="password" name="password" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="LOGIN" /></td>
                        </tr>
                    </tbody>
                </table>

            </form>
        </div>
    </body>
</html>
