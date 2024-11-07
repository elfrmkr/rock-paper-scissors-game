package eu.tutorials.kotlinbasics

fun main() {
    do {
        val playerChoice = getPlayerChoice()
        val computerChoice = getComputerChoice()

        println("Computer chose: $computerChoice")

        val winner = determineWinner(playerChoice, computerChoice)
        displayResult(winner)

        println("Play again? (yes/no)")
    } while (readln().lowercase() == "yes")
}

    fun getPlayerChoice(): String {
        println("Rock, paper, scissors: Enter your choice")
        val validChoices = listOf("rock", "paper", "scissors")
        var playerChoice = readln().lowercase()

        while (playerChoice !in validChoices) {
            println("Invalid choice, please choose rock, paper, or scissors")
            playerChoice = readln().lowercase()
        }
        return playerChoice
    }

    fun getComputerChoice(): String {
        val choices = listOf("rock", "paper", "scissors")
        return choices.random()
    }

    fun determineWinner(playerChoice: String, computerChoice: String): String {
        return when {
            playerChoice == computerChoice -> "Tie"
            playerChoice == "rock" && computerChoice == "scissors" -> "Player"
            playerChoice == "paper" && computerChoice == "rock" -> "Player"
            playerChoice == "scissors" && computerChoice == "paper" -> "Player"
            else -> "Computer"
        }
    }

    fun displayResult(winner: String) {
        if (winner == "Tie") {
            println("It's a tie!")
        } else {
            println("$winner wins!")
        }
    }
