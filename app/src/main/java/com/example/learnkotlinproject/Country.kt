package com.example.learnkotlinproject

data class Country (
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flag: String,
        ){
    fun languagesToString() : String{
        var result = "";

        for ((index, language) in languages.withIndex()){
            result += if(index != languages.lastIndex){
                "${language.name}, "
            }else{
                language.name
            }
        }

        return languages.joinToString (separator = ","){ it.name};
    }
}

data class Language(
    val name: String,
)