package com.company.modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GravarTxt {

    String path = "C:\\Users\\Izabella\\resultado.txt";

    public GravarTxt() {
    }

    public void processarTexto(ArrayList<String> lines) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void registrarAcao(String transacao, Conta conta, Long valor){
        ArrayList<String> imprimir = new ArrayList<>();

        switch(transacao){
            case "deposito":
                imprimir.add("==========================[DEPOSITO]================================");
                imprimir.add("Nome:" + conta.getCliente().getNome());
                imprimir.add("Id:" + conta.getCliente().getIdCliente());
                imprimir.add("Nº Conta:" + conta.getNumeroConta());
                imprimir.add("Valor deposito:" + valor);
                imprimir.add("Saldo atual:" + conta.getSaldo());
                imprimir.add("Realizado em:" + new Date(System.currentTimeMillis()));
                processarTexto(imprimir);
                imprimir.clear();
                break;
            case "saque":
                imprimir.add("==========================[SAQUE]====================================");
                imprimir.add("Nome:" + conta.getCliente().getNome());
                imprimir.add("Id:" + conta.getCliente().getIdCliente());
                imprimir.add("Nº Conta:" + conta.getNumeroConta());
                imprimir.add("Valor Saque:" + valor);
                imprimir.add("Saldo atual:" + conta.getSaldo());
                imprimir.add("Realizado em:" + new Date(System.currentTimeMillis()));
                processarTexto(imprimir);
                imprimir.clear();
                break;
            case "criacaoDeConta":
                imprimir.add("==========================[CADASTRO DE CONTA]====================================");
                imprimir.add("Nome:" + conta.getCliente().getNome());
                imprimir.add("Id:" + conta.getCliente().getIdCliente());
                imprimir.add("Nº Conta:" + conta.getNumeroConta());
                imprimir.add("Saldo atual:" + conta.getSaldo());
                imprimir.add("Realizado em:" + new Date(System.currentTimeMillis()));
                processarTexto(imprimir);
                imprimir.clear();
                break;
        }
    }

}
