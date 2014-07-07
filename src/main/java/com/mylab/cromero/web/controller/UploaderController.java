package com.mylab.cromero.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping(value = "/")
public class UploaderController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Value("${ruta.fichero.descripcion.normalizada}")
	private String ficheroNomalizado;

	@Value("${ruta.fichero.descripcion.errorit}")
	private String ficheroErrorIt;
	
	@Value("${ruta.fichero.descripcion.funcionit}")
	private String funcionIt;
	
	@Value("${ruta.fichero.descripcion.resolucionerror}")
	private String resolucionError;
	
	
	@Value("${url.servicio}")
	private String urlServicio;
	
	
	

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		logger.debug("welcome index page");
		return "hello";

	}

	@RequestMapping(value = "/subir", method = RequestMethod.GET)
	public @ResponseBody
	String provideUploadInfo() {
		return "haz post en esta url para poder enviar ficheros.";
	}

	@RequestMapping(value = "/subir", method = RequestMethod.POST)
	public @ResponseBody
	String handleFileUpload(@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3,
			@RequestParam("file4") MultipartFile file4) {
		logger.debug("valores de datos {}",resolucionError);
		logger.debug("Subiendo los ficheros {},{},{}",file1.getName(),file2.getName(),file3.getName());
		if (!file1.isEmpty() || file2.isEmpty() || file3.isEmpty()) {
			try {
				byte[] bytes = file1.getBytes();
				logger.debug("Subiendo fichero numero 1");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(ficheroNomalizado+file1.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				logger.debug("Subiendo fichero numero 2");
				bytes = file2.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(new File(ficheroErrorIt+file2.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				logger.debug("Subiendo fichero numero 3 tamaño:{}",file3.getSize());
				bytes = file3.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(new File(funcionIt+file3.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				logger.debug("Subiendo fichero numero 4");
				bytes = file4.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(new File(resolucionError+file4.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				logger.debug("Ficheros subidos correctamente {},{},{}",file1.getName(),file2.getName(),file3.getName());
				//TODO CALL WEB SERVICE
				//RestTemplate restTemplate = new RestTemplate();
			   
				
				return "has subido correctamente los ficheros al servidor central ";
			} catch (Exception e) {
				logger.error("error al subir ficheros al servidor");
				return "error subiendo los ficheros al servidor " + file1.getName() + " => "
						+ e.getMessage();
			}
		} else {
			return "error al subir archivos envie por favor los cuatro ficheros ";
		}
	}

}