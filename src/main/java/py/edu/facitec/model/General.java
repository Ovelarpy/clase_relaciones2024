package py.edu.facitec.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

//aplicacion de herencia para la persistencia
@MappedSuperclass
public class General {
	
	@Id                       //para crear pk de la bd
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Codigo;

	public Integer getCodigo() {
		return Codigo;
	}

	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}

	@Override
	public String toString() {
		return "General [Codigo=" + Codigo + "]";
	}

	

}
