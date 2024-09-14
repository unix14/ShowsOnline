package com.triPCups.blogs.showsOnline.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ברוכים הבאים לאפליקציית \"הצגות אונליין\"!\n" +
                "\n" +
                "אם אתם אוהבים להתרגש מקסם התיאטרון, האפליקציה שלנו פה עבורכם. חוויה ייחודית של מופעים, תיאטרונים והצגות מהנות זמינה לכם בכל עת ובכל מקום.\n" +
                "\n" +
                "בימים אלו אנו מביאים לכם את הצגות הילדים הטובות ביותר, תיאטרונים מרתקים והופעות מוזיקליות מרגשות, והכל זמין לצפייה ישירה.\n" +
                "\n" +
                "הצטרפו אלינו ותחוו את התרגשות התיאטרון בכל עת ובכל מקום. אנו מזמינים אתכם להיות חלק מחוויה מרהיבה זו ולטעום את מגווניות התרבויות התיאטרון.\n" +
                "\n" +
                "תהנו מההצגות ושתהיה חוויה נהדרת!"
    }
    val text: LiveData<String> = _text
}