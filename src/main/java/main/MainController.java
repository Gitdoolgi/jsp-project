package main.list;

import domain.Main;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String m = req.getParameter("m");
        if (m != null) {
            m = m.trim();
            /*switch (m) {
                //case "select": select; break;
            }*/
        }else
            mainPage(req, res);
    }

    private void mainPage(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        MainService service = MainService.getInstance();

        ArrayList<Main> main = service.mainPageS();

        String view = "mainPage.jsp";
        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req, res);
    }


}
