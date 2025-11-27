# 🦋 Butterfly Collector 🦋

<img width="683" height="547" alt="capa_jogo" src="https://github.com/user-attachments/assets/abbda1e2-15ed-4cef-9d92-cf018869a501" />

**Identificação:**

* **Nome:** Raffaela Ferrão Lences
* **Curso:** Sistemas de Informação UFSM
---
## Proposta:

  Minha proposta foi a criação de um jogo sobre Entomologia, estilo Top-down RPG, com o objetivo de encontrar insetos e coletá-los. A cada inseto encontrado pelo mapa teria um quiz de múltipla escolha sobre o tema. Em caso de acerto, o inseto é coletado e vai para um "insetário", que pode ser acessado a qualquer momento. Inspirado no trabalho "Jardim Botânico Quest" na parte dos quizzes. O objetivo do jogo seria coletar todos insetos.

  Consegui seguir bem a proposta, mas por escolha estética alterei para apenas borboletas e não insetos num geral, assim a apresentação do insetário ficou melhor organizada. As questões são específicas sobre as borboletas de Santa Maria e região, informações tiradas de um artigo da UFSM. De resto, é exatamente como foi descrito na proposta.
  
---

## Processo de desenvolvimento:

ETAPAS DE DESENVOLVIMENTO: 

Para o desenvolvimento, dividi o projeto em várias partes menores, assim ficou mais simples para ver o progresso de pouco a pouco, adicionando uma função de cada vez. Foram feitas sete grandes partes, mas também houveram mudanças frequentes nas partes do código em que vi necessidade de melhoria. Explicando cada uma delas:

1. Criação do mapa e do player, e movimentação da câmera: Primeiro adicionei um mapa simples, ainda em escalas aleatórias, sprite do player e sua movimentação. O player iniciava no canto da tela. Ainda não tinha animação, apenas a imagem deslizando pelo mapa. As teclas para movimentação são WASD. Após isso, troquei a câmera estática por uma câmera que segue o player pela tela, mostrando a área dentro da janela, o próprio libGDX ja possuia esse recurso, então foi simples implementar

2. Limites do mapa e animação do player: Aqui o sprite do jogador que era uma imagem estática foi trocado por uma sprite sheet (4 direction) e adicionada a animação, separando a folha em frames e mostrando na sequência e direções corretas. Também alterei pra que o jogador fosse gerado bem no centro da tela Com a movimentação pronta, foi necesário adicionar um limite pro player não sair andando para fora do mapa. Aqui o limite ainda era o tamanho das bordas do mapa, usando a largura e altura da imagem do mapa, quando ele chegava nas bordas ainda dava pra ver a tela além do mapa. Inicialmente pensei em colocar uma imagem maior (de um lago ou floresta por exemplo) ou uma cor na paleta do mapa para ser o "além do mapa". Mais tarde, por escolha estética, alterei pra que ele ficasse apenas numa área central do mapa, assim o que está fora do mapa para de ser exibido.

3. Criação das borboletas: Até o momento, tudo ainda está em uma única GameScreen. Aqui as borboletas foram geradas pelo mapa, em posições aleatórias no plano (que foram alteradas mais tarde), e colisão entre player e borboleta. Com a colisão funcionando, foi adicionada a lógica inicial de coletar borboleta. Quando o player colide com a borboleta, ela consta como "coletada" e é apagada do mapa. Ainda sem condições, bastava colidir e ela desaparecia.

4. Tela de quiz: Primeiro pensei em colocar o quiz como uma Entity, aparecendo na prórpria tela principal do jogo, semelhante ao jogo analisado "Jardim Botânico Quest". Para dar uma diferenciada, preferi colocar o quiz em outra screen, que apenas mostra as questões, tela preta simples e questão no centro. Agora as borboletas não são mais coletadas apenas por colisões. A cada colisão, a tela de quiz abre e só fecha quando ele for respondido corretamente, a borboleta é coletada no momento que a resposta certa é escolhida. No começo coloquei apenas duas questões, exibidas aleatóriamente, no final liguei uma questão própria para cada uma das borboletas, referente a própria espécie dela.

5. Adiciona inventário: Com a mecânica de coletar as borboletas após responder o quiz, era necessário um local que exibisse quais já foram coletadas. Implementei uma nova screen para o inventário/insetário. Nessa screen adicionei uma imagem simples para o fundo e criei uma matriz 3x3 no centro da tela. Quando uma borboleta é coletada, ela vai para o seu lugar definido dentro dessa matriz. As imagens das borboletas são semelhantes as exibidas no mapa, mas em maior escala e com o nome da espécie escrito embaixo de cada uma delas.

6. Menu: Para o jogo não começar direto na tela principal, adicionei uma tela de menu básica. Nessa tela é exibido apenas o título do jogo e um botão de Start no centro, que é ativaddo ao ser clicado ou ao apertar Enter, ambos com animações simples, com a mesma imagem de fundo utilizada na tela de inventário.

7. Finalização: Aqui apenas adicionei na GameScreen principal duas mensagens para o jogador. No canto superior esquerdo coloquei "Insetário(I)" pro usuário saber como acessar o inventário, aparece o tempo todo. No canto inferior direito, quando todas as borboletas forem coletadas, será exibido "Parabéns, todas borboletas coletadas!", indicando que o jogo foi finalizado.


PROBLEMAS: 

Tive problemas com a ferramenta Gradle, pois por algum motivo que não consegui corrigir, a versão HTML não foi gerada. Suponho que o motivo seja a versão que utilizei do Java (17), pois após pesquisar as possíveis causas, essa foi citada diversas vezes. 



---

## Diagrama de classes:

---

## Orientações para execução:

Ter JDK instalado.

No terminal, usar o comando:

./gradlew lwjgl3:run

---

## Resultado final:


https://github.com/user-attachments/assets/2b1131be-d4cc-4af8-ba10-1a3944ca21ba




---

## Referências e créditos (e prompts):



---

