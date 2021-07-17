package p1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String first_Name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_Name;
	}

	public void setFirstName(String firstName) {
		this.first_Name = firstName;
	}

	public String getLastName() {
		return last_Name;
	}

	public void setLastName(String lastName) {
		this.last_Name = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String last_Name;
	
	@Column(name="email")
	private String email;
	
	public Student() {
		
	}

	public Student(String firstName, String lastName, String email) {
		this.first_Name = firstName;
		this.last_Name = lastName;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + first_Name + ", lastName=" + last_Name + ", email=" + email + "]";
	}
	

}
