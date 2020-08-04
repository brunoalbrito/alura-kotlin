fun main() {
    testeOperacoesBasicas()
}

fun testeOperacoesBasicas() {
    println("Bem vindo ao Bytebank")
    val contaBruno = Conta(titular = "Bruno", numeroConta = 1001)
    contaBruno.depositar(0.0)

    println("Valor inicial ${contaBruno.saldo}")


    println("Teste deposito")
    val valorDeposito = 1000.0
    contaBruno.depositar(valorDeposito)
    println("Valor em conta ${contaBruno.saldo}")

    println("Teste saque")
    val valorDeSaque = 50.0
    contaBruno.sacar(valorDeSaque)
    println("Saldo ${contaBruno.saldo}")

    val contaDestino = Conta(titular = "Conta Destino", numeroConta = 1002)
    contaDestino.depositar(0.0)

    println("Testa transferencia")
    println("Antes da transfencia: Origem = ${contaBruno.saldo} / Destino = ${contaDestino.saldo}")
    if (contaBruno.transferir(contaDestino, 300.0))
        println("Após da transfencia: Origem = ${contaBruno.saldo} / Destino = ${contaDestino.saldo}")
    else {
        println("Falha na transferencia")
    }
}

fun testaCopiasEReferencias() {
    val contaBruno = Conta(titular = "Bruno", numeroConta = 1)

    val contaMaria = contaBruno
    contaMaria.titular = "Maria"

    println("${contaBruno.titular}")
    println("${contaMaria.saldo}")
}

fun testaLacos() {
    for (i in 10 downTo 1 step 2) {

        if (i == 4) {
            break
        }

        val titular = "Usuário número $i"
        val numeroConta: Int = 1000 + i
        var saldo = i + 10.0
        println("titular $titular")
        println("número da conta $numeroConta")
        println("saldo da conta $saldo")
        println()
        testaCondicoes(saldo)
    }

    for (i in 1..10) {
        println(i)
    }
}

fun testaCondicoes(saldo: Double) {
    if (saldo > 0.0) {
        println("conta é positiva")
    } else if (saldo == 0.0) {
        println("conta é neutra")
    } else {
        println("conta é negativa")
    }

    val saldoNovo = saldo - 400

    when {
        saldoNovo > 0.0 -> println("conta é positiva")
        saldoNovo == 0.0 -> println("conta é neutra")
        else -> println("conta é negativa")
    }
}

class Conta(var titular: String, var numeroConta: Int) {
    var saldo = 0.0
        /*properties
        set(valor){
            if(valor > 0){
                //println("field $field")
                field = valor
            }
        }*/
        private set

    /*constructor(titular: String, numeroConta: Int){
        this.titular = titular
        this.numeroConta = numeroConta
    }*/

    fun sacar(valorDeSaque: Double) {
        if (this.saldo >= valorDeSaque) {
            this.saldo -= valorDeSaque
        } else {
            println("Não foi possível realizar o saque")
        }
    }

    fun depositar(valorDeposito: Double) {
        if (valorDeposito > 0) {
            this.saldo += valorDeposito
        }
    }

    fun transferir(contaDestino: Conta, valor: Double): Boolean {
        if (this.saldo >= valor) {
            this.sacar(valor)
            contaDestino.depositar(valor)
            return true
        }
        return false
    }
}