package entidade;

public class Aluno {
    
    // ID DO ALUNO
    private Integer id;
    
    // NOME DO ALUNO
    private String nome;
    
    // CURSO DO ALUNO
    private Curso curso;

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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
      
}
