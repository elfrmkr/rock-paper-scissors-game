package eu.tutorials.kotlinbasics

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Basics : AppCompatActivity() {

    private lateinit var computerThinkingHand: ImageView
    private lateinit var computerChoiceIcon: ImageView
    private lateinit var computerChoiceLabel: TextView
    private lateinit var resultText: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        computerThinkingHand = findViewById(R.id.computerThinkingHand)
        computerChoiceIcon = findViewById(R.id.computerChoiceIcon)
        computerChoiceLabel = findViewById(R.id.computerChoiceLabel)
        resultText = findViewById(R.id.resultText)


        val rockButton: Button = findViewById(R.id.rockButton)
        val paperButton: Button = findViewById(R.id.paperButton)
        val scissorsButton: Button = findViewById(R.id.scissorsButton)

        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
    }

    private fun playGame(playerChoice: String) {
        resultText.text = ""
        // Start the hand animation
        val handAnimation = AnimationUtils.loadAnimation(this, R.anim.hand_bounce)
        computerThinkingHand.startAnimation(handAnimation)

        // Display the "computer is choosing..." text
        computerChoiceLabel.text = "Computer is choosing..."
        computerChoiceIcon.visibility = View.GONE  // Hide the icon until the choice is made

        // Get computer choice
        val computerChoice = getComputerChoice()
        // Determine the winner
        var winner = determineWinner(playerChoice, computerChoice)

        // Delay to simulate "thinking" time
        Handler().postDelayed({
            // Stop the animation
            computerThinkingHand.clearAnimation()

            // Update the computer choice label and icon based on choice
            computerChoiceLabel.text = "Computer chose: $computerChoice"
            computerChoiceIcon.setImageResource(getIconForChoice(computerChoice))
            computerChoiceIcon.visibility = View.VISIBLE  // Show the icon

            // Show the result text
            resultText.text = if (winner == "Tie") {
                "It's a Tie!"
            } else {
                "$winner wins!"
            }
            resultText.visibility = View.VISIBLE
        }, 2000)  // 2-second delay for the animation


    }

    private fun getComputerChoice(): String {
        val choices = listOf("rock", "paper", "scissors")
        return choices.random()
    }

    private fun getIconForChoice(choice: String): Int {
        return when (choice) {
            "rock" -> R.drawable.ic_rock
            "paper" -> R.drawable.ic_paper
            "scissors" -> R.drawable.ic_scissors
            else -> R.drawable.ic_rock  // Default icon
        }
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
}
