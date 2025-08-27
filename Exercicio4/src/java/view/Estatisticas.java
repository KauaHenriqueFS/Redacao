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

@WebServlet(name = "Estatisticas", urlPatterns = {"/Estatisticas"})
public class Estatisticas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAOJPA dao;
        
        Double media;
        Double soma;
        Long qtde;
        try {
            dao = new DAOJPA();
            
            media = dao.MediaNota();
            soma = dao.SomaNotas();
            qtde = dao.QuantidadeNotas();

            List<Redacao> maiores = dao.MaiorNota();
            List<Redacao> menores = dao.MenorNota();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Estatísticas das Redações</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Estatísticas das Redações</h1>");
            out.println("<p><strong>Média das Notas:</strong> " + (media != null ? String.format("%.2f", media) : "Sem dados") + "</p>");
            out.println("<p><strong>Soma das Notas:</strong> " + String.format("%.2f", soma) + "</p>");
            out.println("<p><strong>Quantidade de Notas:</strong> " + qtde + "</p>");

            out.println("<h2>Redações com a MAIOR nota</h2>");
            if (maiores != null && !maiores.isEmpty()) {
                out.println("<table>");
                out.println("<tr><th>Código</th><th>Título</th><th>Texto</th><th>Nota</th></tr>");
                for (Redacao r : maiores) {
                    out.println("<tr>");
                    out.println("<td>" + r.getCodigo() + "</td>");
                    out.println("<td>" + r.getTitulo() + "</td>");
                    out.println("<td>" + r.getTexto() + "</td>");
                    out.println("<td>" + r.getNota() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>Nenhuma redação encontrada com maior nota.</p>");
            }

            // Menor nota
            out.println("<h2>Redações com a MENOR nota</h2>");
            if (menores != null && !menores.isEmpty()) {
                out.println("<table>");
                out.println("<tr><th>Código</th><th>Título</th><th>Texto</th><th>Nota</th></tr>");
                for (Redacao r : menores) {
                    out.println("<tr>");
                    out.println("<td>" + r.getCodigo() + "</td>");
                    out.println("<td>" + r.getTitulo() + "</td>");
                    out.println("<td>" + r.getTexto() + "</td>");
                    out.println("<td>" + r.getNota() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } else {
                out.println("<p>Nenhuma redação encontrada com menor nota.</p>");
            }

            out.println("<br><a href='index.html'>Voltar</a>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Erro - Servlet Estatísticas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Erro nas Estatísticas: " + ex.getMessage() + "</h1>");
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
        return "Servlet que mostra estatísticas das redações.";
    }
}
