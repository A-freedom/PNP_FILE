import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.collections.ArrayList

class Dissociation(private var src: File,divNum: Int) {
    private val s = File.separator
    private val dest = File("${src.parent}$s${src.nameWithoutExtension}!")
    var DivFactor:Int
    private var keyLog = ArrayList<String>()
//    val buffer = ByteArray(1024)

    init {
        claer()
        if (!dest.isDirectory) dest.mkdir()
        this.keyLog.add(src.name)
        this.DivFactor = divNum
        dissociationIt()
        zipCutting()
    }

    private fun claer(): Boolean {
        return try {
            if (dest.exists()) {
                for (i in dest.listFiles()) {
                    i.delete()
                }
                dest.delete()
                println("clear Done")
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun zipCutting() {
        ZipUtils("$dest", "${dest.parent}$s${src.nameWithoutExtension}.box")
        claer()
    }

    private fun outingKeyLog(keyLog: ArrayList<String>, diss: File) {
        val file = File("${diss.parent}$s${src.nameWithoutExtension}.key")
        val out = BufferedOutputStream(FileOutputStream(file))
        for (i in keyLog) {
            out.write("$i\n".toByteArray())
        }
        out.close()
        println("write file key  Done : $file")
    }

    private fun writeBytex(byte: ByteArray) {
        var name = ""
        val newFile: BufferedOutputStream
        try {
            name = Math.random().toString()
            newFile = BufferedOutputStream(FileOutputStream("$dest$s$name"))
            newFile.write(byte)
            newFile.close()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("write file Done : $dest$s$name ")
            keyLog.add(name)

        }
    }

    private fun dissociationIt() {
        val src = BufferedInputStream(src.inputStream())
        val orgBytes: ByteArray
        orgBytes = src.buffered().readBytes()
        val orgBytesSize = orgBytes.size
        var from = 0
        var to = 0
        val g: Int = orgBytesSize / DivFactor
        //    ++++++++++++++++++++++ the while start ++++++++++++++++++++++
        while (true) {
            //    ++++++++++++++++++++++ made var to = var of org / 5 ++++++++++++++++++++++
            to += g
            //    ++++++++++++++++++++++ if start ++++++++++++++++++++++
            if (to > orgBytesSize) {
                to = orgBytesSize
            }
            if (to <= orgBytesSize || from < orgBytesSize) {
                val outByteArray = Arrays.copyOfRange(orgBytes, from, to)
                //   ======================== write start ======================
                writeBytex(outByteArray)
                //   ======================== write and ========================
                if (to == orgBytesSize) {
                    break
                }
                from = to
            }
            //    ++++++++++++++++++++++ if and ++++++++++++++++++++++

        }
        //    ======================== AND ========================
        outingKeyLog(keyLog, dest)
    }

}
