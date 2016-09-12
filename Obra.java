package br.ueg.biblio.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Obra extends Titulo {

    @NotNull(message = "É definir pelo menos um autor para a obra.")
    @ManyToMany
    @JoinTable(name = "titulo_autor",
            joinColumns = {
                @JoinColumn(name = "titulo_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "autor_id", nullable = false)})
    private List<Autor> autores;

    @Column(nullable = true, name = "volume")
    private int volume;

    @Size(max = 20, message = "O ISBN possui mais de 20 dígitos, isso não é permitido. Verifique se é um ISBN válido.")
    @Column(name = "isbn", length = 20, nullable = true)
    private String isbn;

    @Size(max = 25, message = "O nº de CDU possui mais de 25 dígitos, isso não é permitido. Verifque se digitou corretamente.")
    @Column(name = "cdu", nullable = true, length = 25)
    private String cdu;

    public Obra() {
        this.autores = new ArrayList<>();
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCdu() {
        return cdu;
    }

    public void setCdu(String cdu) {
        this.cdu = cdu;
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void excluirAutor(Autor autor) {
        this.autores.remove(autor);
    }
}
