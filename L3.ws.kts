import java.security.SecureRandom;

import java.util.*

sealed class Result<T>

class Success<T>(val data : T) : Result<T>(){

override fun toString() = "data: $data"

}

class Error<T>(val message : String = "Unknown error") : Result<T>(){

override fun toString() = "$message\n"

}

enum class Operation{

SORT_ASC

{

override fun <T : Comparable<T» invoke(list: List<T>) : List<T>

{

return list.sorted()

}

},

SORT_DESC

{

override fun <T : Comparable<T» invoke(list: List<T>) : List<T>

{

return list.sortedDescending()

}

},

SHUFFLE

{

override fun <T : Comparable<T» invoke(list: List<T>) : List<T>

{

return list.shuffled()

}

};

abstract operator fun <T : Comparable<T» invoke(list: List<T>): List<T>

}

fun <T : Comparable<T» List<T>.operate(operation: Operation): Result<List<T»

{

if (this.isEmpty()) return Error("Empty")

when (operation)

{

Operation.SORT_ASC -> return Success(Operation.SORT_ASC(this))

Operation.SORT_DESC -> return Success(Operation.SORT_DESC(this))

Operation.SHUFFLE -> return Success(Operation.SHUFFLE(this))

}

}

fun generateStrings(stringsLenght: Int, length : Int) : List<String>

{

var strlist = mutableListOf<String>()

val alphabet: List<Char> = ('a'..'z') + ('A'..'Z')

for(i in 1..length) strlist.add(List(stringsLenght) { alphabet.random() }.joinToString(""))

return strlist

}

fun generateIntegers(length : Int) : List<Int>

{

return (-1000..1000).shuffled().take(length)

}

fun main() {

println(generateStrings(4,5).operate(Operation.SORT_ASC))

println(generateStrings(4,5).operate(Operation.SORT_DESC))

println(generateStrings(4,5).operate(Operation.SHUFFLE))

println(generateIntegers(5).operate(Operation.SORT_ASC))

println(generateIntegers(5).operate(Operation.SORT_DESC))

println(generateIntegers(5).operate(Operation.SHUFFLE))

}

main()