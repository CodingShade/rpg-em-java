import java.util.Scanner;
import java.util.Random;

/**
 * Classe principal que controla o fluxo do jogo.
 * Gerencia as batalhas, turnos e interação com o jogador.
 */
public class Main {
    // Constantes do jogo
    private static final int MAX_BATALHAS = 5;         // Número máximo de batalhas
    private static final int PONTOS_POR_VITORIA = 10;  // Pontos por vitória

    /**
     * Método principal que inicia o jogo.
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Exibe mensagem de boas-vindas
        System.out.println("=== ARENA DE BATALHA ===");
        System.out.println("Bem-vindo ao jogo de batalha por turnos!\n");
        System.out.println("Você enfrentará no máximo " + MAX_BATALHAS + " batalhas.");
        System.out.println("Ganhe " + PONTOS_POR_VITORIA + " pontos por vitória!\n");

        // Configuração do personagem do jogador
        System.out.print("Digite o nome do seu herói: ");
        String nomeHeroi = scanner.nextLine();

        // Menu de seleção de classe
        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro (Vida alta, ataque médio, defesa alta)");
        System.out.println("2 - Mago (Vida baixa, ataque alto, defesa baixa)");
        System.out.println("3 - Arqueiro (Vida média, ataque médio-alto, defesa média)");
        System.out.print("Opção: ");

        int escolhaClasse = obterInteiro(scanner, 1, 3);
        Personagem heroi = criarPersonagem(nomeHeroi, escolhaClasse);

        // Sistema de batalha
        int batalhasVencidas = 0;
        int pontuacaoTotal = 0;
        boolean jogoAtivo = true;

        // Loop principal do jogo
        while (jogoAtivo && heroi.estaVivo() && batalhasVencidas < MAX_BATALHAS) {
            System.out.println("\n=================================");
            System.out.println("Batalha #" + (batalhasVencidas + 1) + " de " + MAX_BATALHAS);

            // Gera um novo inimigo (mais forte a cada batalha)
            Personagem inimigo = gerarInimigo(random, batalhasVencidas);
            System.out.println("Um " + inimigo.nome + " selvagem apareceu!\n");

            // Loop de batalha individual
            while (heroi.estaVivo() && inimigo.estaVivo()) {
                exibirStatus(heroi, inimigo);
                turnoJogador(scanner, heroi, inimigo);

                if (inimigo.estaVivo()) {
                    turnoInimigo(inimigo, heroi);
                }
            }

            // Verifica resultado da batalha
            if (heroi.estaVivo()) {
                batalhasVencidas++;
                pontuacaoTotal += PONTOS_POR_VITORIA;
                System.out.println("\nVocê venceu esta batalha! +" + PONTOS_POR_VITORIA + " pontos!");
                System.out.println("Pontuação atual: " + pontuacaoTotal);

                if (batalhasVencidas < MAX_BATALHAS) {
                    System.out.println("Preparando próximo oponente...");
                }
            } else {
                jogoAtivo = false;
                System.out.println("\nVocê foi derrotado...");
            }
        }

        // Tela de fim de jogo
        System.out.println("\n=== FIM DE JOGO ===");
        System.out.println("Batalhas completadas: " + batalhasVencidas + " de " + MAX_BATALHAS);
        System.out.println("Pontuação final: " + pontuacaoTotal);

        // Mensagens finais baseadas no desempenho
        if (batalhasVencidas == MAX_BATALHAS) {
            System.out.println("\nParabéns! Você completou todas as batalhas!");
            System.out.println("Você é o verdadeiro campeão da arena com " + pontuacaoTotal + " pontos!");
        } else if (heroi.estaVivo()) {
            System.out.println("\nVocê saiu da arena com vida e " + pontuacaoTotal + " pontos!");
        } else {
            System.out.println("\nVocê foi derrotado na batalha " + batalhasVencidas + " com " + pontuacaoTotal + " pontos.");
        }

        // Exibe classificação final
        exibirClassificacao(pontuacaoTotal);

        scanner.close();
    }

    /**
     * Exibe a classificação do jogador baseada na pontuação.
     * @param pontuacao Pontuação final do jogador
     */
    private static void exibirClassificacao(int pontuacao) {
        System.out.println("\n=== SUA CLASSIFICAÇÃO ===");
        if (pontuacao >= 50) {
            System.out.println("★ ★ ★ ★ ★ - Lenda da Arena!");
        } else if (pontuacao >= 40) {
            System.out.println("★ ★ ★ ★ ☆ - Mestre da Batalha!");
        } else if (pontuacao >= 30) {
            System.out.println("★ ★ ★ ☆ ☆ - Guerreiro Experiente!");
        } else if (pontuacao >= 20) {
            System.out.println("★ ★ ☆ ☆ ☆ - Aprendiz Valoroso!");
        } else if (pontuacao >= 10) {
            System.out.println("★ ☆ ☆ ☆ ☆ - Iniciante Corajoso!");
        } else {
            System.out.println("☆ ☆ ☆ ☆ ☆ - Novato na Arena!");
        }
    }

