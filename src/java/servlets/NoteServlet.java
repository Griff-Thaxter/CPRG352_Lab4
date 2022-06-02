
package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        //Grabs whether the "Edit" link was clicked or not
        String edit = request.getParameter("edit");
        System.out.println(edit);
        
        //Check if viewnote.jsp should be displayed
        if(edit == null) {
            //Path for text file
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
            //Read the file
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
            //Saves the first and second lines of the text file into the appropriate variables
            String title = br.readLine();
            String contents = br.readLine();
        
            Note note = new Note(title, contents);
            
            request.setAttribute("note", note);
            
            //Load viewnote.jsp from this servlet
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            return;
        }
        //Check if editnote.jsp should be displayed
        else if (edit != null) {
            //Load editnote.jsp from this servlet
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request, response);
            return;
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        
        try ( 
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)))) {
            
            //User entered values
            String userTitle = request.getParameter("title");
            String userContents = request.getParameter("contents");
            
            //Prints values to the text file
            pw.println(userTitle);
            pw.println(userContents);
            pw.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
        doGet(request, response);
    }
}
