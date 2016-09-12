package br.ueg.biblio.view.managedbean;

import br.ueg.biblio.core.PersistenceCore;
import br.ueg.biblio.core.RepositoryCore;
import br.ueg.biblio.core.ValidationCore;
import br.ueg.biblio.model.Autor;
import br.ueg.biblio.model.Editora;
import br.ueg.biblio.model.Idioma;
import br.ueg.biblio.model.Marca;
import br.ueg.biblio.model.Obra;
import br.ueg.biblio.model.enumerated.ClassesConhecimentoHumano;
import br.ueg.biblio.model.interfaces.Injetavel;
import br.ueg.biblio.util.ManagedBeanUtil;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "obraBean")
@ViewScoped
public class ObraBean implements Injetavel {

    private Obra titulo;

    @Inject
    private PersistenceCore persistence;

    @Inject
    private RepositoryCore repository;

    private List<Editora> listaEditoras;
    private List<Idioma> listaIdiomas;
    private List<Autor> listaAutores;
    private List<Marca> listaTags;

    /*METODOS DE CONFIGURAÇÕES*/
    public void configurarForm() {
        if (ManagedBeanUtil.isNotPostback()) {
            prepararForm();
        }
    }

    private void prepararForm() {

        this.setTitulo(new Obra());
        this.listaEditoras = repository.listaDeEditoras();
        this.listaIdiomas = repository.listaDeIdiomas();
        this.listaAutores = repository.listaDeAutores();
        this.listaTags = repository.listaDeMarcas();

    }

    private void carregarDados() {
    }

    private void configurarTabela() {
    }

    /* METODOS DE ACAO */
    public void salvar() {
        ValidationCore.ajustandoNovaObra(titulo);
        if (persistence.persistir(getTitulo())) {
            prepararForm();
            ManagedBeanUtil.addInfoMessage("Título armazenado com sucesso.");
        } else {
            ManagedBeanUtil.addErrorMessage("Não foi ppossível armazenar informaçõe.");
        }
    }

    public void adicionarEditora(Editora publisher) {
        this.titulo.setEditora(publisher);
    }

    public void adicionarAutor(Autor autor) {
        this.titulo.adicionarAutor(autor);
    }

    public void adidionarObra(Idioma idioma) {
        this.titulo.setIdioma(idioma);
    }

    public void adicionarTag(Marca tag) {
        this.titulo.adicionarTag(tag);
    }

    /* GETTERS E SETTERS */
    public Obra getTitulo() {
        return titulo;
    }

    public void setTitulo(Obra titulo) {
        this.titulo = titulo;
    }

    public ClassesConhecimentoHumano[] getAreas() {

        return ClassesConhecimentoHumano.values();

    }

    public List<Editora> getListaEditoras() {
        return listaEditoras;
    }

    public List<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public List<Autor> getListaAutores() {
        return listaAutores;
    }

    public List<Marca> getListaTags() {
        return listaTags;
    }

}
/*METODOS DE CONFIGURAÇÕES*/
 /* METODOS DE ACAO */
 /* GETTERS E SETTERS */
