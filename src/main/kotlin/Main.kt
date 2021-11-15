/*
- (1) establecerNota(idAlumno:String, evaluacion:String, nota:Float): Boolean
X (2) calculaEvaluacionFinal()
- (3) listaNotas(evaluacion:String): List<Pair>
- (4) numeroAprobados(evaluacion:String): Int
- (5) notaMasBaja(evaluacion:String): Float
- (6) notaMasAlta(evaluacion:String): Float
- (7) notaMedia(evaluacion:String): Float
- (8) hayAlumnosConDiez(evaluacion:String): Boolean
- (9) hayAlumnosAprobados(evaluacion:String): Boolean
- (10) primeraNotaNoAprobada(evaluacion:String): Float
- (11) listaNotasOrdenados(evaluacion:String): List<Pair>
*/

data class Alumno(
    var nombre: String,
    var ap1: String,
    var ap2: String,
    var id: String
)

class Modulo(
    var maxAlumnos: Int
) {
    var alumnos = arrayOfNulls<Alumno>(maxAlumnos)
    var alumnosOrdenados = arrayOfNulls<Alumno>(maxAlumnos)
    var evaluaciones = Array(5) { arrayOfNulls<String>(maxAlumnos) }
    var almacenIndex: Int = 0


    fun matricularAlumno(alumno: Alumno): Boolean {
        when (alumnos.indexOfFirst { it == null }) {
            in 0..maxAlumnos - 1 -> almacenIndex = alumnos.indexOfFirst { it == null }
            !in 0..maxAlumnos - 1 -> almacenIndex = -1
        }
        return if (almacenIndex.equals(-1)) {
            false
        } else {
            alumnos[almacenIndex] = alumno
            evaluaciones[0][almacenIndex] = alumno.id
            true
        }

    }

    fun bajaAlumno(idAlumno: String): Boolean {
        when (alumnos.indexOfFirst { it?.id == idAlumno }) {
            in 0..maxAlumnos - 1 -> almacenIndex = alumnos.indexOfFirst { it?.id == idAlumno }
            !in 0..maxAlumnos - 1 -> almacenIndex = -1
        }
        return if (almacenIndex.equals(-1)) {
            false
        } else {
            alumnos[almacenIndex] = null
            for (i in 0..4) {
                evaluaciones[i][almacenIndex] = null
            }
            true
        }

    }

    //TESTING ONLY
    fun imprimirListaAlumnos() {
        alumnosOrdenados = alumnos
        alumnosOrdenados.sortWith(nullsLast(compareBy { it.id }))

        println("--------------------------------")
        println("LISTA ALUMNOS")
        println("--------------------------------")
        alumnosOrdenados.forEach { println("${it?.nombre} ${it?.ap1} || Identificador: ${it?.id}") }
        println("--------------------------------")
        println("")
    }

    //TESTING ONLY
    fun establecerNotaV1() {
        for (i in 0..maxAlumnos - 1) {
            evaluaciones[1][i] = (0..10).random().toDouble().toString()
            evaluaciones[2][i] = (0..10).random().toDouble().toString()
            evaluaciones[3][i] = (0..10).random().toDouble().toString()
            evaluaciones[4][i] = (0..10).random().toDouble().toString()
        }
    }

    fun listaNotas(): List<Pair> {
        var (idAlumno, NotaAlumno) = Pair ("", "")
    }

}

fun main() {
    var alumno1 = Alumno("Juen", "Carglas", "Lunas", "1")
    var alumno2 = Alumno("Pablo", "Carglas", "Lunas", "3")
    var alumno3 = Alumno("Pedro", "Carglas", "Lunas", "2")

    var moduloPROG = Modulo(20)

    moduloPROG.matricularAlumno(alumno1)
    moduloPROG.matricularAlumno(alumno2)
    moduloPROG.matricularAlumno(alumno3)

    moduloPROG.imprimirListaAlumnos()

    moduloPROG.establecerNotaV1()

}