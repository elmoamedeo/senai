package controller;

import entidade.Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.ListCoordenadorView;
import view.LoginAdminView;

public class AdminController implements ActionListener {
    // ciao senai sadsdygygy
    private LoginAdminView loginAdmin;
    private ListCoordenadorView listCoord;
    private Admin admin;
    
    public AdminController (LoginAdminView loginAdmin, ListCoordenadorView listCoord) {
        this.loginAdmin = loginAdmin;
        this.listCoord = listCoord;
    }
    
    public void iniciar() {
        construirEAssinarEventos();
    }

    private void construirEAssinarEventos() {
        loginAdmin.getBtnOk().addActionListener(this);
        loginAdmin.getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botao = (JButton) e.getSource();
        if (botao == loginAdmin.getBtnCancelar()) {
            loginAdmin.setVisible(false);
        }
        if (botao == loginAdmin.getBtnOk()) {
            verificarSenha();
        }
    }

    private void verificarSenha() {
        String valor = loginAdmin.getTfUsu().getText();
        if ("elmoamedeo".equals(valor)){
            listCoord.setVisible(true);
            loginAdmin.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Você digitou a senha errada!");
        }
    }

}
