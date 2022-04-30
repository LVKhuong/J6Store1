package com.j6d1.rest.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.j6d1.service.UploadService;

@CrossOrigin("*")
@RestController
public class UploadRestController {

	@Autowired
	UploadService uploadService;

	@GetMapping("/rest/upload/{folder}/{file}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable("folder") String folder,
			@PathVariable("file") String file) {
		//
		if (!file.equals("") || file != null) {
			try {
				Path filename = Paths.get(folder, file);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);

				return ResponseEntity
						.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			} catch (Exception e) {
				//
			}
		}

		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) {
		String savedFile = uploadService.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		node.put("name", savedFile);

		return node;
	}

	@DeleteMapping("/rest/upload/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String filename) {
		Path path = Paths.get(folder, filename);
		path.toFile().delete();
	}

	@GetMapping("/rest/upload/{folder}")
	public List<String> list(@PathVariable("folder") String folder) {
		List<String> filenames = new ArrayList<>();
		File path = Paths.get(folder).toFile();
		if (path.exists()) {
			File[] files = path.listFiles();
			for (File file : files) {
				filenames.add(file.getName());
			}
		}

		return filenames;
	}

}
