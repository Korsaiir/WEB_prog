//import java.util.*

open class Person(
    var name : String,
    var birthYear : Int
){
    override fun toString() = "name: $name birthYear: $birthYear \n"

    var nowYear = 2021
    var age :Int = nowYear - birthYear;
    //open fun getAge(){return age}
}

class Student(
    name : String,
    birthYear : Int,
    var averageGrade : Float,
    var extramural : Boolean = false
):Person(name,birthYear) {

    override fun toString() = "name: $name birthYear: $birthYear averageGrade: $averageGrade extramural: $extramural\n"
}

class Lecturer(
    name : String,
    birthYear : Int,
    var degree : String,
    var experienceFrom : Int
):Person(name,birthYear){
    override fun toString() = "name: $name birthYear: $birthYear degree: $degree experienceFrom: $experienceFrom\n"
}

fun MutableList<Person>.sortByAge():List<Person> {
    return this.sortedByDescending { it.age }
}

fun MutableList<Person>.sortByNameStudents():List<Student>
{
    return this.filter{ it is Student }.sortedByDescending{ it.name } as List<Student>
}

fun MutableList<Person>.sortByAverageGrade(exceptExtramural : Boolean):List<Student>
{
    return if (!exceptExtramural)
        (this.filter{ it is Student } as List<Student>).sortedByDescending{ it.averageGrade }
    else (this.filter{ it is Student } as List<Student>).filter{ !it.extramural }.sortedByDescending{ it.averageGrade }
}

fun main(){
    var list = mutableListOf(Student("Makakov",2000,4.3f),
        Student("Krinjov",1999, 3.9f),
        Student("Bolayev",2001,4.7f,true),
        Student("Barboskin",2000,5.0f,true),
        Student("Tremasov",2002,4.4f),
        Lecturer("Showmaev",1983,"PhD",2007),
        Lecturer("Backaeva",1989,"PhD",2018),
        Lecturer("Pudgin",1968,"Doctor of science",1995),
        Lecturer("Madonov",1981,"Doctor of science",2004),
        Lecturer("Fistova",1976,"PhD",1999))
    println("Список до изменений:\n"+list)
    println("Сортировка по возрасту:\n"+list.sortByAge())
    println("Сортировка по имени студентов:\n"+list.sortByNameStudents())
    println("Сортировка по средней оценке студентов:\n"+list.sortByAverageGrade(true))
}

main()