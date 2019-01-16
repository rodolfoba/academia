# banco24h - Rodolfo

Exercício final do Banco 24H

## Parte 1 - Caixa 24h

Um Caixa 24h deve ser capaz de acessar diferentes Bancos desconhecidos e realizar as seguintes operações básicas na conta do usuário:
- Consultar extrato
- Consultar Saldo
- Realizar Transferência (no mesmo banco)
- Realizar Depósito
- Saque

Nenhuma operação pode ser realizada se deixar o saldo abaixo do limite


## Parte 2

A Parte 2 deve ser compatível com a Parte1. Refactorings devem ser feitos na Parte 1 somente se necessário e devem ser justificados através de comentários.

**Banco Alfa**
- Transferência
- Consulta extrato
- Consulta Saldo
- Saque
- Não permite depósito

As contas podem ser do tipo Comum ou Especial. As comuns têm limite de 3 operações por dia e não podem ficar negativas. As especiais não têm limite de operações e podem ficar negativas até 1.000 reais.

**Banco Beta**
- Consulta extrato (taxa de 0,50)
- Consulta Saldo
- Transferência (taxa de 1,00)
- Saque (taxa de 0,5% + 1,00)
- Depósito

As contas podem ser do tipo Comum ou Premium. As comuns têm limite de2 operações por dia e podem ficar negativas até 200 reais. As especiais não têm limite de operações e podem ficar negativas até 5.000 reais.

### Orientações gerais

Todos os módulos devem ser implementados usando boas práticas de Java e OO.

O projeto completo (Parte 1 + Parte 2) deve usar todos os assuntos aprendidos:   
- Classes / Atributos
- Encapsulamento
- Classe Abstrata
- Interface
- Sobrecarga
- Construtores
- Polimorfismo
- Exceptions
- Visibilidade
- Pacotes
- etc...

Implementar Testes Unitários para as funcionalidades.

A Parte 2 será implementada por um aluno diferente do que desenvolveu a Parte 1