package controller;

import dao.AlunoDao;
import dao.AlunoDaoJDBC;
import entidade.Aluno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.AlunoTableModel;
import view.EditAlunoView;
import view.ListAlunoView;
import view.NewAlunoView;

public class AlunoController implements ActionListener {

    private EditAlunoView editAlunoView;
    private ListAlunoView listAlunoView;
    private NewAlunoView newAlunoView;
    private Aluno aluno = null;

    public AlunoController(EditAlunoView editAlunoView, ListAlunoView listAlunoView, NewAlunoView newAlunoView) {
        this.editAlunoView = editAlunoView;
        this.listAlunoView = listAlunoView;
        this.newAlunoView = newAlunoView;
    }

    public void iniciar() {
        ConstruirEAssinarEventos();
        editAlunoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        newAlunoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        listAlunoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void ConstruirEAssinarEventos() {
        popularTabela();
        editAlunoView.getTfId().setEnabled(false);
        editAlunoView.getBtnCancelar().addActionListener(this);
        editAlunoView.getBtnSalvar().addActionListener(this);
        listAlunoView.getBtnEditar().addActionListener(this);
        listAlunoView.getBtnExcluir().addActionListener(this);
        listAlunoView.getBtnNovo().addActionListener(this);
        newAlunoView.getBtnOk().addActionListener(this);
        newAlunoView.getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == listAlunoView.getBtnExcluir()) {
            selecionarNaTabela();
            if (aluno != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o aluno?");
                if (resposta == JOptionPane.YES_OPTION) {
                    AlunoDao dao = new AlunoDaoJDBC();
                    dao.excluir(aluno);
                    aluno = null;
                    popularTabela();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela!");
            }
        }
        if (botao == listAlunoView.getBtnEditar()) {
            selecionarNaTabela();
            if (aluno != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente editar o aluno?");
                if (resposta == JOptionPane.YES_OPTION) {
                    limparCampos();
                    alunoSelecionadoParaEditView();
                    editAlunoView.setVisible(true);
                }
            }
        }
        if (botao == listAlunoView.getBtnNovo()) {
            limparCampos();
            newAlunoView.setVisible(true);
        }
        if (botao == newAlunoView.getBtnOk()) {
            novoAlunoView();
            newAlunoView.setVisible(false);
        }
        if (botao == newAlunoView.getBtnCancelar()) {
            limparCampos();
            aluno = null;
            newAlunoView.setVisible(false);
        }
        if (botao == editAlunoView.getBtnCancelar()) {
            limparCampos();
            aluno = null;
            editAlunoView.setVisible(false);
            listAlunoView.getTbAluno().clearSelection();
        }
        if (botao == editAlunoView.getBtnSalvar()) {
            editarAluno();
        }
    }

    private void selecionarNaTabela() {
        int posicaoSelecionada = listAlunoView.getTbAluno().getSelectedRow();
        if (posicaoSelecionada > -1) {
            aluno = ((AlunoTableModel) (listAlunoView.getTbAluno().getModel())).getAlunos().get(posicaoSelecionada);
        } else {
            aluno = null;
        }
    }

    private void popularTabela() {
        AlunoDao dao = new AlunoDaoJDBC();
        AlunoTableModel ctm = new AlunoTableModel();
        ctm.setAluno(dao.getAll());
        listAlunoView.getTbAluno().setModel(ctm);
    }

    private void alunoSelecionadoParaEditView() {
        editAlunoView.getTfId().setText(aluno.getId().toString());
        editAlunoView.getTfNome().setText(aluno.getNome());
        /* ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
        editAlunoView.getTfTurma().setText(aluno.getTurma());
        */
    }

    private void limparCampos() {
        editAlunoView.getTfId().setText("");
        editAlunoView.getTfNome().setText("");
        editAlunoView.getTfTurma().setText("");
        newAlunoView.getTfNome().setText("");
        newAlunoView.getTfTurma().setText("");
    }

    private void editViewParaAlunoSelecionado() {
        aluno.setNome(editAlunoView.getTfNome().getText());
        /* ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
        aluno.setTurma(editAlunoView.getTfTurma().getText());
        */
    }

    private void novoAlunoView() {
        Aluno umNovoAluno = new Aluno();
        umNovoAluno.setNome(newAlunoView.getTfNome().getText());
        /* ALTERAÇÃO NECESSÁRIA JÁ QUE O CURSO DEIXOU DE SER UMA SIMPLES STRING E VIROU CLASSE/TABELA
        umNovoAluno.setTurma(newAlunoView.getTfTurma().getText());
        */
        AlunoDao dao = new AlunoDaoJDBC();
        dao.salvar(umNovoAluno);
        popularTabela();
    }

    private void editarAluno() {
        editViewParaAlunoSelecionado();
        AlunoDao dao = new AlunoDaoJDBC();
        dao.salvar(aluno);
        aluno = null;
        limparCampos();
        popularTabela();
        editAlunoView.setVisible(false);
    }
}
