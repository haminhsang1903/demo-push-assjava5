package poly.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Staffs")
public class Staffs {
	@Id
	@Column(name="Id")
	@NotBlank(message = "ID not allow null")
	private String id;
	
	@Column(name="Name")
	@NotBlank(message = "Name not allow null")
	private String name;
	
	@Column(name="Gender")
	private Boolean gender;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="Birthday")
	private Date birthday;
	
	@Column(name="Photo")
	@NotBlank(message = "Photo not allow null")
	private String photo;
	
	@Column(name="Email")
	@NotBlank(message = "Email not allow null")
	@Email()
	private String email;
	
	@Column(name="Phone")
	@NotBlank(message = "Phone not allow null")
	private String phone;
	
	@Column(name="Salary")
	@NotNull(message = "Salary not allow null")
	private Double salary;
	
	@Column(name="Notes")
	private String notes;
	
	@ManyToOne
	@JoinColumn(name="DepartId")
	private Departs depart;
	
	@OneToMany(mappedBy = "staff",fetch = FetchType.EAGER)
	private Collection<Records> record;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Departs getDepart() {
		return depart;
	}

	public void setDepart(Departs depart) {
		this.depart = depart;
	}

	public Collection<Records> getRecord() {
		return record;
	}

	public void setRecord(Collection<Records> record) {
		this.record = record;
	}
	public Staffs() {}
	public Staffs(String id, String name, Boolean gender, Date birthday, String photo, String email, String phone,
			Double salary, String notes, Departs depart) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.photo = photo;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.notes = notes;
		this.depart = depart;
	
	}
	
	
	
}
