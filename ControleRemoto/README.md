# ControleRemoto

TV
- construtor (array de canais possíveis)
- canal ativo
- canais possíveis (array)
- volume (0 a 50)
- ligada ou não

Controle Remoto
- construtor (TV)
- muda o canal (através de número, e aumentar e diminuir canal [setinhas])
- liga e desliga
- aumenta diminui volume (setinhas)

Testes unitários de todas as funcionalidades no controle remoto
- Usar Asserts 

Tem que verificar:
- se o volume está dentro do intervalo possível
- se o canal existe


**Evolução do nosso exercício da TV**

Controle até agora recebia uma TV.
Agora Controle recebe uma interface chamada Controlavel.

- A sua TV implementará uma interface controlavel
- Um Tablet que poderá ser controlado por controle remoto implementará Controlavel
- Um Som que poderá ser controlado por controle remoto implementará Controlavel
- Um Porta Retrato Digital com música que poderá ser controlado por controle remoto implementará Controlavel
