# JAVA-CLI-APP - Cálculo de Imposto sobre Operações de Ações

#Introdução

Este projeto implementa um programa de linha de comando (CLI) que calcula o imposto a ser pago sobre operações no mercado financeiro de ações, seguindo as regras de ganho de capital.

#Como Funciona

O programa recebe listas de operações do mercado financeiro de ações em formato JSON via entrada padrão (stdin). Para cada operação de venda, calcula-se o imposto devido conforme as regras estabelecidas.

Cada linha da entrada representa uma simulação independente. O estado do programa é mantido apenas para cada linha e reinicializado a cada nova entrada.

#Regras do Cálculo do Imposto

A alíquota de imposto é de 20% sobre o lucro.

O preço médio ponderado das compras é recalculado sempre que uma nova compra é feita.

Se houver prejuízos anteriores, eles devem ser deduzidos antes de calcular o imposto.

Nenhum imposto é devido em operações de venda com valor total ≤ R$ 20.000,00.

Nenhum imposto é devido em operações de compra.

#Entrada e Saída

##Entrada

O programa recebe entradas via stdin com listas de operações JSON, onde cada operação possui:

operation: "buy" (compra) ou "sell" (venda)

unit-cost: Preço unitário da ação

quantity: Quantidade de ações negociadas

###Exemplo de entrada:

```
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
{"operation":"sell", "unit-cost":20.00, "quantity": 5000}]
```

##Saída

A saída é uma lista JSON contendo os valores de imposto pago para cada operação processada.

###Exemplo de saída:
```
[{"tax": 0.00}, {"tax": 10000.00}]
```

#Compilar e Executar

##Compile o projeto:

```
mvn clean package
```

##Execute:

Execute o arquivo 'Application' - "Run 'Application.main()'"
