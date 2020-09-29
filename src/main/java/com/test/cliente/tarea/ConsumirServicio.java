package com.test.cliente.tarea;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.cliente.config.URIS;
import com.test.mongoLib.EntidadesMongo.Alcaldia;
import com.test.mongoLib.EntidadesMongo.Unidad;
import com.test.mongoLib.crudBeans.MongoCrud;
import com.test.mongoLib.modelo.RespuestaServicio;
import com.test.mongoLib.modelo.RespuestaServicioUnidad;

@Component
public class ConsumirServicio {

	@Autowired
	private RestTemplate template;
	@Autowired
	private MongoCrud mongoCrud;

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public void setMongoCrud(MongoCrud mongoCrud) {
		this.mongoCrud = mongoCrud;
	}

	private static final Logger logger = LoggerFactory.getLogger(ConsumirServicio.class);

	
	/**
	 * Metodo encargado de recuperar 
	 * la ubicacion de cada unidad 
	 * */
	@Scheduled(fixedRate = 50000)
	private void obtenerUbicaciones() {
		logger.info("Cargando ubicaciones actuales");
		RespuestaServicioUnidad respuesta = template.getForObject(URIS.URI_UbicacionMb, RespuestaServicioUnidad.class);
		mongoCrud.guardarDocumentos(
				respuesta.getRespuesta().stream().map((p) -> {
					p.getCampos().fijarPunto();
					return p.getCampos();}).collect(Collectors.toList()));
	}

	
	/*
	 * Elimina las colecciones de las alcaldias y unidades disponibles 
	 * y las restablece con los valores recuperados en cada consulta al servicio  
	 * 
	 * */
	@Scheduled(fixedRate = 6000 * 100)
	private void obtenerInformacionAlcaldias() {
		logger.info("Obteniendo Catalogos");
		
		mongoCrud.borrarColeccion(new Alcaldia().obtenerNombreColeccion());
		mongoCrud.borrarColeccion(new Unidad().obtenerNombreColeccion());

		RespuestaServicio respuesta = template.getForObject(URIS.URI_INFO_ALCALDIA, RespuestaServicio.class);
		mongoCrud.guardarDocumentos(
				respuesta.getRespuesta().stream().map(p -> p.getCampos()).collect(Collectors.toList()));

		RespuestaServicioUnidad respuestaUnidades = template.getForObject(URIS.URI_UbicacionMb,
				RespuestaServicioUnidad.class);
		mongoCrud.guardarDocumentos(respuestaUnidades.getRespuesta().stream().map((p) -> {
			return new Unidad(p.getCampos().getIdUnidad(), p.getCampos().getEtiqueta());
		}).collect(Collectors.toList()));
	}

}
