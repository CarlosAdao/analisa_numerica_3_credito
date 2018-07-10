/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoRungeKutta2;

import File.File_;
import com.towel.math.Expression;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class RungerKutta2 {

    static String expressao;
    float a, b, y0, x0 = 0, h;
    static double precisao;
    static int iteracoes;
    static String directoryName = "Metodo Runge Kutta 2";
    static ArrayList<String> resultado = new ArrayList<>();

    public static void main(String[] args) {
        RungerKutta2 rk2 = new RungerKutta2();
    }

    public RungerKutta2() {

        ArrayList<String> atributos = File_.read(directoryName);
        expressao = atributos.get(0);
        a = Float.parseFloat(atributos.get(1));
        b = Float.parseFloat(atributos.get(2));
        y0 = Float.parseFloat(atributos.get(3));
        h = Float.parseFloat(atributos.get(4));
        calculaMetodo(x0, y0, h);
    }

    /*Introduz dentro da funÃ§Ã£o a variavel x e y*/
    private double calculaFuncao(double x, double y) {

        Expression exp = new Expression(expressao);
        exp.setVariable("x", x);
        exp.setVariable("y", y);
        return exp.resolve();
    }

    private double calculaMetodo(double xi, double yi, double h) {
        int it = 0; //Guarda a quantidade de iterações  
        String r;// guarda os resultados das iterações

        String titulo = "[MÉTODO RUNGE KUTTA 2]\n";
        String legenda = ("it\txi\tyi\n");
        System.out.println(titulo + "\n" + legenda);
        resultado.add(titulo);
        resultado.add(legenda);

        while (xi < b) {

            //as duas constantes de RK 
            double k1 = h * calculaFuncao(xi, xi);
            double k2 = h * calculaFuncao(xi + (h / 2), yi + (k1 / 2));
            double y1 = yi + ((1 / 2) * k1 + (1 / 2) * k2) * h;
            r = (it + "\t" + xi + "\t" + y1);
            xi = xi + h;
            yi = y1;
            System.out.println(r);
            it++;
            resultado.add(r);//armazena o resultado de cada iteração no vetor de resultados para imprimir no arquivo

        }
        return 0;
    }

}
