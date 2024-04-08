package edu.com.trailslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

suspend fun insertTrails(trailDao: TrailDao) {
    var temp = 0
    val trails: List<Trail> = listOf(Trail(id = temp++,
        name = "Główny szlak beskidzki",
        description = "Najdłuższy szlak w polskich górach, liczy około 496 km i przebiega przez Beskid Śląski, Beskid Żywiecki, Gorce, Beskid Sądecki, Beskid Niski oraz Bieszczady. Biegnąc najwyższymi partiami polskich Beskidów, umożliwia dotarcie m.in. na Stożek, Baranią Górę, Babią Górę, Policę, Turbacz, Lubań, Przehybę, Radziejową, Jaworzynę Krynicką, Rotundę, Cergową, Chryszczatą, Smerek i Halicz, a także do miejscowości takich jak Ustroń, Węgierska Górka, Jordanów, Rabka-Zdrój, Krościenko nad Dunajcem, Rytro, Krynica-Zdrój, Iwonicz-Zdrój, Rymanów-Zdrój, Komańcza, Cisna, Ustrzyki Górne i in. Historia Głównego Szlaku Beskidzkiego sięga okresu międzywojennego. Przebieg części zachodniej (Ustroń – Krynica) został zaprojektowany przez Kazimierza Sosnowskiego i ukończony w 1929 roku. Wschodnia część, według projektu Mieczysława Orłowicza, została ukończona w 1935 i prowadziła z Sianek aż do Czarnohory, która znajdowała się wówczas w granicach Polski. Po połączeniu obu fragmentów w roku 1935 szlak nazwano Głównym Szlakiem Karpackim, a jego patronem został Józef Piłsudski.",
        image = R.drawable.beskidy
    ),
        Trail(id = temp++,
            name = "Orla perć",
            description = "Najdłuższy szlak w polskich górach, liczy około 496 km i przebiega przez Beskid Śląski, Beskid Żywiecki, Gorce, Beskid Sądecki, Beskid Niski oraz Bieszczady. Biegnąc najwyższymi partiami polskich Beskidów, umożliwia dotarcie m.in. na Stożek, Baranią Górę, Babią Górę, Policę, Turbacz, Lubań, Przehybę, Radziejową, Jaworzynę Krynicką, Rotundę, Cergową, Chryszczatą, Smerek i Halicz, a także do miejscowości takich jak Ustroń, Węgierska Górka, Jordanów, Rabka-Zdrój, Krościenko nad Dunajcem, Rytro, Krynica-Zdrój, Iwonicz-Zdrój, Rymanów-Zdrój, Komańcza, Cisna, Ustrzyki Górne i in. Historia Głównego Szlaku Beskidzkiego sięga okresu międzywojennego. Przebieg części zachodniej (Ustroń – Krynica) został zaprojektowany przez Kazimierza Sosnowskiego i ukończony w 1929 roku. Wschodnia część, według projektu Mieczysława Orłowicza, została ukończona w 1935 i prowadziła z Sianek aż do Czarnohory, która znajdowała się wówczas w granicach Polski. Po połączeniu obu fragmentów w roku 1935 szlak nazwano Głównym Szlakiem Karpackim, a jego patronem został Józef Piłsudski.",
            image = R.drawable.orla),
        Trail(id = temp++,
            name = "Główny szlak sudecki",
            description = "szlak turystyczny w Sudetach, znakowany kolorem czerwonym szlak turystyczny czerwony, prowadzący ze Świeradowa-Zdroju do Prudnika.",
            image = R.drawable.sudety),
        Trail(id= temp++,
            name = "Grzbiet karkonoszy",
            description = "Jest to pierwsza część, ciągnącego się dalej na wschód, Głównego Szlaku Sudeckiego. Rozpoczyna się w Szklarskiej Porębie, a kończy na Przełęczy Okraj, a jego długość to około 35 kilometrów. Wędrówka jest o tyle przyjemna, że po pokonaniu pierwszego większego podejścia (pod Szrenicę), większość trasy wiedzie grzbietem, bez wielkich przewyższeń. Na szlaku miniemy najpiękniejsze miejsca w Karkonoszach - Twarożnik (fantazyjne skałki) czy słynne i niezwykle widowiskowe Śnieżne Kotły. Trasa od Słonecznika z widokiem na schronisko „Samotnia” pod Śnieżkę jest uznawana za jeden z ładniejszych fragmentów szlaku. Należy pamiętać, że Główny Szlak Sudecki przy schronisku pod Śnieżką odbija w stronę Karpacza. Jeżeli jednak chcemy kontynuować przygodę z grzbietem Karkonoskim, należy iść na najwyższy szczyt Sudetów - Śnieżkę (1603 m n.p.m.) i dalej, do Przełęczy Okraj. Dzięki schroniskom (Schronisko na Hali Szrenickiej, schronisko „Odrodzenie”, schronisko pod Śnieżką), a także kilku schroniskom po stronie czeskiej, wędrówkę można podzielić na kilka dni. Szlak ten można również pokonać zimą - pieszo, na nartach lub rakietach. Jest wówczas szczególnie urokliwy, zwłaszcza dla wielbicieli zimy. Należy jednak pamiętać o odpowiednim przygotowaniu i o tym, że podczas zimowych przejść tempo bardzo spada.",
            image = R.drawable.karkonosze),
        Trail(id = temp,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski))

    trails.forEach { trail ->
        trailDao.upsertTrail(trail)
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrailApp()
        }
    }
}

@Composable
fun TrailApp(){
    val context = LocalContext.current
    val database = TrailDatabase.getDatabaseInstance(context)
    val dao = database.trailDao()
    val trails by  dao.getTrailsOrderedByName().collectAsState(initial = emptyList())
    val viewModel: TrailViewModel = viewModel()

    LaunchedEffect(Unit) {
        if (trails.isEmpty()) {
            //dao.deleteAllTrails()
            insertTrails(dao)
        }
    }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "trailsList") {
        composable("trailsList") {
            TrailsList(navController, trails, viewModel)
        }
        composable( "trailDetails/{trailId}/{trailName}/{trailDescription}/{trailImage}") { backStackEntry ->
            val trailId = backStackEntry.arguments?.getString("trailId").orEmpty().toInt()
            val trailName = backStackEntry.arguments?.getString("trailName").orEmpty()
            val trailDescription = backStackEntry.arguments?.getString("trailDescription").orEmpty()
            val trailImage = backStackEntry.arguments?.getString("trailImage").orEmpty().toInt()

            val trail = Trail(
                trailId,
                trailName,
                trailDescription,
                trailImage)
            TrailDetails(trail, viewModel)
        }
    }
}