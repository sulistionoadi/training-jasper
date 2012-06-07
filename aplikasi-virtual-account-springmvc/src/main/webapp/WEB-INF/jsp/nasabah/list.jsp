<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daftar Nasabah</title>
    </head>
    <body>
        <h1>Daftar Nasabah</h1>

        <a href="form">Tambah Data Nasabah</a> <br/>
        <a href="list/cetak?format=pdf">PDF</a> | 
        <a href="list/cetak?format=xls">XLS</a> |
        <a href="list/direct">Direct</a>
        <table>
            <tr>
                <th>Nomer</th>
                <th>Nama</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${daftarNasabah}" var="nasabah">
                <tr>
                    <td>${nasabah.nomer}</td>
                    <td>${nasabah.nama}</td>
                    <td>
                        <a href="view?id=${nasabah.id}">lihat</a> | 
                        <a href="form?id=${nasabah.id}">edit</a> | 
                        <a href="delete?id=${nasabah.id}">hapus</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
