/**
 * Classe que representa o personagem Mago.
 * Possui baixa vida e defesa, mas alto poder de ataque com chance de feitiços poderosos.
 */
public class Mago extends Personagem {
    /**
     * Construtor do Mago.
     * @param nome Nome do mago
     */
    public Mago(String nome) {
        // Chama o construtor da superclasse com atributos específicos
        super(nome, 80, 25, 8); // Vida baixa, ataque alto, defesa baixa
    }

    /**
     * Calcula o dano do ataque do mago.
     * Tem 30% de chance de lançar um feitiço poderoso.
     * @return Valor do dano causado
     */
    @Override
    protected int calcularDano() {
        if (Math.random() < 0.3) { // 30% de chance
            System.out.println(this.nome + " canaliza poder mágico!");
            return ataque + 15 + (int)(Math.random() * 10); // Dano mágico aumentado
        }
        return ataque + (int)(Math.random() * 8); // Dano normal
    }
}