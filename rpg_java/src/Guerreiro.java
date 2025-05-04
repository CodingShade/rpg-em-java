/**
 * Classe que representa o personagem Guerreiro.
 * Possui alta vida e defesa, com chance de golpes críticos.
 */
public class Guerreiro extends Personagem {
    /**
     * Construtor do Guerreiro.
     * @param nome Nome do guerreiro
     */
    public Guerreiro(String nome) {
        // Chama o construtor da superclasse com atributos específicos
        super(nome, 120, 18, 15); // Vida alta, ataque médio, defesa alta
    }

    /**
     * Calcula o dano do ataque do guerreiro.
     * Tem 20% de chance de dar um golpe crítico.
     * @return Valor do dano causado
     */
    @Override
    protected int calcularDano() {
        if (Math.random() < 0.2) { // 20% de chance
            System.out.println("Golpe crítico do guerreiro!");
            return (int)(ataque * 1.5) + (int)(Math.random() * 5); // Dano aumentado
        }
        return ataque + (int)(Math.random() * 5); // Dano normal
    }
}