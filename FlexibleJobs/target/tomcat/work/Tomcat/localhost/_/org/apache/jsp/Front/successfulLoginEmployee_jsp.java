/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-08 12:22:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Front;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import accounts.Account;
import accounts.AccountDao;

public final class successfulLoginEmployee_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>FlexibleJobs | World's best freelancing Webpage</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<nav class = \"menu\">\r\n");
      out.write("    <a href=\"/webapp/index.jsp\">\r\n");
      out.write("        <img src=\"/Front/logo.png\" style=\"width:100px;height:40px; float: left\">\r\n");
      out.write("    </a>\r\n");
      out.write("\r\n");
      out.write("    <a href=\"\">\r\n");
      out.write("        <p style=\"float: left\"> Find Job</p>\r\n");
      out.write("    </a>\r\n");
      out.write("\r\n");
      out.write("    ");

        String username = request.getParameter("username");
    
      out.write("\r\n");
      out.write("    <p style=\"float: right\"> Welcome, ");
      out.print( username);
      out.write(" </p>\r\n");
      out.write("\r\n");
      out.write("</nav>\r\n");
      out.write("<hr style=\"border-color: darkslategray\">\r\n");
      out.write("<hr style=\"border-color: darkslategray\">\r\n");
      out.write("\r\n");
      out.write("<div style=\"border-radius: 15px; position: absolute; margin: 150px; width:50%;height:70%; border-color: darkslategray;\r\n");
      out.write(" background-color: antiquewhite\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("    <div style=\"border-radius: 15px\">\r\n");
      out.write("        ");
 AccountDao accountDao = (AccountDao) request.getServletContext().getAttribute("accountDao");
        Account account = accountDao.SelectByUsername(username);
        
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div style=\"border-radius: 15px\">\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div style=\"border-radius: 15px\">\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
