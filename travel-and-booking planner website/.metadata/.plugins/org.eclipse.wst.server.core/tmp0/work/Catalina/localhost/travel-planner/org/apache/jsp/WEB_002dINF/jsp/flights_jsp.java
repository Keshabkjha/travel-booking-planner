/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.102
 * Generated at: 2025-05-09 05:44:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import com.travelplanner.model.Flight;

public final class flights_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.travelplanner.model.Flight");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Flight> flights = (List<Flight>) request.getAttribute("flights");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <title>Book Flight - Travel Planner</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"../static/css/style.css\">\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            background: #f7fafd;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("        .navbar {\n");
      out.write("            width: 100%;\n");
      out.write("            background: linear-gradient(90deg, #2980b9 0%, #6dd5fa 100%);\n");
      out.write("            color: #fff;\n");
      out.write("            padding: 0.7em 0;\n");
      out.write("            box-shadow: 0 2px 8px rgba(41,128,185,0.08);\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            position: sticky;\n");
      out.write("            top: 0;\n");
      out.write("            z-index: 100;\n");
      out.write("        }\n");
      out.write("        .navbar .logo {\n");
      out.write("            font-size: 1.7em;\n");
      out.write("            font-weight: bold;\n");
      out.write("            margin-left: 2em;\n");
      out.write("            letter-spacing: 2px;\n");
      out.write("        }\n");
      out.write("        .navbar ul {\n");
      out.write("            list-style: none;\n");
      out.write("            display: flex;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("        .navbar ul li {\n");
      out.write("            margin: 0 1.2em;\n");
      out.write("        }\n");
      out.write("        .navbar ul li a {\n");
      out.write("            color: #fff;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-size: 1.1em;\n");
      out.write("            font-weight: 500;\n");
      out.write("            transition: color 0.2s;\n");
      out.write("            padding: 6px 12px;\n");
      out.write("            border-radius: 6px;\n");
      out.write("        }\n");
      out.write("        .navbar ul li a:hover, .navbar ul li a.active {\n");
      out.write("            background: rgba(255,255,255,0.18);\n");
      out.write("            color: #fff;\n");
      out.write("        }\n");
      out.write("        .navbar .user-info {\n");
      out.write("            margin-right: 2em;\n");
      out.write("            font-size: 1em;\n");
      out.write("            font-weight: 500;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("        }\n");
      out.write("        .navbar .user-info span {\n");
      out.write("            margin-right: 1em;\n");
      out.write("        }\n");
      out.write("        .flights-section {\n");
      out.write("            max-width: 1000px;\n");
      out.write("            margin: 2.5em auto;\n");
      out.write("            background: #fff;\n");
      out.write("            border-radius: 18px;\n");
      out.write("            box-shadow: 0 8px 32px rgba(41,128,185,0.10);\n");
      out.write("            padding: 2em 1em;\n");
      out.write("        }\n");
      out.write("        .flights-title {\n");
      out.write("            color: #2980b9;\n");
      out.write("            font-size: 2em;\n");
      out.write("            font-weight: bold;\n");
      out.write("            margin-bottom: 1.2em;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        .flights-grid {\n");
      out.write("            display: flex;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("            gap: 2em;\n");
      out.write("            justify-content: center;\n");
      out.write("        }\n");
      out.write("        .flight-card {\n");
      out.write("            background: linear-gradient(120deg, #6dd5fa 0%, #2980b9 100%);\n");
      out.write("            color: #fff;\n");
      out.write("            border-radius: 16px;\n");
      out.write("            box-shadow: 0 4px 16px rgba(41,128,185,0.10);\n");
      out.write("            width: 260px;\n");
      out.write("            max-width: 90vw;\n");
      out.write("            margin-bottom: 1.5em;\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction: column;\n");
      out.write("            align-items: center;\n");
      out.write("            padding: 1.5em 1em 1.2em 1em;\n");
      out.write("            transition: transform 0.2s, box-shadow 0.2s;\n");
      out.write("        }\n");
      out.write("        .flight-card:hover {\n");
      out.write("            transform: translateY(-6px) scale(1.03);\n");
      out.write("            box-shadow: 0 8px 32px rgba(41,128,185,0.18);\n");
      out.write("        }\n");
      out.write("        .flight-card img {\n");
      out.write("            width: 100%;\n");
      out.write("            max-width: 220px;\n");
      out.write("            height: 130px;\n");
      out.write("            object-fit: cover;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            margin-bottom: 1em;\n");
      out.write("            box-shadow: 0 2px 8px rgba(41,128,185,0.10);\n");
      out.write("        }\n");
      out.write("        .flight-card h3 {\n");
      out.write("            margin: 0.2em 0 0.5em 0;\n");
      out.write("            font-size: 1.3em;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        .flight-card p {\n");
      out.write("            font-size: 1em;\n");
      out.write("            margin-bottom: 1.2em;\n");
      out.write("            color: #f0f6fa;\n");
      out.write("        }\n");
      out.write("        .flight-card .price {\n");
      out.write("            font-size: 1.1em;\n");
      out.write("            font-weight: bold;\n");
      out.write("            margin-bottom: 1em;\n");
      out.write("        }\n");
      out.write("        .book-btn {\n");
      out.write("            background: #fff;\n");
      out.write("            color: #2980b9;\n");
      out.write("            border: none;\n");
      out.write("            padding: 10px 28px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 1em;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s, color 0.3s;\n");
      out.write("        }\n");
      out.write("        .book-btn:hover {\n");
      out.write("            background: #2980b9;\n");
      out.write("            color: #fff;\n");
      out.write("            border: 1px solid #fff;\n");
      out.write("        }\n");
      out.write("        @media (max-width: 700px) {\n");
      out.write("            .flights-section {\n");
      out.write("                padding: 1em 0.2em;\n");
      out.write("            }\n");
      out.write("            .flights-grid {\n");
      out.write("                flex-direction: column;\n");
      out.write("                gap: 1em;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <!-- Font Awesome for icons -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"navbar\">\n");
      out.write("        <div class=\"logo\">Travel Planner</div>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/dashboard\"><i class=\"fa fa-home\"></i> Dashboard</a></li>\n");
      out.write("            <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/search\"><i class=\"fa fa-search\"></i> Search</a></li>\n");
      out.write("            <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/flights\" class=\"active\"><i class=\"fa fa-plane\"></i> Flights</a></li>\n");
      out.write("            <li><a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/bookings\"><i class=\"fa fa-suitcase\"></i> My Bookings</a></li>\n");
      out.write("        </ul>\n");
      out.write("        <div class=\"user-info\">\n");
      out.write("            <span><i class=\"fa fa-user-circle\"></i></span>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"flights-section\">\n");
      out.write("        <div class=\"flights-title\">\n");
      out.write("            <i class=\"fa fa-plane\"></i> Book a Flight\n");
      out.write("        </div>\n");
      out.write("        <div class=\"flights-grid\">\n");
      out.write("            ");

                if (flights != null && !flights.isEmpty()) {
                    for (Flight f : flights) {
            
      out.write("\n");
      out.write("                <div class=\"flight-card\">\n");
      out.write("                    <img src=\"");
      out.print( f.getImageUrl() );
      out.write("\" alt=\"");
      out.print( f.getAirline() );
      out.write("\">\n");
      out.write("                    <h3>");
      out.print( f.getAirline() );
      out.write("</h3>\n");
      out.write("                    <p>\n");
      out.write("                        <i class=\"fa fa-plane-departure\"></i> <b>From:</b> ");
      out.print( f.getDeparture() );
      out.write("<br>\n");
      out.write("                        <i class=\"fa fa-plane-arrival\"></i> <b>To:</b> ");
      out.print( f.getArrival() );
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    <div class=\"price\">$");
      out.print( f.getPrice() );
      out.write("</div>\n");
      out.write("                    <form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/pay\" method=\"get\">\n");
      out.write("                        <input type=\"hidden\" name=\"type\" value=\"flight\">\n");
      out.write("                        <input type=\"hidden\" name=\"flightId\" value=\"");
      out.print( f.getId() );
      out.write("\">\n");
      out.write("                        <button class=\"book-btn\" type=\"submit\"><i class=\"fa fa-check\"></i> Book Flight</button>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            ");

                    }
                } else {
            
      out.write("\n");
      out.write("                <div style=\"color:#888; text-align:center; width:100%;\">No flights found.</div>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
