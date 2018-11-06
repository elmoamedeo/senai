package dao;

import entidade.Coordenador;
import java.util.List;

public interface CoordenadorDao {
    
    public void salvar (Coordenador coordenador);
    
    public void excluir (Coordenador coordenador);
    
    public List<Coordenador> getAll();
    
}
