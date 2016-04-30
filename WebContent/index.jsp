<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="validations.js"></script>
<title>Find ip</title>
</head>
<body class="content">
<%! String ip; %> 
	<br>
	<br>
	<div c>


		<fieldset class="middlecontent">

			<legend>Find IP</legend>

			<form class="middlecontent" name="myform" action="Main" method="post">
				<table border="0">

					<tr>
						<td>Enter ID:</td>
						<td><input type="text" name="id" maxlength="5"
							required></td>
					</tr>
					<tr>
						<td>IP:</td>
						<td><input type="text" name="ip" maxlength="20" value="<%= (ip=(String)request.getSession().getAttribute("ip"))==null?"":ip %>"
							></td>
					</tr>

					<td><input type="submit" value="submit"></td>
					<td><input type="reset" value="reset"></td>
					<tr>
				</table>
				<input type="hidden" name="action" value="search">
			</form>
		</fieldset>

	</div>
</body>
</html>




