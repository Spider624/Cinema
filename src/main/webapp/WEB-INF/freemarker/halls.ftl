<!DOCTYPE html>
<html>
<head>
    <title>Halls</title>
</head>
<style>
    body {
        height: 100vh;
        font-family: Verdana, sans-serif;
    }
    .container {
        width: 800px;
        height: 100%;
        display: flex;
        flex-direction: column;
        margin: 50px auto;
    }

    .container-content {
        width: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
    }

    .add-hall-form {
        width: 250px;
        margin-right: 50px;
        display: flex;
        justify-content: left;
    }
    input[type=text], input[type=password] {
        height: 25px;
        width: calc(100% - 10px);
        padding: 5px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }
    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }
    .createbtn {
        background-color: #5237d5;
        color: white;
        border: none;
        cursor: pointer;
        width: 100%;
        height: 35px;
        opacity: 0.9;
        font-size: 12pt;
    }
    .createbtn:hover {
        opacity:1;
        cursor: pointer;
    }

    .halls-list {
        width: calc(100% - 300px);
        display: flex;
        flex-direction: column;
    }
    .container-label {
        color: #5237d5;
        margin: 2px;
        font-size: 13pt;
        text-align: left;
    }
    table {
        font-size: 10pt;
        border-collapse: collapse;
    }
    hr {
        height: 2px;
        background-color: #5237d5;
        border: none;
        width: 100%;
        padding: 0;
        margin: 0 0 15px;
    }
    td, th {
        border: 1px solid #dddddd;
        text-align: center;
        padding: 4px;
    }
    th {
        background-color: #dddddd;
    }
</style>
<body>
<div class="container">
    <p class="container-label">Movie halls</p>
    <hr>
    <div class="container-content">
        <div class="add-hall-form">
            <form action="/admin/panel/halls" method="post">
                <label for="seatsCount"><b style="font-size: 10pt">Seats count</b></label>
                <input autocomplete="false" type="text" min="1" placeholder="Enter count" name="seatsCount" id="seatsCount" required>
                <button type="submit" class="createbtn" value="/admin/panel/halls">Create hall</button>
            </form>
        </div>
        <div class="halls-list">
            <table>
                <tr>
                    <th>ID</th>
                    <th>Seats count</th>
                    <th>Vacant</th>
                </tr>
                <#list model["halls"] as hall>
                    <tr>
                        <td>${hall.id}</td>
                        <td>${hall.seatsCount}</td>
                        <td>${hall.vacant?string("yes", "no")}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>
</body>
</html>