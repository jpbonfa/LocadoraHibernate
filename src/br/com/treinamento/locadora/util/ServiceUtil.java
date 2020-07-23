package br.com.treinamento.locadora.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author joaop
 */
public class ServiceUtil {

    public static String dataAtual() {

        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.format(data);
    }

    public static  int[] quebraData(String data) {// inicio do metodo
        // variavel do retorno do metodo
        int retorno[] = new int[3];
        // procedimento para separar os elementos da data recebida
        String aux[] = data.split("/");
        // valorizando posição 0 - dia
        retorno[0] = Integer.parseInt(aux[0]);
        // valorizando posição 1 -mês
        retorno[1] = Integer.parseInt(aux[1]);
        // valorizando posição 2 -ano
        retorno[2] = Integer.parseInt(aux[2]);

        return retorno;

    }// fim do metodo
}
