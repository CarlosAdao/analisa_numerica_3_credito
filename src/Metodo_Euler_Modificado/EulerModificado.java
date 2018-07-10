package Metodo_Euler_Modificado;

import File.File_;
import com.towel.math.Expression;
import java.util.ArrayList;

//          Análise Númeria turma 2017.2                                     */
//          Discente: José Carlos da Silva Adão  nº matricula 201020048      */
//                                                                           */

public class EulerModificado {

    static String expressao;
    float a, b, y0, x0 = 0, h;
    static double precisao;
    static int iteracoes;
    static String directoryName = "Metodo Euler Modificado";
    static ArrayList<String> resultado = new ArrayList<>();

    public static void main(String[] args) {
        EulerModificado em = new EulerModificado();

    }

    public EulerModificado() {

        ArrayList<String> atributos = File_.read(directoryName);
        expressao = atributos.get(0);
        a = Float.parseFloat(atributos.get(1));
        b = Float.parseFloat(atributos.get(2));
        y0 = Float.parseFloat(atributos.get(3));
        h = Float.parseFloat(atributos.get(4));
        calculaMetodo(x0, y0, h);
        //System.out.println("Expressão -> " + expressao + " No intervalo de: " + a + " >= " + b + " y0: " + y0 + " h: " + h);
        //System.out.println("Valor da funçãoo: " + calculaFuncao(1, 1));

    }

    /*Introduz dentro da funÃ§Ã£o a variavel x e y*/
    private double calculaFuncao(double x, double y) {

        Expression exp = new Expression(expressao);
        exp.setVariable("x", x);
        exp.setVariable("y", y);
        return exp.resolve();
    }

    private double calculaMetodo(double xi, double yi, double h) {
        int it = 0; //quantidade de iteraÃ§Ãµes 
        String r;// guarda os resultados das iteraÃ§Ãµes

        String titulo = "[MÉTODO DE EULER MODIFICADO]\n";
        String legenda = ("it\txi\tyi\n");
        System.out.println(titulo+"\n"+legenda);
        resultado.add(titulo);
        resultado.add(legenda);
        
        while (xi < b) {
            double ye1 = yi + (calculaFuncao(xi, yi) * h);
            double x1 = xi + h;
            double ymo = yi + ((calculaFuncao(xi, yi) + (calculaFuncao(xi, yi))) / 2) * h;//meu y modificado 
            r = (it + "\t" + x1 + "\t" + ymo);
            System.out.println(r);
            it++;
            yi = ymo;
            xi = x1;
            resultado.add(r);//armazena o resultado de cada iteração no vetor de resultados para imprimir no arquivo
        }
        return 0;
    }

}
