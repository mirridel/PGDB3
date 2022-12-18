package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("paid_delivery")
	@Expose
	private Boolean paidDelivery;

	public Stores(){
		this.id = -1;
		this.email = "";
		this.paidDelivery = true;
	}

	public Stores(String email, Boolean paidDelivery){
		this.email = email;
		this.paidDelivery = paidDelivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPaidDelivery() {
		return paidDelivery;
	}

	public void setPaidDelivery(Boolean paidDelivery) {
		this.paidDelivery = paidDelivery;
	}

	@Override
	public String toString() {
		return "Stores{" +
				"email=" + email +
				", paidDelivery=" + paidDelivery +
				'}';
	}
}
