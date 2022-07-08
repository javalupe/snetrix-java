# Projeto `Snetrix`

# Descrição Resumida do Projeto/Jogo

O jogo Snetrix foi criado a partir da junção de dois jogos clássicos: Snake e
Tetris. Durante o jogo, o usuário deve, por meio das setas de seu teclado,
movimentar a cobra para que a mesma de alimente das comidas de cores
pré-estabelecidas, a fim de formar com seu corpo, um formato específico
demonstrado na tela. Ao consegui formar, a cobra perde esse pedaço formado e o
usuário ganha pontos. O jogo termina em três condições: Quando o formato
incluir a cabeça da cobra, ou seja, a cabeça for removida, quando a cobra
encosta em si mesma ou quando encosta em uma das laterais do espaço de jogo.
Por meio do uso do JSwing como interface gráfica foi criado o Snetrix, sendo
auxiliado por diversos principios da Programação Orientada a Objetos

# Equipe
* `Luiza Coelho de Souza` - `247257`
* `Pedro Sader Azevedo` - `243245`

# Arquivo Executável do Jogo

[snetrix.jar](snetrix.jar)

# Slides do Projeto

## Slides da Prévia
> https://docs.google.com/presentation/d/1VjUHh6TvXTcX7MuGttllpbNoGelKi59lC5gMIKN51RE/edit#slide=id.p

## Slides da Apresentação Final
> https://docs.google.com/presentation/d/1AYmHwLMVmCIzhkl_hy9OlpR2MiHO1PmSlwrjADaJJ_M/edit#slide=id.p

# Relatório de Evolução

> Relatório de evolução, descrevendo as evoluções do design do projeto, dificuldades enfrentadas, mudanças de rumo, melhorias e lições aprendidas. Referências aos diagramas e recortes de mudanças são bem-vindos.



# Destaques de Código

## Destaque de Algoritmo

### Diagrama do Destaque de Algoritmo

![](assets/destaque-algoritmo.png)

### Código do Destaque de Algoritmo

```java
public Shape generate(int size, Color color) {
    ...
    Coordinate newBlock;
    for (int i = 0; i < size; i++) {
        newBlock = latestBlock.clone();
        newBlock.towards(Direction.randomDirection());
        if (blocks.contains(newBlock)) {
            i--;
        } else {
            blocks.add(newBlock);
            latestBlock = newBlock;
            ...
        }
        ...
    return new Shape(blocks, color, width, heigth, minX, minY);
}
```

```java
// sobrecarga
public Shape generate(int size) {
    return generate(size, RandomColor.generate());
}

// sobrecarga
public Shape generate(){
    Random rand = new Random();
    return generate(rand.nextInt(this.minSize, this.maxSize));
}

```

## Destaques de Orientação a Objetos

### Diagrama do Destaque de Orientação a Objetos

![](assets/destaque-oo.png)

### Código do Destaque de Orientação a Objetos 

```java
public abstract class Actor implements Remove, Insert, Draw {
    private Coordinate location;
    private Color color;

    public Actor(){
        this.location = SnakePanel.getInstance().getRandomCoordinate();
        this.color = RandomColor.generate();
    }
```

> Explicação de como a POO foi usada e quais suas vantagens, referenciando o diagrama.

# Destaques de Pattern

## Diagrama do Pattern

![](assets/destaque-pattern.png)

## Código do Pattern

```java
@Override // Snake
public void notifyUpdate() {
    for (Observer obs : moveObservers) {
        obs.update();
    }
}
```

```java
@Override // Subject
public void notifyUpdate() {
    for (Observer obs : removalObservers) {
        obs.update();
    }
}
```

> Explicação de como o pattern foi adotado e quais suas vantagens, referenciando o diagrama.

# Conclusões e Trabalhos Futuros


