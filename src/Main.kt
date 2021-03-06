package src

import java.io.File
import java.nio.file.Paths
import java.util.*
import kotlin.collections.HashMap
 
fun getCharacters(type: String): HashMap<Char, Array<String>> {
    // Function to retrieve characters from the respective text files.
    val lines: Any
    val characterLines: Int
    if (type == "roman") { // Retrieve roman-font text
        lines = File(Paths.get("roman.txt").toString()).readLines()
        characterLines = 11
    } else { // Retrieve medium-font text
        lines = File(Paths.get("medium.txt").toString()).readLines()
        characterLines = 4
    }
    val characters = HashMap<Char, Array<String>>()
    var index = 1
    while (index != lines.size) {
        val line = lines[index].split(" ")
        val character = line[0][0]
        characters[character] = lines.slice(index + 1 until index + characterLines).toTypedArray()
        index += characterLines
    }
    return characters
 
}
 
fun getTextArray(name: String, type: String): Array<String> {
    // Function to retrieve an array of the signature
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
            for (x in 0..2) {arr[x] += blankSpace}
            if (type == "roman") {
                for (x in 3..9) {arr[x] += blankSpace}
            }
            continue
        }
        val character = characters[char]
        for (x in 0..2) {arr[x] = arr[x] + (character?.get(x) ?: "")}
        if (type == "roman") {
            for (x in 3..9) {arr[x] = arr[x] + (character?.get(x) ?: "")}
        }
    }
    return arr
}
 
fun printArt(name: String, tag: String) {
    // Main function that prints the artwork
    val romanArray = getTextArray(name, "roman")
    val romanArrayLength = romanArray[0].length
    val mediumArray = getTextArray(tag, "medium")
    val mediumArrayLength = mediumArray[0].length
    val length = maxOf(romanArrayLength, mediumArrayLength) + 6
    val spaces = length - mediumArrayLength
    val leftSpace = (length - romanArrayLength) / 2 - 1
    var rightSpace = leftSpace - 1
    if ((length - romanArrayLength) % 2 != 0) {
        rightSpace += 1
    }
    println("8".repeat(length + 1))
    for (index in 0..9) {println("88${" ".repeat(leftSpace)}${romanArray[index]}${" ".repeat(rightSpace)}88")}
    if (spaces % 2 == 1) {
        for (index in 0..2) {println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[index]} ${" ".repeat(spaces / 2 - 2)}88")}
    } else {
        for (index in 0..2) {println("88${" ".repeat(spaces / 2 - 2)} ${mediumArray[index]} ${" ".repeat(spaces / 2 - 3)}88")}
    }
    println("8".repeat(length + 1))
}
 
fun main() {
    val scanner = Scanner(System.`in`)
    print("Type in your name>>")
    val name = scanner.nextLine().trim()
    print("Type in your tag>>")
    val tag = scanner.nextLine().trim()
    printArt(name, tag)
}