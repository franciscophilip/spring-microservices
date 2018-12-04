package bcntec.training.springboot.microservices.intro.reactive;

class Greet {
	private String message;
public Greet() {}

	public Greet(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
