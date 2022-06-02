
package servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {  
    public int counter = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {     
        //Grabs whether the "Edit" link was clicked or not
        String edit = request.getParameter("edit");
        
        //Check if viewnote.jsp should be displayed
        if(counter == 0) {
            //Path for text file
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
            //Read the file
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
            //Saves the first and second lines of the text file into the appropriate variables
            String title = br.readLine();
            String contents = br.readLine();
        
            Note note = new Note(title, contents);
            
            request.setAttribute("note", note);
            
            //Increments counter so that the viewnote.jsp is not displayed again
            counter++;
            
            //Load viewnote.jsp from this servlet
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            return;
        }
        //Check if editnote.jsp should be displayed
        else if (edit.equals("true")) {
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
        //Sets counter back to 0 so that viewnote.jsp is loaded
        counter = 0;
        doGet(request, response);
    }
}
