package tp5nicolas;/*
 * ZooServlet.java
 *
 * Created on March 20, 2004, 10:43 AM
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A servlet to display the animals.
 *
 * @author Philippe Poulard
 */
public class ZooServlet extends HttpServlet
{

    public final static String
            /** The path to the stylesheet. */
            XSLT_PATH = "WEB-INF/zoo.xsl", /**
 * The path to the XML doc.
 */
XML_PATH = "./tp5nicolas/zoo.xml";

    /**
     * Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        ServletContext webApp = this.getServletContext();
        try
        {


            // JAXP stuff here


        }
        catch(Exception ex)
        {
            throw new ServletException(ex);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            java.io.IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            java.io.IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "Zoo";
    }

}
