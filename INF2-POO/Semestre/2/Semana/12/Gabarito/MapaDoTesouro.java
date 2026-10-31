// É um Item, mas não é negociável: o mapa é único e não está à venda
public class MapaDoTesouro extends Item {
    private String destino;

    public MapaDoTesouro(String destino) {
        super("Mapa do tesouro", 0.1);
        this.destino = destino;
    }

    @Override
    public String efeito() {
        return "leva à " + destino;
    }
}
