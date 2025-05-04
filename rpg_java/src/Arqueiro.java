/**
 * Classe que representa o personagem Arqueiro.
 * Possui atributos balanceados e ataques precisos.
 */
public class Arqueiro extends Personagem {
    /**
     * Construtor do Arqueiro.
     * @param nome Nome do arqueiro
     */
    public Arqueiro(String nome) {
        // Chama o construtor da superclasse com atributos específicos
        super(nome, 90, 20, 10); // Vida média, ataque médio-alto, defesa média
    }

    /**
     * Calcula o dano do ataque do arqueiro.
     * Possui ataques mais precisos (menor variação no dano).
     * @return Valor do dano causado
     */
    @Override
    protected int calcularDano() {
        // Dano mais consistente (menor variação aleatória)
        return ataque + (int)(Math.random() * 3) + 2;
    }
}