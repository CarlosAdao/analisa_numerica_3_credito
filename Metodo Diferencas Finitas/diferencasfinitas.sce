//          Análise Númeria turma 2017.2                                     */
//          Discente: José Carlos da Silva Adão  nº matricula 201020048      */
//                                                                           */

//                   Implementação Diferencas Finitas                        */

//Abri o arqui em forma de leitura e escrita
in = mopen('C:\Users\carlos\Documents\Uesc\Analise Numerica\Análise Numérica\Relatório e Implementações- Terceiro Crédito\Métodos\DiferencasFinitas\entrada.txt', 'r');
out = mopen('C:\Users\carlos\Documents\Uesc\Analise Numerica\Análise Numérica\Relatório e Implementações- Terceiro Crédito\Métodos\DiferencasFinitas\saida.txt', 'w');

[n, funP, funQ, funR, a, b, N, alpha, betha] = mfscanf(in, '%[^\n] %[^\n] %[^\n] %f %f %f %f %f');

mclose(in);

deff('y=p(x)',funP);           // função p(x)
deff('w=q(x)',funQ);           // função q(x)
deff('z=r(x)',funR);           // função r(x)
//algoritmo

if(N < 2) then
    mfprintf(out, 'Quantidade de pontos menor que dois.');
    exit(0);
end;

h = (b - a)/(N+1);
x(1) = a + h;
A(1)(1) = 2 + h*h*q(x(1));
A(1)(2) = -1 + (h/2)*p(x(1));
A(1)(N+1) = -h*h*r(x(1)) + (1 + (h/2)*p(x(1)))*alpha;

for(i = 2:N-1)
    x(i) = a + i*h;
    A(i)(i-1) = -1 - (h/2)*p(x(i));
    A(i)(i) = 2 + h*h*q(x(i));
    A(i)(i+1) = -1 + (h/2)*p(x(i));
    A(i)(N+1) = -h*h*r(x(i));
end;

x(N) = b - h;
A(N)(N-1) = -1 - (h/2)*p(x(N));
A(N)(N) = 2 + h*h*q(x(N));
A(N)(N+1) = -h*h*r(x(N)) + (1 - (h/2)*p(x(N)))*betha;

//Método de Eliminação de Gauss

mat = A;
tam = N;

//algoritmo

for(i = 1:tam-1)
    for(j = i:tam)
        if(mat(j,i)) then
            break;
		end;
	end;
    if(j == tam) then
        mfprintf(out, 'Nao existe uma solucao unica!!!');
        return
	end;
    if(j ~= i)
        for(k = 1:tam+1)
            aux = mat(i,k);
            mat(i,k) = mat(j,k);
            mat(j,k) = aux;
		end;
	end;
    for(j = i+1:tam)
        mji = mat(j,i)/mat(i,i);
        for(k = i:tam+1)
            mat(j,k) = mat(j,k) - mji*mat(i,k);
		end;
	end;
end;
//PROCESSO QUE VERIFICA SE A ULTIMA LINHA E NULA
if(~mat(tam,tam)) then
    mfprintf(out, 'Nao existe uma solucao unica!!!');
    return;
end;
//PROCESSO DE SUBSTITUICAO REGRESSIVA
vet(tam) = mat(tam,tam+1)/mat(tam,tam);
for(i = tam-1:-1:1)
    soma = 0;
    for(j = i+1:tam)
        soma = soma + mat(i,j)*vet(j);
    end;
	vet(i) = (mat(i,tam+1)-soma)/mat(i,i);
end;

//RESULTADOS
mfprintf(out, 'Aproximação da equação diferencial y``, onde p(x) = %s, q(x) = %s e r(x) = %s para o problema de contorno.\n\n', funP, funQ, funR);
mfprintf(out, '%.2f\t%f\n', a, alpha);
for(i = 1:N)
    mfprintf(out, '%.2f\t%f\n', x(i), vet(i));
end;
mfprintf(out, '%.2f\t%f\n', b, betha);

mclose(out);

