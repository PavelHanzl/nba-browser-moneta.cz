# NBA Browser App

## Popis
NBA Browser je aplikace pro prohlížení informací o hráčích a týmech NBA. Byla vytvořena na základě zadánní pro vstupní pohovor pro společnost MONETA Money Bank. Poskytuje funkce pro vyhledávání hráčů, zobrazení detailů hráčů a týmů, včetně statistik a obrázků.

## Zadání
Cílem aplikace je zobrazit seznam NBA hráčů (se jménem, příjmením, pozicí a klubem ve kterým
hraje). Jakmile uživatel dojde na konec listu, seznam se donačte o dalších 35 záznamů. Po kliknutí
na vybraného hráče je zobrazen detail s veškerými informacemi. Dále z detailu hráče je také
možné se prokliknout na detail klubu ve kterém hraje s dostupnými informacemi.
Pro získání informací o hráčích je vhodné použít https://balldontlie.io/).

### Aplikace by měla:
- být naprogramována v Kotlinu v architektuře MVI nebo MVVM
- využívat principy clean code
- používat Retrofit pro síťovou komunikaci
- mít napsáno UI v Android Jetpack Compose
- zpracovávat obrázky v Glidu
- ostatní knihovny lze použít dle vlastního zvážení.
- Přidání Javadocu k výslednému kódu je vítané.

## Funkce
- **Seznam všech NBA Hráčů**: Seznam všech NBA hráčů získaných z API.
- **Detaily Hráčů**: Zobrazuje podrobné informace o hráčích, včetně fotografií a doplňujících informací.
- **Detaily Týmů**: Informace o NBA týmech s logy a dalšími doplňujícími údaji.

## Technologie
- **Kotlin & Jetpack Compose**: Pro moderní a efektivní vývoj UI.
- **Retrofit & OkHttp**: Pro síťové požadavky a API komunikaci.
- **Koin**: Pro dependency injection.
- **Glide**: Pro zpracování a zobrazování obrázků.
- **Paginace**: Vlastní implementace stránkování pro načítání z api a efektivní nakládání s daty.

## Architektura
- Využívá MVVM architektonický vzor pro oddělení logiky a UI.
- KDoc komentáře pro dokumentaci kódu.

## Omezení API 

### Nová verze API
Nově od 18.2. 2024 je k dispozici nová implementace api ( https://docs.balldontlie.io/ ) s lehkými odlišnostmi od původního - oproti starému api vyžaduje autentifikaci a jinak nakládá se stránkováním). V projektu bylo využito již nové api.

### Načítání obrázků hráčů a log týmů
V projektu NBA Browser bylo rozhodnuto nepoužívat reálné obrázky hráčů a log týmů z důvodu nedostupnosti přímých odkazů na obrázky v poskytovaném API (https://docs.balldontlie.io/). Další veřejně dostupné API, které by mohlo poskytnout obrázky, nebylo v době implementace k dispozici. 

Alternativní řešení popisované např. zde již nebylo možné, jelikož v článku zmiňovaný endpoint již není dostupný, což mi znemožnilo jeho implementaci.

Jako dočasné řešení byly implementovány enum třídy s pevně danými URL obrázků, aby byl naplněn požadavek na využití Glide knihovny pro načítání obrázků. Toto řešení je možné v budoucnu nahradit připojením k placenému API, které by poskytovalo požadované obrázky.

## Omezení - A

---
Pavel Hanzl