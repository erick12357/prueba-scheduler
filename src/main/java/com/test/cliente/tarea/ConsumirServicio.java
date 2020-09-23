package com.test.cliente.tarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.cliente.modelo.DatosUnidad;
import com.test.cliente.modelo.MbRastreoResp;

@Component
public class ConsumirServicio {

	@Autowired
	private RestTemplate template;
	private final String URI = "https://datos.cdmx.gob.mx/api/records/1.0/search/?dataset=prueba_fetchdata_metrobus&q=&rows=1000";

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	private static final Logger logger = LoggerFactory.getLogger(ConsumirServicio.class);

	@Scheduled(fixedRate = 5000)
	private void consumir() {
		MbRastreoResp resp = template.getForObject(URI, MbRastreoResp.class);
		for (DatosUnidad unidad : resp.getRecords()) {
			System.out.println(unidad.getRecordid());
		}
	}
}
