class Etc {
    fun correct(p1: String): String {
        val p1Array = ArrayList<Char>(); for (i in p1) {
            p1Array.add(i)
        }

        if (p1Array[0].toString() == "'" && p1Array.last().toString() == "'") {
            p1Array.removeAt(0);p1Array.removeAt(p1Array.size - 1)
        }
        if (p1Array[0].toString() == "'") {
            p1Array.removeAt(0)
        }
        var out = "";for (i in p1Array) {
            out += i.toString()
        }; return out
    }

    fun giver(p1: String, p2: Int): String {
        var out = ""
        if (p2 == 1) {
            println(p1.toUpperCase())
            out = readLine().toString()
        } else if (p2 == 0) {
            print(p1.toUpperCase())
            out = readLine().toString()
        }
        return out

    }

}