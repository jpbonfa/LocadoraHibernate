/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treinamento.locadora.util;

import javax.swing.JOptionPane;

/**
 *
 * @author joaop
 */
public class JOptionPaneUtil {

    public static void erro(String msg) {
        JOptionPane.showMessageDialog(null, msg, Mensagem.erro, 0);

    }

    public static void sucesso(String msg) {
        JOptionPane.showMessageDialog(null, msg, Mensagem.sucesso, 1);

    }
}
