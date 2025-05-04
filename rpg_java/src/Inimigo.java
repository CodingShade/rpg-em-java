/**
 * Classe que representa os inimigos que o jogador enfrentará.
 * Possui comportamento automático controlado pelo jogo.
 */
public class Inimigo extends Personagem {
    /**
     * Construtor do Inimigo.
     * @param nome Nome do inimigo
     * @param vida Pontos de vida
     * @param ataque Poder de ataque
     * @param defesa Poder de defesa
     */
    public Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    /**
     * Calcula o dano do ataque do inimigo.
     * Tem 10% de chance de dar um golpe surpresa.
     * @return Valor do dano causado
     */
    @Override
    protected int calcularDano() {
        int danoBase = ataque + (int)(Math.random() * 5);

        if (Math.random() < 0.1) { // 10% de chance
            System.out.println(this.nome + " acerta um golpe surpresa!");
            return danoBase + 5; // Dano aumentado
        }
        return danoBase; // Dano normal
    }

    /**
     * Realiza uma ação aleatória durante o turno do inimigo.
     * @param alvo Personagem que será alvo da ação
     */
    public void acaoAleatoria(Personagem alvo) {
        double chance = Math.random();

        if (chance < 0.7) { // 70% de chance de atacar
            this.atacar(alvo);
        } else if (chance < 0.9) { // 20% de chance de defender
            this.defender();
        } else { // 10% de chance de não fazer nada
            System.out.println(this.nome + " hesita...");
        }
    }
}