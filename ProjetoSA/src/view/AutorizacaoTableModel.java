package view;

import entidade.Autorizacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AutorizacaoTableModel extends AbstractTableModel {

    private List<Autorizacao> autorizacao = new ArrayList();

    public List<Autorizacao> getAutorizacoes() {
        return autorizacao;
    }

    public void setAutorizacao(List<Autorizacao> autorizacao) {
        this.autorizacao = autorizacao;
    }

    @Override
    public int getRowCount() {
        return autorizacao.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "IDAUT";
            }
            case 1: {
                return "IDALUNO";
            }
            case 2: {
                return "IDCOORDENADOR";
            }
            case 3: {
                return "DTCRIACAO";
            }
            case 4: {
                return "DTSAIDA";
            }
            default: {
                return "";
            }
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String valor = "";
        Autorizacao umaAutorizacao = autorizacao.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                valor = umaAutorizacao.getId().toString();
                break;
            }
            case 1: {
                valor = umaAutorizacao.getIdAluno().toString();
                break;
            }
            case 2: {
                valor = umaAutorizacao.getIdCoordenador().toString();
                break;
            }
            case 3: {
                valor = umaAutorizacao.getCriacao().toString();
                break;
            }
            case 4: {
                if (umaAutorizacao.getSaida() == null) {
                    valor = "";
                } else {
                    valor = umaAutorizacao.getSaida().toString();
                }
                break;
            }
        }
        return valor;
    }
}
