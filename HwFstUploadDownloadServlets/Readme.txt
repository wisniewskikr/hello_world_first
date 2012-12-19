UPLOAD

1. Form in html 

<form ... method="POST" enctype="multipart/form-data">

2. Annotation in sevlet
@MultipartConfig

3. Object Part
final Part filePart = request.getPart("file");
InputStream is = filePart.getInputStream();


---------------------------------------------------------------


DOWNLOAD

1. Response in servlet
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	OutputStream os = response.getOutputStream();