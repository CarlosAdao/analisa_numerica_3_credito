/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodo_Euler;

import File.File_;
import com.towel.math.Expression;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */

public class Euler {

    static String expressao;
    float a, b, y1, h;
    static double precisao;
    static int iteracoes;
    static String directoryName = "Metodo Euler";
    static ArrayList<String> resultado = new ArrayList<>();

    public static void main(String[] args) {
        Euler e = new Euler();
    }

    public Euler() {
        /*Pega os atributos do arquivo de entrada*/
        ArrayList<String> atributos = File_.read(directoryName);
        expressao = atributos.get(0);
        a = Float.parseFloat(atributos.get(1));
        b = Float.parseFloat(atributos.get(2));
        y1 = Float.parseFloat(atributos.get(3));
        h = Float.parseFloat(atributos.get(4));
        System.out.println("ExpressÃ£o -> " + expressao + " No intervalo de: " + a + " >= " + b + " y1: " + y1 + " h: " + h);
       calculaMetodo(a, y1, h);
    }

    /*Introduz dentro da funÃ§Ã£o a variavel x*/
    private double calculaFuncao(double x) {

        Expression exp = new Expression(expressao);
        exp.setVariable("x", x);
        return exp.resolve();
    }

    private double calculaMetodo(double xi, double yi, double h) {
        int it = 0; //quantidade de iteraÃ§Ãµes 
        String r;// guarda os resultados das iteraÃ§Ãµes
        
        String titulo = "[MÉTODO DE EULER]\n";
        String legenda = ("it\txi\tyi\n");
        
        resultado.add(titulo);
        resultado.add(legenda);
        System.out.println(titulo);//Titulo
        System.out.println(legenda);//Legenda das iterações
        while (xi < b) {
            
            yi = yi + h * calculaFuncao(xi);
            r = (it+"\t"+xi+"\t"+yi);
            System.out.println(r);
            xi = xi+h;
            it++;
            resultado.add(r);//armazena o resultado de cada iteração no vetor de resultados para imprimir no arquivo
        }
        return 0;
    }

}
