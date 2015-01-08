package com.mylab.cromero.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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
	public @ResponseBody String provideUploadInfo() {
		return "haz post en esta url para poder enviar ficheros.";
	}

	@RequestMapping(value = "/subir", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3,
			@RequestParam("file4") MultipartFile file4) throws IOException {
	
		if (!file1.isEmpty() || file2.isEmpty() || file3.isEmpty()) {

			byte[] bytes = file1.getBytes();
			logger.debug("Uploading file 1");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(ficheroNomalizado+ file1.getOriginalFilename())));
			stream.write(bytes);
			stream.close();
			logger.debug("Uploading file  2");
			bytes = file2.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(new File(
					ficheroErrorIt + file2.getOriginalFilename())));
			stream.write(bytes);
			stream.close();
			logger.debug("Uploading file  3 size:{}", file3.getSize());
			bytes = file3.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(new File(funcionIt + file3.getOriginalFilename())));
			stream.write(bytes);
			stream.close();
			logger.debug("Uploading file 4");
			bytes = file4.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(new File(
					resolucionError + file4.getOriginalFilename())));
			stream.write(bytes);
			stream.close();
			logger.debug("Ficheros uploading OK {},{},{}", file1.getName(),file2.getName(), file3.getName());
			return "Ficheros uploading OK ";

		} else {
			return "Ficheros uploading KO ";
		}
	}

	@ExceptionHandler(value = { MaxUploadSizeExceededException.class })
	protected String handleMaxUpload(RuntimeException ex, WebRequest request) {
		logger.error("MaxUploadSizeExceededException Server error");
		return "MaxUploadSizeExceededException ";
	}

	@ExceptionHandler(value = { MaxUploadSizeExceededException.class })
	protected String handleConflict(RuntimeException ex, WebRequest request) {
		logger.error("Exception on server Server error", ex);
		return "File uploader error ";
	}
}