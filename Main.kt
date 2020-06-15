import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap
 
fun getCharacters(type: String): HashMap<Char, Array<String>> {
    var lines: Any = ""
    var characterLines = 0
    if (type == "roman") {
        lines = File(Paths.get("roman.txt").toString()).readLines()
        characterLines = 11
    } else {
        lines = File(Paths.get("medium.txt").toString()).readLines()
        characterLines = 4
    }
    val characters = HashMap<Char, Array<String>>()
    var index = 1
    while (index != lines.size) {
        val line = lines[index].split(" ")
        val character = line[0][0]
//        val characterWidth = line[0][1]
        characters[character] = lines.slice(index + 1 until index + characterLines).toTypedArray()
        index += characterLines
    }
    return characters
 
}
 
fun getTextArray(name: String, type: String): Array<String> {
    val characters: HashMap<Char, Array<String>>
    val arr: Array<String>
    val blankSpace: String
    if (type == "medium" ) {
        characters = getCharacters("medium")
        arr = arrayOf("", "", "")
        blankSpace = "     "
    } else {
        characters = getCharacters("roman")
        arr = arrayOf("", "", "", "", "", "", "", "", "", "")
        blankSpace = "          "
    }
    for (char in name) {
        if (char.isWhitespace()) {
            arr[0] += blankSpace
            arr[1] += blankSpace
            arr[2] += blankSpace
            if (type == "roman") {
                arr[3] += blankSpace
                arr[4] += blankSpace
                arr[5] += blankSpace
                arr[6] += blankSpace
                arr[7] += blankSpace
                arr[8] += blankSpace
                arr[9] += blankSpace
            }
            continue
        }
        val character = characters[char]
        arr[0] = arr[0] + (character?.get(0) ?: "")
        arr[1] = arr[1] + (character?.get(1) ?: "")
        arr[2] = arr[2] + (character?.get(2) ?: "")
        if (type == "roman") {
            arr[3] = arr[3] + (character?.get(3) ?: "")
            arr[4] = arr[4] + (character?.get(4) ?: "")
            arr[5] = arr[5] + (character?.get(5) ?: "")
            arr[6] = arr[6] + (character?.get(6) ?: "")
            arr[7] = arr[7] + (character?.get(7) ?: "")
            arr[8] = arr[8] + (character?.get(8) ?: "")
            arr[9] = arr[9] + (character?.get(9) ?: "")
        }
    }
    return arr
}
 
fun printArt(name: String, tag: String) {
    val romanArray = getTextArray(name, "roman")
    val romanArrayLength = romanArray[0].length
    val mediumArray = getTextArray(tag, "medium")
    val mediumArrayLength = mediumArray[0].length
 
    val length = maxOf(romanArrayLength, mediumArrayLength) + 6
    val spaces = length - mediumArrayLength
    val leftSpace = (length - romanArrayLength) / 2 - 1
    var rightSpace = leftSpace
    if ((length - romanArrayLength) % 2 != 0) {
        rightSpace += 1
    }
    println("8".repeat(length + 2))
    println("88${" ".repeat(leftSpace)}${romanArray[0]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[1]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[2]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[3]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[4]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[5]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[6]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[7]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[8]}${" ".repeat(rightSpace)}88")
    println("88${" ".repeat(leftSpace)}${romanArray[9]}${" ".repeat(rightSpace)}88")
    if (spaces % 2 == 1) {
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[0]} ${" ".repeat(spaces / 2 - 1)}88")
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[1]} ${" ".repeat(spaces / 2 - 1)}88")
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[2]} ${" ".repeat(spaces / 2 - 1)}88")
    } else {
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[0]} ${" ".repeat(spaces / 2 - 2)}88")
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[1]} ${" ".repeat(spaces / 2 - 2)}88")
        println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[2]} ${" ".repeat(spaces / 2 - 2)}88")
    }
    println("8".repeat(length + 2))
}
 
fun main() {
    val scanner = Scanner(System.`in`)
    print("Type in your name>>")
    val name = scanner.nextLine().trim()
    print("Type in your tag>>")
    val tag = scanner.nextLine().trim()
    printArt(name, tag)
}