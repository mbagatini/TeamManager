package entidades;

public class TipoCompeticao {

    private char codigo;
    private String descricao;

    public TipoCompeticao(char codigo) {
        this.codigo = codigo;
        switch (codigo) {
            case '1':
                this.descricao = "AMISTOSO";
                break;
            case '2':
                this.descricao = "ESTADUAL";
                break;
            case '3':
                this.descricao = "REGIONAL";
                break;
            case '4':
                this.descricao = "NACIONAL";
                break;
            default:
                this.descricao = " ";
                break;     
        }
    }

    public char getCodigo() {
        return codigo;
    }

    public void setCodigo(char codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}
