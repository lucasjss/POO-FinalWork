import java.util.Objects;

public class Disco {
    // Bernardo Fernando Reolon

    private String nomeDisco, autor, gravadora, ano;;

    public Disco(String nomeDisco, String autor, String gravadora, String ano) {
        this.nomeDisco = nomeDisco;
        this.autor = autor;
        this.gravadora = gravadora;
        this.ano = ano;
    }

    public Disco() {
    }

    public String getAutor() {
        return autor;
    }

    public String getNomeDisco() {
        return nomeDisco;
    }

    public String getGravadora() {
        return gravadora;
    }

    public String getAno() {
        return ano;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNomeDisco(String nomeDisco) {
        this.nomeDisco = nomeDisco;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return nomeDisco +
                " - " + autor +
                " - " + gravadora +
                " - " + ano;
    }

    @Override
    public boolean equals(Object nomeDisco) {
        return super.equals(nomeDisco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNomeDisco(), getAutor(), getGravadora(), getAno());
    }
}