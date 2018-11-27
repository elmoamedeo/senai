package entidade;

public class Coordenador {
    
    // ID DO COORDENADOR
    private Integer id;
    
    // NOME DO COORDENADOR
    private String nome;
    
    // CURSOS QUE O COORDENADOR ADM ((** PROVAVELMENTE AQUI DEVERÁ SER ADICIONADO O ARRAYLIST **))
    private String curso;
    
    // SENHA DO COORDENADOR ((** PROVAVELMENTE SERÁ DESCARTADO PARA QUE POSSAMOS UTILIZAR PERMISSÕES **))
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
