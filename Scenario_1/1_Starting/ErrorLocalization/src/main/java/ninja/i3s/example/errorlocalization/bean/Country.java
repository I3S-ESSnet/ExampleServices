package ninja.i3s.example.errorlocalization.bean;

public class Country {
    public String country;
    public String code;

    public Country() {}

    public String getName() {
		return this.country;
	}
	public void setName(String  country) {
		this.country = country;
    }

    public String getCode() {
		return this.code;
	}
	public void setCode(String  code) {
		this.code = code;
    }
}