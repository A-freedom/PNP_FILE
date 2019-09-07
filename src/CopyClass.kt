import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File

class CopyDirectory(srcFolder: File, destFolder: File) {
    private val s = File.separator

    init {
        copyFolder(srcFolder, destFolder)
    }

    private fun copyFolder(src: File, dest: File) {
        println("++++++++++++++++'$src'++++++++++++++++")
        if (!src.exists()) {
            println("Directory does not exist.")
            //just exit
            System.exit(0)
        }

        if (!checkExists(dest)) System.exit(0)

        val listOfSrcfiles = ArrayList<File>()

        src.list().mapTo(listOfSrcfiles) { (File(it)) }

        for (i in listOfSrcfiles) {
            if (File("$src$s$i").isFile) {
                println("$src$s$i  -to-  $dest$s$i")
                if (!copyFile(File("$src$s$i"), File("$dest$s$i"))) System.exit(0)
            } else {
                copyFolder(File("$src$s$i"), File("$dest$s$i"))
            }
        }

    }

    private fun checkExists(dest: File): Boolean {
        return if (!dest.exists()) {
            dest.mkdir()
            true
        } else {
            true
        }

    }

    private fun copyFile(in1: File, out1: File): Boolean {
        return try {
            clear(out1)
            val data = BufferedInputStream(in1.inputStream())
            val out = BufferedOutputStream(out1.outputStream())
            val theDataByteArray = data.readBytes()
            out.write(theDataByteArray)
            out.close()
            true
        } catch (e: Exception) {
            false
        }


    }

    private fun clear(out: File) {
        if (out.isFile || out.isDirectory) {
            out.delete()

        }

    }
}
