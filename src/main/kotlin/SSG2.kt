fun main(){
    println("SIMPLE SSG 시작 ==")

    while (true){
        print("명령어) ")
        val command = readLineTrim()

        val rq = Rq(command)

        println("rq.actionPath : ${rq.actionPath}")
        println("rq.queryStr : ${rq.queryStr}")
    }


    println("SIMPLE SSG 시작 ==")
}

fun readLineTrim() = readLine()!!.trim()

class Rq(command: String){
    val actionPath: String
    val queryStr: String

    init {
        val commandBits = command.split("?", limit = 2)
        actionPath = commandBits[0]
        queryStr = commandBits[1]
    }
}