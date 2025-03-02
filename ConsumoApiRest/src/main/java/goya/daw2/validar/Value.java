package goya.daw2.validar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
//public record Value(Long id, String quote) { }

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {
	
	Long id;
	String quote;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	@Override
	public String toString() {
		return "Value [id=" + id + ", quote=" + quote + "]";
	}
	
}
