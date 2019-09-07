//Import all needed packages
//Thank you who participated in building this class
//Moved from https://stackoverflow.com/questions/15968883/how-to-zip-a-folder-itself-using-java
//Modified by A-freedom "ali mousa aomrin "{ https://github.com/A-freedom }

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipUtils(src: String, dest: String) {
    private var fileList = ArrayList<String>()
    private var OUTPUT_ZIP_FILE = dest
    private var SOURCE_FOLDER = src // SourceFolder path

    private fun zipIt(zipFile: String) {
        val buffer = ByteArray(1024)
//        val source = File(SOURCE_FOLDER).name
        val fos: FileOutputStream
        var zos: ZipOutputStream? = null
        try {
            fos = FileOutputStream(zipFile)
            zos = ZipOutputStream(fos)

            println("Output to Zip : " + zipFile)

            for (file in this.fileList) {
                println("File Added : " + file)
//                val ze = ZipEntry(source + File.separator + file)
                val ze = ZipEntry(file)
                zos.putNextEntry(ze)
                val fis = FileInputStream(SOURCE_FOLDER + File.separator + file)
                var len: Int
                while (true) {
                    len = fis.read(buffer)
                    if (len > 0) {
                        zos.write(buffer, 0, len)
                    } else {
                        break
                    }
                }

            }

            zos.closeEntry()
            println("Folder successfully compressed")

        } catch (ex: IOException) {
            ex.printStackTrace()
        } finally {
            try {
                assert(zos != null)
                zos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    private fun generateFileList(node: File) {
        // add file only
        if (node.isFile) {
            fileList.add(generateZipEntry(node.toString()))
        }

        if (node.isDirectory) {
            val subNote = node.listFiles()!!
            for (file in subNote) {
                generateFileList(file)
            }
        }
    }

    private fun generateZipEntry(file: String): String {
        return file.substring(SOURCE_FOLDER.length + 1, file.length)
    }

    init {
        if (!(check())){
            error("error +++++ ")
            System.exit(0)}
        generateFileList(File(SOURCE_FOLDER))
        zipIt(OUTPUT_ZIP_FILE)
    }

    private fun check():Boolean {
        return try {
            true
        }catch (e:Exception){
            false
        }
    }


}