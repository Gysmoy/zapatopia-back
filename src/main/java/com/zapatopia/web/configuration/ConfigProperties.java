package com.zapatopia.web.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zapatopia.response")
public class ConfigProperties {

	private String codigo200;
	private String codigo400;
	private String mensaje200;
	private String mensaje400;
	
	
	public String getCodigo200() {
		return codigo200;
	}
	public void setCodigo200(String codigo200) {
		this.codigo200 = codigo200;
	}
	public String getCodigo400() {
		return codigo400;
	}
	public void setCodigo400(String codigo400) {
		this.codigo400 = codigo400;
	}
	public String getMensaje200() {
		return mensaje200;
	}
	public void setMensaje200(String mensaje200) {
		this.mensaje200 = mensaje200;
	}
	public String getMensaje400() {
		return mensaje400;
	}
	public void setMensaje400(String mensaje400) {
		this.mensaje400 = mensaje400;
	}

	
}
