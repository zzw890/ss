<%@ page contentType="text/html; charset=gb2312"%>
<%
String manager=(String)session.getAttribute("manager");
//楠�璇��ㄦ�锋�����诲�
if (manager==null || "".equals(manager)){
	response.sendRedirect("login.jsp");dsfsdfsdaf
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="118" valign="bottom" background="Images/top_bg.gif" bgcolor="#EEEEEE"><table width="73%" height="79"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="69" align="right" valign="bottom">dsdsfsddfds褰�����dsfsdfsd褰��ㄦ�凤�<%=manager%></td>
        </tr>
    </table></td>
  </tr>
</table>
