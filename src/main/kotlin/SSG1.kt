// /article/detail?id=1&title=dfas&body=fgs

fun main(){
    println("== SIMPLE SSG 시작 ==")

    while(true) {
        print("명령어) ")
        val command = readLinTrim()

        val commandBits = command.split("?", limit = 2)
        val url = commandBits[0]
        val paramStr = commandBits[1]  // id=1&title=dfas&body=fgs
        val paramStrBits = paramStr.split("&")

        val paramMap = mutableMapOf<String, String>()


        for (paramBit in paramStrBits) {
            val paramBitBits = paramBit.split("=", limit = 2)
            val key = paramBitBits[0]
            val value = paramBitBits[1]

            paramMap[key] = value
        }
        when (url) {
            "system/exit" ->{
                println("프로그램을 종료 합니다.")
                break
            }
            "/article/detail" -> {
                val id = paramMap["id"]!!.toInt()

                println("$id 번  게시물이 선택 되었습니다")
            }
        }
    }


    println("== SIMPLE SSG 끝 ==")
}

fun readLinTrim() = readLine()!!.trim()