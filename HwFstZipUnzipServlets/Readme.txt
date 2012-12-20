ZIP FILE

Just add OutputStream to ZipOutputStream and 
put file name to ZipEntry.

ZipOutputStream zos = new ZipOutputStream(os);
ZipEntry ze = new ZipEntry(fileName);
zos.putNextEntry(ze);


----------------------------------------------------------------------------


UNZIP FILE

Just add InputStream to ZipInputStream and 
get file name from ZipEntry.

ZipInputStream zis = new ZipInputStream(is);
ZipEntry ze = zis.getNextEntry();
String fileName = ze.getName();