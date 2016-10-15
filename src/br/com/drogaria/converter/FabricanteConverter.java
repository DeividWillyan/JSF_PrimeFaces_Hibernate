package br.com.drogaria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@FacesConverter("fabricanteConverter")
public class FabricanteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			FabricanteDAO dao = new FabricanteDAO();
			Fabricante f = new Fabricante();
			f = dao.buscarPorCodigo(codigo);
			return f;
		} catch (RuntimeException ex) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Fabricante f = (Fabricante) objeto;
			Long codigo = f.getCodigo();
			return codigo.toString();
		} catch (RuntimeException ex) {
			return null;
		}

	}

}
