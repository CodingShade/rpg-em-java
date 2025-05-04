import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa um personagem genérico no jogo.
 * Define atributos e comportamentos básicos que todos os personagens compartilham.
 */
public abstract class Personagem {
    // Atributos básicos de um personagem
    protected String nome;         // Nome do personagem
    protected int vida;            // Vida atual
    protected int vidaMaxima;      // Vida máxima
    protected int ataque;          // Poder de ataque
    protected int defesa;          // Capacidade de defesa
    protected List<String> inventario; // Itens disponíveis
    protected boolean defendendo;  // Indica se está defendendo no turno atual

    /**
     * Construtor da classe Personagem.
     * @param nome Nome do personagem
     * @param vida Pontos de vida
     * @param ataque Poder de ataque
     * @param defesa Poder de defesa
     */
    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.inventario = new ArrayList<>();
        this.defendendo = false;

        // Adiciona 3 poções de cura ao inventário inicial
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
    }

    /**
     * Realiza um ataque contra outro personagem.
     * @param alvo Personagem que será atacado
     */
    public void atacar(Personagem alvo) {
        int dano = calcularDano();
        System.out.println(this.nome + " ataca " + alvo.nome + "!");
        alvo.tomarDano(dano);
        this.defendendo = false; // Defesa só vale por um turno
    }

    /**
     * Ação de defesa - reduz o dano recebido no próximo ataque.
     */
    public void defender() {
        System.out.println(this.nome + " está se defendendo!");
        this.defendendo = true;
    }

    /**
     * Recebe dano de um ataque.
     * @param dano Quantidade de dano a ser recebido
     */
    public void tomarDano(int dano) {
        if (defendendo) {
            dano = Math.max(1, dano - defesa); // Garante pelo menos 1 de dano
            System.out.println(this.nome + " defendeu parte do ataque!");
        }

        vida -= dano;
        System.out.println(this.nome + " sofreu " + dano + " de dano!");

        if (vida <= 0) {
            vida = 0;
            System.out.println(this.nome + " foi derrotado!");
        }
    }

    /**
     * Verifica se o personagem está vivo.
     * @return true se vivo, false se derrotado
     */
    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Retorna uma string com o status atual do personagem.
     * @return String formatada com informações do personagem
     */
    public String getStatus() {
        return nome + " - Vida: " + vida + "/" + vidaMaxima +
                " | Ataque: " + ataque + " | Defesa: " + defesa +
                " | Poções: " + contarPocoes();
    }

    /**
     * Usa um item do inventário.
     * @param item Nome do item a ser usado
     */
    public void usarItem(String item) {
        if (inventario.contains(item)) {
            inventario.remove(item);
            if (item.equals("Poção de Cura")) {
                vida = Math.min(vidaMaxima, vida + 20); // Cura sem ultrapassar o máximo
                System.out.println(nome + " usou Poção de Cura e recuperou 20 de vida!");
            }
        }
    }

    /**
     * Conta quantas poções de cura estão no inventário.
     * @return Número de poções disponíveis
     */
    private int contarPocoes() {
        int count = 0;
        for (String item : inventario) {
            if (item.equals("Poção de Cura")) {
                count++;
            }
        }
        return count;
    }

    /**
     * Método abstrato para cálculo de dano - deve ser implementado pelas subclasses.
     * @return Valor do dano a ser causado
     */
    protected abstract int calcularDano();
}