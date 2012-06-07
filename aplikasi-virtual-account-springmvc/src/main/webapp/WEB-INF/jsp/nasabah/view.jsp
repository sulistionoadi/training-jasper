<%-- 
    Document   : form
    Created on : Jun 4, 2012, 4:50:06 PM
    Author     : endy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Data Nasabah</title>
    </head>
    <body>
        <h1>Lihat Data Nasabah</h1>
        
            <table>
                <tr>
                    <td>Nomer</td>
                    <td>${nasabah.nomer}</td>
                </tr>
                <tr>
                    <td>Nama</td>
                    <td>${nasabah.nama}</td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td>${nasabah.email}</td>
                </tr>
                <tr>
                    <td>Tanggal Lahir</td>
                    <td>${nasabah.tanggalLahir}</td>
                </tr>
            </table>
    </body>
</html>
