package edu.com.trailslist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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

@SuppressLint("SuspiciousIndentation")
@Composable
fun TrailItem(trail: Trail) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenWidthData is screenInfoData.ScreenType.Tablet
            || screenInfoData.screenHeightData is screenInfoData.ScreenType.Tablet

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(if (isTablet) 2.dp else 1.dp, Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(trail.image),
                    contentDescription = "Trail image",
                    modifier = Modifier
                        .size(if (isTablet) 180.dp else 140.dp)
                        .clip(RoundedCornerShape(if (isTablet) 45.dp else 40.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = trail.name,
                        fontSize = if (isTablet) 25.sp else 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
}

@Composable
fun PhoneUI(navController: NavController, trails: List<Trail>) {
    LazyColumn {
        items(trails.size) { index ->
            val trail = trails[index]
            Box(modifier = Modifier
                .clickable {
                    navController.navigate(
                        "trailDetails/${trail.name}/${trail.description}/${trail.image}"
                    )
                }) {
                TrailItem(trail = trail)
            }
        }
    }
}

@Composable
fun TabletUI(vertical: Boolean, trails: List<Trail>) {
    var expanded by remember { mutableStateOf(true) }
    var selectedTrail by remember { mutableStateOf<Trail?>(null) }
    val fill = if (expanded) 1f else 0.5f

    LazyColumn (modifier = Modifier.fillMaxHeight(if (vertical) fill else 1f)) {
        items(trails.size) { index ->
            val trail = trails[index]
            Box(modifier = Modifier
                .fillMaxWidth(if (vertical) 1f else fill)
                .clickable {
                    expanded = false
                    selectedTrail = trail
                }) {
                TrailItem(trail = trail)
            }
        }
    }

    selectedTrail?.let { trail: Trail ->
        TrailDetails(trail = trail)
    }
}

@Composable
fun TrailsList(navController: NavController) {
    val context = LocalContext.current
    val database = TrailDatabase.getDatabaseInstance(context)
    val dao = database.trailDao()
    val trails by  dao.getTrailsOrderedByName().collectAsState(initial = emptyList())
    val size = trails.size

    if (size == 0) {
        LaunchedEffect(Unit) {
            //dao.deleteAllTrails()
            insertTrails(dao)
        }
    }


    val screenInfoData = screenInfo()
    if (screenInfoData.screenWidthData is screenInfoData.ScreenType.Phone &&
        screenInfoData.screenHeightData is screenInfoData.ScreenType.Phone) {
        PhoneUI(navController = navController, trails)
    }
    else if (screenInfoData.screenWidthData is screenInfoData.ScreenType.Tablet) {
        Row {
            TabletUI(false, trails)
        }
    }
    else {
        Column {
            TabletUI(true, trails)
        }
    }
}