package models;

public class KhachHang {
	String cmnd;
	String ho;
	String ten;
	String diachi;
	String phai;
	String ngaycap;
	String sodt;
	String mapgd;
	
	public KhachHang(String cmnd, String ho, String ten, String diachi, String phai, String ngaycap, String sodt,
			String mapgd) {
		super();
		this.cmnd = cmnd;
		this.ho = ho;
		this.ten = ten;
		this.diachi = diachi;
		this.phai = phai;
		this.ngaycap = ngaycap;
		this.sodt = sodt;
		this.mapgd = mapgd;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getPhai() {
		return phai;
	}

	public void setPhai(String phai) {
		this.phai = phai;
	}

	public String getNgaycap() {
		return ngaycap;
	}

	public void setNgaycap(String ngaycap) {
		this.ngaycap = ngaycap;
	}

	public String getSodt() {
		return sodt;
	}

	public void setSodt(String sodt) {
		this.sodt = sodt;
	}

	public String getMapgd() {
		return mapgd;
	}

	public void setMapgd(String mapgd) {
		this.mapgd = mapgd;
	}
	
	
}
