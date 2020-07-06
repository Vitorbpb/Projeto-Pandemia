package com.company.ui;

import com.company.exception.ExcecaoConta;
import com.company.gerenciador.ControleBanco;
import com.company.modelo.Cliente;
import com.company.modelo.Conta;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PrincipalUI {
    private JPanel telaPrincipal;
    private JTextField txtNome;
    private JTextField txtId;
    private JLabel lblNome;
    private JLabel lblId;
    private JTextField txtConta;
    private JTextField txtSaldo;
    private JButton btCriarConta;
    private JButton btProConta;
    private JTextField txtValor;
    private JButton btDepositar;
    private JButton btSacar;
    private JButton btConsultar;
    private JButton btSair;
    private JLabel lblNConta;
    private JLabel lblSaldo;
    private JLabel lblValor;
    private JLabel lblMov;
    private JButton consultarButton;
    private Conta conta;

    public JPanel getPrincipalUI() {
        return telaPrincipal;
    }

    public PrincipalUI() {
        //instanciar contas
        ControleBanco controleBanco = new ControleBanco();

        btDepositar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Long numConta = Long.valueOf(txtConta.getText());

                try {
                    controleBanco.depositarDinheiro(numConta, Long.valueOf(txtValor.getText()));
                    atualizarTela(controleBanco);
                } catch (ExcecaoConta e) {
                    if (e.getMessage() == "Sucesso") {
                        JOptionPane.showMessageDialog(null,
                                "Valor depositado com sucesso");
                        txtValor.setText(null);

                        if (e.getMessage() == "Nao encontrado") {
                            JOptionPane.showMessageDialog(null,
                                    "ERRO: Conta não encontrada");
                            txtValor.setText(null);

                        }
                        if (e.getMessage() == "ERRO ao depositar") {
                            JOptionPane.showMessageDialog(null,
                                    "ERRO ao depositar");
                            txtValor.setText(null);
                        }
                    }
                }
            }

        });


        btSacar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                //Pesquisar a conta para realizar o deposito
                Long numConta = Long.valueOf(txtConta.getText());
                try {
                    controleBanco.sacarDinheiro(numConta, Long.valueOf(txtValor.getText()));
                    atualizarTela(controleBanco);
                } catch (ExcecaoConta e) {

                    if (e.getMessage() == "Sucesso") {
                        JOptionPane.showMessageDialog(null,
                                "Saque efetuado com sucesso");
                        txtValor.setText(null);

                    }
                    if (e.getMessage() == "Nao encontrado") {
                        JOptionPane.showMessageDialog(null,
                                "ERRO: Conta não encontrada");
                        txtValor.setText(null);

                    }
                    if (e.getMessage() == "Saldo inferior") {
                        JOptionPane.showMessageDialog(null,
                                "ERRO: Saldo inferior");
                        txtValor.setText(null);
                    }

                }
            }
        });

        btCriarConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (validarCampos("cadastroConta")) {

                    //criando conta
                    Conta conta = new Conta();
                    conta.setNumeroConta(Long.parseLong(txtConta.getText()));

                    //cadastra cliente
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(Integer.parseInt(txtId.getText()));
                    cliente.setNome(txtNome.getText());

                    //associando cliente a conta criada
                    conta.setCliente(cliente);

                    //cadastra conta
                    controleBanco.criarConta(conta);
                    JOptionPane.showMessageDialog(null,
                            "Conta cadastrada com sucesso");
                }

            }
        });

        btProConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                try {
                    if (validarCampos("procuraConta")) {

                        Long numConta = Long.valueOf(txtConta.getText());
                        Conta conta = controleBanco.procurarConta(numConta);
                        txtNome.setText(conta.getCliente().getNome());
                        txtId.setText(String.valueOf(conta.getCliente().getIdCliente()));
                        txtSaldo.setText(String.valueOf(conta.getSaldo()));
                    }

                }catch (ExcecaoConta e){
                    if (e.getMessage() == "Nao encontrado") {
                        JOptionPane.showMessageDialog(null,
                                "ERRO: Conta nao encontrada");

                    }

                }

            }
        });

        btSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                JOptionPane.showMessageDialog(null,
                        "Finalizando o Programa");
                System.exit(0);

            }
        });
    }

    public void atualizarTela(ControleBanco controleBanco) throws ExcecaoConta {

        try {
            Long numConta = Long.valueOf(txtConta.getText());
            Conta conta = controleBanco.procurarConta(numConta);

            if (!(conta == null)) {
                txtNome.setText(conta.getCliente().getNome());
                txtId.setText(String.valueOf(conta.getCliente().getIdCliente()));
                txtSaldo.setText(String.valueOf(conta.getSaldo()));

            }

        }catch (ExcecaoConta e){
            if (e.getMessage() == "Nao encontrado") {
                JOptionPane.showMessageDialog(null,
                        "ERRO: Conta nao encontrada");

            }
        }

    }

    public boolean validarCampos(String numero) {

        boolean flag = true;
        if (numero.equals("procuraConta")) {
            if (txtConta.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Preencher campo: NUMERO DA CONTA");
                flag = false;
            }
        }
        if (numero.equals("cadastroConta")) {
            if (txtNome.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Preencher campo: NOME");
                flag = false;
            } else if (txtConta.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Preencher campo: NUMERO DA CONTA");
                flag = false;

            } else if (txtId.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,
                        "Preencher campo: ID CLIENTE");
                flag = false;

            }

        }
        return flag;
    }
}