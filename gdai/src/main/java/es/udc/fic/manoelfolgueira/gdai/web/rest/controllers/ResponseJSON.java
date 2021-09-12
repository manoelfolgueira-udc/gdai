package es.udc.fic.manoelfolgueira.gdai.web.rest.controllers;

public class ResponseJSON {
	private String responseCode;
	private String responseDescription;
	public ResponseJSON() {}
	public ResponseJSON(String responseCode, String responseDescription) {
		super();
		this.responseCode = responseCode;
		this.responseDescription = responseDescription;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDescription() {
		return responseDescription;
	}
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result + ((responseDescription == null) ? 0 : responseDescription.hashCode());
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
		ResponseJSON other = (ResponseJSON) obj;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (responseDescription == null) {
			if (other.responseDescription != null)
				return false;
		} else if (!responseDescription.equals(other.responseDescription))
			return false;
		return true;
	}
	
	
}
