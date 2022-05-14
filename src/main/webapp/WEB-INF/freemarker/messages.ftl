<html>
<head>
    <title>Welcome!</title>
</head>

<style>
    .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:600px;max-width:100%;font-family:Calibri}
    .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
    .datatable tr {border:1px solid #888}
    .datatable td {padding:2px;border:1px solid #888}
</style>

<body>
<h1>Welcome John Doe!</h1>

<table class="datatable">
    <tr>
        <th>Id</th>  <th>Uuid</th> <th>Message</th>
    </tr>
    <#list model["messages"] as message>
        <tr>
            <td>${message.id}</td> <td>${message.uuid}</td> <td>${message.message}</td>
        </tr>
    </#list>
</table>
</body>
</html>