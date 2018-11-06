package controller;

import dao.AutorizacaoDao;
import dao.AutorizacaoDaoJDBC;
import entidade.Aluno;
import entidade.Autorizacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import view.AutorizacaoTableModel;
import view.ListAutorizacoesView;
import view.ListGuaritaView;

public class GuaritaController implements ActionListener {

    private ListGuaritaView guaritaView;
    private Autorizacao autorizacao;
    private Aluno aluno;
    private ListAutorizacoesView listAutView;

    public GuaritaController(ListGuaritaView guaritaView) {
        this.guaritaView = guaritaView;
    }

    public void iniciar() {
        ConstruirEAssinarEventos();
        guaritaView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void ConstruirEAssinarEventos() {
        popularTabela();
        guaritaView.getBtnLiberar().addActionListener(this);
        guaritaView.getBtnAtt().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == guaritaView.getBtnLiberar()) {
            selecionarNaTabela();
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente liberar este aluno?");
            if (resposta == JOptionPane.YES_OPTION) {
                operacao();
            }
        }
        if (botao == guaritaView.getBtnAtt()) {
            popularTabela();
        }
    }

    private void popularTabela() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        AutorizacaoTableModel atm = new AutorizacaoTableModel();
        atm.setAutorizacao(dao.getAll());
        guaritaView.getTbGuarita().setModel(atm);
    }

    private void selecionarNaTabela() {
        int posicaoSelecionada = guaritaView.getTbGuarita().getSelectedRow();
        if (posicaoSelecionada > -1) {
            autorizacao = ((AutorizacaoTableModel) (guaritaView.getTbGuarita().getModel())).getAutorizacoes().get(posicaoSelecionada);
        } else {
            autorizacao = null;
        }
    }

    private void operacao() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        dao.salvar(autorizacao);
        popularTabela();
    }
}
