/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-08-14 11:42:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.Front;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jobs.JobDatabase;
import jobs.Job;
import jobs.ApplicationDAO;
import jobs.Application;
import java.util.Set;
import servlets.FlexibleJobsConstants;
import accounts.AccountDao;
import accounts.Account;
import accounts.PersonalData;

public final class EmployerJob_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");

    int jobId=Integer.parseInt(request.getParameter("jobId"));
    JobDatabase jobDao=(JobDatabase) request.getServletContext().getAttribute("jobDao");
    Job job=jobDao.getJob(jobId);

    ApplicationDAO appdao=(ApplicationDAO) request.getServletContext().getAttribute("appDao");
    Set<Application> apps=appdao.getApplicationsForJob(jobId);

    AccountDao accDao=(AccountDao) request.getServletContext().getAttribute("accountDao");

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <title>FlexibleJobs | World's best freelancing Webpage</title>\r\n");
      out.write("    ");
      out.print(job.getHeader());
      out.write("<br>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<a href=/Front/successfulLoginEmployer.jsp>Back</a><br>\r\n");
      out.write("<label>Status:");
      out.print(job.getJobStatus());
      out.write("</label><br>\r\n");
      out.write("<label>Date posted:");
      out.print(job.getDate());
      out.write("</label><br>\r\n");
      out.write("<label>");
      out.print(job.getDescription());
      out.write("</label><br>\r\n");
      out.write("<a href=/approve?jobId=");
      out.print(jobId);
      out.write(">Close Job</a><br>\r\n");
      out.write("<label>Applications:");
      out.print(apps.size());
      out.write("</label><br>\r\n");
      out.write("<br><br><br>\r\n");
      out.write("<label>Applications</label><br>\r\n");

    for (Application app:apps) {
        Account employee=accDao.selectByUsername(app.getEmployee());
        PersonalData data= employee.getPersonalData();
        
      out.write("\r\n");
      out.write("<a href=/Front/UserProfile.jsp?username=");
      out.print(employee.getUserName());
      out.write('>');
      out.print(data.getFirstName()+" "+data.getLastName());
      out.write("</a><br>\r\n");
      out.write("        ");

        if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_WAITING)){
            
      out.write("\r\n");
      out.write("<form>\r\n");
      out.write("    <input type=\"submit\" value=\"Interview\">\r\n");
      out.write("</form>\r\n");
      out.write("<form action=\"/appManager?jobId=+");
      out.print(jobId);
      out.write("&?employee=");
      out.print(app.getEmployee());
      out.write("\" method=\"post\">\r\n");
      out.write("    <input type=\"submit\" value=\"Hire\">\r\n");
      out.write("</form>\r\n");
      out.write("<form action=\"/appManager?jobId=");
      out.print(jobId);
      out.write("&?employee=");
      out.print(app.getEmployee());
      out.write("\" method=\"post\">\r\n");
      out.write("    <input type=\"submit\" value=\"Reject\">\r\n");
      out.write("</form><br>\r\n");
      out.write("            ");

        }else if(app.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_HIRED)){
            
      out.write("\r\n");
      out.write("<form action=\"/Front/approveWork.jsp?jobId=");
      out.print(jobId);
      out.write("&?employee=");
      out.print(app.getEmployee());
      out.write("\">\r\n");
      out.write("    <input type=\"submit\" value=\"Approve\">\r\n");
      out.write("</form>\r\n");
      out.write("<form action=\"/appManager?jobId=");
      out.print(jobId);
      out.write("&?employee=");
      out.print(app.getEmployee());
      out.write("\" method=\"post\">\r\n");
      out.write("    <input type=\"submit\" value=\"Reject\">\r\n");
      out.write("</form><br>\r\n");
      out.write("            ");

        }
    }
            
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