    /**
     * Obtém um número inteiro do usuário dentro de um intervalo.
     * @param scanner Objeto Scanner para entrada
     * @param min Valor mínimo aceitável
     * @param max Valor máximo aceitável
     * @return Número válido digitado pelo usuário
     */
    private static int obterInteiro(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.print("Valor inválido. Digite entre " + min + " e " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    /**
     * Cria um personagem do tipo especificado.
     * @param nome Nome do personagem
     * @param classe Tipo de personagem (1-Guerreiro, 2-Mago, 3-Arqueiro)
     * @return Instância do personagem criado
     */
    private static Personagem criarPersonagem(String nome, int classe) {
        switch (classe) {
            case 1: return new Guerreiro(nome);
            case 2: return new Mago(nome);
            case 3: return new Arqueiro(nome);
            default: throw new IllegalArgumentException("Classe inválida");
        }
    }

    /**
     * Gera um inimigo com atributos baseados na dificuldade atual.
     * @param random Objeto Random para geração de valores aleatórios
     * @param nivelDificuldade Nível atual de dificuldade (baseado em batalhas vencidas)
     * @return Instância do inimigo gerado
     */
    private static Personagem gerarInimigo(Random random, int nivelDificuldade) {
        String[] nomes = {"Goblin", "Orc", "Esqueleto", "Bandido", "Lobisomem"};
        String nome = nomes[random.nextInt(nomes.length)];

        // Aumenta atributos conforme a dificuldade
        int vida = 50 + (nivelDificuldade * 10);
        int ataque = 10 + (nivelDificuldade * 2);
        int defesa = 5 + nivelDificuldade;

        return new Inimigo(nome, vida, ataque, defesa);
    }

    /**
     * Exibe o status atual dos combatentes.
     * @param heroi Personagem do jogador
     * @param inimigo Inimigo atual
     */
    private static void exibirStatus(Personagem heroi, Personagem inimigo) {
        System.out.println("\n=== STATUS ===");
        System.out.println("HERÓI: " + heroi.getStatus());
        System.out.println("INIMIGO: " + inimigo.getStatus() + "\n");
    }

    /**
     * Executa o turno do jogador.
     * @param scanner Objeto Scanner para entrada
     * @param heroi Personagem do jogador
     * @param inimigo Inimigo atual
     */
    private static void turnoJogador(Scanner scanner, Personagem heroi, Personagem inimigo) {
        System.out.println("Seu turno:");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar Poção de Cura");
        System.out.print("Escolha sua ação: ");

        int acao = obterInteiro(scanner, 1, 3);

        switch (acao) {
            case 1:
                heroi.atacar(inimigo);
                break;
            case 2:
                heroi.defender();
                break;
            case 3:
                if (heroi.inventario.contains("Poção de Cura")) {
                    heroi.usarItem("Poção de Cura");
                } else {
                    System.out.println("Você não tem poções de cura!");
                }
                break;
        }
    }

    /**
     * Executa o turno do inimigo.
     * @param inimigo Inimigo atual
     * @param heroi Personagem do jogador
     */
    private static void turnoInimigo(Personagem inimigo, Personagem heroi) {
        System.out.println("\nTurno do inimigo:");
        if (inimigo instanceof Inimigo) {
            ((Inimigo) inimigo).acaoAleatoria(heroi);
        } else {
            inimigo.atacar(heroi);
        }
    }
}