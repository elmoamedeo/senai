package main;

import controller.AdminController;
import controller.AlunoController;
import controller.AutorizacoesController;
import controller.CoordenadorController;
import controller.GuaritaController;
import controller.MenuController;
import view.EditAlunoView;
import view.EditAutorizacaoView;
import view.EditCoordenadorView;
import view.ListAlunoView;
import view.ListAutorizacoesView;
import view.ListCoordenadorView;
import view.ListGuaritaView;
import view.LoginAdminView;
import view.LoginCoordenadorView;
import view.NewAlunoView;
import view.NewCoordenadorView;
import view.TelaPrincipalView;

public class Principal {

    public static void main(String[] args) {
       
        iniciarControllersEViews();
    }

    private static void iniciarControllersEViews() {
        
        // VIEWS
        EditAlunoView editAluno = new EditAlunoView();
        EditAutorizacaoView editAut = new EditAutorizacaoView();
        ListAlunoView listAluno = new ListAlunoView();
        ListAutorizacoesView listAut = new ListAutorizacoesView();
        ListGuaritaView listGua = new ListGuaritaView();
        TelaPrincipalView principal = new TelaPrincipalView();
        NewAlunoView newAluno = new NewAlunoView();
        LoginAdminView loginAdmin = new LoginAdminView();
        EditCoordenadorView editCoord = new EditCoordenadorView();
        LoginCoordenadorView loginCoord = new LoginCoordenadorView();
        ListCoordenadorView listCoord = new ListCoordenadorView();
        NewCoordenadorView newCoord = new NewCoordenadorView();
        
        // CONTROLLERS
        MenuController menuC = new MenuController(principal, listAluno, listGua, listAut, loginCoord, loginAdmin);
        AlunoController alunoC = new AlunoController(editAluno, listAluno, newAluno);
        AutorizacoesController autC = new AutorizacoesController(editAut, listAut, loginCoord);
        GuaritaController guaC = new GuaritaController(listGua);
        AdminController admC = new AdminController(loginAdmin, listCoord);
        CoordenadorController coordC = new CoordenadorController(listCoord, editCoord, newCoord, loginCoord, listAut);
        menuC.iniciar();
        alunoC.iniciar(); 
        guaC.iniciar();
        autC.iniciar();
        admC.iniciar();
        coordC.iniciar();
    }
}
