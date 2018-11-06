package controller;

import dao.AlunoDao;
import dao.AlunoDaoJDBC;
import dao.AutorizacaoDao;
import dao.AutorizacaoDaoJDBC;
import entidade.Aluno;
import entidade.Autorizacao;
import entidade.Coordenador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.AlunoTableModel;
import view.AutorizacaoTableModel;
import view.CoordenadorLoginTableModel;
import view.EditAutorizacaoView;
import view.ListAutorizacoesView;
import view.LoginCoordenadorView;

public class AutorizacoesController implements ActionListener {

    private EditAutorizacaoView editAutView;
    private ListAutorizacoesView listAutView;
    private LoginCoordenadorView loginView;
    private Coordenador coordenador;
    private Autorizacao autorizacao;
    private Aluno aluno;

    public AutorizacoesController(EditAutorizacaoView editAutView, ListAutorizacoesView listAutView, LoginCoordenadorView loginView) {
        this.editAutView = editAutView;
        this.listAutView = listAutView;
        this.loginView = loginView;
    }

    public void iniciar() {
        ConstruirEAssinarEventos();
        popularTabelaAut();
        popularTabelaAlunoAut();
        editAutView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        listAutView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void ConstruirEAssinarEventos() {
        editAutView.getBtnAutorizar().addActionListener(this);
        listAutView.getBtnNovaAutorizacao().addActionListener(this);
        listAutView.getBtnExcluir().addActionListener(this);
        loginView.getBtnOk().addActionListener(this);
        loginView.getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == listAutView.getBtnNovaAutorizacao()) {
            editAutView.setVisible(true);
            popularTabelaAlunoAut();
        }
        if (botao == listAutView.getBtnExcluir()) {
            selecionarNaTabelaAutorizacao();
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a autorização?");
            if (resposta == JOptionPane.YES_OPTION) {
                excluirAutorizacao();
            }
        }
        if (botao == editAutView.getBtnAutorizar()) {
            selecionarNaTabela();
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente fazer uma autorização para este aluno?");
            if (resposta == JOptionPane.YES_OPTION) {
                fazerAutorizacao();
            }
        }
        if (botao == loginView.getBtnCancelar()) {
            loginView.setVisible(false);
        }
        if (botao == loginView.getBtnOk()) {
            selecionarNaTabelaCoordLogin();
        }
    }

    private void selecionarNaTabela() {
        int posicaoSelecionada = editAutView.getTbAutAluno().getSelectedRow();
        if (posicaoSelecionada > -1) {
            aluno = ((AlunoTableModel) (editAutView.getTbAutAluno().getModel())).getAlunos().get(posicaoSelecionada);
        } else {
            aluno = null;
        }
    }

    private void popularTabelaAut() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        AutorizacaoTableModel atm = new AutorizacaoTableModel();
        atm.setAutorizacao(dao.getAll());
        listAutView.getTbAutorizacao().setModel(atm);
    }

    private void popularTabelaAlunoAut() {
        AlunoDao dao = new AlunoDaoJDBC();
        AlunoTableModel ctm = new AlunoTableModel();
        ctm.setAluno(dao.getAll());
        editAutView.getTbAutAluno().setModel(ctm);
    }

    private void selecionarNaTabelaAutorizacao() {
        int posicaoSelecionada = listAutView.getTbAutorizacao().getSelectedRow();
        if (posicaoSelecionada > -1) {
            autorizacao = ((AutorizacaoTableModel) (listAutView.getTbAutorizacao().getModel())).getAutorizacoes().get(posicaoSelecionada);
        } else {
            autorizacao = null;
        }
    }

    private void selecionarNaTabelaCoordLogin() {
        int posicaoSelecionada = loginView.getTbCoordLogin().getSelectedRow();
        if (posicaoSelecionada > -1) {
            coordenador = ((CoordenadorLoginTableModel) (loginView.getTbCoordLogin().getModel())).getCoordenadores().get(posicaoSelecionada);
        } else {
            coordenador = null;
        }
    }

    private void fazerAutorizacao() {
        Autorizacao novaAutorizacao = new Autorizacao();
        novaAutorizacao.setAluno(aluno);
        novaAutorizacao.setCoordenador(coordenador);
        novaAutorizacao.setCriacao(new Date());
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        dao.salvar(novaAutorizacao);
        editAutView.setVisible(false);
        popularTabelaAut();
    }

    private void excluirAutorizacao() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        dao.excluir(autorizacao);
        autorizacao = null;
        popularTabelaAut();
    }
}
