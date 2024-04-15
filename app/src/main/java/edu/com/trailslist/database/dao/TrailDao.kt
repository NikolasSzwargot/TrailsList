package edu.com.trailslist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.com.trailslist.R
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.util.TrailType
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailDao {

    @Upsert
    suspend fun upsertTrail(trail: Trail)

    @Delete
    suspend fun deleteTrail(trail: Trail)

    @Query("SELECT * FROM trails ORDER BY name ASC")
    fun getTrailsOrderedByName(): Flow<List<Trail>>

    @Query("DELETE FROM trails")
    suspend fun deleteAllTrails()

    @Query("SELECT * FROM trails where id = :id")
    suspend fun getTrailById(id: Int): Trail

    @Query("UPDATE trails SET measuredTime = :time WHERE id = :id")
    suspend fun updateMeasuredTime(id: Int, time: Long)

    @Query("SELECT measuredTime FROM trails WHERE id = :trailId")
    fun getMeasuredTime(trailId: Int): LiveData<Long?>

    @Query("SELECT * FROM trails where type = :type")
    fun getTrailsByType(type: Int): Flow<List<Trail>>

}

suspend fun insertTrails(trailDao: TrailDao) {
    var temp = 0
    val trails: List<Trail> = listOf(
        Trail(id = temp++,
            name = "Główny szlak beskidzki",
            description = "Najdłuższy szlak w polskich górach, liczy około 496 km i przebiega przez Beskid Śląski, Beskid Żywiecki, Gorce, Beskid Sądecki, Beskid Niski oraz Bieszczady. Biegnąc najwyższymi partiami polskich Beskidów, umożliwia dotarcie m.in. na Stożek, Baranią Górę, Babią Górę, Policę, Turbacz, Lubań, Przehybę, Radziejową, Jaworzynę Krynicką, Rotundę, Cergową, Chryszczatą, Smerek i Halicz, a także do miejscowości takich jak Ustroń, Węgierska Górka, Jordanów, Rabka-Zdrój, Krościenko nad Dunajcem, Rytro, Krynica-Zdrój, Iwonicz-Zdrój, Rymanów-Zdrój, Komańcza, Cisna, Ustrzyki Górne i in. Historia Głównego Szlaku Beskidzkiego sięga okresu międzywojennego. Przebieg części zachodniej (Ustroń – Krynica) został zaprojektowany przez Kazimierza Sosnowskiego i ukończony w 1929 roku. Wschodnia część, według projektu Mieczysława Orłowicza, została ukończona w 1935 i prowadziła z Sianek aż do Czarnohory, która znajdowała się wówczas w granicach Polski. Po połączeniu obu fragmentów w roku 1935 szlak nazwano Głównym Szlakiem Karpackim, a jego patronem został Józef Piłsudski.",
            image = R.drawable.beskidy,
            measuredTime = 0L,
            type = TrailType.LOW_LYING
        ),
        Trail(id = temp++,
            name = "Orla perć",
            description = "Najdłuższy szlak w polskich górach, liczy około 496 km i przebiega przez Beskid Śląski, Beskid Żywiecki, Gorce, Beskid Sądecki, Beskid Niski oraz Bieszczady. Biegnąc najwyższymi partiami polskich Beskidów, umożliwia dotarcie m.in. na Stożek, Baranią Górę, Babią Górę, Policę, Turbacz, Lubań, Przehybę, Radziejową, Jaworzynę Krynicką, Rotundę, Cergową, Chryszczatą, Smerek i Halicz, a także do miejscowości takich jak Ustroń, Węgierska Górka, Jordanów, Rabka-Zdrój, Krościenko nad Dunajcem, Rytro, Krynica-Zdrój, Iwonicz-Zdrój, Rymanów-Zdrój, Komańcza, Cisna, Ustrzyki Górne i in. Historia Głównego Szlaku Beskidzkiego sięga okresu międzywojennego. Przebieg części zachodniej (Ustroń – Krynica) został zaprojektowany przez Kazimierza Sosnowskiego i ukończony w 1929 roku. Wschodnia część, według projektu Mieczysława Orłowicza, została ukończona w 1935 i prowadziła z Sianek aż do Czarnohory, która znajdowała się wówczas w granicach Polski. Po połączeniu obu fragmentów w roku 1935 szlak nazwano Głównym Szlakiem Karpackim, a jego patronem został Józef Piłsudski.",
            image = R.drawable.orla,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Główny szlak sudecki",
            description = "szlak turystyczny w Sudetach, znakowany kolorem czerwonym szlak turystyczny czerwony, prowadzący ze Świeradowa-Zdroju do Prudnika.",
            image = R.drawable.sudety,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id= temp++,
            name = "Grzbiet karkonoszy",
            description = "Jest to pierwsza część, ciągnącego się dalej na wschód, Głównego Szlaku Sudeckiego. Rozpoczyna się w Szklarskiej Porębie, a kończy na Przełęczy Okraj, a jego długość to około 35 kilometrów. Wędrówka jest o tyle przyjemna, że po pokonaniu pierwszego większego podejścia (pod Szrenicę), większość trasy wiedzie grzbietem, bez wielkich przewyższeń. Na szlaku miniemy najpiękniejsze miejsca w Karkonoszach - Twarożnik (fantazyjne skałki) czy słynne i niezwykle widowiskowe Śnieżne Kotły. Trasa od Słonecznika z widokiem na schronisko „Samotnia” pod Śnieżkę jest uznawana za jeden z ładniejszych fragmentów szlaku. Należy pamiętać, że Główny Szlak Sudecki przy schronisku pod Śnieżką odbija w stronę Karpacza. Jeżeli jednak chcemy kontynuować przygodę z grzbietem Karkonoskim, należy iść na najwyższy szczyt Sudetów - Śnieżkę (1603 m n.p.m.) i dalej, do Przełęczy Okraj. Dzięki schroniskom (Schronisko na Hali Szrenickiej, schronisko „Odrodzenie”, schronisko pod Śnieżką), a także kilku schroniskom po stronie czeskiej, wędrówkę można podzielić na kilka dni. Szlak ten można również pokonać zimą - pieszo, na nartach lub rakietach. Jest wówczas szczególnie urokliwy, zwłaszcza dla wielbicieli zimy. Należy jednak pamiętać o odpowiednim przygotowaniu i o tym, że podczas zimowych przejść tempo bardzo spada.",
            image = R.drawable.karkonosze,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.LOW_LYING),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
        Trail(id = temp++,
            name = "Szlak piastowski",
            description = "trasa turystyczna w Polsce, przebiegająca przez województwo wielkopolskie i województwo kujawsko-pomorskie. Szlak funkcjonuje przede wszystkim jako trasa wycieczek autokarowych i samochodowych oraz szlak rowerowy (Piastowski Trakt Rowerowy). W 2012 otrzymał Certyfikat Polskiej Organizacji Turystycznej za najlepszy produkt turystyczny, jako unikatowy i wyjątkowo ważny dla polskiego dziedzictwa.",
            image = R.drawable.piastowski,
            measuredTime = 0L,
            type = TrailType.MOUNTAIN),
    )

    trails.forEach { trail ->
        trailDao.upsertTrail(trail)
    }
}