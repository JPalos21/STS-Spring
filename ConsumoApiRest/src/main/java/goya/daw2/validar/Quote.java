package goya.daw2.validar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
//public record Quote(String type, Value value) { }

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	String type;
	Value value;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Quote [type=" + type + ", value=" + value + "]";
	}
	
	
}