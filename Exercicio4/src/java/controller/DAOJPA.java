/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.Query;
import model.BancoJPA;
import model.Redacao;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author prampero
 */
public class DAOJPA {

    public void cadastrar(Object obj) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            bb.sessao.getTransaction().begin();
            bb.sessao.persist(obj);
            bb.sessao.getTransaction().commit();
            BancoJPA.conexao.close();
        } catch (Exception ex) {
            throw new Exception("Erro ao gravar: " + ex.getMessage());
        }
    }
    public Object getById(Class tipo, final int id) throws Exception {
        BancoJPA bb;
        Object obj;
        try {
            bb = new BancoJPA();
            obj = bb.sessao.find(tipo, id);
            BancoJPA.conexao.close();
            return obj;
        } catch (Exception ex) {
            throw new Exception("Erro ao getById: " + ex.getMessage());
        } finally {

        }

    }
    public List<Redacao> listarTodos() throws Exception {
        BancoJPA bb;
        List<Redacao> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createNamedQuery("Redacao.findAll", Redacao.class).getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro ao listarTodos: " + ex.getMessage());
        }
    }
    public List<Redacao> listarTitulo(String titulo) throws Exception {
        BancoJPA bb;
        List<Redacao> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery(
                        "SELECT r FROM Redacao r WHERE r.titulo LIKE :titulo", Redacao.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro ao listar por título: " + ex.getMessage());
        }
    }

    public List<Redacao> listarNota(Double nota) throws Exception {
        BancoJPA bb;
        List<Redacao> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery(
                        "SELECT r FROM Redacao r WHERE r.nota = :nota", Redacao.class)
                    .setParameter("nota", nota)
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro ao listar por nota: " + ex.getMessage());
        }
    }
    
    public Double MediaNota() throws Exception {
        BancoJPA bb;
        Double media;
        try {
            bb = new BancoJPA();
            media = (Double) bb.sessao.createQuery("SELECT avg(r.nota) FROM Redacao r", Double.class)
                    .getSingleResult();
            BancoJPA.conexao.close();
            return media;
        } catch (Exception ex) {
            throw new Exception(" Erro no listar média: " + ex.getMessage());
        }
    }
    
    public List<Redacao> MaiorNota() throws Exception {
        BancoJPA bb;
        List<Redacao> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery(
                    "SELECT r FROM Redacao r WHERE r.nota = (SELECT max(r2.nota) FROM Redacao r2)",
                    Redacao.class)
                .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro no listar maior nota: " + ex.getMessage());
        }
    }

    public List<Redacao> MenorNota() throws Exception {
        BancoJPA bb;
        List<Redacao> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery(
                    "SELECT r FROM Redacao r WHERE r.nota = (SELECT min(r2.nota) FROM Redacao r2)",
                    Redacao.class)
                .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro no listar menor nota: " + ex.getMessage());
        }
    }
    
        public Double SomaNotas() throws Exception {
        BancoJPA bb;
        Double soma;
        try {
            bb = new BancoJPA();
            soma = bb.sessao.createQuery(
                    "SELECT SUM(r.nota) FROM Redacao r", Double.class)
                    .getSingleResult();
            BancoJPA.conexao.close();
            return soma != null ? soma : 0.0;
        } catch (Exception ex) {
            throw new Exception("Erro ao calcular a soma das notas: " + ex.getMessage());
        }
    }
        
    public Long QuantidadeNotas() throws Exception {
    BancoJPA bb;
    Long qtd;
    try {
        bb = new BancoJPA();
        qtd = bb.sessao.createQuery(
                "SELECT COUNT(r.nota) FROM Redacao r", Long.class)
                .getSingleResult();
        BancoJPA.conexao.close();
        return qtd != null ? qtd : 0L;
    } catch (Exception ex) {
        throw new Exception("Erro ao contar as notas: " + ex.getMessage());
    }
}



}
    

    
/*
    public List<Aluno> listarTodosSemCache() throws Exception {
        BancoJPA bb;
        Query consulta;
        List<Aluno> lista;
        try {
            bb = new BancoJPA();
            consulta = bb.sessao.createNamedQuery("Aluno.findAll", Aluno.class);
            consulta.setHint(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS);  //não utiliza o cache
            lista = consulta.getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception("Erro ao listarTodos: " + ex.getMessage());
        }
    }

    public List<Aluno> listar() throws Exception {
        BancoJPA bb;
        List<Aluno> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery("select a from Aluno a order by a.nome")
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            return (null);
        }
    }

    public List<Aluno> listarSemCache() throws Exception {
        BancoJPA bb;
        List<Aluno> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery("select a from Aluno a order by a.nome")
                    .setHint(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS) //não utiliza o cache
                    .getResultList();
            BancoJPA.conexao.close();
            return (lista);

        } catch (Exception ex) {
            return (null);
        }
    }

    public List<Nota> listarNotas(int codigo) throws Exception {
        BancoJPA bb;
        List<Nota> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery("SELECT n FROM Nota n where n.codaluno.codigo = :cod")
                    .setParameter("cod", codigo)
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            return (null);
        }
    }

    public List<Object> listaMedia() throws Exception {
        BancoJPA bb;
        List<Object> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery("SELECT a.nome, avg(n.valor) FROM Nota n inner join n.codaluno a group by a.codigo,a.nome")
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception(" Erro no listar média: " + ex.getMessage());
        }
    }

    public List<Object> listaAlunoMaiorNota() throws Exception {
        BancoJPA bb;
        List<Object> lista;
        try {
            bb = new BancoJPA();
            lista = bb.sessao.createQuery("select distinct a.nome, n.valor from Nota n inner join n.codaluno a "
                    + " where n.valor in (select max(n1.valor) from Nota n1) ")
                    .getResultList();
            BancoJPA.conexao.close();
            return lista;
        } catch (Exception ex) {
            throw new Exception(" Erro no listar maior nota: " + ex.getMessage());
        }
    }

    /*
    public List<Usuario> listarTodosUsuarios() throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return bb.sessao.createNamedQuery("Usuario.findAll" , Usuario.class).getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao listarTodosUsuarios: " + ex.getMessage());
        }
    }
    
    public List<Object> mensagens0(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT  m FROM Mensagem m  where m.codigoremetente in (select u from Usuario u where u.codigo=:cod) or m.codigoremetente in "
          + " (select a.codusuario from Amigo a, Usuario u where a.codamigo= u.codigo and u.codigo=:cod) order by m.datahora desc")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Mensagens: " + ex.getMessage());
        }
    }
    //Retorna a lista de mensagem que o usuario com codigo passado pode receber.
     public List<Object> mensagens(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return bb.sessao.createQuery("SELECT m FROM Mensagem m inner join m.codigoremetente u where  u.codigo=:cod or m.codigoremetente in "
          + " (select a.codusuario from Amigo a inner join a.codamigo u where u.codigo=:cod) order by m.datahora desc")
          .setParameter("cod", codigo)
          .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Mensagens: " + ex.getMessage());
        }
    }
    
    public Object login(String n, String s) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return   bb.sessao.createQuery("SELECT u FROM Usuario u where u.nome=:nome and u.senha=:senha")
                    .setParameter("nome",n)
                    .setParameter("senha", s).getSingleResult();
        } catch (Exception ex) {
            return(null);
        }
    }
    
    
    // retorna a lista de usuarios que receberam a declaracao de amizade do codigo recebido
    public List<Usuario> listarAmigos(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT a.codamigo FROM Amigo a inner join a.codusuario u where u.codigo=:cod")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar Amigos: " + ex.getMessage());
        }
    }
    
    // retorna a lista de usuarios que receberam a declaracao de amizade do codigo recebido
    public List<Usuario> listarUsuariosDeclaramAmizade(Integer codigo) throws Exception {
        BancoJPA bb;
        try {
            bb = new BancoJPA();
            return  bb.sessao.createQuery("SELECT a.codusuario FROM Amigo a inner join a.codamigo u where u.codigo=:cod")
                    .setParameter("cod", codigo)
                    .getResultList();
        } catch (Exception ex) {
            throw new Exception("Erro ao buscar quem declarou amizade: " + ex.getMessage());
        }
    }
     */


