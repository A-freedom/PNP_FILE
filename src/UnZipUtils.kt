//Import all needed packages
//Thank you who participated in building this class
//Moved from https://stackoverflow.com/questions/15968883/how-to-zip-a-folder-itself-using-java
//Modified by A-freedom "ali mousa aomrin "{ https://github.com/A-freedom }

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class UnZipUtils(zipFile: String, outputFolder: String) {

    init {
        unZipIt(zipFile, outputFolder)
    }

    fun unZipIt(zipFile: String, outputFolder: String) {
        val buffer = ByteArray(1024)
        try {
            //create output directory is not exists
            val folder = File(outputFolder)
            if (!folder.exists()) {
                folder.mkdir()
            }
            //get the zip file content
            val zis = ZipInputStream(FileInputStream(zipFile))
            //get the zipped file list entry
            var ze: ZipEntry? = zis.nextEntry
            while (ze != null) {
                val fileName = ze.name
                val newFile = File(outputFolder + File.separator + fileName)
                println("file unzip : " + newFile.absoluteFile)
                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                File(newFile.parent).mkdirs()
                val fos = FileOutputStream(newFile)
                var len: Int
                while (true) {
                    len = zis.read(buffer)
                    if (len > 0) {
                        fos.write(buffer, 0, len)
                    } else {
                        break
                    }

                }
                fos.close()
                ze = zis.nextEntry
            }
            zis.closeEntry()
            zis.close()
            println("Done")
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }
    private fun check():Boolean {
        return try {
            true
        }catch (e:Exception){
            false
        }
    }
}