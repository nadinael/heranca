package br.ueg.biblio.model;

import br.ueg.biblio.model.enumerated.ClassesConhecimentoHumano;
import br.ueg.biblio.model.enumerated.DisponibilidadeStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "titulo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Titulo extends Simples {

    @NotBlank(message = "Um nome válido é obrigatório.")
    @Size(min = 2, max = 150, message = "Verifique a quantidade de letras no nome.")
    @Column(nullable = false, length = 150, name = "nome")
    private String nome;

    @NotBlank(message = "Um código de barras (UCP) é obrigatório.")
    @Size(max = 30, message = "Verifique a quantidade de letrtas no código de barras.")
    @Column(name = "cod_barras", nullable = false, length = 30)
    private String codBarras;

    @NotNull(message = "Defina uma classe ao título.")
    @Enumerated(EnumType.STRING)
    @Column(name = "area_abordagem", nullable = false, length = 20)
    private ClassesConhecimentoHumano areaCon;

    @NotNull(message = "Idioma de ser definido.")
    @ManyToOne(targetEntity = Idioma.class)
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    @Size(max = 240, message = "Sintetize sua observação, ela passou do limite permitido de 240 letras.")
    @Column(name = "observacao", nullable = true, length = 240)
    private String observacao;

    @NotNull(message = "Uma editora deve ser definida.")
    @ManyToOne(targetEntity = Editora.class)
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    @Column(name = "edicao", nullable = false)
    private int edicao;

    @Size(max = 20, message = "O nº de CDD possui mais de 20 dígitos, isso não é permitido. Verifque se digitou corretamente.")
    @Column(nullable = true, name = "cdd", length = 20)
    private String cdd; //opt

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = true, name = "data_publicacao")
    private Date data;

    @OneToMany
    @JoinTable(name = "titulo_exemplar",
            joinColumns = {
                @JoinColumn(name = "titulo_id", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "exemplar_id", nullable = false)})
    private List<Exemplar> copias;

    @NotNull(message = "É preciso pelo menos uma Tag (Marcação) em um título.")
    @ManyToMany
    @JoinTable(name = "titulo_marca",
            joinColumns = {
                @JoinColumn(name = "titulo_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "marca_id")})
    private List<Marca> marcadores;

    @NotNull(message = "Status de disponibilidade da obra está nulo.")
    @Enumerated(EnumType.STRING)
    @Column(name = "disp_status", nullable = false, length = 20)
    private DisponibilidadeStatus dispStatus;

    public Titulo() {
        this.marcadores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras.toUpperCase();
    }

    public DisponibilidadeStatus getDispStatus() {
        return dispStatus;
    }

    public void setDispStatus(DisponibilidadeStatus dispStatus) {
        this.dispStatus = dispStatus;
    }

    public ClassesConhecimentoHumano getAreaCon() {
        return areaCon;
    }

    public void setAreaCon(ClassesConhecimentoHumano areaCon) {
        this.areaCon = areaCon;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Marca> getMarcadores() {
        return marcadores;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getCdd() {
        return cdd;
    }

    public void setCdd(String cdd) {
        this.cdd = cdd;
    }

    public List<Exemplar> getCopias() {
        return copias;
    }

    public void adicionarTag(Marca tag) {
        this.marcadores.add(tag);
    }

    public void excluirTag(Marca tag) {
        this.marcadores.remove(tag);
    }

    public void adicionarCopia(Exemplar exemplar) {
        this.copias.add(exemplar);
    }

    public void excluirCopia(Exemplar exemplar) {
        this.copias.remove(exemplar);
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
