package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Person;

public class HelloWorldServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET requests will end up here!
        
        // Load a JSP from this servlet
        getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp").forward(request, response);
        // After a JSP has been loaded, stop the code call
        return; // VERY IMPORTANT!
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // POST requests will end up here!
        
        // Capture the incoming parameters from the JSP
        String firstname = request.getParameter("first_name");
        String lastname = request.getParameter("last_name");
        
        // Validation
        if( firstname == null || firstname.equals("") || lastname == null || lastname.equals("") ){
            // if validation fails:
            // Set attributes in the request object, to be forwarded to the JSP
            request.setAttribute("firstName", firstname);
            request.setAttribute("lastName", lastname);
            // use an attribute as a flag to indicate that an error has occured
            request.setAttribute("error",true);
            // load the form once again
            getServletContext().getRequestDispatcher("/WEB-INF/helloWorldForm.jsp").forward(request, response);
            // after a JSP has been loaded, stop the code call
            return;
        }
        
        // if validation passes:
        // we can safely create a new instance of our object
        Person helloPerson = new Person(firstname,lastname);
        // set the Person object as an attribute in the request object
        request.setAttribute("person", helloPerson);
        
        // Load the JSP
        getServletContext().getRequestDispatcher("/WEB-INF/sayHello.jsp").forward(request, response);
        // Stop the code call after loading a JSP
        return;
    }

}
