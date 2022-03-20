package com.jcodepoint.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.jcodepoint.model.Contributor;

@Named("bean")
@ApplicationScoped
public class ExampleBean {

	private List<Contributor> contributors;

	private Contributor temp;
	private Contributor contributorAddTemp;
	
	@PostConstruct
	public void init() {
		//Inicializar datos
		this.contributors = new ArrayList<Contributor>();
		this.contributors.add(new Contributor(1, "Carl", 40, "USA", 100.0));
		this.contributors.add(new Contributor(2, "Bruce", 35, "Canada", 50.0));
		this.contributors.add(new Contributor(3, "Mark", 35, "Australia", 120.0));
		this.contributors.add(new Contributor(4, "Claire", 35, "France", 200.0));
	}
	
	public List<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors(List<Contributor> contributors) {
		this.contributors = contributors;
	}
	
	public Contributor getContributorAddTemp() {
		return contributorAddTemp;
	}

	public void setContributorAddTemp(Contributor contributorAddTemp) {
		this.contributorAddTemp = contributorAddTemp;
	}

	public Boolean isAnyRowEditable() {
		Optional<Contributor> contributor = 
				this.contributors.stream().filter(c -> c.getEditable() != null && c.getEditable()).findAny();

		if (contributor.isPresent()) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public String edit(Contributor c) {
		this.temp = new Contributor(c);
		c.setEditable(Boolean.TRUE);
		return "inicio";
	}

	public String delete(Contributor c) {
		this.contributors.remove(c);
		return "inicio";
	}

	public String cancelar(Contributor c) {
		c.setAge(this.temp.getAge());
		c.setContribution(this.temp.getContribution());
		c.setCountry(this.temp.getCountry());
		c.setName(this.temp.getName());
		c.setEditable(Boolean.FALSE);
		return "inicio";
	}

	public String guardar(Contributor c) {
		c.setEditable(Boolean.FALSE);
		return "inicio";
	}

	
	public String agregar() {
		this.contributorAddTemp = new Contributor();
		return "inicio";
	}
	
	public String agregarAceptar() {
		this.contributors.add(new Contributor(this.contributorAddTemp));
		this.contributorAddTemp = null;
		return "inicio";
	}
	
	public String agregarCancelar() {
		this.contributorAddTemp = null;
		return "inicio";
	}

}

