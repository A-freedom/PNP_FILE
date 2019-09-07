import java.io.File

fun main(args: Array<String>) {
    if (!args.isEmpty()) {
        when (args[0]) {
            "-d" -> {
                var divnum = 2000
                val div = args[2]
                if (div != "") {
                    divnum = div.toInt()
                } else {
                    println("*** used 2000".toUpperCase()); Thread.sleep(750)
                }

                Dissociation(src = File(Etc().correct(args[1])), divNum = divnum)


            }
            "-a" -> {
                Aggregation(src = File(Etc().correct(args[1])))
            }
        }
    } else {
        while (true) {
            println("+++++++++++++++++++++++++++++++++++++++++++++++")
            print("Hi (1) for distortion ; (2) for aggregation : ".toUpperCase())
            val chose = readLine().toString()
            when (chose) {
                "1" -> {
                    val inUrl = Etc().giver("ENTER URL FILE in : ", 0)
                    print(("INTER Divide factor note\n" + "(All that increased the number of files increased) default(2000): ").toUpperCase())
                    var divNum = 2000
                    val read = Etc().giver("", 0)
                    if (read != "") divNum = read.toInt() else {
                        println("*** used 2000".toUpperCase()); Thread.sleep(750)
                    }

                    Dissociation(src = File(Etc().correct(inUrl)), divNum = divNum)


                }
                "2" -> {
                    val inUrl = Etc().giver("enter folder url : ", 0)
                    Aggregation(src = File(Etc().correct(inUrl)))
                }
            }
        }
    }
}


