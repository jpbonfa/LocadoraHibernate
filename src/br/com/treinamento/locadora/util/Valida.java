package br.com.treinamento.locadora.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author joaop
 */
public class Valida {

    public static boolean verificaStringVazio(String args) {// inicio metodo
        if (args.trim().equals("")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo

    public static boolean verificaIntZero(String string) {// inicio do metodo
        try {
            int args = Integer.parseInt(string);

            if (args == 0) {// if
                return true;
            } // fim do if

        } catch (Exception e) {
            return true;
        }
        return false;

    }// inicio do metodo

    public static boolean verificaDoubleZero(String string) {// inicio metodo
        try {
            double args = Double.parseDouble(string);
            if (args == 0) {// inicio do if
                return true;
            } // fim do if

        } catch (Exception e) {
            return true;
        }
        return false;

    }// fim metodo

    public static boolean verificaCpfVazio(String cpf) {// inicio do metodo
        if (cpf.equals("   .   .   -  ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if
    }// fim do metodo

    public static boolean verificaRgVazio(String rg) {// inicio do metodo
        if (rg.equals("  .   .   - ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo

    public static boolean verificaCepVazio(String cep) {// inicio do metodo
        if (cep.equals("     -   ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaTelefoneVazio(String telefone) {// inicio do metodo
        if (telefone.equals("(  )    -    ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaCelularVazio(String celular) {// inicio do metodo
        if (celular.equals("(  )     -    ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaDataVazio(String data) {// inicio do metodo
        if (data.equals("  /  /    ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo]

    public static boolean apenasLetras(String string) {

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher match = pattern.matcher(string);

        if (match.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validaData(int[] args) {
        if (args[0] < 0 || args[0] > 31) {
            return true;
        }
        if (args[1] < 0 || args[1] > 12) {
            return true;

        } 
        Calendar cal;
        cal = GregorianCalendar.getInstance();

        if (args[2] > cal.get(Calendar.YEAR)) {
            return true;
        } 
        return false;
    }
}
