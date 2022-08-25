package br.com.sisnema.milionarios.tests;

import br.com.sisnema.milionarios.dto.PaisDTO;
import br.com.sisnema.milionarios.entities.Pais;

public class Factory {

    public static Pais criarPais() {
        Pais pais = new Pais(1, "Bulgária");
        return pais;
    }

    public static PaisDTO criarPaisDto() {
        Pais pais = criarPais();
        return new PaisDTO(pais);
    }
}
