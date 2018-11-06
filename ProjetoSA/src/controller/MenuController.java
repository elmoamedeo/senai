package controller;

import dao.AlunoDao;
import dao.AlunoDaoJDBC;
import dao.AutorizacaoDao;
import dao.AutorizacaoDaoJDBC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import view.AlunoTableModel;
import view.AutorizacaoTableModel;
import view.ListAlunoView;
import view.ListAutorizacoesView;
import view.ListGuaritaView;
import view.LoginAdminView;
import view.LoginCoordenadorView;
import view.TelaPrincipalView;

public class MenuController implements ActionListener {

    private TelaPrincipalView mainView;
    private ListAlunoView alunoV;
    private ListGuaritaView guaV;
    private ListAutorizacoesView autV;
    private LoginCoordenadorView coordV;
    private LoginAdminView adminV;
    
    public MenuController(TelaPrincipalView mainView, ListAlunoView alunoV, ListGuaritaView guaV,
            ListAutorizacoesView autV, LoginCoordenadorView coordV, LoginAdminView adminV) {
        this.mainView = mainView;
        this.alunoV = alunoV;
        this.guaV = guaV;
        this.autV = autV;
        this.coordV = coordV;
        this.adminV = adminV;
    }

    public void iniciar() {
        mainView.setVisible(true);
        ConstruirEAssinarEventos();
        popularTabelaAut();
        popularTabelaAluno();
        popularTabelaGuaritaAut();
        mainView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void ConstruirEAssinarEventos() {
        mainView.getBtnAlunos().addActionListener(this);
        mainView.getBtnAut().addActionListener(this);
        mainView.getBtnGuarita().addActionListener(this);
        mainView.getBtnAdmin().addActionListener(this);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == mainView.getBtnAlunos()) {
            alunoV.setVisible(true);
        }
        if (botao == mainView.getBtnAut()) {
            coordV.setVisible(true);
        }
        if (botao == mainView.getBtnGuarita()) {
            guaV.setVisible(true);
        }
        if (botao == mainView.getBtnAdmin()) {
            adminV.setVisible(true);
        }
    }

    private void popularTabelaAut() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        AutorizacaoTableModel atm = new AutorizacaoTableModel();
        atm.setAutorizacao(dao.getAll());
        autV.getTbAutorizacao().setModel(atm);    
    }

    private void popularTabelaAluno() {
        AlunoDao dao = new AlunoDaoJDBC();
        AlunoTableModel ctm = new AlunoTableModel();
        ctm.setAluno(dao.getAll());
        alunoV.getTbAluno().setModel(ctm);
    }

    private void popularTabelaGuaritaAut() {
        AutorizacaoDao dao = new AutorizacaoDaoJDBC();
        AutorizacaoTableModel atm = new AutorizacaoTableModel();
        atm.setAutorizacao(dao.getAll());
        guaV.getTbGuarita().setModel(atm);  
    }
}
