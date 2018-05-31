package uio.androidbootcamp.moviesapp.model.services

import uio.androidbootcamp.moviesapp.model.models.Movie

//Manejo de Servicios Web
class MovieService(private val presenterOutput: MoviePresenterOutput) {

    fun findMovieByName(name: String) {
        if (name.isNotBlank()) {
            val movie = Movie(id = 1, name = name, overview = "SINOPSIS\n" +
                    "Película no recomendada a menores de 12 años.\n" +
                    "Superman (Henry Cavill) se ha convertido en la figura más controvertida del mundo. " +
                    "Mientras que muchos siguen creyendo que es un emblema de esperanza, otro gran número de personas lo consideran una amenaza para " +
                    "la humanidad. Para el influyente Bruce Wayne (Ben Affleck), Superman es claramente un peligro para la sociedad, su poder resulta " +
                    "imprudente y alejado de la mano del gobierno. Por eso, ante el temor de las acciones que pueda llevar a cabo un superhéroe con unos " +
                    "poderes casi divinos, decide ponerse la máscara y la capa para poner a raya al superhéroe de Metrópolis. Mientras que la opinión" +
                    " pública debate sobre el interrogante de cuál es realmente el héroe que necesitan, el Hombre de Acero y Batman, enfrentados entre sí" +
                    ", se sumergen en una contienda el uno contra el otro. La rivalidad entre ellos está alimentada por el rencor y la venganza, y nada " +
                    "puede disuadirlos de librar esta guerra. Hostigados por el multimillonario Lex Luthor (Jesse Eisenberg), Batman y Superman se ven las" +
                    " caras en una lucha sin precedentes. Pero las cosas se complican cuando una nueva y peligrosa amenaza pronto cobra fuerza, poniendo a " +
                    "toda la humanidad en el mayor peligro que nunca se haya conocido antes. Esta nueva y oscura amenaza, que surge con la figura de un " +
                    "tercer hombre con poderes superlativos llamado Doomsday, puede poner en serio peligro al mundo y causar la destrucción total. " +
                    "Será entonces cuando el Último Hijo de Krypton y el Caballero Oscuro unan sus fuerzas con Wonder Woman (Gal Gadot) para " +
                    "enfrentarse todos juntos a esta amenazante nueva máquina de matar. Zack Snyder (300: El origen de un imperio, El hombre de acero) " +
                    "es el encargado de dirigir el enfrentamiento entre los dos superhéroes más famosos de DC Comics, que cuenta con una banda sonora " +
                    "compuesta por Hans Zimmer (Interstellar, 12 años de esclavitud) y un guión escrito por Chris Terrio (Argo) y David S. Goyer " +
                    "(El caballero oscuro: La leyenda renace). Los actores protagonistas son Ben Affleck (Argo, The Town. Ciudad de ladrones, " +
                    "The company men ) y Henry Cavill (El hombre de acero, Immortals, Si la cosa funciona). Completan el reparto los actores " +
                    "Jesse Eisenberg (A Roma con amor, La red social), Gal Gadot (Triple 9, Fast & Furious 7), Jason Momoa (Juego de tronos, " +
                    "Conan el Bárbaro), Diane Lane (Trumbo: La lista negra de Hollywood, Del revés), Jeremy Irons (Tren de noche a Lisboa, " +
                    "El ladrón de palabras), Amy Adams (La gran estafa americana, Her), Laurence Fishburne (Hannibal, Contagio) y Michael Shannon " +
                    "(Boardwalk Empire, Take Shelter).",
                    posterPath = "https://image.tmdb.org/t/p/w500/wwemzKWzjKYJFfCeiB57q3r4Bcm.png")
            presenterOutput.showMovieInformation(movie)
        } else {
            presenterOutput.showMovieInformation(null)
        }
    }
}

interface MoviePresenterOutput {
    fun showMovieInformation(movie: Movie?)
}
