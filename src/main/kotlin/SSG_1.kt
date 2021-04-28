import java.text.SimpleDateFormat

fun main(){
    println("== SIMPLE SSG 시작 ==")

    articleRepository.makeTestArticles()

    while(true){
        print("명령어) ")
        val command = readLineTrim()

        val rq = Rq(command)

        when(rq.actionPath){
            "/system/exit" -> {
                println("프로그래을 종료합니다.")
                break
            }
            "/article/detail" -> {
                val id = rq.getIntparam("id", 0)

                if (id == 0) {
                    println("id를 입력해주세요.")
                    continue
                }
                val article = articleRepository.articles[id - 1]

                println(article)
            }
        }
    }


    println("== SIMPLE SSG 시작 ==")
}


fun readLineTrim() = readLine()!!.trim()

class Rq(command: String){
    val actionPath:String
    val paramMap: Map<String, String>

    init{
        val commandBits = command.split("?", limit = 2)
        actionPath = commandBits[0]
        val queryStr = if(commandBits.lastIndex == 1 && commandBits[1].isNotEmpty()){
            commandBits[1].trim()
        }else{
            ""
        }
        paramMap = if(queryStr.isEmpty()){
            mapOf()
        }else{
            val paramMapTemp = mutableMapOf<String, String>()
            val queryStrBits = queryStr.split("&")

            for (queryStrBit in queryStrBits) {
                val queryStrBitBits = queryStrBit.split("=", limit = 2)
                val paramKey = queryStrBitBits[0]
                val paramValue = if (queryStrBitBits.lastIndex == 1 && queryStrBitBits[1].isNotEmpty()) {
                    queryStrBitBits[1]
                } else {
                    ""
                }
                if (paramValue.isNotEmpty()) {
                    paramMapTemp[paramKey] = paramValue
                }
            }
            paramMapTemp.toMap()
        }
    }
    fun getStrparam(paramKey: String, default: String): String{
        return paramMap[paramKey] ?: default
    }
    fun getIntparam(paramValue: String, default: Int): Int{
        return if (paramMap[paramValue] != null){
            try{
                paramMap[paramValue]!!.toInt()
            }catch(e: NumberFormatException){
                default
            }
        }else{
            default
        }
    }
}

data class Article(
    val id: Int,
    val regDate: String,
    val updateDate: String,
    val title: String,
    val body: String
)


object articleRepository {
    val articles = mutableListOf< Article >()
    var lastId = 0

    fun addArticle(title: String, body: String){
        val id = ++lastId
        val regDate = Util.getNowDateStr()
        val updateDate = Util.getNowDateStr()
        articles.add(Article(id, regDate, updateDate, title, body))

    }
    fun makeTestArticles(){
        for (id in 1..100){
            addArticle("제목_$id", "내용_$id")
        }
    }
}

object Util{
    fun getNowDateStr(): String{
        val format = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        return format.format(System.currentTimeMillis())
    }
}





