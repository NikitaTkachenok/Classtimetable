<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Buildings</h2>

	<table border="1">

		<th>Id</th>
		<th>BuildingName</th>

		<c:forEach var="building" items="${buildings}" varStatus="status">
			<tr>
				<td>${building.id}</td>
				<td>${building.buildingName}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
