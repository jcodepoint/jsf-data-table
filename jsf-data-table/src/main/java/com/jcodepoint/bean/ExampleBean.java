package com.jcodepoint.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.AjaxBehaviorEvent;
//import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.jcodepoint.model.Contributor;

@Named("bean")
@ApplicationScoped
public class ExampleBean {

	private List<Contributor> contributors;

	private Contributor temp;
	private Contributor contributorAddTemp;
	private Boolean sortAscFlag;
	private String nameFilter;
	
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

	public Boolean getSortAscFlag() {
		return sortAscFlag;
	}

	public void setSortAscFlag(Boolean sortAscFlag) {
		this.sortAscFlag = sortAscFlag;
	}

	public String getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(String nameFilter) {
		this.nameFilter = nameFilter;
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

	
	private final Comparator<Contributor> sortAsc = new Comparator<Contributor>() {
		public int compare(Contributor c1, Contributor c2) {
			return c1.getName().compareTo(c2.getName());
		}
	};
	
	private final Comparator<Contributor> sortDesc = new Comparator<Contributor>() {
		public int compare(Contributor c1, Contributor c2) {
			return c2.getName().compareTo(c1.getName());
		}
	};

	public String sortByName() {
		if (this.sortAscFlag == null) {
			this.sortAscFlag = Boolean.TRUE;
		} else {
			this.sortAscFlag = !this.sortAscFlag;
		}

		Collections.sort(this.contributors, this.sortAscFlag ? sortAsc : sortDesc);
		return "";
	}

	//ValueChangeEvent event
	//AjaxBehaviorEvent event
	
	public void applyFilter(AjaxBehaviorEvent event) {
		String input = this.nameFilter.toLowerCase().trim();
		
		if (input.equals("")) {
			for(Contributor c : this.contributors) {
				c.setDisplayable(Boolean.TRUE);
			}
		} else {
			for(Contributor c : this.contributors) {
				if (c.getName().toLowerCase().startsWith(input)) {
					c.setDisplayable(Boolean.TRUE);
				} else {
					c.setDisplayable(Boolean.FALSE);
				}
			}
		}
	}
	
}

