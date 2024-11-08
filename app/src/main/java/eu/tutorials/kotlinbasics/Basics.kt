// Basics.kt
package eu.tutorials.kotlinbasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Basics : AppCompatActivity() {

    private lateinit var computerChoiceText: TextView
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        computerChoiceText = findViewById(R.id.computerChoiceText)
        resultText = findViewById(R.id.resultText)

        val rockButton: Button = findViewById(R.id.rockButton)
        val paperButton: Button = findViewById(R.id.paperButton)
        val scissorsButton: Button = findViewById(R.id.scissorsButton)

        // Set onClickListeners for each choice button
        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
    }

    private fun playGame(playerChoice: String) {
        // Get computer choice
        val computerChoice = getComputerChoice()

        // Display computer's choice
        computerChoiceText.text = "Computer chose: $computerChoice"

        // Determine the winner
        val winner = determineWinner(playerChoice, computerChoice)

        // Display the result
        displayResult(winner)
    }

    private fun getComputerChoice(): String {
        // Randomly select from rock, paper, or scissors
        val choices = listOf("rock", "paper", "scissors")
        return choices.random()
    }

    private fun determineWinner(playerChoice: String, computerChoice: String): String {
        // Determine who wins based on the player's and computer's choices
        return when {
            playerChoice == computerChoice -> "Tie"
            playerChoice == "rock" && computerChoice == "scissors" -> "Player"
            playerChoice == "paper" && computerChoice == "rock" -> "Player"
            playerChoice == "scissors" && computerChoice == "paper" -> "Player"
            else -> "Computer"
        }
    }

    private fun displayResult(winner: String) {
        // Update resultText to show the winner or if it's a tie
        resultText.text = when (winner) {
            "Tie" -> "It's a tie!"
            "Player" -> "You win!"
            "Computer" -> "Computer wins!"
            else -> ""
        }
    }
}
