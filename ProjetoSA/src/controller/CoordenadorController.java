package controller;

import dao.CoordenadorDao;
import dao.CoordenadorDaoJDBC;
import entidade.Coordenador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.CoordenadorLoginTableModel;
import view.CoordenadorTableModel;
import view.EditCoordenadorView;
import view.ListAutorizacoesView;
import view.ListCoordenadorView;
import view.LoginCoordenadorView;
import view.NewCoordenadorView;

public class CoordenadorController implements ActionListener {

    private Coordenador coord;
    private ListCoordenadorView listCordView;
    private EditCoordenadorView editCordView;
    private NewCoordenadorView newCordView;
    private LoginCoordenadorView loginCordView;
    private ListAutorizacoesView listAutView;

    public CoordenadorController(ListCoordenadorView listCordView, EditCoordenadorView editCordView,
            NewCoordenadorView newCordView, LoginCoordenadorView loginCordView, ListAutorizacoesView listAutView) {
        this.listCordView = listCordView;
        this.editCordView = editCordView;
        this.newCordView = newCordView;
        this.loginCordView = loginCordView;
        this.listAutView = listAutView;
    }

    public void iniciar() {
        construirEAssinarEventos();
        popularTabelaCoord();
        popularTabelaCoordLogin();
    }

    private void construirEAssinarEventos() {
        editCordView.getTfID().setEnabled(false);
        listCordView.getBtnNovo().addActionListener(this);
        listCordView.getBtnEditar().addActionListener(this);
        listCordView.getBtnExcluir().addActionListener(this);
        newCordView.getBtnOk().addActionListener(this);
        newCordView.getBtnCancelar().addActionListener(this);
        editCordView.getBtnOk().addActionListener(this);
        editCordView.getBtnCancelar().addActionListener(this);
        loginCordView.getBtnCancelar().addActionListener(this);
        loginCordView.getBtnOk().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == listCordView.getBtnEditar()) {
            selecionarNaTabela();
            if (coord != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente editar o coordenador?");
                if (resposta == JOptionPane.YES_OPTION) {
                    limparCampos();
                    coordenadorSelecionadoParaEditView();
                    editCordView.setVisible(true);
                }
            }
        }
        if (botao == listCordView.getBtnNovo()) {
            limparCampos();
            newCordView.setVisible(true);
        }
        if (botao == listCordView.getBtnExcluir()) {
            selecionarNaTabela();
            if (coord != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o coordenador?");
                if (resposta == JOptionPane.YES_OPTION) {
                    excluirCoordenador();
                }
            }
        }
        if (botao == editCordView.getBtnOk()) {
            editarCoordenador();
        }
        if (botao == editCordView.getBtnCancelar()) {
            limparCampos();
            coord = null;
            editCordView.setVisible(false);
        }
        if (botao == newCordView.getBtnOk()) {
            novoCoordenador();
            newCordView.setVisible(false);
        }
        if (botao == newCordView.getBtnCancelar()) {
            limparCampos();
            coord = null;
            newCordView.setVisible(false);
        }
        if (botao == loginCordView.getBtnOk()) {
            coordenadorSelecionadoParaLogin();
            loginCoordenador();
        }
        if (botao == loginCordView.getBtnCancelar()) {
            loginCordView.setVisible(false);
        }
    }

    private void popularTabelaCoord() {
        CoordenadorDao dao = new CoordenadorDaoJDBC();
        CoordenadorTableModel ctm = new CoordenadorTableModel();
        ctm.setCoordenador(dao.getAll());
        listCordView.getTbCoordenadores().setModel(ctm);
    }

    private void popularTabelaCoordLogin() {
        CoordenadorDao dao = new CoordenadorDaoJDBC();
        CoordenadorLoginTableModel cltm = new CoordenadorLoginTableModel();
        cltm.setCoordenador(dao.getAll());
        loginCordView.getTbCoordLogin().setModel(cltm);
    }

    private void selecionarNaTabela() {
        int posicaoSelecionada = listCordView.getTbCoordenadores().getSelectedRow();
        if (posicaoSelecionada > -1) {
            coord = ((CoordenadorTableModel) (listCordView.getTbCoordenadores().getModel())).getCoordenadores().get(posicaoSelecionada);
        } else {
            coord = null;
        }
    }

    private void coordenadorSelecionadoParaEditView() {
        editCordView.getTfID().setText(coord.getId().toString());
        editCordView.getTfNome().setText(coord.getNome());
        editCordView.getTfCurso().setText(coord.getCurso());
        editCordView.getTfSenha().setText(coord.getSenha());
    }

    private void limparCampos() {
        editCordView.getTfID().setText("");
        editCordView.getTfNome().setText("");
        editCordView.getTfCurso().setText("");
        editCordView.getTfSenha().setText("");
        newCordView.getTfNome().setText("");
        newCordView.getTfCurso().setText("");
        newCordView.getTfSenha().setText("");
    }

    private void novoCoordenador() {
        Coordenador umNovoCoordenador = new Coordenador();
        umNovoCoordenador.setNome(newCordView.getTfNome().getText());
        umNovoCoordenador.setCurso(newCordView.getTfCurso().getText());
        umNovoCoordenador.setSenha(newCordView.getTfSenha().getText());
        CoordenadorDao dao = new CoordenadorDaoJDBC();
        dao.salvar(umNovoCoordenador);
        popularTabelaCoord();
    }

    private void editarCoordenador() {
        editViewParaCoordenadorSelecionado();
        CoordenadorDao dao = new CoordenadorDaoJDBC();
        dao.salvar(coord);
        popularTabelaCoord();
        editCordView.setVisible(false);
    }

    private void excluirCoordenador() {
        CoordenadorDao dao = new CoordenadorDaoJDBC();
        dao.excluir(coord);
        coord = null;
        popularTabelaCoord();
    }

    private void loginCoordenador() {
        if (coord == null) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela!");
        } else {
            if (coord.getSenha() == null ? loginCordView.getPfSenhaCoord().getText()
                    == null : coord.getSenha().equals(loginCordView.getPfSenhaCoord().getText())) {
                listAutView.setVisible(true);
                loginCordView.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "A senha não coincide com o coordenador selecionado!");
            }
        }
    }

    private void coordenadorSelecionadoParaLogin() {
        int posicaoSelecionada = loginCordView.getTbCoordLogin().getSelectedRow();
        if (posicaoSelecionada > -1) {
            coord = ((CoordenadorLoginTableModel) (loginCordView.getTbCoordLogin().getModel())).getCoordenadores().get(posicaoSelecionada);
        } else {
            coord = null;
        }
    }

    private void editViewParaCoordenadorSelecionado() {
        coord.setNome(editCordView.getTfNome().getText());
        coord.setCurso(editCordView.getTfCurso().getText());
        coord.setSenha(editCordView.getTfSenha().getText());
    }

}
