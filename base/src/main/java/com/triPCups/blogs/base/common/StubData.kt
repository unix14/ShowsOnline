package com.triPCups.blogs.base.common

import com.triPCups.blogs.base.models.BlogFeed
import com.triPCups.blogs.base.models.BlogPost
import com.triPCups.blogs.base.models.HyperLink
import com.triPCups.blogs.base.models.PostComment
import com.triPCups.blogs.base.models.PostTag

object StubData {

    fun getShowsByTheater(): BlogPost {
        return BlogPost(
            title = "ספי אםגם טודוודוד",
            shortDescription = "משך הסרטון 7:23",
            description = "קטע קצר מהמופע",
            thumbnail = "https://i.ytimg.com/vi/jtwV8LX1mlk/sddefault.jpg",
            links = arrayListOf(
                HyperLink(
                    url = "https://www.youtube.com/watch?v=jtwV8LX1mlk",
                    text = "לצפייה בסרטון"
                )
            )
        )
    }


    fun getOldShows(): BlogFeed = BlogFeed(
        arrayListOf(
            BlogPost(
                title = "הומאז' ללהקת החאן המיתולוגית משנות השבעים",
                description = "\"קטעים מתוך סרטה של עידית אברהמי \"שחקנים נגד קהל<br/>" +
                        "<br/>" +
                        "להקת החאן הירושלמי 1972-82<br/>" +
                        "אורי אברהמי, אהרון אלמוג, שבתאי קונורטי ,אבי פניני, רחל הפלר, שפרה מילשטיין, עליזה רוזן, שלמה תרשיש, גבי אלדור, רחל שור, רחל לוי, אשר צרפתי, אבינועם מור חיים, נעמי בכר, שמיל בן ארי, ששון גבאי, ספי ריבלין, צביקה הלפרין, אלי דנקר, משה אסם, דוד זאבי, שלום קינן, מיכאל [אלכס] קלצ'קין<br/>" +
                        ",ישראל גוריון, מאיר פניגשטיין<br/>" +
                        "במאים: מייקל אלפרדס, אילן רונן, רות זיו אייל, הלל נאמן, מיכל גוברין, יעקב רז, יונתן מרזר, חנן שניר, דוד זינדר<br/>" +
                        "עיצוב תפאורה: פרידה קלפהולץ, משה שטרנפלד, דוד שריר, איתן לוי<br/>" +
                        "עיצוב תאורה: בן ציון מוניץ<br/>" +
                        "\" רוב אנשי החאן ככולם תמימי דעים: בשנות השבעים , תחת שרביטו של רונן החאן היה התיאטרון המבריק והמרתק בארץ , עם \" מלחמות היהודים\" , \"צרלי קצרלי\", \"קדימה ביתר\",  ועוד. זה היה תיאטרון ירושלמי אמיתי.\" ידיעות אחרונות – המגזין 16.6.2010",
                shortDescription = "משך הסרטון 20:48",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=3Hcnkp9ZIB4",
                        text = "לצפייה אונליין"
                    )
                ),
                thumbnail = "https://i.ytimg.com/vi/3Hcnkp9ZIB4/hqdefault.jpg"
            ),
            BlogPost(
                title = "אנה קרנינה תיאטרון באר שבע",
                shortDescription = "משך ההצגה 1:3:00",
                description =
                "ANNA KARENINA\n" +
                        "<br/>\n" +
                        "BY\n" +
                        "<br/>\n" +
                        "LEO TOLSTOY\n" +
                        "<br/>\n" +
                        "Adapted by\n" +
                        "<br/>\n" +
                        "Helen Edmundson\n" +
                        "<br/>\n" +
                        "אנה קרנינה\n" +
                        "<br/>\n" +
                        "בשיתוף עם תיאטרון באר שבע\n" +
                        "<br/>\n" +
                        "הצגת בכורה: 18/11/2007\n" +
                        "<br/>\n" +
                        "<br/><br/><b><u>אודות ההצגה:</u></b><br/>\n" +
                        "אנה קרנינה קלאסיקה בעיבוד חדשני. אנה היא אשת חברה פופולארית ומכובדת, הנשואה לבעל שאותו אינה אוהבת, ואם לבן. קונסטנטין לוין הוא בעל אחוזה עשיר, שאינו חש בנוח בחברה הרוסית האריסטוקרטית ומעדיף את חיי הכפר. אנה נאלצת להתמודד עם נקמנותו של בעלה ועם הבידוד החברתי שניכפה עליה מפאת בגידתה בו. לוין מתקשה להתגבר על סירובה של קיטי להינשא לו משום שהעדיפה את וורונסקי על פניו. הוא מתמסר יותר ויותר לעבודה הפיסית ולכתיבה מקצועית. הרהוריו של לוין על משמעות החיים פוסקים משהוא זוכה לבסוף באהבתה של קיטי. המשמעות שאנה מוצאת באהבתה הייצרית לוורונסקי דועכת בבליל של חשדות ומורפיום עד שהיא משליכה את עצמה מתחת לגלגלי רכבת.\n" +
                        "<br/>\n" +
                        "<br/><br/><b><u>יוצרים:</u></b><br/>\n" +
                        "על פי רומן מאת: ל.נ. טולסטוי\n" +
                        "<br/>\n" +
                        "עיבוד: הלן אדמונסון\n" +
                        "<br/>\n" +
                        "תרגום: רבקה משולח\n" +
                        "<br/>\n" +
                        "בימוי: אילן רונן\n" +
                        "<br/>\n" +
                        "תפאורה: כנרת קיש\n" +
                        "<br/>\n" +
                        "תלבושות: ילנה קלריך\n" +
                        "<br/>\n" +
                        "מוסיקה: יוסי בן נון\n" +
                        "<br/>\n" +
                        "תנועה: מרינה בלטוב\n" +
                        "<br/>\n" +
                        "תאורה: פליס רוס\n" +
                        "<br/>\n" +
                        "שפה ודיבור: יוני לוקאס\n" +
                        "<br/>\n" +
                        "שחקנים\n" +
                        "<br/>\n" +
                        "אנה: יבגניה דודינה\n" +
                        "<br/>\n" +
                        "לוין: רמי הויברגר\n" +
                        "<br/>\n" +
                        "קארנין: אלכס אנסקי\n" +
                        "<br/>\n" +
                        "ורונסקי: יובל סגל, קובי ליבנה\n" +
                        "<br/>\n" +
                        "דולי / הרוזנת ורונסקי: דורית לב-ארי\n" +
                        "<br/>\n" +
                        "סטיבה / פטריצקי: אורי הוכמן\n" +
                        "<br/>\n" +
                        "קיטי: הילה וידור\n" +
                        "<br/>\n" +
                        "הנסיכה בטסי/אגאטה: רות לנדאו\n" +
                        "<br/>\n" +
                        "ואסילי / המוות: חיים חובה\n" +
                        "<br/>\n" +
                        "המוות / ניקולאי / כומר: אליאן ולג'י\n" +
                        "<br/>\n" +
                        "אישה ברכבת / אומנת / אחות: לאה קמחזי\n" +
                        "<br/>\n" +
                        "סריוז'ה: איתי צ'רט-ליפשיץ, יעקב גוטקוב\n",
                thumbnail = "https://i.ytimg.com/vi/d0E53UtRYz8/sddefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=d0E53UtRYz8",
                        text = "לצפייה אונליין"
                    )
                )
            ),
            BlogPost(
                title = "מלכוד 22",
                shortDescription = "משך ההצגה 2:29:52",
                description = "CATCH 22 by J. Heller Directed by Ilan Ronen The young Company Of Habima מלכוד 22...",
                thumbnail = "https://i.ytimg.com/vi/tK5rSuyeaWk/sddefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=tK5rSuyeaWk",
                        text = "לצפייה בחלק 1"
                    ),
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=1DElqXeu6Sw",
                        text = "לצפייה בחלק 2"
                    )
                )
            ),
            BlogPost(
                title = "חשמלית ושמה תשוקה",
                shortDescription = "משך ההצגה 1:1:23",
                description = "<br/>\n" +
                        "תאטרון בית לסין מציג - חשמלית ושמה תשוקה\n" +
                        "<br/>\nתל אביב 2002" +
                        "<br/><br/><b><u>אודות ההצגה:</u></b><br/>\n" +
                        "תרגום: אהוד מנור\n" +
                        "<br/>\n" +
                        "בימוי: אילן רונן\n" +
                        "<br/>\n" +
                        "תפאורה: אלכסנדרה נרדי\n" +
                        "<br/>\n" +
                        "עריכה מוסיקלית: יוסי בן נון\n" +
                        "<br/><br/>\n" +
                        "<br/><br/><b><u>משתתפים:</u></b><br/>\n" +
                        "מירב גרובר, גיל פרנק, הדס קלדרון, גולן אזולאי ,שמוליק כהן, ענת צדקוני, הילה שיקמן, יצחק צוקר, איתן רונן, אריה שריקי\n" +
                        "<br/>\n" +
                        "A STREETCAR NAMED DESIRE - Part 1\n" +
                        "<br/>\n" +
                        "BY T. WILLIAMS\n" +
                        "<br/>\n" +
                        "BEIT LESSIN THEATRE - TEL AVIV 2002\n" +
                        "<br/>\n" +
                        "DIRECTED BY ILAN RONEN\n" +
                        "<br/>\n",
                thumbnail = "https://i.ytimg.com/vi/5UrAmKsneis/sddefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=5UrAmKsneis",
                        text = "לצפייה בחלק א"
                    ),
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=fT2quHLnZBU",
                        text = "לצפייה בחלק ב"
                    )
                )
            ),
        )
    )

    fun getOldShorts(): BlogFeed = BlogFeed(
        arrayListOf(
            BlogPost(
                title = "ההצגה חייבת להיפסק: מוני ובראבא על הרגע שבו קיבלו הוראה לא לעלות לבמה",
                shortDescription = "משך הסרטון 6:55",
                description = "הקורונה הורידה את המסך על כל אירועי התרבות - והצליחה להשבית אפילו את צמד הקומיקאים הוותיקים מוני ובראבא. ליווינו את השניים בזמן שקיבלו הוראה להוריד את המסך ולא לעלות לבמה בערב הבכורה של הצגתם<br/>תיאטרון חיפה התכונן לכבוד ערב הבכורה של הצגה קורעת מצחוק בכיכובו של הצמד המיתולוגי - מוני מושונוב ושלמה בראבא. אבל כמו שאומר הפתגם - האדם מתכנן ואלוהים צוחק. הכתבה הייתה אמורה להיות עליזה ולעסוק באיחוד של מוני ובראבא. אחרי שהצמד  ערך את החזרות חסרה רק החגיגיות של הפרימיירה עם הצחוקים של הקהל - אך בפועל זהו טקס אשכבה סמלי של ענף הבידור.<br/>קהל של אמיצים, או של מדחיקים, כבר מתחיל להיאסף במבואה כדי לראות את מוני ובראבא ואז מוריד נתניהו את גזרת המאה, הוא הורה שאסור לקיים כינוסים בהם יהיו מעל מאה משתתפים. בתגובה תהה בראבא מאיזה יום חלה ההנחיה: \"איך מתרגמים את ההוראה של ראש הממשלה, מה הכוונה? זאת אומרת שמרגע זה? אנשים יבואו, לא יבואו? זאת אומרת, זה הזוי, אני לא זוכר בכל שנותיי רגע הזוי כזה\".<br/>גם מושונוב עצמו אינו יודע אם ההצגה תתקיים: \"לא יודע, אנחנו מחכים. אין לי מושג\". מושונוב מדוכדך בעליל, משום שהקאסט הקטן כולל את בנו מיכאל. \"שוקלים לבטל\", סיפר בפנים נפולות. <br/>זה היה לפני 29 שנים כשתוכנית זהו זה הפכה לציפרלקס הלאומי בתקופה הכאוטית של מלחמת המפרץ הראשונה - כשנפלו כאן טילי סקאד מעיראק. מסכות וניילונים היו אמורים להגן מפני נשק כימי, והעם החרד בציון שמר על שפיות והומור בעזרת הבאבא בובה מושונוב. <br/>\"עקב הודעת ראש הממשלה  לפני כדקות אחדות אנחנו נאלצים לא לקיים את ההצגה הערב\", הכריזו במבואת התיאטרון למבקרים. שלמה בראבא ניסה להסביר את המשמעות של התפשטות נגיף הקורונה ברחבי העולם: \"האמת שזה בכלל רגע פילוסופי. אב הקיום החליט שאנחנו",
                thumbnail = "https://i.ytimg.com/vi/Ct_6s02MxiY/hqdefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=Ct_6s02MxiY",
                        text = "לצפייה בסרטון"
                    )
                )
            ),
            BlogPost(
                title = "ספי ריבלין סיפר הכנסיה בבוסטון",
                shortDescription = "משך הסרטון 7:23",
                description = "קטע קצר מהמופע",
                thumbnail = "https://i.ytimg.com/vi/jtwV8LX1mlk/sddefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=jtwV8LX1mlk",
                        text = "לצפייה בסרטון"
                    )
                )
            ),
            BlogPost(
                title = "תיאטרון הבימה -  מסילה לדמשק - ראיון עם רות דר, מעצבת תפאורה",
                shortDescription = "משך ההצגה 6:30",
                description = "תיאטרון הבימה -  מסילה לדמשק - ראיון עם רות דר, מעצבת תפאורה",
                thumbnail = "https://i.ytimg.com/vi/yLYmB4DsF3Q/maxresdefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=yLYmB4DsF3Q",
                        text = "לצפייה אונליין"
                    )
                )
            ),
            BlogPost(
                title = "מסך אישי עם אילן רונן כמנהלו האומנותי של תיאטרון הקאמרי",
                description = "ערוץ 1 שנה 1989",
                shortDescription = "משך הסרטון 36:23",
                thumbnail = "https://i.ytimg.com/vi/MabiAXg5XJQ/sddefault.jpg",
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=MabiAXg5XJQ",
                        text = "לצפייה בסרטון"
                    )
                )

            ),
        )
    )

    fun getStubImport(): BlogFeed = BlogFeed(
        arrayListOf(
            BlogPost(
                title = "בגן של ביאליק - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 47:10",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה. מחזמר משירי ארץ ישראל",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%91%D7%92%D7%9F%20%D7%A9%D7%9C%20%D7%91%D7%99%D7%90%D7%9C%D7%99%D7%A7%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%90%D7%95%D7%A8%D7%A0%D7%94%20%D7%A4%D7%95%D7%A8%D7%AA%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=QghJDTTWmnw",
                        text = "לצפייה אונליין"
                    )
                )
            ),
        ),
        possibleTags = arrayListOf(
            PostTag("תיאטרון"),
            PostTag("ילדים"),
        )
    )


    fun getBlogFeed(): BlogFeed = BlogFeed(
        arrayListOf(
            BlogPost(
                title = "אי המטמון - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 1:04:41",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה.<br/>מוזמנים להיכנס ולצפות בהצגות האונליין של התיאטרון, ששולח לכם חיבוק ואהבה.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%90%D7%99%20%D7%94%D7%9E%D7%98%D7%9E%D7%95%D7%9F%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=dDCTyZfftEI",
                        text = "לצפייה אונליין"
                    )
                )
            ),
            BlogPost(
                title = "בוא אליי פרפר נחמד - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 53:42",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין. אנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.<br/><br/>אודות ההצגה:<br/><br/>הצגת ילדים מוזיקלית עם שירי הילדות האהובים.<br/>לגילי 3+.<br/><br/>צילום: כפיר בולטין.<br/><br/>הצגת ילדים מוסיקלית עם שירי הילדות האהובים! לגילאי 3+<br/><br/>חזי הוא הזחל הכי שמח בגינה. מדי יום הוא מבלה עם חבריו הזחלים בשירים, במשחקים ובזלילה של עלים טעימים.<br/>הכל משתנה כשחזי שומע שהערב, כשהשמש תשקע, הוא יהפוך לפרפר צבעוני וגדול. חזי המפוחד לא רוצה להשתנות, הוא רוצה שהחיים שלו יישארו בדיוק אותו הדבר! אף אחד מהחברים לא מבין אותו, הוא נשאר לבדו ופוגש את תותית האפרוחית, היחידה שמרגישה כמותו. גם לה מצפה שינוי גדול כשהשמש תשקע: אז תבקע ביצה והיא תהפוך לאחות גדולה. יחד עם מלי, נמלה נועזת ואמיצה שלא רוצה להפוך לפועלת חרוצה, הם יוצאים למסע הרפתקאות אל צמרת העץ. הכל כדי לעצור את השמש, שלא תשקע, כך יוכלו לא לגדול ולהישאר מי שהם. בדרך צפוי להם מאבק לא פשוט עם חיות הלילה: העכבישה והעטלפים, שמנסים למנוע את מימוש התכנית בכל מחיר. אבל דווקא שם, על צמרת העץ, עולים פתאום הספקות: ״אולי בכל זאת כדאי להשתנות?״ שלושת החברים יצטרכו להבין ששינויים (גם אם הם נראים קצת מפחידים) מביאים איתם דברים טובים.<br/><br/>כל הזכויות שמורות להוצאת הקיבוץ המאוחד בע\"מ<br/><br/>הביקורות משבחות:<br/>\"צוות השחקנים גרם לילדים בכשרון רב לשיר ולרקוד. הציון 10!\" -סלונה<br/>\"מגנט לצופים הצעירים... הפקה מושקעת בכל קנה מידה וכיף גדול - גם להורים\" -כלכליסט<br/>\"הקלאסיקה הזאת מקבלת חיוּת ושמחה שסוחפת את כל הילדים בקהל... ואילו ההורים חזרו אחורה בזמן, לימים בהם הכל היה פשוט יותר\" -הארץ<br/><br/>מחזה: עודד אהרליך והדס קלדרון | בימוי: עידן עמית<br/>מוסיקה: טל בלכרוביץ'| גיטרות ומיקסים: רמי אוסרווסר כוריאוגרפיה: אור משיח | עיצוב תאורה: אלי אשכנזי | עיצוב תלבושות: לימור הרשקו | שפה ודיבור: מרגלית גז | ע במאי: טלי אגרונוב | ייצור בובות – סטודיו עמרי בר-לב<br/>משתתפים: תום אנטופולסקי, אופיר וייל, הראל מוראד, לורן סביר, אבי סרוסי, נועם ערד<br/><br/>https://www.youtube.com/watch?v=Qa-7LB2LJ2w",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%91%D7%95%D7%90%20%D7%90%D7%9C%D7%99%D7%99%20%D7%A4%D7%A8%D7%A4%D7%A8%20%D7%A0%D7%97%D7%9E%D7%93%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.webp?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=Qa-7LB2LJ2w",
                        text = "לצפייה אונליין"
                    )
                )
            ),
            BlogPost(
                title = "האוצר מתחת לגשר - תיאטרון גשר",
                shortDescription = "משך ההצגה 1:17:39",
                description = "תיאטרון גשר מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מעלה לצפייה ישירה אונליין את הצגות הילדים שלו.<br/><br/>אנחנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.<br/><br/>לילדים מגיל 5 ומעלה.<br/>תיאטרון גשר.<br/>מאת רועי חן.<br/>בימוי: אמיר י. וולף<br/><br/>אודות ההצגה:<br/><br/>מסע משעשע ונוגע ללב בעקבות האוצר הקסום.<br/>מאת רועי חן.<br/>בימוי: אמיר י. וולף.<br/>לגילי 5+.<br/><br/><a href=\"https://www.youtube.com/watch?list=PLxlwS38j-scy8avNjsHNbVf1T3KWOj-Nm&v=Au1xkRBdQCI&feature=youtu.be\" target=\"_blank\">לצפייה בהצגה</a>",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%94%D7%90%D7%95%D7%A6%D7%A8%20%D7%9E%D7%AA%D7%97%D7%AA%20%D7%9C%D7%92%D7%A9%D7%A8%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%92%D7%A9%D7%A8%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%942.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?list=PLxlwS38j-scy8avNjsHNbVf1T3KWOj-Nm&v=Au1xkRBdQCI&feature=youtu.be",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "היידי בת ההרים - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 1:21:19",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין.<br/><br/>אודות ההצגה:<br/><br/>מחזה על פי ספרה של יוהנה ספירי.<br/>לגילי 6+<br/>צילום: בני גם זו לטובה.<br/><br/>מחזה על פי סיפרה של יוהנה ספירי<br/>תיאטרון המדיטק בשיתוף הסטודיו למשחק מייסודו של יורם לוינשטיין<br/><br/>לגילאי 6 ומעלה<br/><br/>מחזה: עידו ריקלין<br/>בימוי: רפי ניב<br/>עיצוב תפאורה: טליה אוטולנגי<br/>עיצוב תלבושות: יהודית אהרון<br/>מוסיקה: נדב רובינשטיין<br/>עיצוב תאורה: זיו וולושין<br/>עיצוב תנועה: איריס לנה<br/>עיצוב בובות: אמירה פנקס<br/>הדרכת בובות: מעין רזניק<br/>שחקנים: אודי בן דוד, רודיה קוזלובסקי, מיכל ויינברג, יעל בוטון, אביגיל הררי, הלל קפון, גל קורן, אבי אזולאי, כרמל קנדל, נעה הר ציון, סיוון מסט<br/><br/><a href=\"https://www.youtube.com/watch?v=j0puZR6__8A&ab_channel=%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7\" target=\"_blank\">לצפייה בהצגה</a>",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%94%D7%99%D7%99%D7%93%D7%99%20%D7%91%D7%AA%20%D7%94%D7%94%D7%A8%D7%99%D7%9D%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.webp?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=j0puZR6__8A&ab_channel=%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "הכיתה המעופפת - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 1:07:18",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין.<br/><br/>אודות ההצגה:<br/><br/>על-פי ספרו של אריך קסטנר.<br/>לגילי 7+.<br/><br/>צילום: בני גמזו.<br/> על-פי ספרו של אריך קסטנר<br/>בשיתוף הסטודיו למשחק של יורם לוינשטיין   2013<br/><br/>גילאי 7 ומעלה<br/><br/>מחזה: עידו ריקלין<br/>בימוי: רפי ניב<br/>תפאורה: סבטלנה ברגר<br/>תלבושות: יהודית אהרון<br/>מוסיקה: נדב רובינשטיין<br/>תנועה: איריס לנה<br/>תאורה: זיו וולושין<br/>תנועה תיאטרלית: גנאדי בביצקי<br/>שחקנים: אודי בן דוד, דני גבע, רודיה קוזלובסקי, עמית אפשטיין, גל אמיתי, נדב לאור, עידו לוסקי, איתמר שקד, שפי מרציאנו, אמרי ביטון, אדי אלתרמן<br/><br/><a href=\"https://www.youtube.com/watch?v=fEAxZPUC1rk&ab_channel=%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7\" target=\"_blank\">לצפייה בהצגה</a>",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%94%D7%9B%D7%99%D7%AA%D7%94%20%D7%94%D7%9E%D7%A2%D7%95%D7%A4%D7%A4%D7%AA%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.webp?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=fEAxZPUC1rk&ab_channel=%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "המסע המופלא לארץ המילים - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 1:01:53",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין.<br/><br/>אנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.<br/><br/>אודות ההצגה:<br/><br/>הצגה על מילים, ילדים ומפלצות עם לב רגיש.<br/>לגילי 5+.<br/><br/>לילה אחד נשמעים קולות רחש משונים מכיוון הספרייה בחדרם של שירה ודורון. כשהם מדליקים את האור, הם מגלים שמישהו מסתורי בילגן להם את החדר והפיל את כל הספרים לרצפה. החקירה אחר 'הפושע הנמלט' מוציאה אותם אל מסע הרפתקאות מופלא ב\"ארץ המילים\", שם יסייעו למילים של השפה העברית במלחמתן במפלצת הבוץ המטורללת \"אנדרלמוסיה\" המאיימת להשמידן.<br/><br/>במסעם הם יפגשו מילים שונות ומשונות, מפחידות ומצחיקות, זקנות וצעירות. הם ילמדו הרבה על השפה העברית ועל עצמם, ויגלו שגם למפלצות יש לפעמים לב רך ורגיש.<br/><br/>לגילאי 5 ומעלה<br/><br/>על פי רב המכר של רוביק רוזנטל<br/>מחזה ובימוי: נועם שמואל<br/>מוסיקה מקורית: אמיר לקנר<br/>תנועה: שרון גל<br/>תלבושות: אביה בש<br/>ביצוע תלבושות: ורוניקה שור<br/>אביזרים: רועי עקאב<br/>תפאורה: זאב לוי<br/>וידאו: נמרוד צין<br/>תאורה: זיו וולושין<br/>ע במאי: נועה ינאי<br/>משתתפים: בר אקרמן, עדי ארד, אסף דגני, רוני מרחבי, קרן סלנט, דן קיזלר, הלל קפון / אדם הירש",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%94%D7%9E%D7%A1%D7%A2%20%D7%94%D7%9E%D7%95%D7%A4%D7%9C%D7%90%20%D7%9C%D7%90%D7%A8%D7%A5%20%D7%94%D7%9E%D7%99%D7%9C%D7%99%D7%9D%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media&token=eecd053e-2ec4-44eb-8d61-c7385c3e20b8",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=Eh5p2_3IPLk&ab_channel=%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "התרנגולים: הרמיקס - תזמורת המהפכה",
                shortDescription = "משך ההצגה 1:21:56",
                description = "בתחילת אוקטובר, בעיצומו של \"פסטיבל המהפכה\", פרצה המלחמה, ומאז אנו חווים ימים קשים מנשוא. תזמורת המהפכה מציעה הפוגה קלה מהחדשות, וזאת באמצעות צפייה חופשית אונליין במופעי הפסטיבל. אנו מזמינים אתכם לצפות בהופעה המרהיבה של תזמורת המהפכה בהשראת להקת התרנגולים המיתולוגית. הופעה זו כללה הופעה מרהיבה של תזמורת המהפכה, אנסמבל של שחקנים וזמרים מוכרים ואהובים, ולצידם מקהלת הילדים \"העפרוני\". ההופעה התקיימה במסגרת \"סדרת המהפכה\" באופרה הישראלית ב-17.12.2019.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%94%D7%AA%D7%A8%D7%A0%D7%92%D7%95%D7%9C%D7%99%D7%9D%20%D7%94%D7%A8%D7%9E%D7%99%D7%A7%D7%A1%20%D7%AA%D7%96%D7%9E%D7%95%D7%A8%D7%AA%20%D7%94%D7%9E%D7%94%D7%A4%D7%9B%D7%94%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media&token=eecd053e-2ec4-44eb-8d61-c7385c3e20b8",
                tags = arrayListOf(PostTag("תזמורת"), PostTag("מוסיקה"), PostTag("פסטיבל המהפכה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=FaZn70A3ywA",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "זהבה ושלושת הדובים - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 54:43",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה. מוזמנים להיכנס ולצפות בהצגות האונליין של התיאטרון, ששולח לכם חיבוק ואהבה.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%96%D7%94%D7%91%D7%94%20%D7%95%D7%A9%D7%9C%D7%95%D7%A9%D7%AA%20%D7%94%D7%93%D7%95%D7%91%D7%99%D7%9D%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%90%D7%95%D7%A8%D7%A0%D7%94%20%D7%A4%D7%95%D7%A8%D7%AA%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים"), PostTag("הצגה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=ODL2f3Eh984",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "יוסי בנאי והמהפכה - תזמורת המהפכה",
                shortDescription = "משך ההצגה 54:52",
                description = "בתחילת אוקטובר, בעיצומו של 'פסטיבל המהפכה', פרצה המלחמה, ומאז אנו חווים ימים קשים מנשוא. תזמורת המהפכה מציעה הפוגה קלה מהחדשות, וזאת באמצעות צפייה חופשית אונליין במופעי הפסטיבל.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%99%D7%95%D7%A1%D7%99%20%D7%91%D7%A0%D7%90%D7%99%20%D7%95%D7%94%D7%9E%D7%94%D7%A4%D7%9B%D7%94%20-%20%D7%AA%D7%96%D7%9E%D7%95%D7%A8%D7%AA%20%D7%94%D7%9E%D7%94%D7%A4%D7%9B%D7%94%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תזמורת"), PostTag("מוזיקה"), PostTag("פסטיבל המהפכה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.kan.org.il/content/kan/kan-11/p-374649/",
                        text = "לצפייה במופע"
                    )
                )
            ),
            BlogPost(
                title = "ילדיסקו - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 1:03:54",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין. אנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%99%D7%9C%D7%93%D7%99%D7%A1%D7%A7%D7%95%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים"), PostTag("מוזיקה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=GYy9ExcfiXw",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "לעבור את הקיר - תיאטרון בית ליסין",
                shortDescription = "משך ההצגה 1:03:55",
                description = "תיאטרון בית ליסין ממשיך עם המסורת הנהדרת של פתיחת הצגות אונליין על מנת לרומם את רוח העם, והפעם- 'לעבור את הקיר' המחזמר האהוב שירד השנה מהבמה. מהיוצרים של הלהיט 'אפס ביחסי אנוש' ובכיכובם של חן אמסלם ועוז זהבי.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%9C%D7%A2%D7%91%D7%95%D7%A8%20%D7%90%D7%AA%20%D7%94%D7%A7%D7%99%D7%A8%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("מחזמר"), PostTag("מוזיקה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=5WrhDJ9gXzs",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "מבצע סבא - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 1:05:58",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה. מחזמר משירי ארץ ישראל. השימוש בקישור זה ובתוכן הינו בהסכמת היוצרים, ולצורך הנעמת זמנם של ילדי ישראל והוריהם בתקופת החירום שעקב מגפת הקורונה.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%9E%D7%91%D7%A6%D7%A2%20%D7%A1%D7%91%D7%90%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%90%D7%95%D7%A8%D7%A0%D7%94%20%D7%A4%D7%95%D7%A8%D7%AA%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("מחזמר"), PostTag("מוזיקה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=Y3tZmNKqCTQ",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "מלך סיאם - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 1:07:54",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה. מחזמר משירי ארץ ישראל. השימוש בקישור זה ובתוכן הינו בהסכמת היוצרים, ולצורך הנעמת זמנם של ילדי ישראל והוריהם בתקופת החירום שעקב מגפת הקורונה.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%9E%D7%9C%D7%9A%20%D7%A1%D7%99%D7%90%D7%9D%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%90%D7%95%D7%A8%D7%A0%D7%94%20%D7%A4%D7%95%D7%A8%D7%AA%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("מחזמר"), PostTag("מוזיקה")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=Cr8cSqAzVh4",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "מסיבה בגן העכברים - תיאטרון אורנה פורת",
                shortDescription = "משך ההצגה 51:21",
                description = "תיאטרון אורנה פורת לילדים ולנוער מזמין את ילדי ישראל להפוגה תיאטרלית ומהנה. הצגה מוסיקלית על פי ספרה של שלומית כהן אסיף, לגילאי 7-3. מילות השירים: אפרים סידון ושלומית כהן-אסיף מחזה: אפרים סידון בימוי: ישראל גוריון ההצגה הופקה בשיתוף בית הספר לאמנויות הבמה של סמינר הקיבוצים.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%9E%D7%A1%D7%99%D7%91%D7%94%20%D7%91%D7%92%D7%9F%20%D7%94%D7%A2%D7%9B%D7%91%D7%A8%D7%99%D7%9D%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%90%D7%95%D7%A8%D7%A0%D7%94%20%D7%A4%D7%95%D7%A8%D7%AA%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(
                    PostTag("תיאטרון"),
                    PostTag("מוסיקה"),
                    PostTag("ילדים"),
                    PostTag("ספרות")
                ),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=_EvArQ8skwk",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "מסעות אודיסאוס - תיאטרון גשר",
                shortDescription = "משך ההצגה 1:10:37",
                description = "תיאטרון גשר מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מעלה לצפייה ישירה אונליין את הצגות הילדים שלו. אנחנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.\n\nפרטים וכרטיסים: [קישור לרכישת כרטיסים](https://bit.ly/3Cp9Sgv)\n\nפנטזיה סוחפת ומלהיבה על פי הומרוס\nמחזה מאת רועי חן\nבימוי: שיר גולדברג\n\n**אודות ההצגה:**\n\nהצגה לכל המשפחה, פנטזיה סוחפת ומלהיבה על פי הומרוס.\nמאת: רועי חן.\nבימוי: שיר גולדברג.\nלגילי 5+.\n\n[לצפייה בהצגה](https://www.youtube.com/watch?v=twfIncc1mVc&list=PLxlwS38j-scy8avNjsHNbVf1T3KWOj-Nm&ab_channel=GesherTheatre)",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%9E%D7%A1%D7%A2%D7%95%D7%AA%20%D7%90%D7%95%D7%93%D7%99%D7%A1%D7%90%D7%95%D7%A1%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(
                    PostTag("תיאטרון"),
                    PostTag("ילדים"),
                    PostTag("פנטזיה"),
                    PostTag("מוסיקה")
                ),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=twfIncc1mVc&list=PLxlwS38j-scy8avNjsHNbVf1T3KWOj-Nm&ab_channel=GesherTheatre",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "קאיטוש הילד המכשף - תיאטרון המדיטק",
                shortDescription = "משך ההצגה 56:08",
                description = "תיאטרון המדיטק מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מציע מגוון הצגות ילדים לצפייה ישירה אונליין. אנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.\n\n**אודות ההצגה:**\n\nהצגה על פי ספרו של יאנוש קורצ'אק.\nלגילי 7+.\nצילום: לירן לוי.\n\nההצגה קאיטוש הילד המכשף, על פי ספרו של יאנוש קורצ'אק, לגילי 7+\n\nההצגה קטפה פרסים ושבחים בפסטיבל חיפה להצגות ילדים 2018:\n*פרס הבימוי לבמאי גמא פריד\n*פרס השחקן הטוב ביותר לרוי סער (קאיטוש)\n*פרס הוקרה לשחקנית שיר אברמוב\n*ציון לשבח למעצבת התפאורה ליטל רייס\n\nההצגה מאושרת על ידי ועדת סל תרבות ומתאימה לכיתות א'-ו'\n\n**קאיטוש הוא ילד יחיד ומיוחד.**\n\n[לצפייה בהצגה](https://www.youtube.com/watch?v=oMv4UKjJMeY)",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%A7%D7%90%D7%99%D7%98%D7%95%D7%A9%20%D7%94%D7%99%D7%9C%D7%93%20%D7%94%D7%9E%D7%9B%D7%A9%D7%A3%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%94%D7%9E%D7%93%D7%99%D7%98%D7%A7%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.webp?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=oMv4UKjJMeY",
                        text = "לצפייה בהצגה"
                    )
                )
            ),
            BlogPost(
                title = "קונצ'רטו לגשש ותזמורת - תזמורת המהפכה",
                shortDescription = "משך ההצגה 1:44:15",
                description = "בתחילת אוקטובר, בעיצומו של 'פסטיבל המהפכה', פרצה המלחמה, ומאז אנו חווים ימים קשים מנשוא. תזמורת המהפכה מציעה הפוגה קלה מהחדשות, וזאת באמצעות צפייה חופשית אונליין במופעי הפסטיבל.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%A7%D7%95%D7%A0%D7%A6'%D7%A8%D7%98%D7%95%20%D7%9C%D7%92%D7%A9%D7%A9%20%D7%95%D7%AA%D7%96%D7%9E%D7%95%D7%A8%D7%AA%20%D7%94%D7%9E%D7%94%D7%A4%D7%9B%D7%94%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(
                    PostTag("מוזיקה"),
                    PostTag("תזמורת"),
                    PostTag("קונצ'רטו"),
                    PostTag("פסטיבל המהפכה")
                ),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?v=HURCJ12hYxs",
                        text = "לצפייה בהצגה"
                    )
                ),
            ),
            BlogPost(
                title = "רוח התיאטרון - תיאטרון גשר",
                shortDescription = "משך ההצגה 1:01:39",
                description = "תיאטרון גשר מחבק ותומך בילדי ישראל ועוטף ישראל בפרט. על מנת לנסות ולהפיג מעט מהמתח בתקופה זו, התיאטרון מעלה לצפייה ישירה אונליין את הצגות הילדים שלו. אנחנו והתיאטרון מאחלים ומקווים לימים שקטים וטובים יותר.",
                thumbnail = "https://firebasestorage.googleapis.com/v0/b/shows-online.appspot.com/o/shows%2F%D7%A8%D7%95%D7%97%20%D7%94%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20-%20%D7%AA%D7%99%D7%90%D7%98%D7%A8%D7%95%D7%9F%20%D7%92%D7%A9%D7%A8%2F%D7%AA%D7%9E%D7%95%D7%A0%D7%94.jpg?alt=media",
                tags = arrayListOf(PostTag("תיאטרון"), PostTag("ילדים")),
                links = arrayListOf(
                    HyperLink(
                        url = "https://www.youtube.com/watch?list=PLxlwS38j-scy8avNjsHNbVf1T3KWOj-Nm&v=C64RusW3eqI&feature=youtu.be&ab_channel=GesherTheatre",
                        text = "לצפייה בהצגה"
                    )
                ),
//                author = Author("תיאטרון גשר", "https://example.com/author/gesher"),
            )


        ),
        possibleTags = arrayListOf(
            PostTag("תיאטרון"),
            PostTag("ילדים"),
            PostTag("מחזמר"),
            PostTag("מוזיקה"),
            PostTag("קונצ'רטו"),
            PostTag("תזמורת"),
            PostTag("פסטיבל המהפכה"),
        )
    )


    private fun getPostComments(): ArrayList<PostComment> {
        val list = arrayListOf<PostComment>()

        for (i in 0..5) {
            list.add(
                PostComment(
                    author = "Random User",
                    title = "!! Wow Omfg !!!!!11",
                    text = "wow!! i neva beleived this would actually would work omfgggggggg 1!! eysss !! hahah this is so coool oiomfg "
                )
            )
        }

        return list
    }


}