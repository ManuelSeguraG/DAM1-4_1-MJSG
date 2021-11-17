import kotlin.math.min

/*
- (1) establecerNota(idAlumno:String, evaluacion:String, nota:Float): Boolean
X (2) calculaEvaluacionFinal()
- (3) listaNotas(evaluacion:String): List<Pair>
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
    var evaluaciones = Array(4) { FloatArray(maxAlumnos) }
    var almacenIndex: Int = 0
    var almacenAprobados: Int = 0


    fun matricularAlumno(alumno: Alumno): Boolean {
        when (alumnos.indexOfFirst { it == null }) {
            in 0..maxAlumnos - 1 -> almacenIndex = alumnos.indexOfFirst { it == null }
            !in 0..maxAlumnos - 1 -> almacenIndex = -1
        }
        return if (almacenIndex.equals(-1)) {
            false
        } else {
            alumnos[almacenIndex] = alumno
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
                evaluaciones[i][almacenIndex] = -1f
            }
            true
        }

    }


    //TESTING ONLY
    fun establecerNota() {
        for (i in 0..alumnos.indexOfLast { it?.id != null }) {
            evaluaciones[0][i] = (0..10).random().toFloat()
            evaluaciones[1][i] = (0..10).random().toFloat()
            evaluaciones[2][i] = (0..10).random().toFloat()
        }
    }

    fun calcularNotaFinal() {
        var media: Float = 0f
        for (i in 0..alumnos.indexOfLast { it?.id != null }) {
            media = (evaluaciones[0][i] + evaluaciones[1][i] + evaluaciones[2][i]) / 3
            evaluaciones[3][i] = media
        }
    }

    fun numeroAprobados(evaluacion: String): Int {
        almacenAprobados = 0
        for (i in 0..alumnos.indexOfLast { it?.id != null }) {
            if (evaluaciones[evaluacion.toInt() - 1][i] != null) {
                if (evaluaciones[evaluacion.toInt() - 1][i]!! in 5.0f..10.0f) {
                    almacenAprobados++
                }
            }
        }
        return almacenAprobados
    }

    fun notaMasBaja (evaluacion: Int): Float? {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.minOrNull()
    }

    fun notaMasAlta (evaluacion: Int): Float? {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.maxOrNull()
    }

    fun notaMedia (evaluacion: Int): Double {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.average()
    }

    fun hayAlumnosConDiez(evaluacion:Int): Boolean {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.contains(10.0f)
    }

    fun hayAlumnosAprobados(evaluacion:Int): Boolean {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.any { it >= 5.0f }
    }

    fun primeraNotaNoAprobada(evaluacion:Int): Float? {
        return evaluaciones[evaluacion - 1].filterNot { it == -1f }.firstOrNull { it < 5.0f }
    }

    fun notasFinales() {
        for (i in 0..alumnos.indexOfLast { it?.id != null }) {
            print("|${evaluaciones[3][i]}| ")
        }
    }

}

fun main() {
    var alumno1 = Alumno("Juen", "Carglas", "Lunas", "1")
    var alumno2 = Alumno("Pablo", "Carglas", "Lunas", "3")
    var alumno3 = Alumno("Pedro", "Carglas", "Lunas", "2")
    var alumno4 = Alumno("Piedra", "Carglas", "Lunas", "4")
    var alumno5 = Alumno("Joseba", "Carglas", "Lunas", "5")
    var alumno6 = Alumno("Aurora", "Carglas", "Lunas", "6")
    var alumno7 = Alumno("Juen", "Carglas", "Lunas", "7")
    var alumno8 = Alumno("Pablo", "Carglas", "Lunas", "8")
    var alumno9 = Alumno("Pedro", "Carglas", "Lunas", "9")
    var alumno10 = Alumno("Piedra", "Carglas", "Lunas", "10")
    var alumno11 = Alumno("Joseba", "Carglas", "Lunas", "11")
    var alumno12 = Alumno("Aurora", "Carglas", "Lunas", "12")

    var moduloPROG = Modulo(20)

    moduloPROG.matricularAlumno(alumno1)
    moduloPROG.matricularAlumno(alumno2)
    moduloPROG.matricularAlumno(alumno3)
    moduloPROG.matricularAlumno(alumno4)
    moduloPROG.matricularAlumno(alumno5)
    moduloPROG.matricularAlumno(alumno6)
    moduloPROG.matricularAlumno(alumno7)
    moduloPROG.matricularAlumno(alumno8)
    moduloPROG.matricularAlumno(alumno9)
    moduloPROG.matricularAlumno(alumno10)
    moduloPROG.matricularAlumno(alumno11)
    moduloPROG.matricularAlumno(alumno12)

    moduloPROG.establecerNota()

    println(moduloPROG.numeroAprobados("1"))
    println(moduloPROG.notaMasBaja(1))
    println(moduloPROG.notaMasAlta(1))
    println(moduloPROG.notaMedia(1))
    println(moduloPROG.hayAlumnosConDiez(1))
    println(moduloPROG.hayAlumnosAprobados(1))
    println(moduloPROG.primeraNotaNoAprobada(1))
    moduloPROG.calcularNotaFinal()

    moduloPROG.notasFinales()


}