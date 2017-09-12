package common;

public class Order {

	private String mId;

	public Order(String mId) {
		super();
		this.mId = mId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}
	
	@Override
	public String toString() {
		return "Order ID : " + mId;
	}
}
