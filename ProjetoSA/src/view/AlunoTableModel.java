package view;

import entidade.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AlunoTableModel extends AbstractTableModel {

    private List<Aluno> aluno = new ArrayList();
    
    public List<Aluno> getAlunos() {
        return aluno;
    }
    
    public void setAluno(List <Aluno> aluno) {
        this.aluno = aluno;
    }
    
    @Override
    public int getRowCount() {
        return aluno.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "ID";
            }
            case 1: {
                return "Nome";
            }
            case 2: {
                return "Turma";
            }
            default: {
                return "";
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String valor = "";
        Aluno umAluno = aluno.get(rowIndex);
        switch(columnIndex) {
            case 0: {
                valor = umAluno.getId().toString();
                break;
            }
            case 1: {
                valor = umAluno.getNome();
                break;
            }
            case 2: {
                valor = umAluno.getTurma();
                break;
            }          
        }
        return valor;
    }   
}
