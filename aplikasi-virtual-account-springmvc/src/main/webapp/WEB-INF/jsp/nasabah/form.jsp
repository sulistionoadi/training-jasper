<%-- 
    Document   : form
    Created on : Jun 4, 2012, 4:50:06 PM
    Author     : endy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Form Nasabah</title>
    </head>
    <body>
        <h1>Entri Data Nasabah</h1>
        
        <form:form modelAttribute="nasabah" method="post">
            <table>
                <tr>
                    <td>Nomer</td>
                    <td> <form:input path="nomer" /></td>
                    <td> <font style="color: red;"> <form:errors path="nomer"/> </font> </td>
                </tr>
                <tr>
                    <td>Nama</td>
                    <td><form:input path="nama" /></td>
                    <td> <form:errors path="nama"/> </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><form:input path="email" /></td>
                    <td> <form:errors path="email"/> </td>
                </tr>
                <tr>
                    <td>Tanggal Lahir</td>
                    <td><form:input path="tanggalLahir" /></td>
                    <td> <form:errors path="tanggalLahir"/> </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="Simpan" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
