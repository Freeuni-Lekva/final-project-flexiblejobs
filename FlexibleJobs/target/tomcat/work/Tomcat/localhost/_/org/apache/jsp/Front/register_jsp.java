/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-08 20:29:56 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Front;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import servlets.FlexibleJobsConstants;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>FlexibleJobs | World's best freelancing Webpage</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"/registerHandler\" method=\"post\">\r\n");
      out.write("    <label for=\"usernameInput\">username: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"username\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter username\" id=\"usernameInput\">\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"firstnameInput\">firstname: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"firstname\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter firstname\" id=\"firstnameInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"lastnameInput\">lastname: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"lastname\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter lastname\" id=\"lastnameInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"livingPlaceInput\">living place: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"livingPlace\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter living place\" id=\"livingPlaceInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"profileHeadingInput\">profile heading: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"profileHeading\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter profile heading\" id=\"profileHeadingInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"profileDescriptionInput\">profile description: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"profileDescription\" type=\"text\"\r\n");
      out.write("           placeholder=\"Enter profile description\" id=\"profileDescriptionInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"passwordInput\">password: </label>\r\n");
      out.write("    <input style=\"background-color: palegreen; border-radius: 15px\" name=\"password\" type=\"password\"\r\n");
      out.write("           placeholder=\"Enter password\" id=\"passwordInput\">\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <label for=\"typeChoice\"> choose type:</label>\r\n");
      out.write("    <select name=\"type\" id=\"typeChoice\">\r\n");
      out.write("        <option value=\"employee\"> Employee</option>\r\n");
      out.write("        <option value=\"employer\"> Employer</option>\r\n");
      out.write("        <option value=\"administrator\"> Administrator</option>\r\n");
      out.write("    </select>\r\n");
      out.write("\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("\r\n");
      out.write("    <input style=\"border-radius: 15px; background-color: palegreen\" type=\"submit\" value=\"Login\">\r\n");
      out.write("    <br>\r\n");
      out.write("    <br>\r\n");
      out.write("</form>\r\n");
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