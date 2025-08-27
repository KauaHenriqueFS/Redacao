package view;

import controller.DAOJPA;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Redacao;

@WebServlet(name = "Listar", urlPatterns = {"/Listar"})
public class Listar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAOJPA dao;
        List<Redacao> lista;

        try {
            dao = new DAOJPA();
            lista = dao.listarTodos();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Redações</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Redações Cadastradas</h1>");

            if (lista.isEmpty()) {
                out.println("<p>Nenhuma redação cadastrada.</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Código</th>");
                out.println("<th>Título</th>");
                out.println("<th>Texto</th>");
                out.println("<th>Nota</th>");
                out.println("</tr>");

                for (Redacao r : lista) {
                    out.println("<tr>");
                    out.println("<td>" + r.getCodigo() + "</td>");
                    out.println("<td>" + r.getTitulo() + "</td>");
                    out.println("<td>" + r.getTexto() + "</td>");
                    out.println("<td>" + r.getNota() + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            out.println("<br><a href='index.html'>Voltar</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Erro - Servlet Listar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro no Listar: " + ex.getMessage() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista todas as redações cadastradas.";
    }
}
