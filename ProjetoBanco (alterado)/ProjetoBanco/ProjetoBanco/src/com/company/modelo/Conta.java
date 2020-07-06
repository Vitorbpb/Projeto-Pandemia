package com.company.modelo;

import com.company.exception.ExcecaoConta;


public class Conta {
    long numeroConta;
    Cliente cliente;
    GravarTxt gravarTxt = new GravarTxt();

    public Conta(long numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public Conta() {

    }
    public void registrarAcao(String acao, long valor){
        gravarTxt.registrarAcao(acao, this, valor);
    }

    public long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private double saldo;

    public void depositar(double valor) throws ExcecaoConta {
        if (valor <= 0) {
            throw new ExcecaoConta("ERRO ao depositar");
        } else {
            saldo += valor;
            registrarAcao("deposito", (long) valor);
            throw new ExcecaoConta("Sucesso");
        }
    }
        public void sacar ( double valor) throws ExcecaoConta {
            if (valor > saldo) {
                throw new ExcecaoConta("Saldo inferior");
            } else {
                saldo -= valor;
                registrarAcao("saque", (long) valor);
                throw new ExcecaoConta("Sucesso");
            }
        }

        public double getSaldo () {
            return saldo;
        }

    }
