package com.triPCups.blogs.base.repos.user_name_generator

class AnonNameGeneratorImpl : RandomGenerator {

    private val names = arrayListOf(
        "ריצ'רד",
        "סימון",
        "בן",
        "שמחה",
        "תום"
    )

    private val colors = arrayListOf(
        "ירוק",
        "לבן",
        "כחול",
        "אדום",
        "שחור",
        "כתום",
        "חום"
    )

    private val adjectives = arrayListOf(
        "כבד",
        "פשוט",
        "לא ידוע",
        "לא מהומן",
        "ניתן להרוס",
        "גמיש",
        "מסוכן",
        "פושט",
        "הסטרי",
        "היסטרי",
        "היסטורי",
        "מסתורי",
        "מטרילן",
        "כועס",
        "שמח",
        "מוזר",
        "מטעין",
        "דוקטור",
        "גברת",
        "פרופסור",
        "מר",
        "מיסטרס",
        "צבעוני"
    )

    private val items = arrayListOf(
        "כיסא",
        "שולחן",
        "מחברת",
        "ספר",
        "פתק",
        "שלט",
        "גלגל",
        "אהבה",
        "רכב",
        "אנדרואיד",
        "רובוט",
        "אדם",
        "חייזר",
        "בוט",
        "חיים",
        "במה",
        "גיטרה"
    )

    private val nature = arrayListOf(
        "כיסא",
        "לאבה",
        "מפל",
        "גשם",
        "במבוק",
        "עץ",
        "שיח",
        "אגם"
    )

    private val fruits = arrayListOf(
        "מלפפון",
        "בננה",
        "אננס",
        "תפוח",
        "אפרסק",
        "דלעת"
    )


//    private val names = arrayListOf(
//        "Richard",
//        "Simon",
//        "Ben",
//        "Simcha",
//        "Tom"
//    )
//
//    private val colors = arrayListOf(
//        "Green",
//        "White",
//        "Blue",
//        "Red",
//        "Black",
//        "Orange",
//        "Brown"
//    )
//
//    private val adjectives = arrayListOf(
//        "Heavy",
//        "Simply",
//        "Unknowingly",
//        "Untrusted",
//        "Destroyable",
//        "Flexible",
//        "Smoochy",
//        "Chaotic",
//        "Hysteric",
//        "Historic",
//        "Mysterious",
//        "Mischievous",
//        "Barbaric",
//        "Angry",
//        "Happy",
//        "Weird",
//        "Cheater",
//        "Dr.",
//        "Mr.",
//        "Mrs.",
//        "Professor",
//        "Mister",
//        "Mistress",
//        "Colorful"
//    )
//
//    private val items = arrayListOf(
//        "Chair",
//        "Desk",
//        "Notebook",
//        "Book",
//        "Note",
//        "Remote",
//        "Wheel",
//        "Love",
//        "Car",
//        "Android",
//        "Robot",
//        "Person",
//        "Alien",
//        "Bot",
//        "Life",
//        "Stage",
//        "Guitar",
//    )
//
//    private val nature = arrayListOf(
//        "Chair",
//        "Lava",
//        "Waterfall",
//        "Rain",
//        "Bambook",
//        "Tree",
//        "Bush",
//        "Lake"
//    )
//
//    private val fruits = arrayListOf(
//        "Cucumber",
//        "Banana",
//        "Pineapple",
//        "Apple",
//        "Peach",
//        "Watermelon"
//    )


    private val possibleMatches: ArrayList<Pair<ArrayList<String>, ArrayList<String>>>
        get() = arrayListOf<Pair<ArrayList<String>, ArrayList<String>>>().apply {
            add(Pair(adjectives, names))
            add(Pair(adjectives, items))
            add(Pair(adjectives, nature))
            add(Pair(adjectives, fruits))
            add(Pair(adjectives, colors))

            add(Pair(colors, names))
            add(Pair(colors, items))
            add(Pair(colors, nature))
            add(Pair(colors, fruits))
        }

    private fun getRandomFirstWord(): String = possibleMatches.random().first.random()

    private fun getRandomSecondWord(): String = possibleMatches.random().second.random()

    private fun getRandomPair(): Pair<ArrayList<String>, ArrayList<String>> = possibleMatches.random()

    override fun generate(): String // = getRandomFirstWord() + " " + getRandomSecondWord()
    {
        getRandomPair().let { pair ->
            return pair.first.random() + " " + pair.second.random()
        }
    }
}