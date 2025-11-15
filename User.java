package login; //Define o pacote onde a classe está.

import java.sql.Connection; //Importa a interface Connection usada para representar a conexão com o banco.
import java.sql.DriverManager; //Importa DriverManager para obter conexões JDBC.
import java.sql.ResultSet; //ResultSet representa o resultado de uma query.
import java.sql.Statement; //Statement permite executar comandos SQL.

public class User { //Início da classe User.
    public Connection conectarBD() { //Método público que tenta criar/retornar uma Connection JDBC.
        Connection conn = null; //Inicializa conn com null. Caso algo dê errado, o método retornará null
        try { //Início do bloco onde se tenta carregar driver e conectar.
            Class.forName("com.mysql.Driver.Manager").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123"; // Monta a URL de conexão
            conn = DriverManager.getConnection(url); //Tenta obter a conexão com base na URL.
        } catch (Exception e) { } //captura toda Exception e não faz nada
        return conn; //Retorna Connection (ou null se falhou).
    }

    public String nome = ""; //Campo público que armazena o nome recuperado do BD.
    public boolean result = false; //Campo público que indica sucesso da verificação.

    public boolean verificarUsuario(String login, String senha) { //Método que tenta verificar credenciais de usuário. Recebe login e senha como String.
        String sql = ""; //Inicializa a variável sql com string vazia.
        Connection conn = conectarBD(); //Obtém a conexão chamando conectarBD().

        // INSTRUÇÃO SQL
        // sql = "select nome from usuarios ";
        sql = sql + "where login = '" + login + "'"; //Concatena a cláusula where.
        sql = sql + " and senha = '" + senha + "'"; //Continua montagem do where.
        try { //Início do bloco que executa query.
            Statement st = conn.createStatement(); //Cria um Statement.
            ResultSet rs = st.executeQuery(sql); //Executa a query e obtém ResultSet.
            if (rs.next()) { //Verifica se veio pelo menos uma linha no resultado.
                nome = rs.getString("nome"); //Obtém o valor da coluna nome do ResultSet e salva no campo público nome.
                result = true; //Define o campo result como true em caso de sucesso.
            }
        } catch (Exception e) { } //Captura qualquer exceção e não faz nada.
        return result; //Retorna o campo result.
    }
}
//fim da class
