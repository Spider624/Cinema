<html>
<head>
    <title>Halls</title>
</head>

<style>
    .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:600px;max-width:100%;font-family:Calibri}
    .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
    .datatable tr {border:1px solid #888}
    .datatable td {padding:2px;border:1px solid #888}
</style>

<body>
<h1>Movie halls page!</h1>

<table class="datatable">
    <tr>
        <th>Id</th>  <th>Uuid</th> <th>Seats number</th>
    </tr>
    <#list model["halls"] as hall>
        <tr>
            <td>${hall.id}</td> <td>${hall.uuid}</td> <td>${hall.seatsCount}</td>
        </tr>
    </#list>
</table>
</body>
</html>