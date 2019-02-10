package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_permission")

public class UserPermission {

	// Attributes:
	private int PermissionID;
	private String PermissionName;
	private Date CreatedAt;
	private Date UpdatedAt;
	
	public UserPermission() {
		super();
	}
	
	public UserPermission(int PermissionID, String PermissionName, Date CreatedAt, Date UpdatedAt) {
		this.PermissionID = PermissionID;
		this.PermissionName = PermissionName;
		this.CreatedAt = CreatedAt;
		this.UpdatedAt = UpdatedAt;
	}

	@Id
	@Column(name = "PermissionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPermissionID() {
		return PermissionID;
	}
	
	@Column(name = "PermissionName")
	public String getPermissionName() {
		return PermissionName;
	}
	
	@Column(name = "UpdatedAt", insertable=false, updatable=false)
	public Date getCreatedAt() {
		return CreatedAt;
	}
	
	@Column(name = "UpdatedAt", insertable=false, updatable=false)
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	
	public void setPermissionID(int permissionID) {
		PermissionID = permissionID;
	}
	
	public void setPermissionName(String permissionName) {
		PermissionName = permissionName;
	}
	
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
}
