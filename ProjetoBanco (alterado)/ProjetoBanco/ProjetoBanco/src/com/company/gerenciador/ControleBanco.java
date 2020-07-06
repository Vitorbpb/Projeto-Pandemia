package com.company.gerenciador;

import com.company.exception.ExcecaoConta;
import com.company.modelo.Conta;
import com.company.ui.PrincipalUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ControleBanco {

    public static void main(String[] args) {
        JFrame telaP = new JFrame();
        telaP.setContentPane(new PrincipalUI().getPrincipalUI());
        telaP.setSize(300,500);
        telaP.setLocation(400,180);
        telaP.setTitle("Banco");
        telaP.setVisible(true);
        telaP.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    List<Conta> contas = new ArrayList<>();
    ArrayList<String> imprimir = new ArrayList<>();

    public List<Conta> getContas() {
        return contas;
    }

    public void sacarDinheiro(Long numero, Long valor) throws ExcecaoConta {
        for (Conta conta : contas) {
            if(conta.getNumeroConta() == numero){
                conta.sacar(valor);
                return;
            }
        }
        throw new ExcecaoConta("Nao encontrado");
    }

    public void depositarDinheiro(Long numero, Long valor) throws ExcecaoConta {
        for (Conta conta : contas) {
            if(conta.getNumeroConta() == numero){
                conta.depositar(valor);
                return;
            }
        }
        throw new ExcecaoConta("Nao encontrado");
    }

    public Conta procurarConta(Long numero) throws ExcecaoConta {
        Conta contaLocalizada = null;
        for (Conta conta:contas)
        if (conta.getNumeroConta()== numero){
            contaLocalizada = conta;
            return conta;
        }
        throw new ExcecaoConta("Nao encontrado");
    }

    public void criarConta(Conta conta){
        contas.add(conta);
        conta.registrarAcao("criacaoDeConta", (long)0);
    }
}