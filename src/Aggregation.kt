import java.io.*

class Aggregation(src: File) {
    private val s = File.separator
    private var key: File = File("")
    private var listOfKeyNumber = ArrayList<File>()
    private var src = File("")
    private var finalFile = ""

    init {
        if (src.isFile) {
            val outputFolder = "${src.parent}$s${src.nameWithoutExtension}"
            UnZipUtils("$src", outputFolder)
            this.src = File(outputFolder)
            findLog()
            addingFileToLog()
            backup()
            println("remove : $outputFolder")

            for (i in File(outputFolder).listFiles()){i.delete()};File(outputFolder).delete()
        }else{
            println("error file in is not file !!")
        }
    }

    private fun findLog(){
        var found = false
        var localKey = File(s)
        val listFile = this.src.parentFile.listFiles()
        for (i in listFile) {
            if ("$i".endsWith(".key")) {
                localKey = i;println("Find key Done :" + i);found = true;break
            }
        }
        if (!found) {
            println("found log filed ")
            localKey = File(Etc().giver("enter url file of key : ", 0))
        }
        this.key = localKey
    }

    private fun addingFileToLog() {
        if (key.isFile)        println(key)
        val readLine = FileReader(key)
        val listFile = readLine.readLines()
        for (i in listFile) {
            listOfKeyNumber.add(File("$src$s$i"))
        }

    }

    private fun backup() {
        try {
            val outFile = File("${this.listOfKeyNumber[0]}")
            this.finalFile = outFile.parentFile.parent+s+outFile.name

            val mkdirsFile = BufferedOutputStream(FileOutputStream(outFile.parentFile.parent+s+outFile.name, true))
            listOfKeyNumber.removeAt(0)
            for (i in listOfKeyNumber) {
                println("Read file Done :" + i)
                val bytes = BufferedInputStream(FileInputStream(i)).readBytes()
                mkdirsFile.write(bytes)
            }
            mkdirsFile.close()
        } catch (e: Exception) {
            println(e)
        } finally {
            println("Make File :$finalFile")
        }

    }

}
