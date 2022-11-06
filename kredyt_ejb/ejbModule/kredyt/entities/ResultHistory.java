package kredyt.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historia_wynikow database table.
 * 
 */
@Entity
@Table(name="historia_wynikow")
@NamedQuery(name="ResultHistory.findAll", query="SELECT h FROM ResultHistory h")
public class ResultHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Lob
	@Column(name="nazwa_uzytkownika")
	private String nazwaUzytkownika;

	@Column(name="wysokosc_raty")
	private float wysokoscRaty;

	public ResultHistory() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNazwaUzytkownika() {
		return this.nazwaUzytkownika;
	}

	public void setNazwaUzytkownika(String nazwaUzytkownika) {
		this.nazwaUzytkownika = nazwaUzytkownika;
	}

	public float getWysokoscRaty() {
		return this.wysokoscRaty;
	}

	public void setWysokoscRaty(float wysokoscRaty) {
		this.wysokoscRaty = wysokoscRaty;
	}

}