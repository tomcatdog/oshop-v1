package me.xiaoy.mvc.manager.resource.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_resource")
public class Resource{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	@Column(name="parentid")
	private String parentid;
	@Column(name="filename")
	private String filename;
	@Column(name="addtime")
	private Long addtime;
	@Column(name="adduser")
	private String adduser;
	@Column(name="restype")
	private String restype;
	@Column(name="respath")
	private String respath;
	@Column(name="ressize")
	private Double ressize;
	@Column(name="fileext")
	private String fileext;
	@Column(name="contenttype")
	private String contenttype;
	@Column(name="ext1")
	private String ext1;
	@Column(name="ext2")
	private String ext2;
	@Column(name="ext3")
	private String ext3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Long getAddtime() {
		return addtime;
	}
	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}
	public String getAdduser() {
		return adduser;
	}
	public void setAdduser(String adduser) {
		this.adduser = adduser;
	}
	public String getRestype() {
		return restype;
	}
	public void setRestype(String restype) {
		this.restype = restype;
	}
	public String getRespath() {
		return respath;
	}
	public void setRespath(String respath) {
		this.respath = respath;
	}
	public Double getRessize() {
		return ressize;
	}
	public void setRessize(Double ressize) {
		this.ressize = ressize;
	}
	public String getFileext() {
		return fileext;
	}
	public void setFileext(String fileext) {
		this.fileext = fileext;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	
}