> Acreditamos que nosso projeto Snetrix teve um resultado ótimo e dentro do esperado, mas encontramos diversas dificuldades durante o caminho e tivemos que seguir caminhos diferentes do que pensavamos inicialmente. A ideia de construir todos os elementos como listas e matrizes foi rapidamente descartada ao passo que fomos descobrindo como seria o movimento da Snake e como funcionariam os principais métodos que tratavam do Shape, que necessitariam sabem qual era o bloco seguinte e o anterior. Além disso, o uso do JSwing como interface gráfica foi interessante, por um lado conseguimos aprender o necessário para fazer o jogo rodar e ser construuído com a estética que esperavamos (o JSwing tornou o uso de Data desnecessário, já que todos os formatos foram desenhados com funções próprias do JSWing, como DrawRect) mas por outro lado, é um dos principais pontos a serem melhorados. Com mais estudo e tempo seria possivel criar classes separadas para Model e View (enquanto no jogo atual esses dois aspectos estão juntos, o o codigo um pouco longo), deixando o jogo com classes menores e mais organizadas, com o objetivo definido para cada uma tratando-se de JSwing. O uso do Singleton também foi algo controverso, ja que de certa maneira entendemos sua funcionalidade mas ocasionou em uma construção complicada do código, havendo que sempre notar as instância e suas atualizações. 

# Documentação dos Componentes

O vídeo a seguir apresenta um detalhamento de um projeto baseado em componentes:

# Diagramas

## Diagrama Geral da Arquitetura do Jogo

> ![Arquitetura Geral](arquiteturaGeral.jpg)

> Neste diagrama pode-se perceber todos os componentes principais do jogo. A classe principal, Game, é por onde tudo se conecta, e a ela estão ligadas a classe Controller (a qual recebe e controla os input pelo teclado do jogador) e os três principais Jpanels do jogo. Esses JPanels são: ScorePanel (ao qual esta ligado o Score e o coloca em display), SnakePanel (ao qual estão ligados todos os elementos e classes que se relacionam diretamente com a Snake) e ShapePanel (ao qual estão ligados todos os elementos e classes que se relacionam com os Shapes). Também no diagrama estão presentes algumas das principais classes do jogo como ShapeHunter e ShapeContainer, os elementos da herança de Actor (Segment e Food) e elementos adicionais que foram necessários serem adicionados durante a construção do jogo, como um JPanel adicional, o ShapeContainer.


## Componente `<Nome do Componente>`

> Resumo do papel do componente e serviços que ele oferece.

![Componente](diagrama-componente.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `<caminho completo da classe com pacotes>` <br> Exemplo: `pt.c08componentes.s20catalog.s10ds.DataSetComponent`
Autores | `<nome dos membros que criaram o componente>`
Interfaces | `<listagem das interfaces do componente>`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

```java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
```

## Detalhamento das Interfaces

### Interface `<nome da interface>`

`<Resumo do papel da interface.>`

```
<Interface em Java.>
```

Método | Objetivo
-------| --------
`<id do método em Java>` | `<objetivo do método e descrição dos parâmetros>`

## Exemplo:

### Interface `ITableProducer`

Interface provida por qualquer fonte de dados que os forneça na forma de uma tabela.

```java
public interface ITableProducer {
  String[] requestAttributes();
  String[][] requestInstances();
}
```

Método | Objetivo
-------| --------
`requestAttributes` | Retorna um vetor com o nome de todos os atributos (colunas) da tabela.
`requestInstances` | Retorna uma matriz em que cada linha representa uma instância e cada coluna o valor do respectivo atributo (a ordem dos atributos é a mesma daquela fornecida por `requestAttributes`.

### Interface `IDataSetProperties`

Define o recurso (usualmente o caminho para um arquivo em disco) que é a fonte de dados.

```java
public interface IDataSetProperties {
  public String getDataSource();
  public void setDataSource(String dataSource);
}
```

Método | Objetivo
-------| --------
`getDataSource` | Retorna o caminho da fonte de dados.
`setDataSource` | Define o caminho da fonte de dados, informado através do parâmetro `dataSource`.

# Plano de Exceções

## Diagrama da hierarquia de exceções

Plano | Execução
----- | ----- 
![](assets/plano-de-excessoes-inicial.png) | ![](assets/plano-de-excessoes-final.png)

## Descrição das classes de exceção

Classe | Descrição | Implementada
----- | ----- | ------
`SegmentException` | Engloba todas as exceções relacionadas à classe Segment. Usamos ela diretamente quando o método `moveHead()`, da class Segment, é chamado em um Segment que não é a cabeça da Snake. | ✔️
`SegmentOutOfBoundsException` | Indica tentativa de acesso a um segmento fora do alcance. Muitas vezes foi chamado a partir do `catch` de um `IndexOutBoundsException`. | ✔️
`SnakeException` | Engloba todas as exceções relacionadas à classe Snake. | ❌
`SnakeException` | Indica situação em que Snake fica com a sua lista de Segments vazia. Decidimos não implementar isso como excessão, pois tornou-se uma mecânica do jogo | ❌
