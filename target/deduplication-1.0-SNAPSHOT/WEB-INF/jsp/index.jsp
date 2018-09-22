<%-- 
    Document   : index
    Created on : Sep 22, 2018, 3:13:54 PM
    Author     : thakk
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        
        <script>
            $(document).ready(function(){
                $.ajax({
                    url:'getdata.htm',
                    success:function(response){
                        var data = JSON.parse(response);
                        var duplicate = data[0];
                        var non_duplicate = data[1];
                        for(var i in duplicate){
                            $('#duplicate_table').append('<tr><Td>'
                                    +duplicate[i].row_id
                                    +'<td>'+duplicate[i].first_name+'</td>'
                                    +'<td>'+duplicate[i].last_name+'</td>'
                                    +'<td>'+duplicate[i].company+'</td>'
                                    +'<td>'+duplicate[i].email+'</td>'
                                    +'<td>'+duplicate[i].address1+'</td>'
                                    +'<td>'+duplicate[i].address2+'</td>'
                                    +'<td>'+duplicate[i].zip+'</td>'
                                    +'<td>'+duplicate[i].city+'</td>'
                                    +'<td>'+duplicate[i].state_long+'</td>'
                                    +'<td>'+duplicate[i].state_short+'</td>'
                                    +'<td>'+duplicate[i].phone+'</td>'
                                    +'</td></tr>');
                        }
                        for(var i in non_duplicate){
                            $('#non_duplicate_table').append('<tr><Td>'
                                    +non_duplicate[i].row_id
                                    +'<td>'+non_duplicate[i].first_name+'</td>'
                                    +'<td>'+non_duplicate[i].last_name+'</td>'
                                    +'<td>'+non_duplicate[i].company+'</td>'
                                    +'<td>'+non_duplicate[i].email+'</td>'
                                    +'<td>'+non_duplicate[i].address1+'</td>'
                                    +'<td>'+non_duplicate[i].address2+'</td>'
                                    +'<td>'+non_duplicate[i].zip+'</td>'
                                    +'<td>'+non_duplicate[i].city+'</td>'
                                    +'<td>'+non_duplicate[i].state_long+'</td>'
                                    +'<td>'+non_duplicate[i].state_short+'</td>'
                                    +'<td>'+non_duplicate[i].phone+'</td>'
                                    +'</td></tr>');
                        }
                        
                    }
                });
            });
        </script>
    </head>
    <body>
        <table id="duplicate_table">
            <caption><u><b> Pontential Duplicate Entry </b></u></caption>
            <Tr>
                <Td>Row_id</Td>
                <Td>First Name</Td>
                <Td>Last Name</Td>
                <Td>Company</Td>
                <Td>Email</Td>
                <Td>Address1</Td>
                <Td>Address2</Td>
                <Td>Zip</Td>
                <Td>City</Td>
                <Td>State Long</Td>
                <Td>State Short</Td>
                <Td>Phone</Td>
            </Tr>
            
        </table>
        <br/><br/>
        <table id="non_duplicate_table">
            <caption><u><b> Pontential Non-Duplicate Entry </b></u></caption>
            <Tr>
                <Td>Row_id</Td>
                <Td>First Name</Td>
                <Td>Last Name</Td>
                <Td>Company</Td>
                <Td>Email</Td>
                <Td>Address1</Td>
                <Td>Address2</Td>
                <Td>Zip</Td>
                <Td>City</Td>
                <Td>State Long</Td>
                <Td>State Short</Td>
                <Td>Phone</Td>
            </Tr>
            
        </table>
    </body>
</html>
