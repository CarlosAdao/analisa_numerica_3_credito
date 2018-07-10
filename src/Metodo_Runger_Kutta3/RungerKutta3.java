/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodo_Runger_Kutta3;

import File.File_;
import com.towel.math.Expression;
import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class RungerKutta3 {

    static String expressao;
    float a, b, y0, x0 = 0, h;
    static double precisao;
    static int iteracoes;
    static String directoryName = "Metodo Runge Kutta 3";
    static ArrayList<String> resultado = new ArrayList<>();

    public static void main(String[] args) {
        RungerKutta3 rk3 = new RungerKutta3();
    }
    
    public RungerKutta3() {
        
        ArrayList<String> atributos = File_.read(directoryName);
        expressao = atributos.get(0);
        a = Float.parseFloat(atributos.get(1));
        b = Float.parseFloat(atributos.get(2));
        y0 = Float.parseFloat(atributos.get(3));
        h = Float.parseFloat(atributos.get(4));
        calculaMetodo(x0, y0, h);

    }

    /*Introduz dentro da função a variavel x e y*/
    private double calculaFuncao(double x, double y) {

        Expression exp = new Expression(expressao);
        exp.setVariable("x", x);
        exp.setVariable("y", y);
        return exp.resolve();
    }

    private double calculaMetodo(double xi, double yi, double h) {
        int it = 0; //Guarda a quantidade de iterações  
        String r;// guarda os resultados das iterações

        String titulo = "[MÉTODO RUNGE KUTTA 3]\n";
        String legenda = ("it\txi\tyi\n");
        System.out.println(titulo + "\n" + legenda);
        resultado.add(titulo);
        resultado.add(legenda);

        while (xi < b) {
            //constante de Runge Kutta
            double k1 = calculaFuncao(xi, yi);//calcula o k1 
            double k2 = calculaFuncao(xi + (h / 2), yi + ((k1 * h) / 2));//calcula o k2
            double k3 = calculaFuncao(xi + h, yi - ((k1 * h) + (2 * k2 * h)));//f(xo+h,yo-((k1*h)+(2*k2*h)))
            double y1 = yi + (k1 + 4 * k2 + k3) * h / 6;
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
