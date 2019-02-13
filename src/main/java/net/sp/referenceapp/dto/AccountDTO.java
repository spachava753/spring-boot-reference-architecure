package net.sp.referenceapp.dto;

import java.io.Serializable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import net.sp.referenceapp.domain.Account;

public class AccountDTO implements Serializable {

  private String id;

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @NotBlank
  @Digits(fraction = 0, integer = 10)
  private String contact;


  @NotBlank
  @Digits(fraction = 0, integer = 9)
  private String ssn;

  @NotBlank
  protected String firstName;

  @NotBlank
  protected String lastName;

  public AccountDTO() {}


  public AccountDTO(Account account) {
    this.id = account.getId();
    this.username = account.getUsername();
    this.password = account.getPassword();
    this.contact = account.getContact();
    this.ssn = account.getSsn();
    this.firstName = account.getFirstName();
    this.lastName = account.getLastName();
  }


  public String getId() {

    return id;
  }

  public void setId(String id) {

    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getFirstName() {

    return this.firstName;
  }

  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  public String getLastName() {

    return this.lastName;
  }

  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contact == null) ? 0 : contact.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((ssn == null) ? 0 : ssn.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AccountDTO other = (AccountDTO) obj;
    if (contact == null) {
      if (other.contact != null)
        return false;
    } else if (!contact.equals(other.contact))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (ssn == null) {
      if (other.ssn != null)
        return false;
    } else if (!ssn.equals(other.ssn))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }


  @Override
  public String toString() {
    return "AccountDTO [id=" + id + ", username=" + username + ", password=" + password
        + ", contact=" + contact + ", ssn=" + ssn + ", firstName=" + firstName + ", lastName="
        + lastName + "]";
  }

}

