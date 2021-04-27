fun main(){
    println("== SIMPLE SSG 시작 ==")

    while(true) {
        print("명령어) ")
        val command = readLinTrim()
    }

    println("== SIMPLE SSG 끝 ==")
}

fun readLinTrim() = readLine()!!.trim()


class Rq(command: String){
    val actionPath: String
    val paramMap:Map<String, String>

    init {
        val commandBits = command.split("?", limit = 2)
        actionPath = commandBits[0]
        val queryStr = if (commandBits.lastIndex == 1 && commandBits[1].isNotEmpty()) {
            commandBits[1].trim()
        } else {
            ""
        }

        paramMap = if (queryStr.isEmpty()) {
            mapOf()
        } else {
            val paramMapTemp = mutableMapOf<String, String>()
            val queryStrBits = queryStr.split("&")

            for(queryStrBit in queryStrBits ){
                val queryStrBitBits = queryStrBit.split("=",limit = 2)
                val paramkey = queryStrBitBits[0]
                val paramValue = if(queryStrBitBits.lastIndex== 1 && queryStrBitBits[1].isNotEmpty()){
                    queryStrBitBits[1]
                }else {
                    ""
                }
                if(paramValue.isNotEmpty()){
                    paramMapTemp[paramkey] = paramValue
                }
            }
            paramMapTemp.toMap()

        }
    }
}